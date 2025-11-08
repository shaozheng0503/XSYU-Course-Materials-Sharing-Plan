import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class GraphPanel extends JPanel {
    private String equation;

    public GraphPanel() {
        setBackground(Color.WHITE);
    }

    public void plot(String equation) {
        this.equation = equation;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // 绘制坐标轴
        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;

        g2d.setColor(Color.BLACK);
        g2d.drawLine(0, centerY, width, centerY); // X轴
        g2d.drawLine(centerX, 0, centerX, height); // Y轴

        // 绘制表达式曲线
        if (equation != null && !equation.isEmpty()) {
            g2d.setColor(Color.BLUE);
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");

            for (int x = 0; x < width; x++) {
                double xValue = (x - centerX) / 100.0; // 假设缩放比例为100
                try {
                    double yValue = ((Number) engine.eval(equation.replace("x", String.valueOf(xValue)))).doubleValue();
                    int screenY = centerY - (int) (yValue * 100);
                    g2d.drawLine(x, screenY, x, screenY);
                } catch (ScriptException e) {
                    System.err.println("无法解析表达式: " + e.getMessage());
                }
            }
        }
    }
}