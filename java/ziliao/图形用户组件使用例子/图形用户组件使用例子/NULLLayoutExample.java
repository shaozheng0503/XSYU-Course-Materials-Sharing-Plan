import javax.swing.*;
import java.awt.*;

public class NULLLayoutExample extends JFrame{
	JButton button1,button2,button3,button4;
	JLabel label;
	public NULLLayoutExample(){
		super("null布局管理");
		Container c=getContentPane();
		c.setLayout(null);
		
		button1=new JButton("北");
		button1.setBounds(120,10,100,50);
		
		button2=new JButton("西");
		button2.setBounds(20,60,100,50);
		
		button3=new JButton("东");
		button3.setBounds(220,60,100,50);
		
		button4=new JButton("南");
		button4.setBounds(120,110,100,50);
		
		label=new JLabel("null布局管理的方向显示");
		label.setBounds(10,150,250,60);
		
		c.add(button1);
		c.add(button2);
		c.add(button3);
		c.add(button4);
		c.add(label);
		
		setSize(360,250);
		setVisible(true);
	}
	public static void main(String args[]){
		NULLLayoutExample nue=new NULLLayoutExample();
		nue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}