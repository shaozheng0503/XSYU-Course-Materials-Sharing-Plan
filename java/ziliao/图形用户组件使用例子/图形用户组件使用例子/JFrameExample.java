import javax.swing.*;
import java.awt.*;
public class JFrameExample extends JFrame{
	ImageIcon image;
     public JFrameExample(){
     	super("用户定义窗口");
     	
     	image=new ImageIcon("image/1.gif");
     	setIconImage(image.getImage());
     	setSize(200,200);
     	setVisible(true);
     }
     public static void main(String args[]){
     	JFrameExample jfe=new JFrameExample();
     	jfe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     }
	
}