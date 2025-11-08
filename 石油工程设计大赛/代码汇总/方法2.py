import pandas as pd
import chardet
from flask import Flask, render_template_string, request
import os
import math
import bisect

# 创建 Flask 应用实例
app = Flask(__name__)

# 读取数据
def read_data(file_path):
    try:
        # 判断文件扩展名是否为.xlsx
        if file_path.endswith('.xlsx'):
            # 使用 pandas 读取 Excel 文件
            data = pd.read_excel(file_path)
        # 判断文件扩展名是否为.csv
        elif file_path.endswith('.csv'):
            # 以二进制模式打开文件
            with open(file_path, 'rb') as f:
                # 检测文件编码
                result = chardet.detect(f.read())
            # 获取检测到的编码
            encoding = result['encoding']
            # 使用检测到的编码读取 CSV 文件
            data = pd.read_csv(file_path, encoding=encoding)
        else:
            # 打印不支持的文件格式信息
            print(f"不支持的文件格式: {file_path}")
            return None
        return data
    # 捕获文件未找到异常
    except FileNotFoundError:
        # 打印文件未找到信息
        print(f"文件 {file_path} 未找到，请检查路径。")
        return None
    # 捕获 Unicode 解码异常
    except UnicodeDecodeError:
        # 打印编码解码错误信息
        print("编码解码错误，请检查文件编码。")
        return None

# 对每列数据进行分类
def categorize_columns(data, num_categories=12):
    """
    对数据的每列数值数据进行分类。

    参数:
    data (pandas.DataFrame): 输入的数据。
    num_categories (int): 分类的数量，默认为 12。

    返回:
    tuple: 包含两个字典，第一个字典是列名到分类边界的映射，第二个字典是列名到所有类别信息的映射。
    """
    # 初始化存储分类边界的字典
    category_dict = {}
    # 初始化存储所有类别信息的字典
    all_categories_info = {}
    # 遍历数据的每一列
    for column in data.columns:
        # 将列数据转换为数值类型，忽略错误值并删除缺失值
        numeric_data = pd.to_numeric(data[column], errors='coerce').dropna()
        # 判断该列是否存在数值数据
        if not numeric_data.empty:
            # 使用 pandas 的 cut 函数进行分类，并获取分类边界
            bins = pd.cut(numeric_data, bins=num_categories, retbins=True)[1]
            # 将列名和分类边界存入 category_dict
            category_dict[column] = bins
            # 初始化当前列的类别信息列表
            column_categories = []
            # 遍历分类边界，生成每个类别的信息
            for i in range(len(bins) - 1):
                column_categories.append(f"第 {i + 1} 类: [{bins[i]}, {bins[i + 1]})")
            # 将列名和类别信息列表存入 all_categories_info
            all_categories_info[column] = column_categories
    return category_dict, all_categories_info

# 查找输入数据所属类别
def find_category(input_data, category_dict):
    # 初始化存储结果类别的字典
    result_categories = {}
    # 初始化存储错误信息的列表
    errors = []
    # 遍历输入数据的每一项
    for column, value in input_data.items():
        # 判断列名是否在分类字典中
        if column in category_dict:
            # 获取该列的分类边界
            bins = category_dict[column]
            try:
                # 将输入值转换为浮点数
                value = float(value)
                # 使用二分查找确定输入值所属的类别索引
                index = bisect.bisect_right(bins, value) - 1
                # 判断索引是否在有效范围内
                if 0 <= index < len(bins) - 1:
                    # 将类别范围和类别编号存入结果字典
                    result_categories[column] = {
                        'range': (bins[index], bins[index + 1]),
                        'category_num': index + 1
                    }
                else:
                    # 根据输入值与分类边界的关系生成相应的提示信息
                    if value < bins[0]:
                        message = f"输入值 {value} 小于分类的最小值 {bins[0]}"
                    elif value > bins[-1]:
                        message = f"输入值 {value} 大于分类的最大值 {bins[-1]}"
                    else:
                        message = '未找到匹配类别'
                    # 将提示信息存入结果字典
                    result_categories[column] = {
                        'range': None,
                        'category_num': None,
                       'message': message
                    }
            # 捕获无法将输入值转换为浮点数的异常
            except ValueError:
                # 将错误信息添加到错误列表中
                errors.append(f"输入的 {column} 值 {value} 无法转换为浮点数。")
        else:
            # 如果列未参与分类，将相应信息存入结果字典
            result_categories[column] = {
                'range': None,
                'category_num': None,
               'message': '该列未参与分类'
            }
    return result_categories, errors

# 生成错误页面
def generate_error_page(error_message):
    # 定义错误页面的 HTML 模板
    error_template = f"""
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>数据读取失败</title>
        <style>
            body {{
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background: linear-gradient(135deg, #000, #030);
                color: #0f0;
                text-align: center;
                padding-top: 50px;
                animation: fadeIn 1s ease;
            }}
            h1 {{
                text-shadow: 0 0 10px #0f0;
                animation: glow 1.5s ease-in-out infinite alternate;
            }}
            @keyframes fadeIn {{
                from {{
                    opacity: 0;
                }}
                to {{
                    opacity: 1;
                }}
            }}
            @keyframes glow {{
                from {{
                    text-shadow: 0 0 10px #0f0;
                }}
                to {{
                    text-shadow: 0 0 20px #0f0, 0 0 30px #0f0;
                }}
            }}
        </style>
    </head>
    <body>
        <h1>{error_message}</h1>
    </body>
    </html>
    """
    return error_template

# 优化后的网页模板
html_template = """
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>数据分类匹配</title>
    <style>
        /* 全局样式 */
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #000, #030);
            color: #0f0;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            animation: fadeIn 1s ease;
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
            }
            to {
                opacity: 1;
            }
        }

       .main-container {
            display: flex;
            width: 90%;
            max-width: 1200px;
            background-color: rgba(0, 30, 0, 0.8);
            border-radius: 10px;
            box-shadow: 0 0 20px #0f0;
            padding: 30px;
            gap: 30px;
            transform: scale(0.95);
            animation: scaleIn 0.5s ease forwards;
        }

        @keyframes scaleIn {
            to {
                transform: scale(1);
            }
        }

       .input-result-container {
            flex: 1;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }

       .category-info-container {
            flex: 1;
            border-left: 1px solid #0f0;
            padding-left: 30px;
            overflow-y: auto;
        }

        h1 {
            font-size: 36px;
            text-shadow: 0 0 10px #0f0;
            margin-bottom: 20px;
            text-align: center;
            animation: glow 1.5s ease-in-out infinite alternate;
        }

        @keyframes glow {
            from {
                text-shadow: 0 0 10px #0f0;
            }
            to {
                text-shadow: 0 0 20px #0f0, 0 0 30px #0f0;
            }
        }

        form {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
        }

        label {
            display: block;
            margin-bottom: 5px;
            transition: color 0.3s ease;
        }

        label:hover {
            color: #0c0;
        }

        input[type="text"] {
            width: 100%; /* 修改为100%，使输入框独占一行 */
            padding: 8px;
            margin-bottom: 15px;
            border: none;
            border-radius: 5px;
            background-color: #030;
            color: #0f0;
            outline: none;
            transition: background-color 0.3s ease, box-shadow 0.3s ease;
        }

        input[type="text"]:focus {
            background-color: #050;
            box-shadow: 0 0 10px #0f0;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #0f0;
            color: #000;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
            transition: background-color 0.3s ease, transform 0.3s ease;
            margin-bottom: 20px;
        }

        input[type="submit"]:hover {
            background-color: #0c0;
            transform: scale(1.05);
        }

        h2 {
            font-size: 24px;
            text-shadow: 0 0 10px #0f0;
            margin-top: 30px;
            text-align: center;
            animation: slideIn 0.5s ease;
        }

        @keyframes slideIn {
            from {
                transform: translateY(-20px);
                opacity: 0;
            }
            to {
                transform: translateY(0);
                opacity: 1;
            }
        }

        ul {
            list-style-type: none;
            padding: 0;
        }

        li {
            margin-bottom: 10px;
            background-color: rgba(0, 50, 0, 0.8);
            padding: 10px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        li:hover {
            background-color: rgba(0, 70, 0, 0.8);
        }

       .category-header {
            cursor: pointer;
            user-select: none;
            transition: color 0.3s ease;
        }

       .category-header:hover {
            color: #0c0;
        }

       .category-details {
            display: none;
            padding-left: 20px;
            animation: slideDown 0.3s ease;
        }

        @keyframes slideDown {
            from {
                transform: translateY(-10px);
                opacity: 0;
            }
            to {
                transform: translateY(0);
                opacity: 1;
            }
        }
    </style>
    <script>
        function toggleDetails(element) {
            const details = element.nextElementSibling;
            if (details.style.display === 'none') {
                details.style.display = 'block';
            } else {
                details.style.display = 'none';
            }
        }
    </script>
</head>

<body>
    <div class="main-container">
        <div class="input-result-container">
            <h1>输入数据查找类别</h1>
            <form method="post">
                {% for column in input_columns %}
                    <label for="{{ column }}">{{ column }}:</label>
                    <input type="text" id="{{ column }}" name="{{ column }}" required>
                {% endfor %}
                <input type="submit" value="查找">
            </form>
            {% if result %}
                <h2>匹配结果</h2>
                <ul>
                    {% for column, info in result.items() %}
                        {% if info.range %}
                            <li>{{ column }} 属于类别范围: [{{ info.range[0] }}, {{ info.range[1] }})，属于第 {{ info.category_num }} 类</li>
                        {% elif info.message %}
                            <li>{{ column }}: {{ info.message }}</li>
                        {% endif %}
                    {% endfor %}
                </ul>
            {% endif %}
            {% if errors %}
                <h2>输入错误</h2>
                <ul>
                    {% for error in errors %}
                        <li style="color: red;">{{ error }}</li>
                    {% endfor %}
                </ul>
            {% endif %}
        </div>
        <div class="category-info-container">
            <h2>所有类的分类信息</h2>
            {% for column, categories in all_categories_info.items() %}
                <div class="category-header" onclick="toggleDetails(this)">{{ column }}</div>
                <ul class="category-details">
                    {% for category in categories %}
                        <li>{{ category }}</li>
                    {% endfor %}
                </ul>
            {% endfor %}
        </div>
    </div>
</body>

</html>
"""

@app.route('/', methods=['GET', 'POST'])
def index():
    # 从环境变量中获取数据文件路径
    file_path = os.getenv('DATA_FILE_PATH')
    # 如果环境变量未设置，则使用默认文件路径
    if file_path is None:
        file_path = '方法2.xlsx'
        print("环境变量 DATA_FILE_PATH 未设置，使用默认文件路径:", file_path)
    # 调用 read_data 函数读取数据
    data = read_data(file_path)
    # 如果数据读取失败，生成错误页面并返回
    if data is None:
        return generate_error_page("数据读取失败，请检查文件路径。")
    # 打印数据的列名
    print("数据列名:", data.columns)
    # 调用 categorize_columns 函数对数据进行分类
    category_dict, all_categories_info = categorize_columns(data)

    # 打印所有分类信息
    print("所有分类信息：")
    for column, categories in all_categories_info.items():
        print(f"列名: {column}")
        for category in categories:
            print(f"  {category}")

    # 定义输入列名列表
    input_columns = ['电阻率', '总孔隙度', '裂缝孔隙度', '成像计算裂缝孔隙度', '饱和度', '密度', '中子', '纵波时差', '斯通利波时差']
    # 初始化结果和错误列表
    result = None
    errors = []
    # 判断请求方法是否为 POST
    if request.method == 'POST':
        # 将表单数据转换为字典
        input_data = request.form.to_dict()
        # 调用 find_category 函数查找输入数据所属类别
        result, errors = find_category(input_data, category_dict)
    # 使用模板渲染函数返回网页
    return render_template_string(html_template, input_columns=input_columns, result=result, all_categories_info=all_categories_info, errors=errors)

if __name__ == '__main__':
    # 运行 Flask 应用，开启调试模式
    app.run(debug=True)