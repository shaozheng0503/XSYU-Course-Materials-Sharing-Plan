import javax.swing.*;
import java.awt.*;

public class GridBagLayoutExample extends JFrame{
	JTextArea textarea;
	JTextField inputtext;
	JLabel label;
	JButton button;
	GridBagLayout layout;
	GridBagConstraints constraints;
	Container container;
	public GridBagLayoutExample(){
		super("网格包布局管理示例");
	    setGUIComponent();
	    pack();
	    setVisible(true);
	}
	public void setGUIComponent(){
		container=getContentPane();
		
		textarea=new JTextArea(10,15);
		textarea.setBackground(Color.yellow);
		label=new JLabel("输入");
		inputtext=new JTextField(10);
		button=new JButton("送出");
		
		layout=new GridBagLayout();//创建一个网格包布局管理器
		container.setLayout(layout);
		constraints=new GridBagConstraints();//创建约束条件	
                
        constraints.fill=GridBagConstraints.HORIZONTAL;
        constraints.gridx=0;//开始水平坐标位置为0
        constraints.gridy=0;//开始垂直坐标位置为0
        constraints.gridwidth=10;//占据行10个单元格位置
        constraints.gridheight=3;//占据列3个单元格位置
        layout.setConstraints(textarea,constraints);
        container.add(textarea);//添加文本区对象
        
        constraints.gridx=0;//开始水平坐标位置为0
        constraints.gridy=GridBagConstraints.RELATIVE;//垂直方向下一个位置
        constraints.gridwidth=2;
        constraints.gridheight=1;
        layout.setConstraints(label,constraints);
        container.add(label);	
        
        constraints.gridx=GridBagConstraints.RELATIVE;//水平方向的下一个位置
        constraints.gridy=3;//开始垂直坐标位置为3
        constraints.gridwidth=6;
        constraints.gridheight=1;
        layout.setConstraints(inputtext,constraints);
        container.add(inputtext);
        
        constraints.gridx=GridBagConstraints.RELATIVE;//水平方向的下一个位置
        constraints.gridy=3;//开始垂直坐标位置为3
        constraints.gridwidth=2;
        constraints.gridheight=1;
        layout.setConstraints(button,constraints);
        container.add(button);	
	}
	public static void main(String args[]){
		GridBagLayoutExample gble=new GridBagLayoutExample();
		gble.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}