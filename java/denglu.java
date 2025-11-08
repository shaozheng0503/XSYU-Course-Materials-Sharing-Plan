import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 登录窗口类
public class LoginWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    // 构造函数，初始化登录窗口
    public LoginWindow() {
        super("Login Window");

        // 设置布局管理器为BorderLayout
        setLayout(new BorderLayout());

        // 创建面板
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2));

        // 添加用户名和密码标签与字段
        loginPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        loginPanel.add(usernameField);

        loginPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        loginPanel.add(passwordField);

        // 添加登录按钮
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                if ("admin".equals(username) && "password".equals(password)) {
                    JOptionPane.showMessageDialog(LoginWindow.this, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(LoginWindow.this, "Invalid username or password.");
                }
            }
        });

        // 将按钮添加到面板底部
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // 添加登录面板到主窗口
        add(loginPanel, BorderLayout.CENTER);

        // 设置窗口属性
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 居中显示
        setVisible(true);
    }

    // 主方法，启动登录窗口
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new LoginWindow();
        });
    }
}
