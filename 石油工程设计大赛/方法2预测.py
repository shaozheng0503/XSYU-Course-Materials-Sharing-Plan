import pandas as pd
from sklearn.ensemble import RandomForestRegressor
from sklearn.model_selection import train_test_split, GridSearchCV
from sklearn.metrics import mean_squared_error
from sklearn.impute import SimpleImputer
from tqdm import tqdm
import tkinter as tk
from tkinter import ttk
import threading
from itertools import product

# 读取 Excel 文件数据
def read_data(file_path):
    try:
        # 利用 pandas 的 read_excel 函数读取指定路径的 Excel 文件
        df = pd.read_excel(file_path)
        return df
    except FileNotFoundError:
        # 若文件未找到，打印提示信息并返回 None
        print(f"文件 {file_path} 未找到，请检查文件路径。")
        return None
    except Exception as e:
        # 若读取过程出现其他异常，打印异常信息并返回 None
        print(f"读取文件时出现错误：{e}")
        return None

# 数据预处理函数
def preprocess_data(df):
    # 筛选出深度在 4399.7 到 5559.6 之间的数据
    filtered_df = df[(df['深度'] >= 4399.7) & (df['深度'] <= 5559.6)]
    # 将数据中非数字字符转换为 NaN
    filtered_df = filtered_df.apply(pd.to_numeric, errors='coerce')
    # 创建一个使用均值填充缺失值的插补器
    imputer = SimpleImputer(strategy='mean')
    # 对筛选后的数据进行缺失值填充
    imputed_df = pd.DataFrame(imputer.fit_transform(filtered_df), columns=filtered_df.columns)
    return imputed_df

# 模型训练和调优函数
def train_model(X_train, y_train):
    # 定义随机森林回归模型的参数网格，用于网格搜索调优
    param_grid = {
        'n_estimators': [50, 100, 150],
        'max_depth': [None, 10, 20, 30],
        'min_samples_split': [2, 5, 10]
    }
    # 创建随机森林回归模型实例
    model = RandomForestRegressor(random_state=42)
    # 创建网格搜索对象，使用 5 折交叉验证和负均方误差作为评分标准
    grid_search = GridSearchCV(model, param_grid, cv=5, scoring='neg_mean_squared_error')
    # 计算总的训练步骤数，用于 tqdm 显示进度
    total_steps = len(param_grid['n_estimators']) * len(param_grid['max_depth']) * len(param_grid['min_samples_split']) * 5
    with tqdm(total=total_steps, desc="模型训练中") as pbar:
        # 生成所有可能的参数组合
        param_combinations = product(param_grid['n_estimators'], param_grid['max_depth'], param_grid['min_samples_split'])
        for params in param_combinations:
            for _ in range(grid_search.cv):
                # 更新 tqdm 进度条
                pbar.update(1)
        # 执行网格搜索，进行模型训练
        grid_search.fit(X_train, y_train)
    # 获取网格搜索得到的最优模型
    best_model = grid_search.best_estimator_
    return best_model

# 单位转换函数，目前直接返回预测结果，可按需修改
def unit_conversion(prediction):
    return prediction

# 根据用户输入深度进行预测的函数
def predict(depth, best_model, y_columns, row_5559_6, output_text, progress_bar):
    try:
        # 将用户输入的深度转换为浮点数
        depth = float(depth)
        # 模拟预测进度，设置进度条最大值
        total_steps = 100
        progress_bar['maximum'] = total_steps
        for i in range(total_steps):
            progress_bar['value'] = i + 1
            root.update_idletasks()
        # 使用最优模型进行预测
        prediction = best_model.predict([[depth]])
        # 进行单位转换
        converted_prediction = unit_conversion(prediction)
        # 清空输出文本框内容
        output_text.delete(1.0, tk.END)
        # 遍历每个目标列
        for i, col in enumerate(y_columns):
            # 检查深度为 5559.6 对应行的该列是否为空值
            if pd.isna(row_5559_6.iloc[0, i]):
                # 若为空值，预测结果对应列设为空值
                result = '空值'
            else:
                # 若不为空值，格式化预测结果，保留六位小数
                result = '{:.6f}'.format(converted_prediction[0][i])
            # 将结果插入到输出文本框
            output_text.insert(tk.END, f"{col}: {result}\n")
    except ValueError:
        # 若输入不是有效的数字，提示用户重新输入
        output_text.delete(1.0, tk.END)
        output_text.insert(tk.END, "输入的深度值不是有效的数字，请重新输入。")
    except IndexError:
        # 若未找到深度为 5559.6 的数据，提示用户检查数据
        output_text.delete(1.0, tk.END)
        output_text.insert(tk.END, "未找到深度为 5559.6 的数据，请检查数据。")

# 异步执行预测函数，防止界面卡顿
def async_predict(depth_entry, best_model, y_columns, row_5559_6, output_text, progress_bar):
    depth = depth_entry.get()
    thread = threading.Thread(target=predict, args=(depth, best_model, y_columns, row_5559_6, output_text, progress_bar))
    thread.start()

# 主程序入口
if __name__ == "__main__":
    # 定义 Excel 文件的路径
    file_path = r'C:\Users\huangshaozheng\Desktop\zongbiao1.xlsx'
    # 调用 read_data 函数读取数据
    df = read_data(file_path)
    if df is not None:
        # 调用 preprocess_data 函数进行数据预处理
        imputed_df = preprocess_data(df)
        # 提取特征（深度列）
        X = imputed_df[['深度']]
        # 提取目标变量（除深度列外的其他列）
        y = imputed_df.drop(columns='深度')
        # 划分训练集和测试集，测试集占比 20%
        X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)
        # 调用 train_model 函数进行模型训练和调优
        best_model = train_model(X_train, y_train)
        # 在测试集上进行预测
        y_pred = best_model.predict(X_test)
        # 计算均方误差
        mse = mean_squared_error(y_test, y_pred)
        # 打印均方误差，保留六位小数
        print(f'均方误差：{mse:.6f}')
        # 获取深度为 5559.6 对应的行数据，并去除深度列
        row_5559_6 = imputed_df[imputed_df['深度'] == 5559.6].drop(columns='深度')

        # 创建 Tkinter 主窗口
        root = tk.Tk()
        root.title('深度预测')
        root.geometry("400x500")

        # 创建输入框框架
        input_frame = tk.Frame(root)
        input_frame.pack(pady=20)

        # 创建深度输入标签
        depth_label = tk.Label(input_frame, text='输入深度值：')
        depth_label.pack(side=tk.LEFT)
        # 创建深度输入框
        depth_entry = tk.Entry(input_frame)
        depth_entry.pack(side=tk.LEFT, padx=10)

        # 创建进度条框架
        progress_frame = tk.Frame(root)
        progress_frame.pack(pady=10)
        # 创建进度条
        progress_bar = ttk.Progressbar(progress_frame, orient="horizontal", length=300, mode="determinate")
        progress_bar.pack()

        # 创建输出框框架
        output_frame = tk.Frame(root)
        output_frame.pack(pady=20)

        # 创建输出结果标签
        output_label = tk.Label(output_frame, text='预测结果：')
        output_label.pack()
        # 创建输出文本框
        output_text = tk.Text(output_frame, height=10, width=50)
        output_text.pack()

        # 创建预测按钮，点击触发异步预测函数
        predict_button = tk.Button(root, text='预测', command=lambda: async_predict(depth_entry, best_model, y.columns, row_5559_6, output_text, progress_bar))
        predict_button.pack(pady=20)

        # 启动 Tkinter 主循环
        root.mainloop()