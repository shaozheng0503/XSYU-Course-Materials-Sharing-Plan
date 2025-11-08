import javax.swing.*;
import java.awt.*;

public class JButtonExample extends JFrame{
	JButton button;
	ImageIcon icon1,icon2,icon3;
	public JButtonExample(){
		super("按钮");
		Container container=getContentPane();
		container.setLayout(new FlowLayout());
		
		icon1=new ImageIcon("image/1.gif");
		icon2=new ImageIcon("image/2.gif");
		icon3=new ImageIcon("image/3.gif");
		
		button=new JButton("命令按钮",icon1);
		button.setRolloverIcon(icon2);//设置翻滚按钮icon2
		button.setPressedIcon(icon3);//设置按下的按钮icon3
		button.setHorizontalAlignment(SwingConstants.LEFT);//设置左对齐；
		
		container.add(button);
		pack();
		setVisible(true);
	}
	public static void main(String args[]){
		JButtonExample jbe=new JButtonExample();
		jbe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}