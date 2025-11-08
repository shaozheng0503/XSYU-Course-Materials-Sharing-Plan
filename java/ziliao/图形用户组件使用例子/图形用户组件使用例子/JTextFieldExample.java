import javax.swing.*;
import java.awt.*;

public class JTextFieldExample extends JFrame{
	JTextField textField;
	JPasswordField passwordField;
	JLabel label1,label2;
	public JTextFieldExample(){
		super("文本框和密码文本框示例");
		Container container=getContentPane();
		container.setLayout(new FlowLayout());
		
		label1=new JLabel("用户名");
		textField=new JTextField("客人",10);
		textField.setFont(new Font("楷体",Font.BOLD,16));//设置字体
		textField.setHorizontalAlignment(SwingConstants.CENTER);//水平中间对齐
		
		label2=new JLabel("密码");
		passwordField=new JPasswordField("default key",10);
		passwordField.setEchoChar('@');//设置回显字符'@'
		
		container.add(label1);
		container.add(textField);
		container.add(label2);
		container.add(passwordField);
	    
	    pack();
	    setVisible(true);
	}
	public static void main(String args[]){
		JTextFieldExample jtfe=new JTextFieldExample();
		jtfe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}