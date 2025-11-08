import javax.swing.*;
import java.awt.*;

public class GridLayoutExample extends JApplet{
	JButton buttons[];	 
	GridLayout layout;
	public void init(){
	     layout=new GridLayout(4,3,20,10);
	     setLayout(layout);               //设置4行3列的网格布局
	     buttons=new JButton[10];
	     for(int i=0;i<buttons.length;i++){
	     	buttons[i]=new JButton("按钮"+(i+1));
	        add(buttons[i]);
	     }	
	}
	public static void main(String args[]){
		JFrame frame=new JFrame("网格布局管理器示例");
		GridLayoutExample gle=new GridLayoutExample();
		gle.init();
		frame.add(gle);		
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}