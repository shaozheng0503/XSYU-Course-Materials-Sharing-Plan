import matplotlib.pyplot as plt
import random

# 设置 matplotlib 支持中文
plt.rcParams['font.sans-serif'] = ['SimHei']  # 使用黑体字体
plt.rcParams['axes.unicode_minus'] = False  # 解决负号显示问题

# 调整图形大小
plt.figure(figsize=(10, 6))

# 生成缺失率从 5% 到 60% 每隔 5% 的数据
missing_rates = [i / 100 for i in range(5, 61, 5)]

# 生成不同模型的填充正确率数据
# GradientBoosting 模型，保持在 73% 左右
gradient_boosting = [0.73]
current_acc = 0.73
for rate in missing_rates[1:]:
    # 波动范围增大
    fluctuation = random.uniform(-0.07, 0.07)
    current_acc = current_acc + fluctuation
    # 确保在 73% 左右小范围波动
    current_acc = max(0.68, min(0.78, current_acc))
    gradient_boosting.append(current_acc)

# RandomForest 模型，保持在 65% 左右
random_forest = [0.65]
prev_acc = 0.65
for rate in missing_rates[1:]:
    # 波动范围增大
    acc = prev_acc + random.uniform(-0.07, 0.07)
    # 确保在 65% 左右小范围波动
    acc = max(0.6, min(0.7, acc))
    random_forest.append(acc)
    prev_acc = acc

# SVR 模型，保持在 65% 左右
svr = [0.65]
prev_acc = 0.65
for rate in missing_rates[1:]:
    # 波动范围增大
    acc = prev_acc + random.uniform(-0.07, 0.07)
    # 确保在 65% 左右小范围波动
    acc = max(0.6, min(0.7, acc))
    svr.append(acc)
    prev_acc = acc

# MLP 模型，保持在 55% 左右
mlp = [0.55]
prev_acc = 0.55
for rate in missing_rates[1:]:
    # 波动范围增大
    acc = prev_acc + random.uniform(-0.07, 0.07)
    # 确保在 55% 左右小范围波动
    acc = max(0.5, min(0.6, acc))
    mlp.append(acc)
    prev_acc = acc

# 平滑 MLP 曲线
def moving_average(data, window_size):
    smoothed = []
    for i in range(len(data)):
        if i < window_size - 1:
            smoothed.append(data[i])
        else:
            smoothed.append(sum(data[i - window_size + 1:i + 1]) / window_size)
    return smoothed

# 使用窗口大小为 3 进行平滑处理
mlp = moving_average(mlp, 3)

# 定义模型列表和对应的颜色
models = [random_forest, svr, gradient_boosting, mlp]
model_names = ['RandomForest', 'SVR', 'GradientBoosting', 'MLP']
colors = ['blue', 'orange', 'green', 'red']

# 绘制每个模型的线和标注数据点
for model, name, color in zip(models, model_names, colors):
    plt.plot(missing_rates, model, label=name, color=color)
    for x, y in zip(missing_rates, model):
        # 增大 fontsize 参数
        plt.text(x, y, f'{y:.2f}', ha='center', va='bottom', fontsize=10, color=color)

# 添加标题
plt.title('不同模型在不同缺失率下的填充正确率')
# 添加 x 轴标签
plt.xlabel('缺失率')
# 添加 y 轴标签
plt.ylabel('填充正确率')
# 添加图例
plt.legend()

# 设置 x 轴和 y 轴的刻度
plt.xticks(missing_rates)
plt.yticks([i / 100 for i in range(0, 101, 5)])

# 添加网格线
plt.grid(True)

# 显示图形
plt.show()