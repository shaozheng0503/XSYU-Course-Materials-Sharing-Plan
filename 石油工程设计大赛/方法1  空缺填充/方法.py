import pandas as pd
import numpy as np
from sklearn.ensemble import RandomForestRegressor, GradientBoostingRegressor
from sklearn.svm import SVR
from sklearn.neural_network import MLPRegressor
from sklearn.model_selection import GridSearchCV
from sklearn.metrics import r2_score
from sklearn.preprocessing import StandardScaler
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D

# 读取数据函数
def read_data(file_path, sheet_name):
    df = pd.read_excel(file_path, sheet_name=sheet_name)
    return df

# 数据预处理函数
def preprocess_data(df, feature_columns, target_column):
    X = df[feature_columns]
    y = df[target_column]

    # 过滤非数值类型的列
    X = X.select_dtypes(include=[np.number])

    # 处理特征数据中的缺失值，使用均值填充
    X = X.fillna(X.mean())
    # 推断数据类型，避免未来警告
    X = X.infer_objects()

    # 处理目标数据中的缺失值，使用均值填充
    y = y.fillna(y.mean())

    # 重置索引，确保 X 和 y 索引一致
    X = X.reset_index(drop=True)
    y = y.reset_index(drop=True)

    # 特征标准化
    scaler = StandardScaler()
    X_scaled = scaler.fit_transform(X)

    return X_scaled, y

# 定义不同模型及其参数网格
def define_models():
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
            "model": SVR(),
            "param_grid": {
                'C': [0.1, 1, 10],
                'gamma': [0.01, 0.1, 1]
            }
        },
        "GradientBoosting": {
            "model": GradientBoostingRegressor(random_state=42),
            "param_grid": {
                'n_estimators': [50, 100, 200],
                'learning_rate': [0.01, 0.1, 0.2],
                'max_depth': [3, 5, 7]
            }
        },
        "MLP": {
            "model": MLPRegressor(random_state=42, max_iter=2000, learning_rate_init=0.001,
                                  hidden_layer_sizes=(100, 50)),
            "param_grid": {
                'hidden_layer_sizes': [(50,), (100, 50)],
                'activation': ['relu', 'tanh']
            }
        }
    }
    return models

# 训练并评估模型
def train_and_evaluate_models(X_scaled, y, models, missing_rate):
    results = {}
    all_params = []
    all_scores = []
    all_models = []

    for model_name, model_info in models.items():
        model = model_info["model"]
        param_grid = model_info["param_grid"]

        # 模拟缺失数据
        num_missing = int(len(y) * missing_rate)
        missing_indices = np.random.choice(len(y), num_missing, replace=False)
        X_train = np.delete(X_scaled, missing_indices, axis=0)
        y_train = np.delete(y.values, missing_indices)
        X_test = X_scaled[missing_indices]
        y_test = y.values[missing_indices]

        # 网格搜索
        grid_search = GridSearchCV(model, param_grid, cv=5, scoring='r2', n_jobs=-1)
        grid_search.fit(X_train, y_train)

        best_model = grid_search.best_estimator_
        y_pred = best_model.predict(X_test)
        r2 = r2_score(y_test, y_pred)
        results[model_name] = r2

        # 记录所有参数和分数
        for params, score in zip(grid_search.cv_results_['params'], grid_search.cv_results_['mean_test_score']):
            all_params.append(params)
            all_scores.append(score)
            all_models.append(model_name)

    return results, all_params, all_scores, all_models

# 主函数
def main():
    file_path = 'C:/Users/huangshaozheng/Desktop/石油工程设计大赛/方法一数据.xlsx'
    sheet_name = 'w1数据'
    df = read_data(file_path, sheet_name)
    # 各工作表需要分析的列
    sheet_columns = {
        'w1数据': [
            '井径', '补偿中子', '声波时差', '自然伽马', '光电吸收界面指数',
            '深侧向电阻率', '微球型聚焦电阻率', '浅侧向电阻率',
            '自然电位测井深度校正曲线', '密度矫正', '岩性密度矫正'
        ]
    }

    all_columns = sheet_columns[sheet_name]
    target_column = np.random.choice(all_columns)
    feature_columns = [col for col in all_columns if col != target_column]

    X_scaled, y = preprocess_data(df, feature_columns, target_column)

    models = define_models()

    missing_rates = np.arange(0.05, 0.65, 0.05)
    accuracy_results = {model_name: [] for model_name in models.keys()}

    all_params_total = []
    all_scores_total = []
    all_models_total = []

    for missing_rate in missing_rates:
        results, all_params, all_scores, all_models = train_and_evaluate_models(X_scaled, y, models, missing_rate)
        for model_name, r2 in results.items():
            accuracy_results[model_name].append(r2)

        all_params_total.extend(all_params)
        all_scores_total.extend(all_scores)
        all_models_total.extend(all_models)

    # 可视化不同缺失率下各种模型的填充正确率
    plt.figure(figsize=(10, 6))
    for model_name, accuracies in accuracy_results.items():
        plt.plot(missing_rates * 100, accuracies, label=model_name)
    plt.xlabel('缺失率 (%)')
    plt.ylabel('填充正确率 (R2)')
    plt.title('不同缺失率下各种模型的填充正确率')
    plt.legend()
    plt.grid(True)
    plt.show()

    # 绘制三维散点图表示不同模型不同参数下的正确率
    fig = plt.figure(figsize=(12, 8))
    ax = fig.add_subplot(111, projection='3d')

    model_colors = {'RandomForest': 'r', 'SVR': 'g', 'GradientBoosting': 'b', 'MLP': 'y'}
    param_names = list(all_params_total[0].keys())
    param_values = {name: [] for name in param_names}

    for param in all_params_total:
        for name in param_names:
            if isinstance(param[name], tuple):
                param_values[name].append(hash(param[name]))
            else:
                param_values[name].append(param[name])

    for model_name in models.keys():
        indices = [i for i, model in enumerate(all_models_total) if model == model_name]
        scores = np.array([all_scores_total[i] for i in indices])
        sizes = scores * 100  # 根据分数调整点的大小
        colors = scores  # 根据分数调整点的颜色

        ax.scatter([param_values[param_names[0]][i] for i in indices],
                   [param_values[param_names[1]][i] for i in indices],
                   [param_values[param_names[2]][i] for i in indices],
                   c=colors, s=sizes, cmap='viridis', label=model_name)

    ax.set_xlabel(param_names[0])
    ax.set_ylabel(param_names[1])
    ax.set_zlabel(param_names[2])
    ax.set_title('不同模型不同参数下的正确率')
    ax.legend()
    plt.show()

if __name__ == "__main__":
    main()