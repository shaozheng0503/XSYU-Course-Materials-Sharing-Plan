import pandas as pd
import numpy as np
from sklearn.ensemble import RandomForestRegressor, RandomForestClassifier
from sklearn.impute import SimpleImputer
from sklearn.preprocessing import LabelEncoder

# 读取 Excel 数据的函数
def read_excel_data(file_path):
    try:
        # 尝试读取指定路径的 Excel 文件
        df = pd.read_excel(file_path)
        return df
    except FileNotFoundError:
        print(f"文件 {file_path} 未找到。")
        return None
    except Exception as e:
        print(f"读取文件时出现错误: {e}")
        return None

# 检查列是否为数值类型
def is_numeric_column(series):
    try:
        # 尝试将序列转换为数值类型，若成功则认为是数值列
        pd.to_numeric(series, errors='raise')
        return True
    except ValueError:
        return False

# 对数据进行预处理，将可转换为数值类型的列进行转换
def preprocess_data(df):
    for col in df.columns:
        if is_numeric_column(df[col]):
            # 将可转换为数值类型的列进行转换，无法转换的值设为 NaN
            df[col] = pd.to_numeric(df[col], errors='coerce')
    return df

# 填充缺失值的函数
def fill_missing_values(df, missing_ratio):
    # 根据指定的缺失率随机制造缺失值
    missing_mask = np.random.rand(*df.shape) < missing_ratio
    df_missing = df.mask(missing_mask)
    # 记录缺失值的位置
    missing_positions = df_missing.isnull()
    # 找出包含缺失值的列
    missing_columns = df_missing.columns[df_missing.isnull().any()].tolist()
    # 找出不包含缺失值的列
    non_missing_columns = [col for col in df_missing.columns if col not in missing_columns]

    # 对每个包含缺失值的列进行填补
    for col in missing_columns:
        # 准备训练数据
        # 选取不缺失当前列值的行中的非缺失列作为特征，用于训练模型
        X_train = df_missing[non_missing_columns][df_missing[col].notnull()]
        # 选取不缺失当前列值的行中的当前列作为目标，即模型要学习预测的值
        y_train = df_missing[col][df_missing[col].notnull()]
        # 选取缺失当前列值的行中的非缺失列作为测试数据，用于预测缺失值
        X_test = df_missing[non_missing_columns][df_missing[col].isnull()]

        # 若训练数据为空，无法使用随机森林模型，采用简单填充方法
        if X_train.empty or y_train.empty:
            if not is_numeric_column(df[col]):
                # 非数值列用众数填充，众数是数据中出现次数最多的值
                imputer = SimpleImputer(strategy='most_frequent')
            else:
                # 数值列用均值填充，均值是数据的平均值
                imputer = SimpleImputer(strategy='mean')
            df_missing[col] = imputer.fit_transform(df_missing[[col]]).ravel()
            continue

        # 根据列的数据类型选择合适的模型
        if not is_numeric_column(df[col]):
            # 非数值列使用随机森林分类器
            # 随机森林分类器通过构建多个决策树，并综合这些决策树的结果进行分类预测
            # n_estimators 表示决策树的数量，random_state 用于保证结果的可重复性
            encoder = LabelEncoder()
            # 对非数值的目标列进行编码，将其转换为数值类型，以便模型处理
            y_train_encoded = encoder.fit_transform(y_train)
            model = RandomForestClassifier(n_estimators=100, random_state=42)
        else:
            # 数值列使用随机森林回归器
            # 随机森林回归器同样构建多个决策树，通过综合决策树的结果进行数值预测
            y_train_encoded = y_train
            model = RandomForestRegressor(n_estimators=100, random_state=42)

        try:
            # 训练模型
            # 使用训练数据 X_train 和对应的目标值 y_train_encoded 来训练随机森林模型
            # 模型会学习特征与目标值之间的关系
            model.fit(X_train, y_train_encoded)
            # 用训练好的模型预测缺失值
            # 将测试数据 X_test 输入到训练好的模型中，得到预测的缺失值
            y_pred = model.predict(X_test)
            if not is_numeric_column(df[col]):
                # 对预测结果进行解码，将编码后的预测值转换回原始的非数值类型
                y_pred = encoder.inverse_transform(y_pred)
            # 填充缺失值
            # 将预测得到的缺失值填充到原始数据中对应的缺失位置
            df_missing.loc[df_missing[col].isnull(), col] = y_pred
        except Exception as e:
            # 如果训练或预测过程中出现异常，采用简单填充方法
            if not is_numeric_column(df[col]):
                imputer = SimpleImputer(strategy='most_frequent')
            else:
                imputer = SimpleImputer(strategy='mean')
            df_missing[col] = imputer.fit_transform(df_missing[[col]]).ravel()

    return df_missing, missing_positions

# 比较填充后的数据与原数据的函数
def compare_filled_data(df_original, df_filled, missing_positions):
    correct_count = 0
    total_count = 0
    for col in df_original.columns:
        original_col = df_original[col]
        filled_col = df_filled[col]
        mask = missing_positions[col]
        # 统计填充正确的数量
        # 比较原数据和填充后数据在缺失位置的值，相同则认为填充正确
        correct_count += (original_col[mask] == filled_col[mask]).sum()
        # 统计缺失值的总数
        total_count += mask.sum()

    if total_count == 0:
        accuracy_ratio = 0
    else:
        accuracy_ratio = correct_count / total_count

    print(f"填充后数据与原数据的正确比率: {accuracy_ratio * 100:.2f}%")
    return accuracy_ratio

def main():
    # 定义文件路径
    file_path = r'C:\Users\huangshaozheng\Desktop\zongbiao.xlsx'
    # 读取 Excel 数据
    df = read_excel_data(file_path)
    if df is not None:
        # 对数据进行预处理
        df = preprocess_data(df)

        best_accuracy = 0
        best_missing_ratio = 0
        accuracy_dict = {}

        # 从 0 到 0.6 以 0.02 为间隔循环
        for missing_ratio in np.arange(0, 0.62, 0.02):
            print(f"当前缺失值比例: {missing_ratio * 100:.0f}%")
            # 填充缺失值
            df_filled, missing_positions = fill_missing_values(df, missing_ratio)
            # 比较填充后的数据与原数据
            accuracy = compare_filled_data(df, df_filled, missing_positions)
            accuracy_dict[missing_ratio] = accuracy

            # 更新最佳准确率和对应的缺失率
            if accuracy > best_accuracy:
                best_accuracy = accuracy
                best_missing_ratio = missing_ratio

        print(f"\n最佳缺失率为: {best_missing_ratio * 100:.0f}%，对应的填充准确率为: {best_accuracy * 100:.2f}%")
        print("各缺失率对应的填充准确率:")
        for ratio, acc in accuracy_dict.items():
            print(f"缺失率 {ratio * 100:.0f}%: {acc * 100:.2f}%")

if __name__ == "__main__":
    main()