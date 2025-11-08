import pandas as pd
import matplotlib.pyplot as plt

# 设置图片清晰度
plt.rcParams['figure.dpi'] = 300

# 设置中文字体，根据系统情况选择合适的字体
plt.rcParams['font.sans-serif'] = ['SimHei']  # 可以根据系统调整，如 'WenQuanYi Zen Hei'、'Microsoft YaHei' 等
plt.rcParams['axes.unicode_minus'] = False  # 解决负号显示问题

# 读取 Excel 文件
excel_file = pd.ExcelFile('C:/Users/huangshaozheng/Desktop/石油工程设计大赛/方法一数据.xlsx')

# 获取所有表名
sheet_names = excel_file.sheet_names

# 遍历不同工作表
for sheet_name in sheet_names:
    # 获取当前工作表的数据
    df = excel_file.parse(sheet_name)

    # 筛选出数值型列
    numeric_df = df.select_dtypes(include='number')

    # 检查是否存在深度列
    if '深度' in numeric_df.columns:
        # 计算与深度的斯皮尔曼相关性（保留两位小数）
        corr_with_depth = numeric_df.corrwith(numeric_df['深度'], method='spearman').round(2)

        # 优化输出表格的显示
        print(f"\n{'='*30} {sheet_name} 各元素与深度的斯皮尔曼相关系数 {'='*30}")
        corr_table = pd.DataFrame(corr_with_depth, columns=['斯皮尔曼相关系数'])
        print(corr_table.to_csv(sep='\t', na_rep='nan'))

        # 创建画布，减小图形大小
        plt.figure(figsize=(8, 6))  # 调整图形大小，这里将宽度设为 8 英寸，高度设为 6 英寸

        # 绘制柱状图
        bars = plt.bar(corr_with_depth.index, corr_with_depth.values)
        plt.xlabel('列名', fontsize=12)
        plt.ylabel('斯皮尔曼相关系数', fontsize=12)
        plt.title(f'{sheet_name} 各元素与深度的斯皮尔曼相关系数柱状图', fontsize=14)
        plt.xticks(rotation=90, fontsize=10)
        plt.yticks(fontsize=10)

        # 添加数值标签
        for bar in bars:
            height = bar.get_height()
            plt.text(bar.get_x() + bar.get_width() / 2, height, f'{height:.2f}', ha='center', va='bottom', fontsize=8)

        plt.tight_layout()  # 调整布局，防止内容显示不全
        plt.show()
    else:
        print(f"\n{'='*30} {sheet_name} {'='*30}")
        print(f'sheet表名为{sheet_name}的数据中不存在"深度"列，无法进行相关性分析。')