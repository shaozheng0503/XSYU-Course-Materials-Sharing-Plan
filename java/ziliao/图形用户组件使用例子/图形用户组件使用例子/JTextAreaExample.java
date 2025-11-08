import javax.swing.*;
import java.awt.*;

public class JTextAreaExample extends JFrame{
	JLabel label1,label2;
	JTextArea textarea1,textarea2;
	
	public JTextAreaExample(){
		super("文本区示例");
		Container container=getContentPane();
		container.setLayout(new FlowLayout());
		
        label1=new JLabel("文本区1");
        label2=new JLabel("文本区2");
        
        textarea1=new JTextArea("欢迎来到Java世界",5,15);//创建文本区，行数5，列数为15
        textarea1.append("\nWelcome to Java World");//尾部添加字符串
        textarea1.setEditable(false);//设置不可写状态
        
        textarea2=new JTextArea("欢迎来到Java世界",4,15);//创建文本区，行数为4，列数为10
        textarea2.insert("\nWelcome to Java World",2);//第一个字后插入文本信息
        
        container.add(label1);
        container.add(textarea1);
        container.add(label2);
        container.add(new JScrollPane(textarea2));
        
        pack();
        setVisible(true);
 	}
 	public static void main(String args[]){
 		JTextAreaExample jtae=new JTextAreaExample();
 		jtae.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 	}
}