import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CardLayoutExample extends JFrame{
	JComboBox comboBox;
	String names[]={"面板1","面板2","面板3","面板4"};
	JPanel controlPanel,displayPanel;       //定义控制面板、显示面板
	JPanel panel1,panel2,panel3,panel4;     
	CardLayout layout;
	public CardLayoutExample(){
		super("卡片布局管理");
		Container container=getContentPane();
		
		displayPanel=new JPanel();           //定义显示面板
		layout=new CardLayout();             //定义卡片布局管理
		displayPanel.setLayout(layout);
		panel1=new JPanel();
		panel1.add(new JLabel("二级面板1"));
		panel2=new JPanel();
		panel2.add(new JLabel("二级面板2"));
		panel3=new JPanel();
		panel3.add(new JLabel("二级面板3"));
		panel4=new JPanel();
		panel4.add(new JLabel("二级面板4"));
		displayPanel.add("面板1",panel1);
		displayPanel.add("面板2",panel2);
		displayPanel.add("面板3",panel3);
		displayPanel.add("面板4",panel4);
		
		controlPanel=new JPanel();            //定义控制面板
		controlPanel.setBackground(Color.green);
		comboBox=new JComboBox(names);
		controlPanel.add(comboBox,BorderLayout.WEST);
		comboBox.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
			   CardLayout cl=(CardLayout)displayPanel.getLayout();
			   cl.show(displayPanel,(String)e.getItem());
			}
		});
		
		
		container.add(controlPanel,BorderLayout.WEST);
		container.add(displayPanel,BorderLayout.CENTER);
		setSize(300,200);
		setVisible(true);
	}
	public static void main(String args[]){
		CardLayoutExample cle=new CardLayoutExample();
		cle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}