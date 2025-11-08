import pandas as pd

# 假设数据存储在一个名为'data.xlsx'的Excel文件中，且数据在第一个工作表
file_path = '数据总表.xlsx'
df = pd.read_excel(file_path)

# 对深度进行分箱，这里分为10个区间，你可以根据实际需求调整
bins = pd.cut(df['深度'], bins=10)
df['深度范围'] = bins

# 存储每个深度范围内有数值列名的结果
depth_range_columns = {}

# 遍历每个深度范围
for depth_bin in df['深度范围'].unique():
    sub_df = df[df['深度范围'] == depth_bin]
    valid_columns = []
    for col in sub_df.columns[1:]:  # 从第二列开始，即深度列之后的列
        # 检查列中是否有非空且非'-'的值
        if (sub_df[col] != '-').any() and sub_df[col].notnull().any():
            valid_columns.append(col)
    depth_range_columns[depth_bin] = valid_columns

# 将结果转换为DataFrame
result_df = pd.DataFrame.from_dict(depth_range_columns, orient='index', columns=['有数值的列名'])
result_df.index.name = '深度范围'
result_df.reset_index(inplace=True)

print(result_df)