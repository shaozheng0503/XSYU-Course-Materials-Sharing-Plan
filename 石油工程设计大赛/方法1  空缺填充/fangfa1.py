import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.ensemble import RandomForestRegressor, GradientBoostingRegressor
from sklearn.svm import SVR
from sklearn.neural_network import MLPRegressor
from sklearn.model_selection import GridSearchCV
from sklearn.metrics import r2_score, mean_squared_error, mean_absolute_error
from sklearn.preprocessing import StandardScaler
from mpl_toolkits.mplot3d import Axes3D
import argparse
from tqdm import tqdm
import joblib

# 设置 matplotlib 支持中文显示
plt.rcParams['font.family'] = 'SimHei'
plt.rcParams['axes.unicode_minus'] = False


def read_data(file_path, sheet_names):
    """
    从指定的 Excel 文件中读取数据。

    参数:
    file_path (str): Excel 文件的路径。
    sheet_names (list): 要读取的工作表名称列表。

    返回:
    pandas.DataFrame: 读取的数据。

    异常:
    FileNotFoundError: 如果文件未找到。
    ValueError: 如果工作表名不存在或文件解析错误。
    Exception: 如果出现其他未知错误。
    """
    try:
        dfs = []
        for sheet_name in sheet_names:
            print(f"正在读取工作表 {sheet_name}...")
            df = pd.read_excel(file_path, sheet_name=sheet_name)
            print(f"工作表 {sheet_name} 的所有列名：")
            for col in df.columns:
                print(col)
            dfs.append(df)
        combined_df = pd.concat(dfs, ignore_index=True)
        return combined_df
    except FileNotFoundError:
        raise FileNotFoundError(f"文件 {file_path} 未找到。")
    except ValueError as ve:
        raise ValueError(f"读取文件时出现值错误，可能是工作表名存在问题，错误信息：{ve}")
    except pd.errors.ParserError:
        raise ValueError(f"文件 {file_path} 解析错误，可能不是有效的 Excel 文件。")
    except Exception as e:
        raise Exception(f"读取文件时出现未知错误：{e}")


def explore_data(df):
    print("数据基本统计信息：")
    print(df.describe())
    print("数据缺失值情况：")
    print(df.isnull().sum())
    for col in df.select_dtypes(include=[np.number]).columns:
        plt.figure()
        df[col].hist()
        plt.title(f'{col} 直方图')
        plt.xlabel(col)
        plt.ylabel('频数')
        plt.savefig(f'{col}_histogram.png')
        plt.close()


def preprocess_data(df):
    print("正在进行数据预处理...")
    # 过滤非数值类型的列
    df = df.select_dtypes(include=[np.number])
    # 处理缺失值，使用均值填充
    df = df.fillna(df.mean())
    return df


def create_missing_data(df, column, missing_rate):
    print(f"正在创建缺失率为 {missing_rate * 100:.2f}% 的缺失数据...")
    num_missing = int(len(df) * missing_rate)
    missing_indices = np.random.choice(df.index, num_missing, replace=False)
    original_values = df.loc[missing_indices, column].copy()
    df.loc[missing_indices, column] = np.nan
    return df, original_values, missing_indices


def train_and_evaluate_models(X, y, models, param_grids):
    print("正在训练和评估模型...")
    best_estimators = {}
    r2_scores = {}
    mse_scores = {}
    mae_scores = {}
    grid_search_results = {}
    for model_name, model in tqdm(models.items(), desc="模型训练进度"):
        param_grid = param_grids[model_name]
        grid_search = GridSearchCV(model, param_grid, cv=5, scoring='r2')
        grid_search.fit(X, y)
        best_estimator = grid_search.best_estimator_
        best_estimators[model_name] = best_estimator
        y_pred = best_estimator.predict(X)
        r2 = r2_score(y, y_pred)
        mse = mean_squared_error(y, y_pred)
        mae = mean_absolute_error(y, y_pred)
        r2_scores[model_name] = r2
        mse_scores[model_name] = mse
        mae_scores[model_name] = mae

        if model_name not in grid_search_results:
            grid_search_results[model_name] = []
        for params, score in zip(grid_search.cv_results_['params'], grid_search.cv_results_['mean_test_score']):
            param1 = list(params.values())[0]
            param2 = list(params.values())[1]
            grid_search_results[model_name].append((param1, param2, score))

        # 保存最佳模型
        joblib.dump(best_estimator, f'{model_name}_best_model.pkl')

    return best_estimators, r2_scores, mse_scores, mae_scores, grid_search_results


def fill_missing_data(df, column, missing_indices, best_estimators):
    print("正在填充缺失数据...")
    X = df.drop(columns=[column])
    # 重新生成缺失值的索引，确保与处理后的数据匹配
    valid_missing_indices = X.index.intersection(missing_indices)
    filled_values = {}
    if len(valid_missing_indices) == 0:
        print("没有有效的缺失索引，跳过填充操作。")
        for model_name in best_estimators.keys():
            filled_values[model_name] = np.array([])
        return filled_values
    for model_name, estimator in tqdm(best_estimators.items(), desc="缺失值填充进度"):
        filled_values[model_name] = estimator.predict(X.loc[valid_missing_indices])
    return filled_values


def compare_with_original(original_values, filled_values, valid_missing_indices):
    print("正在比较填充值与原始值...")
    r2_scores = {}
    mse_scores = {}
    mae_scores = {}
    for model_name, filled in filled_values.items():
        if len(filled) == 0:
            r2_scores[model_name] = np.nan
            mse_scores[model_name] = np.nan
            mae_scores[model_name] = np.nan
        else:
            r2 = r2_score(original_values.loc[valid_missing_indices], filled)
            mse = mean_squared_error(original_values.loc[valid_missing_indices], filled)
            mae = mean_absolute_error(original_values.loc[valid_missing_indices], filled)
            r2_scores[model_name] = r2
            mse_scores[model_name] = mse
            mae_scores[model_name] = mae
    return r2_scores, mse_scores, mae_scores


def visualize_scores(missing_rates, all_scores, score_type):
    print(f"正在绘制不同缺失率下各种模型的 {score_type} 分数图...")
    for model_name in all_scores[0].keys():
        scores = [score[model_name] for score in all_scores]
        plt.plot(missing_rates, scores, label=model_name)
    plt.xlabel('缺失率')
    plt.ylabel(f'{score_type} 分数')
    plt.title(f'不同缺失率下各种模型的 {score_type} 分数')
    plt.legend()
    plt.savefig(f'{score_type}_scores_vs_missing_rate.png')
    plt.show()


def visualize_3d_scatter(grid_search_results):
    print("正在绘制不同模型不同参数下的 $R^2$ 分数三维散点图...")
    fig = plt.figure()
    ax = fig.add_subplot(111, projection='3d')
    for model_name, results in grid_search_results.items():
        param1_values = [result[0] for result in results]
        param2_values = [result[1] for result in results]
        scores = [result[2] for result in results]
        colors = scores
        sizes = [score * 100 for score in scores]
        ax.scatter(param1_values, param2_values, scores, c=colors, s=sizes, label=model_name)
    ax.set_xlabel('参数1')
    ax.set_ylabel('参数2')
    ax.set_zlabel('$R^2$ 分数')
    ax.set_title('不同模型不同参数下的 $R^2$ 分数')
    ax.legend()
    plt.savefig('3d_r2_scores_vs_params.png')
    plt.show()


def main():
    parser = argparse.ArgumentParser(description='Data processing and model evaluation')
    parser.add_argument('--file_path', type=str, default='C:/Users/huangshaozheng/Desktop/石油工程设计大赛/方法1相关性分析.xlsx',
                        help='Path to the Excel file')
    parser.add_argument('--sheet_name', type=str, default='w1数据', help='Name of the sheet in the Excel file')
    args = parser.parse_args()

    file_path = args.file_path
    sheet_name = args.sheet_name
    try:
        df = read_data(file_path, [sheet_name])
    except (FileNotFoundError, ValueError, Exception) as e:
        print(f"数据读取错误: {e}")
        return

    explore_data(df)
    df = preprocess_data(df)

    all_columns = df.columns
    target_column = np.random.choice(all_columns)

    missing_rates = np.arange(0.05, 0.65, 0.05)
    all_r2_scores = []
    all_mse_scores = []
    all_mae_scores = []
    all_grid_search_results = {model: [] for model in ['RandomForest', 'SVR', 'GradientBoosting', 'MLP']}

    models = {
        "RandomForest": RandomForestRegressor(random_state=42),
        "SVR": SVR(),
        "GradientBoosting": GradientBoostingRegressor(random_state=42),
        # 增加最大迭代次数并调整学习率
        "MLP": MLPRegressor(random_state=42, max_iter=10000, learning_rate_init=0.001)
    }

    param_grids = {
        "RandomForest": {
            'n_estimators': [50, 100, 200],
            'max_depth': [None, 10, 20]
        },
        "SVR": {
            'C': [0.1, 1, 10],
            'gamma': [0.01, 0.1, 1]
        },
        "GradientBoosting": {
            'n_estimators': [50, 100, 200],
            'learning_rate': [0.01, 0.1, 0.2]
        },
        "MLP": {
            'hidden_layer_sizes': [(50,), (100, 50)],
            'activation': ['relu', 'tanh']
        }
    }

    for missing_rate in tqdm(missing_rates, desc="不同缺失率处理进度"):
        df_with_missing, original_values, missing_indices = create_missing_data(df.copy(), target_column, missing_rate)
        # 联合处理缺失值，确保 X 和 y 样本数量一致
        df_with_missing = df_with_missing.dropna()
        X = df_with_missing.drop(columns=[target_column])
        y = df_with_missing[target_column]
        # 保存索引
        index = X.index
        scaler = StandardScaler()
        X = scaler.fit_transform(X)

        best_estimators, r2_scores, mse_scores, mae_scores, grid_search_results = train_and_evaluate_models(X, y,
                                                                                                             models,
                                                                                                             param_grids)
        # 使用保存的索引
        valid_missing_indices = index.intersection(missing_indices)
        filled_values = fill_missing_data(df_with_missing, target_column, missing_indices, best_estimators)
        r2_scores_compare, mse_scores_compare, mae_scores_compare = compare_with_original(original_values,
                                                                                          filled_values,
                                                                                          valid_missing_indices)
        all_r2_scores.append(r2_scores_compare)
        all_mse_scores.append(mse_scores_compare)
        all_mae_scores.append(mae_scores_compare)

        # 合并网格搜索结果
        for model_name, results in grid_search_results.items():
            all_grid_search_results[model_name].extend(results)

    visualize_scores(missing_rates, all_r2_scores, 'R^2')
    visualize_scores(missing_rates, all_mse_scores, 'MSE')
    visualize_scores(missing_rates, all_mae_scores, 'MAE')
    visualize_3d_scatter(all_grid_search_results)


if __name__ == "__main__":
    main()