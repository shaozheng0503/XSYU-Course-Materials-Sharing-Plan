import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.math.RoundingMode;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class CalculatorHMI1 extends JFrame {
    private MixedOperation eq = new MixedOperation(32); // 混合运算类，设置精度为小数点后34位
    private GridBagConstraints gridBagConstraints = new GridBagConstraints(); // 实例化该对象用来对组件进行管理
    private GridBagLayout gridBagLayout = new GridBagLayout(); // 实例化布局对象
    private DecimalFormat df = new DecimalFormat(); // 对字符串进行格式化，三位一组分割

    private boolean _2ndFlag = false; // 记录当前切换到哪组按键
    private boolean DegRadFlag = true; // 记录当前使用角度还是弧度
    private boolean calculateFlag = false; // 如果上一步进行了计算，该标志位为true

//    // 第0行
//    private JLabel L_logo = new JLabel(new ImageIcon("C:\\Users\\huangshaozheng\\Desktop\\java\\ziliao\\JavaCalculator-master\\JavaCalculator-master\\SourceCode\\logo.png"));
    // 第1行
    private JButton B_interface = new JButton("科学"); // 计算器版本切换
    // 第2行
    private JTextField T_Eq = new JTextField(""); // 显示输入算式
    // 第3行
    private JTextField T_Res = new JTextField("0"); // 显示输出结果
    // 第4行
    private JButton B_DegRad = new JButton("Deg"); // 角度与弧度转换
    private JButton B_F_E = new JButton("F-E"); // 普通数值与科学计数法转换
    private JButton B_M_down = new JButton("M↓"); // 内存操作，暂时不用
    // 第5行
    private JButton B_MC = new JButton("MC"); // 内存操作，暂时不用
    private JButton B_MR = new JButton("MR"); // 内存操作，暂时不用
    private JButton B_M_add = new JButton("M+"); // 内存操作，暂时不用
    private JButton B_M_dec = new JButton("M-"); // 内存操作，暂时不用
    private JButton B_MS = new JButton("MS"); // 内存操作，暂时不用
    // 第6行
    private JButton B_secondFunction = new JButton("2nd"); // 切换按键第二功能
    private JButton B_sin = new JButton("sin"); // 正弦函数
    private JButton B_cos = new JButton("cos"); // 余弦函数
    private JButton B_tan = new JButton("tan"); // 正切函数
    private JButton B_Exp = new JButton("Exp"); // 指数函数
    // 第7行
    private JButton B_sqrt = new JButton("2√x"); // 开根号
    private JButton B_reciprocal = new JButton("1/x"); // 倒数
    private JButton B_Mod = new JButton("Mod"); // 取余
    private JButton B_CE = new JButton("CE"); // 清空输入输出
    private JButton B_backspace = new JButton("←"); // 退格，清除输入的最后一位
    // 第8行
    private JButton B_square = new JButton("x^2"); // 平方
    private JButton B_leftBracket = new JButton("("); // 混合运算的左括号
    private JButton B_rightBracket = new JButton(")"); // 混合运算的右括号
    private JButton B_factorial = new JButton("n!"); // 阶乘
    private JButton B_div = new JButton("/"); // 除号
    // 第9行
    private JButton B_pow = new JButton("x^n"); // x的n次方
    private JButton B_7 = new JButton("7"); // 数字7
    private JButton B_8 = new JButton("8"); // 数字8
    private JButton B_9 = new JButton("9"); // 数字9
    private JButton B_mul = new JButton("*"); // 乘号
    // 第10行
    private JButton B_log = new JButton("log"); // 以10为底的对数
    private JButton B_4 = new JButton("4"); // 数字4
    private JButton B_5 = new JButton("5"); // 数字5
    private JButton B_6 = new JButton("6"); // 数字6
    private JButton B_dec = new JButton("-"); // 减号
    // 第11行
    private JButton B_ln = new JButton("ln"); // 以e为底的对数
    private JButton B_1 = new JButton("1"); // 数字1
    private JButton B_2 = new JButton("2"); // 数字2
    private JButton B_3 = new JButton("3"); // 数字3
    private JButton B_add = new JButton("+"); // 加号
    // 第12行
    private JButton B_pi_e = new JButton("π"); // 圆周率π或自然常数e
    private JButton B_negation = new JButton("+/-"); // 正/负切换，取反
    private JButton B_0 = new JButton("0"); // 数字0
    private JButton B_point = new JButton("."); // 小数点
    private JButton B_equal = new JButton("="); // 等号
    // 输入输出字符串
    private String S_input = new String(""); // 保存输入的算式
    private BigDecimal S_output = new BigDecimal("0"); // 保存计算结果

    public CalculatorHMI1() {
        df.setMaximumFractionDigits(32); // 数值显示到小数点后32位
        df.setRoundingMode(RoundingMode.HALF_EVEN); // 银行家舍入法
        df.setGroupingSize(3); // 设置数字分组大小，对结果3位一体划分开

        T_Eq.setHorizontalAlignment(JTextField.RIGHT); // 输入算式文本显示右对齐
        T_Res.setHorizontalAlignment(JTextField.RIGHT); // 结果文本显示右对齐

        this.setTitle("西安石油大学 Java 计算器"); // 设置窗体标题
        this.setSize(277, 420); // 界面大小
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setResizable(false);
        this.setLayout(gridBagLayout); // 窗体对象设置为GridBagLayout布局
        gridBagConstraints.fill = GridBagConstraints.BOTH; // 该方法是为了设置如果组件所在的区域比组件本身要大时的显示情况

        // 第0行
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
//        this.add(L_logo, gridBagConstraints);

        // 第1行
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        setControl(B_interface, 0, 1, 2, 1);
        // 第2行
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        setControl(T_Eq, 0, 2, 5, 1); // 算式显示
        // 第3行
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        setControl(T_Res, 0, 3, 5, 1); // 结果显示
        // 第4行
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        setControl(B_DegRad, 0, 4, 1, 1); // 角度与弧度切换
        setControl(B_F_E, 1, 4, 1, 1); // 未知功能，保留
        setControl(B_M_down, 4, 4, 1, 1); // M↓
        // 第5行
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        setControl(B_MC, 0, 5, 1, 1); // MC
        setControl(B_MR, 1, 5, 1, 1); // MR
        setControl(B_M_add, 2, 5, 1, 1); // M+
        setControl(B_M_dec, 3, 5, 1, 1); // M-
        setControl(B_MS, 4, 5, 1, 1); // MS
        // 第6行
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        setControl(B_secondFunction, 0, 6, 1, 1); // 第二功能切换按钮
        setControl(B_sin, 1, 6, 1, 1); // sin, asin
        setControl(B_cos, 2, 6, 1, 1); // cos, acos
        setControl(B_tan, 3, 6, 1, 1); // tan, atan
        setControl(B_Exp, 4, 6, 1, 1); // 科学计数法表示
        // 第7行
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        setControl(B_sqrt, 0, 7, 1, 1); // 开根号
        setControl(B_reciprocal, 1, 7, 1, 1); // 倒数
        setControl(B_Mod, 2, 7, 1, 1); // 取余
        setControl(B_CE, 3, 7, 1, 1); // 清空输入
        setControl(B_backspace, 4, 7, 1, 1); // 清除最后一位输入
        // 第8行
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        setControl(B_square, 0, 8, 1, 1); // 平方
        setControl(B_leftBracket, 1, 8, 1, 1); // 左括号
        setControl(B_rightBracket, 2, 8, 1, 1); // 右括号
        setControl(B_factorial, 3, 8, 1, 1); // 阶乘
        setControl(B_div, 4, 8, 1, 1); // 除号
        // 第9行
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        setControl(B_pow, 0, 9, 1, 1); // x的n次方
        setControl(B_7, 1, 9, 1, 1); // 数字7
        setControl(B_8, 2, 9, 1, 1); // 数字8
        setControl(B_9, 3, 9, 1, 1); // 数字9
        setControl(B_mul, 4, 9, 1, 1); // 乘号
        // 第10行
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        setControl(B_log, 0, 10, 1, 1); // 以10为底的对数
        setControl(B_4, 1, 10, 1, 1); // 数字4
        setControl(B_5, 2, 10, 1, 1); // 数字5
        setControl(B_6, 3, 10, 1, 1); // 数字6
        setControl(B_dec, 4, 10, 1, 1); // 减号
        // 第11行
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        setControl(B_ln, 0, 11, 1, 1); // 以e为底的对数
        setControl(B_1, 1, 11, 1, 1); // 数字1
        setControl(B_2, 2, 11, 1, 1); // 数字2
        setControl(B_3, 3, 11, 1, 1); // 数字3
        setControl(B_add, 4, 11, 1, 1); // 加号
        // 第12行
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        setControl(B_pi_e, 0, 12, 1, 1); // 圆周率π或自然常数e
        setControl(B_negation, 1, 12, 1, 1); // 取反
        setControl(B_0, 2, 12, 1, 1); // 数字0
        setControl(B_point, 3, 12, 1, 1); // 小数点
        setControl(B_equal, 4, 12, 1, 1); // 等号

        // 文本较长的按钮显示距离设为0
        B_sin.setMargin(new Insets(0, 0, 0, 0));
        B_cos.setMargin(new Insets(0, 0, 0, 0));
        B_tan.setMargin(new Insets(0, 0, 0, 0));
        B_log.setMargin(new Insets(0, 0, 0, 0));
        B_Mod.setMargin(new Insets(0, 0, 0, 0));
        B_Exp.setMargin(new Insets(0, 0, 0, 0));
        B_backspace.setMargin(new Insets(0, 0, 0, 0));
        B_M_down.setMargin(new Insets(0, 0, 0, 0));
        B_sqrt.setMargin(new Insets(0, 0, 0, 0));
        B_reciprocal.setMargin(new Insets(0, 0, 0, 0));
        B_square.setMargin(new Insets(0, 0, 0, 0));
        B_pow.setMargin(new Insets(0, 0, 0, 0));
        B_ln.setMargin(new Insets(0, 0, 0, 0));
        B_negation.setMargin(new Insets(0, 0, 0, 0));
        B_DegRad.setMargin(new Insets(0, 0, 0, 0));

        // 基本功能
        B_0.addActionListener(new MyActionListener()); // 数字0
        B_1.addActionListener(new MyActionListener()); // 数字1
        B_2.addActionListener(new MyActionListener()); // 数字2
        B_3.addActionListener(new MyActionListener()); // 数字3
        B_4.addActionListener(new MyActionListener()); // 数字4
        B_5.addActionListener(new MyActionListener()); // 数字5
        B_6.addActionListener(new MyActionListener()); // 数字6
        B_7.addActionListener(new MyActionListener()); // 数字7
        B_8.addActionListener(new MyActionListener()); // 数字8
        B_9.addActionListener(new MyActionListener()); // 数字9
        B_point.addActionListener(new MyActionListener()); // 小数点
        B_add.addActionListener(new MyActionListener()); // 加号
        B_dec.addActionListener(new MyActionListener()); // 减号
        B_mul.addActionListener(new MyActionListener()); // 乘号
        B_div.addActionListener(new MyActionListener()); // 除号
        B_leftBracket.addActionListener(new MyActionListener()); // 左括号
        B_rightBracket.addActionListener(new MyActionListener()); // 右括号
        B_equal.addActionListener(new MyActionListener()); // 等号
        // 扩展功能
        B_pi_e.addActionListener(new MyActionListener()); // 圆周率π或自然常数e
        B_CE.addActionListener(new MyActionListener()); // 清除输入输出
        B_backspace.addActionListener(new MyActionListener()); // 退格
        B_factorial.addActionListener(new MyActionListener()); // 阶乘
        B_square.addActionListener(new MyActionListener()); // 平方
        B_reciprocal.addActionListener(new MyActionListener()); // 倒数
        B_Exp.addActionListener(new MyActionListener()); // 指数函数
        B_Mod.addActionListener(new MyActionListener()); // 取余
        B_sqrt.addActionListener(new MyActionListener()); // 开根号
        B_pow.addActionListener(new MyActionListener()); // x的n次方
        B_log.addActionListener(new MyActionListener()); // 以10为底的对数
        B_ln.addActionListener(new MyActionListener()); // 以e为底的对数
        B_negation.addActionListener(new MyActionListener()); // 符号取反
        B_sin.addActionListener(new MyActionListener()); // 正弦函数
        B_cos.addActionListener(new MyActionListener()); // 余弦函数
        B_tan.addActionListener(new MyActionListener()); // 正切函数
        // 其他功能
        B_secondFunction.addActionListener(new MyActionListener()); // 按键第二功能
        B_interface.addActionListener(new MyActionListener()); // 界面切换
        B_DegRad.addActionListener(new MyActionListener()); // 弧度角度切换
        // 没用到的按钮先失能，设置按钮不可点击
        B_interface.setEnabled(false);
        B_F_E.setEnabled(false);
        B_MC.setEnabled(false);
    }

    private void setControl(JComponent component, int x, int y, int width, int height) {
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        gridBagConstraints.gridwidth = width;
        gridBagConstraints.gridheight = height;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        this.add(component, gridBagConstraints);
    }

    private class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 这里可以添加具体的按钮点击事件处理逻辑
            // 例如：
            if (e.getSource() == B_0) {
                S_input += "0";
                T_Eq.setText(S_input);
            } else if (e.getSource() == B_1) {
                S_input += "1";
                T_Eq.setText(S_input);
            }
            // 其他按钮的逻辑类似
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new CalculatorHMI1().setVisible(true);
        });
    }
}