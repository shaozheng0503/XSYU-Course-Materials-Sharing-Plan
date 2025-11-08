public class PlotGraph {
    public static void main(String[] args) {
        // 这里可以放置一些测试代码
    }

    public void plotGraph() {
        // 获取输入的表达式
        String equation = "x * x"; // 示例表达式

        if (equation.isEmpty()) {
            System.out.println("请输入一个有效的表达式");
            return;
        }

        // 尝试解析表达式并绘制图形
        try {
            // 假设 GraphPanel 有一个 plot 方法可以接受表达式并绘制图形
            GraphPanel graphPanel = new GraphPanel();
            graphPanel.plot(equation);
        } catch (Exception ex) {
            System.out.println("无法解析表达式: " + ex.getMessage());
        }
    }
}