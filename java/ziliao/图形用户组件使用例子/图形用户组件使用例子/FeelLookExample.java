import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class FeelLookExample extends JApplet implements ActionListener{
	private JLabel label;
	private JTextArea text;
	private JButton button1,button2,button3;
	private JPanel panel1;
	public void init(){
		Container container=getContentPane();	
		label=new JLabel("输入信息");
		text=new JTextArea(4,40);
		button1=new JButton("标准观感");          //定义选择标准按钮
		button2=new JButton("Unix观感");          //定义选择Unix观感按钮
		button3=new JButton("Windows观感");      //定义选择Window观感按钮
		button1.addActionListener(this);        
		button2.addActionListener(this);
		button3.addActionListener(this);
		panel1=new JPanel();
		panel1.add(button1);	panel1.add(button2);	panel1.add(button3);
		container.add(label,BorderLayout.NORTH);
		container.add(new JScrollPane(text),BorderLayout.CENTER);
		container.add(panel1,BorderLayout.SOUTH);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==button1){               //显示Java的跨平台标准观感
			showFeelAndLook(UIManager.getCrossPlatformLookAndFeelClassName());
		}else if(e.getSource()==button2){            //显示Unix观感
			showFeelAndLook("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		}else if(e.getSource()==button3){              //显示Window观感
			showFeelAndLook("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");	
}
	}
	public void showFeelAndLook(String lookValue){      //显示观感
		try{ 
UIManager.setLookAndFeel(lookValue);     //按指定名设置观感
			SwingUtilities.updateComponentTreeUI(this); //修改组件观感
		}catch(Exception e){
		JOptionPane.showMessageDialog(null,this,"修改观感失败",JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void main(String args[]){
		JFrame frame=new JFrame();
		FeelLookExample jae=new FeelLookExample();
		frame.setTitle("观感示例-");
		frame.add(jae);
		jae.init();
		frame.setSize(400,200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
