import pandas as pd
from sklearn.ensemble import RandomForestRegressor, GradientBoostingRegressor
from sklearn.svm import SVR
from sklearn.neural_network import MLPRegressor
from sklearn.model_selection import train_test_split, GridSearchCV
from sklearn.metrics import mean_squared_error, mean_absolute_error
import numpy as np
from sklearn.preprocessing import StandardScaler
from scipy import stats
from sklearn.multioutput import MultiOutputRegressor
from joblib import Parallel, delayed
from tqdm import tqdm
import matplotlib.pyplot as plt
from sklearn.feature_selection import SelectKBest, f_regression
import matplotlib.font_manager as fm

# 设置 matplotlib 支持中文显示
plt.rcParams['font.family'] = 'SimHei'
plt.rcParams['axes.unicode_minus'] = False

def read_data(file_path, sheet_name):
    """
    从指定的 Excel 文件中读取数据。

    参数:
    file_path (str): Excel 文件的路径。
    sheet_name (str): 要读取的工作表名称。

    返回:
    pandas.DataFrame: 读取的数据。

    异常:
    FileNotFoundError: 如果文件未找到。
    ValueError: 如果工作表名不存在或文件解析错误。
    Exception: 如果出现其他未知错误。
    """
    try:
        df = pd.read_excel(file_path, sheet_name=sheet_name)
        print("数据的所有列名：")
        for col in df.columns:
            print(col)
        return df
    except FileNotFoundError:
        raise FileNotFoundError(f"文件 {file_path} 未找到。")
    except ValueError as ve:
        raise ValueError(f"读取文件时出现值错误，可能是工作表名 {sheet_name} 不存在，错误信息：{ve}")
    except pd.errors.ParserError:
        raise ValueError(f"文件 {file_path} 解析错误，可能不是有效的 Excel 文件。")
    except Exception as e:
        raise Exception(f"读取文件时出现未知错误：{e}")


def preprocess_data(df, feature_columns, target_columns):
    """
    对数据进行预处理，包括提取特征和目标数据、处理缺失值和异常值、特征标准化。

    参数:
    df (pandas.DataFrame): 原始数据。
    feature_columns (list): 特征列名列表。
    target_columns (list): 目标列名列表。

    返回:
    tuple: 标准化后的特征数据、原始特征数据、目标数据、标准化器。
    """
    try:
        X = df[feature_columns]
        y = df[target_columns]
        print('成功获取特征数据 X，基本信息如下：')
        X.info()
        print('成功获取目标数据 y，基本信息如下：')
        y.info()
    except KeyError as ke:
        print(f"提取数据时出现键错误，可能是列名 {ke} 不存在。")
        exit(1)

    # 将井径列转换为数值类型，处理非数值值为 NaN
    X.loc[:, '井径'] = pd.to_numeric(X['井径'], errors='coerce')

    # 处理特征数据中的缺失值，使用均值填充
    X = X.fillna(X.mean())
    # 推断数据类型，避免未来警告
    X = X.infer_objects()

    # 重置索引，确保 X 和 y 索引一致
    X = X.reset_index(drop=True)
    y = y.reset_index(drop=True)

    # 异常值处理：使用 Z-score 方法检测并替换异常值
    z_scores = np.abs(stats.zscore(X))
    threshold = 3
    X = X[(z_scores < threshold).all(axis=1)]
    y = y[(z_scores < threshold).all(axis=1)]

    # 重置索引
    X = X.reset_index(drop=True)
    y = y.reset_index(drop=True)

    # 特征选择
    selector = SelectKBest(score_func=f_regression, k=8)
    X_selected = selector.fit_transform(X, y.iloc[:, 0])
    selected_features = X.columns[selector.get_support()]

    # 特征标准化
    scaler = StandardScaler()
    X_scaled = scaler.fit_transform(X_selected)

    return X_scaled, pd.DataFrame(X_selected, columns=selected_features), y, scaler


def calculate_filling_metrics(y, y_missing, model, scaler, X):
    """
    计算模型填充缺失值的正确率、均方根误差和平均绝对误差。

    参数:
    y (pandas.DataFrame): 原始目标数据。
    y_missing (pandas.DataFrame): 包含缺失值的目标数据。
    model: 训练好的模型。
    scaler: 特征标准化器。
    X (pandas.DataFrame): 原始特征数据。

    返回:
    tuple: 填充正确率、均方根误差、平均绝对误差
    """
    missing_rows = y_missing.isna().any(axis=1)
    X_missing_scaled = scaler.transform(X[missing_rows])
    y_missing_pred = model.predict(X_missing_scaled)
    y_filled = y_missing.copy()
    y_filled.loc[missing_rows] = y_missing_pred

    y_true_missing = y[y_missing.isna()]
    y_filled_missing = y_filled[y_missing.isna()]

    valid_index = ~y_true_missing.isna().any(axis=1)
    y_true_missing = y_true_missing[valid_index]
    y_filled_missing = y_filled_missing[valid_index]

    if y_true_missing.empty or y_filled_missing.empty:
        print("警告：在计算填充指标时，真实缺失值或填充后缺失值数据为空，可能是数据问题，请检查。")
        return np.nan, np.nan, np.nan

    mse_filling = mean_squared_error(y_true_missing, y_filled_missing)
    if mse_filling == 0:
        accuracy = 1.0
    else:
        accuracy = 1 / (1 + mse_filling)
    rmse = np.sqrt(mse_filling)
    mae = mean_absolute_error(y_true_missing, y_filled_missing)
    return accuracy, rmse, mae


def perform_grid_search(model, param_grid, X_train, y_train):
    """
    执行网格搜索，返回最佳模型。

    参数:
    model: 模型对象
    param_grid: 参数网格
    X_train: 训练特征数据
    y_train: 训练目标数据

    返回:
    最佳模型
    """
    grid_search = GridSearchCV(model, param_grid, cv=5, scoring='neg_mean_squared_error', n_jobs=-1)
    grid_search.fit(X_train, y_train)
    return grid_search.best_estimator_


def train_single_model(model_info, X_train, y_train, y, scaler, X, missing_rate, grid_search_cache=None):
    """
    训练单个模型并计算填充指标。

    参数:
    model_info (dict): 模型信息，包含模型对象和参数网格。
    X_train (numpy.ndarray): 训练特征数据。
    y_train (pandas.DataFrame): 训练目标数据。
    y (pandas.DataFrame): 原始目标数据。
    scaler: 特征标准化器。
    X (pandas.DataFrame): 原始特征数据。
    missing_rate (float): 缺失率。
    grid_search_cache (dict): 网格搜索结果缓存

    返回:
    tuple: 模型名称、填充正确率、均方根误差、平均绝对误差
    """
    try:
        model_name = list(model_info.keys())[0]
        model = model_info[model_name]["model"]
        param_grid = model_info[model_name]["param_grid"]
        print(f"正在训练 {model_name} 模型...")

        if grid_search_cache and model_name in grid_search_cache:
            current_best_model = grid_search_cache[model_name].best_estimator_
        else:
            current_best_model = perform_grid_search(model, param_grid, X_train, y_train)
            if grid_search_cache is not None:
                grid_search_cache[model_name] = GridSearchCV(model, param_grid, cv=5, scoring='neg_mean_squared_error', n_jobs=-1)
                grid_search_cache[model_name].best_estimator_ = current_best_model

        missing_mask = np.random.rand(*y.shape) < missing_rate
        y_missing = y.mask(missing_mask)
        accuracy, rmse, mae = calculate_filling_metrics(y, y_missing, current_best_model, scaler, X)
        return model_name, accuracy, rmse, mae
    except Exception as e:
        print(f"训练 {model_name} 模型时出现错误: {e}")
        return model_name, np.nan, np.nan, np.nan


def train_and_evaluate_models(X_train, y_train, models, X, y, scaler, missing_rate, grid_search_cache=None):
    """
    训练并评估多个模型在指定缺失率下的表现。

    参数:
    X_train (numpy.ndarray): 训练特征数据。
    y_train (pandas.DataFrame): 训练目标数据。
    models (dict): 模型字典，包含模型名称和模型信息。
    X (pandas.DataFrame): 原始特征数据。
    y (pandas.DataFrame): 原始目标数据。
    scaler: 特征标准化器。
    missing_rate (float): 缺失率。
    grid_search_cache (dict): 网格搜索结果缓存

    返回:
    tuple: 最佳模型名称、最佳填充正确率、最佳均方根误差、最佳平均绝对误差
    """
    print(f"\n当前缺失率: {missing_rate * 100:.1f}%")
    results = Parallel(n_jobs=-1)(
        delayed(train_single_model)({model_name: model_info}, X_train, y_train, y, scaler, X, missing_rate, grid_search_cache)
        for model_name, model_info in models.items())

    best_model_name = ""
    best_accuracy = -np.inf
    best_rmse = np.inf
    best_mae = np.inf
    for model_name, accuracy, rmse, mae in results:
        if not np.isnan(accuracy) and accuracy > best_accuracy:
            best_accuracy = accuracy
            best_model_name = model_name
            best_rmse = rmse
            best_mae = mae

    print(f"当前缺失率下的最佳模型为 {best_model_name}，填充正确率: {best_accuracy * 100:.2f}%，均方根误差: {best_rmse:.4f}，平均绝对误差: {best_mae:.4f}")
    return best_model_name, best_accuracy, best_rmse, best_mae


def find_best_model_overall(X_train, y_train, models, X, y, scaler, missing_rates):
    """
    在不同缺失率下找到整体表现最佳的模型。

    参数:
    X_train (numpy.ndarray): 训练特征数据。
    y_train (pandas.DataFrame): 训练目标数据。
    models (dict): 模型字典，包含模型名称和模型信息。
    X (pandas.DataFrame): 原始特征数据。
    y (pandas.DataFrame): 原始目标数据。
    scaler: 特征标准化器。
    missing_rates (list): 缺失率列表

    返回:
    tuple: 最佳模型名称、最佳平均填充正确率、平均均方根误差、平均平均绝对误差
    """
    all_accuracies = {model_name: [] for model_name in models.keys()}
    all_rmse = {model_name: [] for model_name in models.keys()}
    all_mae = {model_name: [] for model_name in models.keys()}
    grid_search_cache = {}

    for missing_rate in tqdm(missing_rates, desc="处理不同缺失率"):
        _, _, _, _ = train_and_evaluate_models(X_train, y_train, models, X, y, scaler, missing_rate, grid_search_cache)
        for model_name in models.keys():
            model_info = {model_name: models[model_name]}
            _, accuracy, rmse, mae = train_single_model(model_info, X_train, y_train, y, scaler, X, missing_rate, grid_search_cache)
            all_accuracies[model_name].append(accuracy)
            all_rmse[model_name].append(rmse)
            all_mae[model_name].append(mae)

    average_accuracies = {}
    average_rmse = {}
    average_mae = {}
    for model_name in models.keys():
        valid_accuracies = [acc for acc in all_accuracies[model_name] if not np.isnan(acc)]
        valid_rmse = [rmse for rmse in all_rmse[model_name] if not np.isnan(rmse)]
        valid_mae = [mae for mae in all_mae[model_name] if not np.isnan(mae)]
        if valid_accuracies:
            average_accuracies[model_name] = np.mean(valid_accuracies)
            average_rmse[model_name] = np.mean(valid_rmse)
            average_mae[model_name] = np.mean(valid_mae)
        else:
            average_accuracies[model_name] = np.nan
            average_rmse[model_name] = np.nan
            average_mae[model_name] = np.nan

    best_overall_model_name = max(average_accuracies, key=average_accuracies.get)
    best_overall_accuracy = average_accuracies[best_overall_model_name]
    best_overall_rmse = average_rmse[best_overall_model_name]
    best_overall_mae = average_mae[best_overall_model_name]
    print(f"\n所有缺失率下平均表现最好的模型为 {best_overall_model_name}，平均填充正确率: {best_overall_accuracy * 100:.2f}%，平均均方根误差: {best_overall_rmse:.4f}，平均平均绝对误差: {best_overall_mae:.4f}")

    # 可视化不同模型在不同缺失率下的填充正确率
    plt.figure(figsize=(10, 6))
    for model_name in models.keys():
        plt.plot(missing_rates, all_accuracies[model_name], label=model_name)
        for i, (x, y) in enumerate(zip(missing_rates, all_accuracies[model_name])):
            if not np.isnan(y):
                plt.annotate(f'{y:.2f}', (x, y), textcoords="offset points", xytext=(0, 5), ha='center')

    plt.xlabel('缺失率')
    plt.xticks(rotation=45)
    plt.ylabel('填充正确率')
    plt.title('不同模型在不同缺失率下的填充正确率')
    plt.legend()
    plt.grid(True)
    plt.tight_layout()
    plt.savefig('filling_accuracy.png')
    plt.show()

    return best_overall_model_name, best_overall_accuracy, best_overall_rmse, best_overall_mae


def train_and_fit_models(X_train, y_train, models):
    """
    训练所有模型并返回训练好的模型列表
    """
    trained_models = []
    for model_name, model_info in models.items():
        model = model_info["model"]
        param_grid = model_info["param_grid"]
        trained_models.append(perform_grid_search(model, param_grid, X_train, y_train))
    return trained_models


def model_ensemble_predict(trained_models, X_scaled):
    """
    模型融合预测（简单平均）

    参数:
    trained_models (list): 训练好的模型列表
    X_scaled (numpy.ndarray): 标准化后的特征数据

    返回:
    numpy.ndarray: 模型融合后的预测结果
    """
    predictions = [model.predict(X_scaled) for model in trained_models]
    ensemble_pred = np.mean(predictions, axis=0)
    return ensemble_pred


def main():
    try:
        # 1. 数据读取
        file_path = 'C:/Users/huangshaozheng/Desktop/石油工程设计大赛/方法一数据.xlsx'
        sheet_name = 'w3数据'  # 修改为 w2 数据的工作表名
        df = read_data(file_path, sheet_name)


        # 2. 数据预处理
        feature_columns = [
            '深度', '井径', '补偿中子', '声波时差', '自然伽马',
            '岩石电阻率1', '岩石电阻率2', '岩石电阻率3', '岩石电阻率4',
            '泵效', '特征1', '深侧向电阻率','岩性密度矫正',
        ]
        target_columns = [
            '密度', '中子', '电阻率', '总孔隙度', '裂缝孔隙度',
             '纵波时差   ',
        ]




        X_scaled, X, y, scaler = preprocess_data(df, feature_columns, target_columns)

        # 3. 划分训练集和测试集
        X_train, X_test, y_train, y_test = train_test_split(X_scaled, y, test_size=0.2, random_state=42)

        # 4. 定义不同模型及其参数网格
        models = {
            "RandomForest": {
                "model": RandomForestRegressor(random_state=42),
                "param_grid": {
                    'n_estimators': [50, 100, 200],
                    'max_depth': [None, 10, 20, 30],
                   'min_samples_split': [2, 5, 10]
                }
            },
            "SVR": {
                "model": MultiOutputRegressor(SVR()),
                "param_grid": {
                    'estimator__C': [0.1, 1, 10],
                    'estimator__gamma': [0.01, 0.1, 1]
                }
            },
            "GradientBoosting": {
                "model": MultiOutputRegressor(GradientBoostingRegressor(random_state=42)),
                "param_grid": {
                    'estimator__n_estimators': [50, 100, 200],
                    'estimator__learning_rate': [0.01, 0.1, 0.2],
                    'estimator__max_depth': [3, 5, 7]
                }
            },
            "MLP": {
                "model": MLPRegressor(random_state=42, max_iter=2000, learning_rate_init=0.001, hidden_layer_sizes=(100, 50)),
                "param_grid": {
                    'hidden_layer_sizes': [(50,), (100, 50)],
                    'activation': ['relu', 'tanh']
                }
            }
        }

        # 5. 不同缺失率下的处理，找到最佳模型
        missing_rates = np.arange(0.05, 0.71, 0.05)
        best_overall_model_name, best_overall_accuracy, best_overall_rmse, best_overall_mae = find_best_model_overall(
            X_train, y_train, models, X, y, scaler, missing_rates)

        # 6. 模型融合预测
        trained_models = train_and_fit_models(X_train, y_train, models)
        X_scaled_full = scaler.transform(X)
        ensemble_pred = model_ensemble_predict(trained_models, X_scaled_full)
        ensemble_pred_df = pd.DataFrame(ensemble_pred, columns=target_columns)
        df[target_columns] = df[target_columns].fillna(ensemble_pred_df)

        # 7. 保存结果
        output_file_path = '方法一数据_融合填充后.xlsx'
        with pd.ExcelWriter(output_file_path) as writer:
            df.to_excel(writer, sheet_name='填充后数据', index=False)
            metrics_df = pd.DataFrame({
                '最佳模型名称': [best_overall_model_name],
                '平均填充正确率': [best_overall_accuracy],
                '平均均方根误差': [best_overall_rmse],
                '平均平均绝对误差': [best_overall_mae]
            })
            metrics_df.to_excel(writer, sheet_name='评估指标', index=False)
        print(f"融合填充后的数据及评估指标已保存到 {output_file_path}")

    except FileNotFoundError as e:
        print(e)
    except ValueError as e:
        print(e)
    except Exception as e:
        print(e)


if __name__ == "__main__":
    main()