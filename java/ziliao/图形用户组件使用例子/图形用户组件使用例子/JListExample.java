import javax.swing.*;
import java.awt.*;

public class JListExample extends JFrame{
	JList list;
	String cityNames[]={"北京","上海","重庆","南京","武汉","杭州"};
	public JListExample(){
		super("列表示例");
		Container container=getContentPane();
		container.setLayout(new FlowLayout());
		
		list=new JList(cityNames);
		list.setSelectionBackground(Color.blue);
		list.setSelectionForeground(Color.red);
	    list.setVisibleRowCount(4);
	    list.setSelectedValue(cityNames[1],true);
	    
	    container.add(new JScrollPane(list));
	    pack();
	    setVisible(true);
	}
	public static void main(String args[]){
		JListExample jle=new JListExample();
		jle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}