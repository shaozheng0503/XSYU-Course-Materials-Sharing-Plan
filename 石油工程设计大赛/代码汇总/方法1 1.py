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
from sklearn.feature_selection import RFE
from xgboost import XGBRegressor
import matplotlib.font_manager as fm

# 设置 matplotlib 支持中文显示
# 指定中文字体为黑体，确保图形中的中文能正常显示
plt.rcParams['font.family'] = 'SimHei'
# 解决负号显示问题
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
        # 使用 pandas 的 read_excel 函数读取指定路径和工作表的数据
        df = pd.read_excel(file_path, sheet_name=sheet_name)
        print("数据的所有列名：")
        # 遍历数据的列名并打印
        for col in df.columns:
            print(col)
        return df
    except FileNotFoundError:
        # 若文件未找到，抛出相应的错误信息
        raise FileNotFoundError(f"文件 {file_path} 未找到。")
    except ValueError as ve:
        # 若出现值错误，可能是工作表名不存在，抛出错误信息
        raise ValueError(f"读取文件时出现值错误，可能是工作表名 {sheet_name} 不存在，错误信息：{ve}")
    except pd.errors.ParserError:
        # 若文件解析错误，可能不是有效的 Excel 文件，抛出错误信息
        raise ValueError(f"文件 {file_path} 解析错误，可能不是有效的 Excel 文件。")
    except Exception as e:
        # 若出现其他未知错误，抛出错误信息
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
        # 从原始数据中提取特征列的数据
        X = df[feature_columns]
        # 从原始数据中提取目标列的数据
        y = df[target_columns]
        print('成功获取特征数据 X，基本信息如下：')
        # 打印特征数据的基本信息
        X.info()
        print('成功获取目标数据 y，基本信息如下：')
        # 打印目标数据的基本信息
        y.info()
    except KeyError as ke:
        # 若提取数据时出现键错误，可能是列名不存在，打印错误信息并退出程序
        print(f"提取数据时出现键错误，可能是列名 {ke} 不存在。")
        exit(1)

    # 处理特征数据中的缺失值，使用均值填充
    X = X.fillna(X.mean())
    # 推断数据类型，避免未来警告
    X = X.infer_objects()

    # 重置索引，确保 X 和 y 索引一致
    X = X.reset_index(drop=True)
    y = y.reset_index(drop=True)

    # 异常值处理：使用 Z-score 方法检测异常值，将异常值替换为上下限
    # 计算特征数据的 Z-score
    z_scores = np.abs(stats.zscore(X))
    # 设置 Z-score 的阈值
    threshold = 3
    # 将异常值替换为非异常值的上下限
    X = X.clip(lower=X[(z_scores < threshold)].min(), upper=X[(z_scores < threshold)].max(), axis=1)

    # 特征选择：使用递归特征消除
    # 初始化随机森林回归器作为特征选择的估计器
    estimator = RandomForestRegressor(random_state=42)
    # 初始化递归特征消除器，选择 8 个特征
    selector = RFE(estimator, n_features_to_select=8)
    # 对特征数据进行特征选择
    X_selected = selector.fit_transform(X, y.iloc[:, 0])
    # 获取被选中的特征列名
    selected_features = X.columns[selector.get_support()]

    # 特征标准化
    # 初始化标准化器
    scaler = StandardScaler()
    # 对选中的特征数据进行标准化处理
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
    # 找出包含缺失值的行
    missing_rows = y_missing.isna().any(axis=1)
    # 对包含缺失值的行的特征数据进行标准化处理
    X_missing_scaled = scaler.transform(X[missing_rows])
    # 使用训练好的模型对缺失值进行预测
    y_missing_pred = model.predict(X_missing_scaled)
    # 复制包含缺失值的目标数据
    y_filled = y_missing.copy()
    # 将预测值填充到缺失值的位置
    y_filled.loc[missing_rows] = y_missing_pred

    # 提取原始目标数据中缺失值的部分
    y_true_missing = y[y_missing.isna()]
    # 提取填充后目标数据中缺失值的部分
    y_filled_missing = y_filled[y_missing.isna()]

    # 找出真实值和填充值都不为缺失值的索引
    valid_index = ~y_true_missing.isna().any(axis=1)
    # 筛选出真实值中有效的部分
    y_true_missing = y_true_missing[valid_index]
    # 筛选出填充值中有效的部分
    y_filled_missing = y_filled_missing[valid_index]

    if y_true_missing.empty or y_filled_missing.empty:
        # 若真实缺失值或填充后缺失值数据为空，打印警告信息并返回 NaN
        print("警告：在计算填充指标时，真实缺失值或填充后缺失值数据为空，可能是数据问题，请检查。")
        return np.nan, np.nan, np.nan

    # 计算均方误差
    mse_filling = mean_squared_error(y_true_missing, y_filled_missing)
    if mse_filling == 0:
        # 若均方误差为 0，填充正确率为 1
        accuracy = 1.0
    else:
        # 计算填充正确率
        accuracy = 1 / (1 + mse_filling)
    # 计算均方根误差
    rmse = np.sqrt(mse_filling)
    # 计算平均绝对误差
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
    # 初始化网格搜索对象，使用 5 折交叉验证，以负均方误差作为评分标准
    grid_search = GridSearchCV(model, param_grid, cv=5, scoring='neg_mean_squared_error', n_jobs=-1)
    # 对模型进行网格搜索
    grid_search.fit(X_train, y_train)
    # 返回最佳模型
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
        # 获取模型名称
        model_name = list(model_info.keys())[0]
        # 获取模型对象
        model = model_info[model_name]["model"]
        # 获取模型的参数网格
        param_grid = model_info[model_name]["param_grid"]
        print(f"正在训练 {model_name} 模型...")

        if grid_search_cache and model_name in grid_search_cache:
            # 若缓存中存在该模型的网格搜索结果，使用缓存中的最佳模型
            current_best_model = grid_search_cache[model_name].best_estimator_
        else:
            # 若缓存中不存在，进行网格搜索并获取最佳模型
            current_best_model = perform_grid_search(model, param_grid, X_train, y_train)
            if grid_search_cache is not None:
                # 将网格搜索结果存入缓存
                grid_search_cache[model_name] = GridSearchCV(model, param_grid, cv=5, scoring='neg_mean_squared_error', n_jobs=-1)
                grid_search_cache[model_name].best_estimator_ = current_best_model

        # 生成随机的缺失掩码
        missing_mask = np.random.rand(*y.shape) < missing_rate
        # 根据缺失掩码生成包含缺失值的目标数据
        y_missing = y.mask(missing_mask)
        # 计算填充指标
        accuracy, rmse, mae = calculate_filling_metrics(y, y_missing, current_best_model, scaler, X)
        return model_name, accuracy, rmse, mae
    except Exception as e:
        # 若训练模型时出现错误，打印错误信息并返回 NaN
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
    # 使用并行计算训练多个模型
    results = Parallel(n_jobs=-1)(
        delayed(train_single_model)({model_name: model_info}, X_train, y_train, y, scaler, X, missing_rate, grid_search_cache)
        for model_name, model_info in models.items())

    best_model_name = ""
    best_accuracy = -np.inf
    best_rmse = np.inf
    best_mae = np.inf
    for model_name, accuracy, rmse, mae in results:
        if not np.isnan(accuracy) and accuracy > best_accuracy:
            # 找出填充正确率最高的模型
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
    # 初始化每个模型的填充正确率、均方根误差和平均绝对误差列表
    all_accuracies = {model_name: [] for model_name in models.keys()}
    all_rmse = {model_name: [] for model_name in models.keys()}
    all_mae = {model_name: [] for model_name in models.keys()}
    # 初始化网格搜索结果缓存
    grid_search_cache = {}

    # 使用 tqdm 显示进度条，遍历不同的缺失率
    for missing_rate in tqdm(missing_rates, desc="处理不同缺失率"):
        # 训练并评估模型
        _, _, _, _ = train_and_evaluate_models(X_train, y_train, models, X, y, scaler, missing_rate, grid_search_cache)
        for model_name in models.keys():
            # 获取单个模型的信息
            model_info = {model_name: models[model_name]}
            # 训练单个模型并计算填充指标
            _, accuracy, rmse, mae = train_single_model(model_info, X_train, y_train, y, scaler, X, missing_rate, grid_search_cache)
            # 将填充指标存入相应的列表
            all_accuracies[model_name].append(accuracy)
            all_rmse[model_name].append(rmse)
            all_mae[model_name].append(mae)

    # 初始化每个模型的平均填充正确率、平均均方根误差和平均平均绝对误差字典
    average_accuracies = {}
    average_rmse = {}
    average_mae = {}
    for model_name in models.keys():
        # 筛选出有效的填充正确率
        valid_accuracies = [acc for acc in all_accuracies[model_name] if not np.isnan(acc)]
        # 筛选出有效的均方根误差
        valid_rmse = [rmse for rmse in all_rmse[model_name] if not np.isnan(rmse)]
        # 筛选出有效的平均绝对误差
        valid_mae = [mae for mae in all_mae[model_name] if not np.isnan(mae)]
        if valid_accuracies:
            # 计算平均填充正确率
            average_accuracies[model_name] = np.mean(valid_accuracies)
            # 计算平均均方根误差
            average_rmse[model_name] = np.mean(valid_rmse)
            # 计算平均平均绝对误差
            average_mae[model_name] = np.mean(valid_mae)
        else:
            # 若没有有效数据，将平均指标设为 NaN
            average_accuracies[model_name] = np.nan
            average_rmse[model_name] = np.nan
            average_mae[model_name] = np.nan

    # 找出平均填充正确率最高的模型
    best_overall_model_name = max(average_accuracies, key=average_accuracies.get)
    # 获取最佳模型的平均填充正确率
    best_overall_accuracy = average_accuracies[best_overall_model_name]
    # 获取最佳模型的平均均方根误差
    best_overall_rmse = average_rmse[best_overall_model_name]
    # 获取最佳模型的平均平均绝对误差
    best_overall_mae = average_mae[best_overall_model_name]
    print(f"\n所有缺失率下平均表现最好的模型为 {best_overall_model_name}，平均填充正确率: {best_overall_accuracy * 100:.2f}%，平均均方根误差: {best_overall_rmse:.4f}，平均平均绝对误差: {best_overall_mae:.4f}")

    # 可视化不同模型在不同缺失率下的填充正确率
    plt.figure(figsize=(10, 6))
    for model_name in models.keys():
        # 绘制每个模型在不同缺失率下的填充正确率曲线
        plt.plot(missing_rates, all_accuracies[model_name], label=model_name)
        for i, (x, y) in enumerate(zip(missing_rates, all_accuracies[model_name])):
            if not np.isnan(y):
                # 在曲线上标注填充正确率
                plt.annotate(f'{y:.2f}', (x, y), textcoords="offset points", xytext=(0, 5), ha='center')

    # 设置 x 轴标签
    plt.xlabel('缺失率')
    # 旋转 x 轴标签，避免重叠
    plt.xticks(rotation=45)
    # 设置 y 轴标签
    plt.ylabel('填充正确率')
    # 设置图形标题
    plt.title('不同模型在不同缺失率下的填充正确率')
    # 显示图例
    plt.legend()
    # 显示网格线
    plt.grid(True)
    # 自动调整布局
    plt.tight_layout()
    # 保存图形
    plt.savefig('filling_accuracy.png')
    # 显示图形
    plt.show()

    return best_overall_model_name, best_overall_accuracy, best_overall_rmse, best_overall_mae


def train_and_fit_models(X_train, y_train, models):
    """
    训练所有模型并返回训练好的模型列表
    """
    trained_models = []
    for model_name, model_info in models.items():
        # 获取模型对象
        model = model_info["model"]
        # 获取模型的参数网格
        param_grid = model_info["param_grid"]
        # 进行网格搜索并获取最佳模型
        trained_models.append(perform_grid_search(model, param_grid, X_train, y_train))
    return trained_models


def model_ensemble_predict(trained_models, X_scaled):
    """
    模型融合预测（加权平均）

    参数:
    trained_models (list): 训练好的模型列表
    X_scaled (numpy.ndarray): 标准化后的特征数据

    返回:
    numpy.ndarray: 模型融合后的预测结果
    """
    # 对每个模型进行预测
    predictions = [model.predict(X_scaled) for model in trained_models]
    # 简单加权平均，可根据模型表现调整权重
    weights = [1 / len(trained_models)] * len(trained_models)
    # 计算模型融合后的预测结果
    ensemble_pred = np.average(predictions, axis=0, weights=weights)
    return ensemble_pred


def main():
    try:
        # 1. 数据读取
        file_path = 'C:/Users/huangshaozheng/Desktop/石油工程设计大赛/方法一数据.xlsx'
        sheet_name = 'w1数据'
        # 调用 read_data 函数读取数据
        df = read_data(file_path, sheet_name)

        # 2. 数据预处理
        feature_columns = [
            '深度', '光电吸收界面指数', '深侧向电阻率', '微球型聚焦电阻率', '浅侧向电阻率',
            '自然电位测井深度校正曲线', '密度矫正', '岩性密度矫正'
        ]
        target_columns = [
            '成像计算裂缝孔隙度', '密度', '中子', '电阻率', '总孔隙度', '裂缝孔隙度',
            '饱和度', '斯通利波时差   ', '纵波时差   '
        ]

        # 调用 preprocess_data 函数对数据进行预处理
        X_scaled, X, y, scaler = preprocess_data(df, feature_columns, target_columns)

        # 3. 划分训练集和测试集
        # 使用 train_test_split 函数将数据划分为训练集和测试集
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
        # 定义缺失率列表
        missing_rates = np.arange(0.05, 0.71, 0.05)
        # 调用 find_best_model_overall 函数找到最佳模型
        best_overall_model_name, best_overall_accuracy, best_overall_rmse, best_overall_mae = find_best_model_overall(
            X_train, y_train, models, X, y, scaler, missing_rates)

        # 6. 模型融合预测
        # 调用 train_and_fit_models 函数训练所有模型
        trained_models = train_and_fit_models(X_train, y_train, models)
        # 对所有特征数据进行标准化处理
        X_scaled_full = scaler.transform(X)
        # 调用 model_ensemble_predict 函数进行模型融合预测
        ensemble_pred = model_ensemble_predict(trained_models, X_scaled_full)
        # 将预测结果转换为 DataFrame
        ensemble_pred_df = pd.DataFrame(ensemble_pred, columns=target_columns)
        # 使用预测结果填充原始数据中的缺失值
        df[target_columns] = df[target_columns].fillna(ensemble_pred_df)

        # 7. 保存结果
        output_file_path = '方法一数据_融合填充后.xlsx'
        with pd.ExcelWriter(output_file_path) as writer:
            # 将填充后的数据保存到 Excel 文件的指定工作表中
            df.to_excel(writer, sheet_name='填充后数据', index=False)
            # 创建评估指标的 DataFrame
            metrics_df = pd.DataFrame({
                '最佳模型名称': [best_overall_model_name],
                '平均填充正确率': [best_overall_accuracy],
                '平均均方根误差': [best_overall_rmse],
                '平均平均绝对误差': [best_overall_mae]
            })
            # 将评估指标保存到 Excel 文件的指定工作表中
            metrics_df.to_excel(writer, sheet_name='评估指标', index=False)
        print(f"融合填充后的数据及评估指标已保存到 {output_file_path}")

    except FileNotFoundError as e:
        # 若文件未找到，打印错误信息
        print(e)
    except ValueError as e:
        # 若出现值错误，打印错误信息
        print(e)
    except Exception as e:
        # 若出现其他未知错误，打印错误信息
        print(e)


if __name__ == "__main__":
    main()