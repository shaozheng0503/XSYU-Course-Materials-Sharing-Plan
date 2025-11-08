import pandas as pd
import seaborn as sns
from matplotlib import font_manager
import matplotlib.pyplot as plt

plt.rcParams['font.sans-serif'] = ['SimHei']  # 用来正常显示中文标签
plt.rcParams['axes.unicode_minus'] = False  # 用来正常显示负号


# 定义文件路径和工作表名称列表
file_path = r'C:\Users\huangshaozheng\Desktop\石油工程设计大赛\方法1相关性分析.xlsx'
sheet_names = ['w1数据', 'w2数据', 'w3数据', 'w4数据', 'w5数据']

# 遍历每个工作表并生成热力图
for sheet_name in sheet_names:
    try:
        # 读取当前工作表的数据
        data = pd.read_excel(file_path, sheet_name=sheet_name)

        # 筛选数值型列
        numeric_data = data.select_dtypes(include=[float, int])

        # 计算斯皮尔曼相关矩阵
        spearman_matrix = numeric_data.corr(method='spearman')

        # 创建热力图
        plt.figure(figsize=(10, 8))
        sns.set(style="whitegrid", font_scale=1.2)
        heatmap = sns.heatmap(
            spearman_matrix,
            annot=True,
            fmt=".2f",
            cmap="coolwarm",
            cbar_kws={"label": "斯皮尔曼相关系数"},
            linewidths=0.5,
            linecolor="gray"
        )

        # 设置标题和标签
        plt.title(f"工作表 {sheet_name} - 斯皮尔曼相关性热力图", fontsize=16)
        plt.xticks(rotation=45, ha='right')
        plt.yticks(rotation=0)

        # 保存图像
        output_image = f'spearman_heatmap_{sheet_name}.png'
        plt.tight_layout()
        plt.savefig(output_image, dpi=300)

        # 显示图像
        plt.show()
    except Exception as e:
        print(f"处理工作表 {sheet_name} 时出错: {e}")