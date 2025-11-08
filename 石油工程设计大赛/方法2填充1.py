import pandas as pd
import numpy as np
from sklearn.experimental import enable_iterative_imputer
from sklearn.impute import IterativeImputer, SimpleImputer
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
        else:
            # 去除非数值列中的特殊字符
            df[col] = df[col].astype(str).str.replace(r'[^\w\s]', '', regex=True)
    return df

# 填充缺失值的函数
def fill_missing_values(df, missing_ratio):
    # 根据指定的缺失率随机制造缺失值
    missing_mask = np.random.rand(*df.shape) < missing_ratio
    df_missing = df.mask(missing_mask)
    # 记录缺失值的位置
    missing_positions = df_missing.isnull()

    # 分离数值列和非数值列
    numeric_cols = df_missing.select_dtypes(include=[np.number]).columns
    non_numeric_cols = df_missing.select_dtypes(exclude=[np.number]).columns

    # 对非数值列进行简单填充
    for col in non_numeric_cols:
        imputer = SimpleImputer(strategy='most_frequent')
        df_missing[col] = imputer.fit_transform(df_missing[[col]]).ravel()

    # 对非数值列进行编码
    encoded_df = df_missing.copy()
    encoders = {}
    for col in non_numeric_cols:
        encoder = LabelEncoder()
        encoded_df[col] = encoder.fit_transform(encoded_df[col].astype(str))
        encoders[col] = encoder

    # 使用 IterativeImputer 进行多重填补
    imputer = IterativeImputer(random_state=42)
    filled_encoded = imputer.fit_transform(encoded_df)
    df_filled = pd.DataFrame(filled_encoded, columns=df_missing.columns)

    # 对编码后的非数值列进行解码
    for col in non_numeric_cols:
        df_filled[col] = encoders[col].inverse_transform(df_filled[col].astype(int))

    return df_filled, missing_positions

# 比较填充后的数据与原数据的函数
def compare_filled_data(df_original, df_filled, missing_positions):
    correct_count = 0
    total_count = 0
    for col in df_original.columns:
        original_col = df_original[col].astype(str)  # 转换为字符串类型
        filled_col = df_filled[col].astype(str)  # 转换为字符串类型
        mask = missing_positions[col]
        correct_count += (original_col[mask] == filled_col[mask]).sum()
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