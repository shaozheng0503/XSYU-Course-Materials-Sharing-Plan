 import pandas as pd
# 基于梯度提升回归的深度预测系统
from sklearn.ensemble import GradientBoostingRegressor
from sklearn.model_selection import train_test_split, GridSearchCV
from sklearn.metrics import mean_squared_error, mean_absolute_error, r2_score
from sklearn.impute import SimpleImputer
from tqdm import tqdm
import tkinter as tk
from tkinter import ttk
import threading
from itertools import product


# 读取 Excel 文件数据
def read_data(file_path):
    try:
        df = pd.read_excel(file_path)
        return df
    except FileNotFoundError:
        print(f"文件 {file_path} 未找到，请检查文件路径。")
        return None
    except Exception as e:
        print(f"读取文件时出现错误：{e}")
        return None


# 数据预处理函数
def preprocess_data(df):
    filtered_df = df[(df['深度'] >= 4399.7) & (df['深度'] <= 5559.6)]
    filtered_df = filtered_df.apply(pd.to_numeric, errors='coerce')
    imputer = SimpleImputer(strategy='mean')
    imputed_df = pd.DataFrame(imputer.fit_transform(filtered_df), columns=filtered_df.columns)
    return imputed_df


# 定义深度范围及其对应参数
def get_depth_ranges_params():
    return {
        (4398.54, 4631.68): {
            "基本测井参数": "深度、井径、补偿中子、声波时差、自然伽马、光电吸收界面指数、深侧向电阻率、微球型聚焦电阻率、浅侧向电阻率、自然电位",
            "校正及相关曲线": "测井深度校正曲线、密度矫正、岩性密度矫正",
            "岩石电阻率": "岩石电阻率1、岩石电阻率2、岩石电阻率3、岩石电阻率4",
            "特殊参数及计算相关": "电阻率、总孔隙度、裂缝孔隙度、成像计算裂缝孔隙度、饱和度、密度、中子、纵波时差、斯通利波时差"
        },
        (4631.68, 4863.66): {
            "基本测井参数": "深度、井径、补偿中子、声波时差、自然伽马、光电吸收界面指数、深侧向电阻率、微球型聚焦电阻率、浅侧向电阻率、自然电位",
            "校正及相关曲线": "测井深度校正曲线、密度矫正、岩性密度矫正",
            "岩石电阻率": "岩石电阻率1、岩石电阻率2、岩石电阻率3、岩石电阻率4",
            "其他参数": "泵效、油气层厚度、中侧向电阻率、spwdh、rtm、真实垂直深度、最近5英尺的钻进速率、34声波高度、16光电系数、28光电系数、34光电系数、电阻率、井温、深层洞穴",
            "特殊参数及计算相关": "声波速度、crpm（通过电阻率估算孔隙度）、总标准化孔隙度、孔隙度评价因子、密度差、体积密度、电阻率.1、总孔隙度、裂缝孔隙度、成像计算裂缝孔隙度、饱和度、密度、中子、纵波时差、斯通利波时差"
        },
        (4863.66, 5095.64): {
            "基本测井参数": "深度、井径、补偿中子、声波时差、自然伽马、光电吸收界面指数、深侧向电阻率、微球型聚焦电阻率、浅侧向电阻率、自然电位",
            "校正及相关曲线": "测井深度校正曲线、密度矫正、岩性密度矫正",
            "岩石电阻率": "岩石电阻率1、岩石电阻率2、岩石电阻率3、岩石电阻率4",
            "其他参数": "泵效、油气层厚度、中侧向电阻率、spwdh、rtm、真实垂直深度、最近5英尺的钻进速率、34声波高度、16光电系数、28光电系数、34光电系数、电阻率、井温、深层洞穴",
            "特殊参数及计算相关": "声波速度、crpm（通过电阻率估算孔隙度）、总标准化孔隙度、孔隙度评价因子、密度差、体积密度、电阻率.1、总孔隙度、裂缝孔隙度、成像计算裂缝孔隙度、饱和度、密度、中子、纵波时差、斯通利波时差"
        },
        (5095.64, 5327.62): {
            "基本测井参数": "深度、井径、补偿中子、声波时差、自然伽马、光电吸收界面指数、深侧向电阻率、微球型聚焦电阻率、浅侧向电阻率、自然电位",
            "校正及相关曲线": "测井深度校正曲线、密度矫正、岩性密度矫正",
            "岩石电阻率": "岩石电阻率1、岩石电阻率2、岩石电阻率3、岩石电阻率4",
            "其他参数": "泵效、油气层厚度、中侧向电阻率、spwdh、rtm、真实垂直深度、最近5英尺的钻进速率、34声波高度、16光电系数、28光电系数、34光电系数、电阻率、井温、深层洞穴",
            "特殊参数及计算相关": "声波速度、crpm（通过电阻率估算孔隙度）、总标准化孔隙度、孔隙度评价因子、密度差、体积密度、电阻率.1、总孔隙度、裂缝孔隙度、成像计算裂缝孔隙度、饱和度、密度、中子、纵波时差、斯通利波时差"
        },
        (5327.62, 5559.6): {
            "基本测井参数": "深度、井径、补偿中子、声波时差、自然伽马、光电吸收界面指数、深侧向电阻率、微球型聚焦电阻率、浅侧向电阻率、自然电位",
            "校正及相关曲线": "测井深度校正曲线、密度矫正、岩性密度矫正",
            "岩石电阻率": "岩石电阻率1、岩石电阻率2、岩石电阻率3、岩石电阻率4",
            "其他参数": "泵效、油气层厚度、中侧向电阻率、spwdh、rtm、真实垂直深度、最近5英尺的钻进速率、34声波高度、16光电系数、28光电系数、34光电系数、电阻率、井温、深层洞穴",
            "特殊参数及计算相关": "声波速度、crpm（通过电阻率估算孔隙度）、总标准化孔隙度、孔隙度评价因子、密度差、体积密度、电阻率.1、总孔隙度、裂缝孔隙度、成像计算裂缝孔隙度、饱和度、密度、中子、纵波时差、斯通利波时差"
        }
    }


# 模型训练和调优函数
def train_model(X_train, y_train):
    param_grid = {
        'n_estimators': [50, 100, 150],
        'learning_rate': [0.01, 0.1, 0.2],
        'max_depth': [3, 4, 5]
    }
    best_models = {}
    for col in y_train.columns:
        model = GradientBoostingRegressor(random_state=42)
        grid_search = GridSearchCV(model, param_grid, cv=5, scoring='neg_mean_squared_error')
        total_steps = len(param_grid['n_estimators']) * len(param_grid['learning_rate']) * len(param_grid['max_depth']) * 5
        with tqdm(total=total_steps, desc=f"模型训练中 - {col}") as pbar:
            param_combinations = product(param_grid['n_estimators'], param_grid['learning_rate'], param_grid['max_depth'])
            for params in param_combinations:
                for _ in range(grid_search.cv):
                    pbar.update(1)
            grid_search.fit(X_train, y_train[col])
        best_model = grid_search.best_estimator_
        best_models[col] = best_model
    return best_models


# 单位转换函数，目前直接返回预测结果，可按需修改
def unit_conversion(prediction):
    return prediction


# 根据用户输入深度进行预测的函数
def predict(depth, best_models, y_columns, row_5559_6, output_text, progress_bar):
    try:
        depth = float(depth)
        total_steps = 100
        progress_bar['maximum'] = total_steps
        predictions = {}
        for col in y_columns:
            model = best_models[col]
            prediction = model.predict([[depth]])
            converted_prediction = unit_conversion(prediction)
            predictions[col] = converted_prediction[0]
        output_text.delete(1.0, tk.END)
        ranges_params = get_depth_ranges_params()
        for (lower, upper), params in ranges_params.items():
            if lower < depth <= upper:
                all_params = []
                for category in ["基本测井参数", "校正及相关曲线", "岩石电阻率", "其他参数", "特殊参数及计算相关"]:
                    all_params.extend(params[category].split('、'))
                for col in y_columns:
                    if col in all_params:
                        if pd.isna(row_5559_6[col].values[0]):
                            result = '空值'
                        else:
                            result = '{:.6f}'.format(predictions[col])
                        output_text.insert(tk.END, f"{col}: {result}\n")
                break
        else:
            output_text.insert(tk.END, "输入的深度值不在预设范围内，请重新输入。")
    except ValueError:
        output_text.delete(1.0, tk.END)
        output_text.insert(tk.END, "输入的深度值不是有效的数字，请重新输入。")
    except IndexError:
        output_text.delete(1.0, tk.END)
        output_text.insert(tk.END, "未找到深度为 5559.6 的数据，请检查数据。")
    except Exception as e:
        output_text.delete(1.0, tk.END)
        output_text.insert(tk.END, f"预测过程中出现未知错误：{e}")
    finally:
        progress_bar['value'] = 0


# 异步执行预测函数，防止界面卡顿
def async_predict(depth_entry, best_models, y_columns, row_5559_6, output_text, progress_bar):
    depth = depth_entry.get()
    thread = threading.Thread(target=predict, args=(depth, best_models, y_columns, row_5559_6, output_text, progress_bar))
    thread.start()
    update_progress_bar(progress_bar)


# 更新进度条
def update_progress_bar(progress_bar):
    if progress_bar['value'] < progress_bar['maximum']:
        progress_bar['value'] += 1
        root.after(10, update_progress_bar, progress_bar)


# 创建主窗口及布局
def create_gui(best_models, y_columns, row_5559_6):
    global root
    root = tk.Tk()
    root.title('深度预测')
    root.geometry("400x500")
    root.configure(bg="#f0f0f0")

    # 标题标签
    title_label = tk.Label(root, text="深度预测系统", font=("Arial", 16, "bold"), bg="#f0f0f0")
    title_label.pack(pady=10)

    # 创建输入框框架
    input_frame = tk.Frame(root, bg="#f0f0f0")
    input_frame.pack(pady=20)

    # 创建深度输入标签
    depth_label = tk.Label(input_frame, text='输入深度值', font=("Arial", 12), bg="#f0f0f0")
    depth_label.pack(side=tk.LEFT)
    # 创建深度输入框
    depth_entry = tk.Entry(input_frame, font=("Arial", 12))
    depth_entry.pack(side=tk.LEFT, padx=10)

    # 创建进度条框架
    progress_frame = tk.Frame(root, bg="#f0f0f0")
    progress_frame.pack(pady=10)
    # 创建进度条
    progress_bar = ttk.Progressbar(progress_frame, orient="horizontal", length=300, mode="determinate")
    progress_bar.pack()

    # 创建输出框框架
    output_frame = tk.Frame(root, bg="#f0f0f0")
    output_frame.pack(pady=20)

    # 创建输出结果标签
    output_label = tk.Label(output_frame, text='预测结果：', font=("Arial", 12), bg="#f0f0f0")
    output_label.pack()
    # 创建输出文本框
    output_text = tk.Text(output_frame, height=10, width=50, font=("Arial", 12))
    output_text.pack()

    # 创建预测按钮，点击触发异步预测函数
    predict_button = tk.Button(root, text='预测', command=lambda: async_predict(depth_entry, best_models, y_columns, row_5559_6, output_text, progress_bar), font=("Arial", 12))
    predict_button.pack(pady=20)

    # 启动 Tkinter 主循环
    root.mainloop()


# 主程序入口
if __name__ == "__main__":
    file_path = r'C:\Users\huangshaozheng\Desktop\zongbiao1.xlsx'
    df = read_data(file_path)
    if df is not None:
        imputed_df = preprocess_data(df)
        X = imputed_df[['深度']]
        y = imputed_df.drop(columns='深度')
        X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)
        best_models = train_model(X_train, y_train)
        y_preds = {}
        for col in y_test.columns:
            model = best_models[col]
            y_pred = model.predict(X_test)
            y_preds[col] = y_pred
            mse = mean_squared_error(y_test[col], y_pred)
            mae = mean_absolute_error(y_test[col], y_pred)
            r2 = r2_score(y_test[col], y_pred)
            print(f'列名: {col}, 均方误差：{mse:.6f}, 平均绝对误差：{mae:.6f}, 决定系数：{r2:.6f}')
        row_5559_6 = imputed_df[imputed_df['深度'] == 5559.6].drop(columns='深度')
        create_gui(best_models, y.columns, row_5559_6)
