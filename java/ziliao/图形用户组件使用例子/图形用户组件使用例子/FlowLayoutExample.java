import javax.swing.*;
import java.awt.*;

public class FlowLayoutExample extends JFrame{
	JPanel panel1,panel2;
	JButton button[];
	JTextField textField[];
	public FlowLayoutExample(){
		super("流布局示例");
		Container container=getContentPane();
		container.setLayout(new FlowLayout());
		container.setBackground(Color.yellow);
		panel1=new JPanel();
		FlowLayout layout1=new FlowLayout();
		layout1.setAlignment(FlowLayout.LEADING);
		layout1.setHgap(0);
		panel1.setLayout(layout1);
		panel1.setBackground(Color.blue);
		button=new JButton[5];
		for(int i=0;i<button.length;i++){
			button[i]=new JButton("按钮"+(i+1));
			panel1.add(button[i]);
		}
		
		panel2=new JPanel();
		panel2.setBackground(Color.red);
		FlowLayout layout2=new FlowLayout(FlowLayout.RIGHT,10,0);
		panel2.setLayout(layout2);
		textField=new JTextField[5];
		for(int i=0;i<textField.length;i++){
			textField[i]=new JTextField("文本  "+(i+1));
			panel2.add(textField[i]);
		}
		
		container.add(panel1);
		container.add(panel2);
		pack();	
	    setVisible(true);
	}
	
	public static void main(String args[]){
		FlowLayoutExample fle=new FlowLayoutExample();
		fle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}