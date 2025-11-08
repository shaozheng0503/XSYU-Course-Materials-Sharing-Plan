import javax.swing.*;
import java.awt.*;

public class JPanelExample extends JFrame{
	JButton[] buttons;
	JPanel panel1;
	CustomPanel panel2;
	public JPanelExample(){
		super("面板示例");
		
	    Container container=getContentPane();
	    container.setLayout(new BorderLayout());
	    
		panel1=new JPanel(new FlowLayout());//创建一个流布局管理的面板
	    buttons=new JButton[4];
	    for(int i=0;i<buttons.length;i++){
	    	buttons[i]=new JButton("按钮 "+(i+1));
	    	panel1.add(buttons[i]);//添加按钮到面板panel1中
	    }
	    
	    panel2=new CustomPanel();
	    
	    container.add(panel1,BorderLayout.NORTH);
	    container.add(panel2,BorderLayout.CENTER);
	    
	    pack();
	    setVisible(true);
	}
	public static void main(String args[]){
		JPanelExample jpe=new JPanelExample();
		jpe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	//定义内部类CustomPanel
	class CustomPanel extends JPanel{
	
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawString("Welcome to Java Shape World",20,20);
			g.drawRect(20,40,130,130);
			g.setColor(Color.green);
			g.fillRect(20,40,130,130);
			g.drawOval(160,40,100,100);
			g.setColor(Color.orange);
            g.fillOval(160,40,100,100);
		}
		public Dimension getPreferredSize(){
			return new Dimension(200,200);			
		}
		
		
	}
}