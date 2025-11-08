import javax.swing.*;
import java.awt.*;
public class JLabelExample extends JFrame{
	JLabel label1,label2,label3;
	ImageIcon icon1,icon2;
	public JLabelExample(){
	    super("标签示例");
	    Container container=getContentPane();
	    container.setLayout(new BorderLayout());
	    icon1=new ImageIcon("image/1.gif");
	    icon2=new ImageIcon("image/2.gif");
	    label1=new JLabel("用文本信息定义");
	    label1.setHorizontalAlignment(SwingConstants.LEFT);
	    
	    label2=new JLabel("用文本和图标定义",icon2,SwingConstants.RIGHT);
	    
	    label3=new JLabel();
	    label3.setIcon(icon1);
	    label3.setText("初始为空标签");
	    label3.setHorizontalAlignment(SwingConstants.RIGHT);
	    
	    container.add(label1,BorderLayout.NORTH);
	    container.add(label2,BorderLayout.CENTER);
	    container.add(label3,BorderLayout.SOUTH);
	    pack();
	    setVisible(true);	
	}
	public static void main(String args[]){
		JLabelExample jle=new JLabelExample();
		jle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}