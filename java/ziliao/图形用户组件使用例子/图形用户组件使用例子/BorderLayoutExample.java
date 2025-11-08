import javax.swing.*;
import java.awt.*;

public class BorderLayoutExample extends JFrame{
	JPanel panel1,panel2,panel3,panel4,panel5;
	public BorderLayoutExample(){
		super("边界布局管理");
		Container container=getContentPane();
		
		panel1=new JPanel();
		panel1.add(new JLabel("北",SwingConstants.CENTER));
		panel1.setBackground(Color.red);
	        
        panel2=new JPanel();
        panel2.add(new JLabel("西",SwingConstants.CENTER));
        panel2.setBackground(Color.orange);
                
        panel3=new JPanel();
        panel3.add(new JLabel("东",SwingConstants.CENTER));
        panel3.setBackground(Color.yellow);
                
        panel4=new JPanel();
        panel4.add(new JLabel("南",SwingConstants.CENTER));
        panel4.setBackground(Color.green);
     
        panel5=new JPanel();
        panel5.add(new JLabel("中",SwingConstants.CENTER));
        panel5.setBackground(Color.white);
               
        container.add(panel1,BorderLayout.NORTH);
        container.add(panel2,BorderLayout.WEST);
        container.add(panel3,BorderLayout.EAST);
        container.add(panel4,BorderLayout.SOUTH);
        container.add(panel5);
        
        pack();
        setVisible(true);
	}
	public static void main(String args[]){
		BorderLayoutExample ble=new BorderLayoutExample();
		ble.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
