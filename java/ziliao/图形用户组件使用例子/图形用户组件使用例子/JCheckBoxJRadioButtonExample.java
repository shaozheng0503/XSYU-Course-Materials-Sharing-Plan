import javax.swing.*;
import java.awt.*;
public class JCheckBoxJRadioButtonExample extends JFrame{
	String cityNames1[]={"北京","上海","重庆","南京","武汉","杭州"};
	String cityNames2[]={"西安","安徽","济南","成都","洛阳"};
	JLabel label1,label2;
	JCheckBox checkbox[];//定义复选框数组
	JRadioButton radiobutton[];//定义单选按钮数组
	ButtonGroup group;//定义按钮组
	public JCheckBoxJRadioButtonExample(){
	    super("复选框与单选按钮示例");
	    Container container=getContentPane();
	    container.setLayout(new FlowLayout());
	    
	    label1=new JLabel("城市1");
	    checkbox=new JCheckBox[6];
	    for(int i=0;i<checkbox.length;i++){
	    	checkbox[i]=new JCheckBox(cityNames1[i]);
	    	if(i%2==0)
	    	  checkbox[i].setSelected(true);//设置奇数号为选中状态
	    }	
	    
	    label2=new JLabel("城市2");
	    group=new ButtonGroup();//定义按钮组
	    radiobutton=new JRadioButton[5];
	    for(int i=0;i<radiobutton.length;i++){
	    	radiobutton[i]=new JRadioButton(cityNames2[i]);//创建单选按钮
	       	group.add(radiobutton[i]);//添加单选按钮到按钮组中
	    	if(i%2==0)
	    	  radiobutton[i].setSelected(true);//设置奇数号为选中
	    }
	    
	    container.add(label1);
	    for(int i=0;i<checkbox.length;i++){
	    	container.add(checkbox[i]);
	    }
	    container.add(label2);
	    for(int i=0;i<radiobutton.length;i++){
	    	container.add(radiobutton[i]);
	    }
	    
	    pack();
	    setVisible(true);
	}
	public static void main(String args[]){
		JCheckBoxJRadioButtonExample jcjbe=new JCheckBoxJRadioButtonExample();
		jcjbe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}