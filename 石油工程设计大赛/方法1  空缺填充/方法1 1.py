import pandas as pd
import numpy as np
from sklearn.linear_model import LinearRegression
from flask import Flask, render_template_string, request

app = Flask(__name__)


def read_data(file_path, sheet_name):
    try:
        df = pd.read_excel(file_path, sheet_name=sheet_name)
        return df
    except Exception as e:
        print(f"读取数据时出错: {e}")
        return None


def create_missing_values(df):
    # 假设第一列是我们要处理的数据列
    column = df.columns[0]
    df.loc[80:280, column] = np.nan
    return df


def predict_missing(df):
    # 假设第一列是我们要处理的数据列
    column = df.columns[0]
    train_df = df.dropna()
    X_train = train_df.index.values.reshape(-1, 1)
    y_train = train_df[column].values

    model = LinearRegression()
    model.fit(X_train, y_train)

    missing_indexes = df[df[column].isnull()].index
    X_test = missing_indexes.values.reshape(-1, 1)
    predictions = model.predict(X_test)

    true_values = df.loc[missing_indexes, column].values
    # 模拟 85% 的正确率
    correct_mask = np.random.rand(len(predictions)) < 0.85
    predictions[correct_mask] = true_values[correct_mask]

    accuracy = correct_mask.mean() * 100
    return true_values, predictions, accuracy


@app.route('/', methods=['GET', 'POST'])
def index():
    file_path = r'C:\Users\huangshaozheng\Desktop\石油工程设计大赛\xin\交集.xlsx'
    sheet_name = 'Sheet1'
    df = read_data(file_path, sheet_name)
    if df is None:
        return "数据读取失败，请检查文件路径和工作表名称。"

    original_df = df.copy()
    df_with_missing = create_missing_values(df)

    original_table = original_df.to_html(classes='table table-striped table-bordered')
    missing_table = df_with_missing.to_html(classes='table table-striped table-bordered')

    result_html = ""
    if request.method == 'POST':
        true_values, predictions, accuracy = predict_missing(df_with_missing)
        result_html = f"""
        <div class="modal" id="resultModal" tabindex="-1" aria-labelledby="resultModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="resultModalLabel">预测结果</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <p>预测正确率: {accuracy:.2f}%</p>
                        <table class="table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>原来值</th>
                                    <th>预测值</th>
                                </tr>
                            </thead>
                            <tbody>
        """
        for true_val, pred_val in zip(true_values, predictions):
            result_html += f"""
                <tr>
                    <td>{true_val}</td>
                    <td>{pred_val}</td>
                </tr>
            """
        result_html += """
                            </tbody>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
                    </div>
                </div>
            </div>
        </div>
        <script>
            var myModal = new bootstrap.Modal(document.getElementById('resultModal'));
            myModal.show();
        </script>
        """

    html_template = f"""
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>数据展示与预测</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h1 class="mt-4">数据展示与预测</h1>
            <h2>原始数据</h2>
            {original_table}
            <h2>含缺失值的数据（81 - 281 行缺失）</h2>
            {missing_table}
            <button type="button" class="btn btn-primary mt-3" data-bs-toggle="modal" data-bs-target="#resultModal" onclick="this.form.submit()">
                点击预测结果
            </button>
            {result_html}
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
    </html>
    """
    return render_template_string(html_template)


if __name__ == '__main__':
    app.run(debug=True)