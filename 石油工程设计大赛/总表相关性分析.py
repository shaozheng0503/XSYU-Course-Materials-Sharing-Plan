import pandas as pd
import numpy as np

def data_cleaning(df):
    """
    数据清洗函数，处理特殊字符、缺失值，转换数据类型，删除缺失值过多的列，并填充剩余缺失值
    :param df: 输入的 DataFrame
    :return: 清洗后的 DataFrame
    """
    # 处理特殊字符和缺失值
    df.replace(['﹣', -999.25], np.nan, inplace=True)
    # 删除全为缺失值的列
    df.dropna(axis=1, how='all', inplace=True)
    # 转换数据类型为数值型
    df = df.apply(pd.to_numeric, errors='coerce')
    # 删除缺失值超过 75% 的列
    threshold = len(df) * 0.75
    df.dropna(thresh=threshold, axis=1, inplace=True)
    # 填充剩余缺失值（使用列均值）
    df.fillna(df.mean(), inplace=True)
    return df

def outlier_handling(df):
    """
    异常值处理函数，使用 IQR 方法检测并处理异常值
    :param df: 输入的 DataFrame
    :return: 处理异常值后的 DataFrame
    """
    # 使用 IQR 方法检测和处理异常值
    Q1 = df.quantile(0.25)
    Q3 = df.quantile(0.75)
    IQR = Q3 - Q1

    lower_bound = Q1 - 1.5 * IQR
    upper_bound = Q3 + 1.5 * IQR

    # 将异常值替换为上下边界的值
    df = df.clip(lower=lower_bound, upper=upper_bound, axis=1)
    return df

def main():
    try:
        # 读取 Excel 文件
        file_path = r'C:\Users\huangshaozheng\Desktop\zongbiao.xlsx'
        df = pd.read_excel(file_path)

        # 预处理列名（处理换行符和特殊字符）
        columns = [
            '深度', '井径', '补偿中子', '声波时差', '自然伽马', '光电吸收界面指数', '深侧向电阻率',
            '微球型聚焦电阻率', '浅侧向电阻率', '自然电位测井深度校正曲线', '密度矫正', '岩性密度矫正',
            '岩石电阻率 1', '岩石电阻率 2', '岩石电阻率 3', '岩石电阻率 4', '泵效', '油气层厚度',
            '中侧向电阻率', 'spwdh', 'rtm', '真实垂直深度', '最近 5 英尺的钻进速率', '34 声波高度',
            '16 光电系数', '28 光电系数', '34 光电系数', '电阻率', '井温', '深层洞穴声波速度',
            'crpm（通过电阻率估算孔隙度）', '总标准化孔隙度', '孔隙度评价因子', '密度差', '体积密度',
            '电阻率', '总孔隙度', '裂缝孔隙度', '成像计算裂缝孔隙度', '饱和度', '密度', '中子',
            '纵波时差', '斯通利波时差'
        ]

        # 如果需要指定列名
        df.columns = columns

        # 数据清洗
        df = data_cleaning(df)

        # 异常值处理
        df = outlier_handling(df)

        # 计算所有列与深度的斯皮尔曼相关性
        all_depth_corr = df.corrwith(df['深度'], method='spearman')

        # 去掉深度列自身的相关性
        all_depth_corr = all_depth_corr.drop('深度')

        # 输出所有列与深度的相关性
        print("\n所有列与深度的相关性：")
        print(all_depth_corr)

    except FileNotFoundError:
        print(f"未找到文件：{file_path}，请检查文件路径是否正确。")
    except Exception as e:
        print(f"发生未知错误：{e}")

if __name__ == "__main__":
    main()