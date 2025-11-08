import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class JMultiWindowsExample extends JFrame{
	JDesktopPane desktop;
	
	JMenuBar bar;
	JMenu menu1,menu2;
	JMenuItem item1,item2;
	
	Container container;
	public JMultiWindowsExample(){
		super("多文档用户界面示例");
		container=getContentPane();
		desktop=new JDesktopPane();
		
		container.add(desktop);
		setJMenuBar(getNewMenuBar());
		
		setSize(400,200);
		setVisible(true);
	}
	public JMenuBar getNewMenuBar(){
		bar=new JMenuBar();
		
		menu1=new JMenu("文件");
		
		item1=new JMenuItem("新建");
		item2=new JMenuItem("关闭");
		item1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(e.getSource()==item1){
				 desktop.add(getNewInternalFrame());
				}
			}
		});
		item2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(e.getSource()==item2)
				System.exit(0);
			}
		});
		menu2=new JMenu("说明");
		
		menu1.add(item1);
		menu1.add(item2);
		bar.add(menu1);
		bar.add(menu2);
		
		return bar;
	}
	public JInternalFrame getNewInternalFrame(){
		JInternalFrame frame=new JInternalFrame("窗口示例",true,true,true,true);
		frame.setSize(300,100);
		frame.add(new JTextArea(4,20));
		frame.setVisible(true);
		return frame;	
	}
	public static void main(String args[]){
		JMultiWindowsExample jmwe=new JMultiWindowsExample();
		jmwe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}