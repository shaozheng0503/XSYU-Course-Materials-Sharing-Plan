import matplotlib.pyplot as plt

# 输入缺失率，以逗号分隔，例如：0.3,0.4,0.5
missing_rates_str = input("请输入不同的缺失率，以逗号分隔: ")
missing_rates = [float(x.strip()) for x in missing_rates_str.split(',')]

# 输入RandomForest的填充正确率，以逗号分隔
random_forest_str = input("请输入RandomForest模型在上述缺失率下的填充正确率，以逗号分隔: ")
random_forest = [float(x.strip()) for x in random_forest_str.split(',')]

# 输入SVR的填充正确率，以逗号分隔
svr_str = input("请输入SVR模型在上述缺失率下的填充正确率，以逗号分隔: ")
svr = [float(x.strip()) for x in svr_str.split(',')]

# 输入GradientBoosting的填充正确率，以逗号分隔
gradient_boosting_str = input("请输入GradientBoosting模型在上述缺失率下的填充正确率，以逗号分隔: ")
gradient_boosting = [float(x.strip()) for x in gradient_boosting_str.split(',')]

# 输入MLP的填充正确率，以逗号分隔
mlp_str = input("请输入MLP模型在上述缺失率下的填充正确率，以逗号分隔: ")
mlp = [float(x.strip()) for x in mlp_str.split(',')]

# 绘制RandomForest的线
plt.plot(missing_rates, random_forest, label='RandomForest', color='blue')
# 绘制SVR的线
plt.plot(missing_rates, svr, label='SVR', color='orange')
# 绘制GradientBoosting的线
plt.plot(missing_rates, gradient_boosting, label='GradientBoosting', color='green')
# 绘制MLP的线
plt.plot(missing_rates, mlp, label='MLP', color='red')

# 添加标题
plt.title('不同模型在不同缺失率下的填充正确率')
# 添加x轴标签
plt.xlabel('缺失率')
# 添加y轴标签
plt.ylabel('填充正确率')
# 添加图例
plt.legend()

# 显示图形
plt.show()