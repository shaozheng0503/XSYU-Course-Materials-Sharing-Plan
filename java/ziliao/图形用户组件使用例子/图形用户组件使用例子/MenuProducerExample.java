import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuProducerExample extends JApplet{
	JMenuBar menuBar;
	JMenu menu1,menu2;
	JMenuItem item1,item2,item3;
	JCheckBoxMenuItem item4,item5;
	JRadioButtonMenuItem item6,item7;
	
	JPopupMenu popupMenu;
	JMenuItem item8,item9;
    Container container;
    
	public void init(){
		setMenuGUI();//设置菜单
		setPopupMenuGUI();//设置弹出菜单
	
		this.getContentPane().addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				popupMenu.show(e.getComponent(),e.getX(),e.getY());
				}
				});
	}
	public void setMenuGUI(){//设置菜单
	   menuBar=new JMenuBar();//创建菜单条
	   
	   menu1=new JMenu("编辑");//定义一级菜单
	   item1=new JMenuItem("复制");//定义菜单项
	   item2=new JMenuItem("剪切");
	   item3=new JMenuItem("粘贴");
	   
	   menu2=new JMenu("高级");//定义二级菜单
	   item4=new JCheckBoxMenuItem("字符转换");//定义复选框菜单项
	   item5=new JCheckBoxMenuItem("密文处理");
	   item6=new JRadioButtonMenuItem("行选择");//定义单选按钮菜单项
	   item7=new JRadioButtonMenuItem("列选择");
	   
	   menu2.add(item4);//添加二级菜单的菜单项
	   menu2.add(item5);
	   menu2.add(item6);
	   menu2.add(item7);
	   
	   menu1.add(item1);//添加一级菜单的菜单项
	   menu1.add(item2);
	   menu1.add(item3);
	   menu1.add(menu2);//将二级菜单menu2添加到一级菜单中
	   
	   menuBar.add(menu1);//添加一级菜单至菜单条中.
	   
	   setJMenuBar(menuBar);
	}
	
	public void setPopupMenuGUI(){//设置弹出菜单
	  popupMenu=new JPopupMenu("弹出菜单");
	  item8=new JMenuItem("查找");
	  item9=new JMenuItem("替换");
	  popupMenu.add(item8);
	  popupMenu.add(item9);
	  
	}
	public static void main(String args[]){
		MenuProducerExample mpe=new MenuProducerExample();
	    JFrame frame=new JFrame("菜单示例");
	    frame.getContentPane().add(mpe,BorderLayout.CENTER);
	    mpe.init();
	    frame.setSize(300,300);
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}