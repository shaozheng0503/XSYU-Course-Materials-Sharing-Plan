import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener
{
    private String[] Name1={"7","8","9","+","4","5","6","-","1","2","3","*","0","c","=","÷"};//C为清除
    private JButton Name[]=new JButton[Name1.length];//按钮
    private JTextField resultText = new JTextField("0.0");//结果文本行
    private String b="";
    public Calculator()
    {
        super("计算器");
        this.setLayout(null);
        //默认为flowLayout流布局的，设置为null即为清空布局管理器之后添加组件，常常是设置组件左上角坐标相对于容器左上角（0，0）的x,y值来确定组件的位置，即使更改容器大小也不会改变位置
        resultText.setBounds(20, 5, 255, 40);//文本框大小
        resultText.setHorizontalAlignment(JTextField.RIGHT);//设置水平对齐方式,右对齐.
        this.add(resultText);
        int x=20,y=55;
        for (int i=0;i<Name1.length;i++)
        {
            Name[i] = new JButton();
            Name[i].setText(Name1[i]);//显示文本
            Name[i].setBounds(x, y, 60, 40);
        if(x<215)
            {
                x+=65;
            }
        else
            {
                x = 20;
                y+=45;
            }
            this.add(Name[i]);
        }
        // 事件监听器
        for (int i = 0; i <Name1.length; i++) 
        {
        	Name[i].addActionListener(this);
        }
        this.setResizable(false);//resizeable值为false时，表示生成的窗体大小是由决定的
        this.setBounds(500, 200, 300, 300);//窗口大小
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);//窗口大小可以发生改变
    }
    public void actionPerformed(ActionEvent e)//动作事件处理，单击按钮，选择组合框
    {
        String label = e.getActionCommand(); //返回的是当前动作指向对象的名称
        if (label=="c"||label=="=")
        {
            if(label=="=")
            {
                String s[]=yunsuan(this.b);
                String result=Shed(s);
                this.b=result+"";
                resultText.setText(this.b);
            }
            else//按钮c为清零
            {
                     this.b="";
                     resultText.setText("0");      
            } 
        }
        else//显示所点的数字
        {
            this.b=this.b+label;
            resultText.setText(this.b);
        }
    }
    private String[] yunsuan(String str)
	{
		String s=""; 
		char a[]=new char[100];
		String jieguo[]=new String[100];
		int top=-1,j=0;
		for (int i=0;i<str.length();i++)
		{
			if ("0123456789.".indexOf(str.charAt(i))>=0)
			{
				s="";//作为承接的字符串，每次开始都要清空
                for (;i<str.length()&&"0123456789.".indexOf(str.charAt(i))>=0;i++)
                {
                    s=s+str.charAt(i);
                }
                i--;
                jieguo[j]=s;
                j++;
			}
			else if ("*÷".indexOf(str.charAt(i))>=0)//*÷高优先级优先入栈
			{
				if (top==-1)//当栈为空时
				{
					top++; 
					a[top]=str.charAt(i);//当前运算符入栈
					 
					
				}
				else
				{
					if ("*%÷".indexOf(a[top])>=0)//栈顶元素也为高优先级运算符
					{
						jieguo[j]=a[top]+"";//栈顶元素出栈加入后缀表达式
						j++;
						a[top]=str.charAt(i);
					} 					
					else if ("+-".indexOf(a[top])>=0)
					{
						top++;
						a[top]=str.charAt(i);
					}
				}
			}
			else if ("+-".indexOf(str.charAt(i))>=0)//栈顶元素为低优先级运算符，当前运算符入栈
			{
				if (top==-1)
				{
					top++;
					a[top]=str.charAt(i);
				}
				else
				{
					if ("*÷".indexOf(a[top])>=0)
					{
						jieguo[j]=a[top]+"";
						j++;
						a[top]=str.charAt(i);
					}
					else if ("+-".indexOf(a[top])>=0)
					{
						jieguo[j]=a[top]+"";
						j++;
						a[top]=str.charAt(i);
					}
				}
			}
		}
		for (;top!=-1;)//遍历结束后将栈中剩余元素依次出栈进入
		{
			jieguo[j]=a[top]+"";
			j++;
			top--;
		}
		return jieguo;
	}
    public String Shed(String str[])
    {
        String Shed[]=new String[100];
        int Top=-1;
        for (int i=0;str[i]!=null;i++)
        {
            if ("+-*÷".indexOf(str[i])<0)//遇到数字字符进栈
            {
                Top++;
                Shed[Top]=str[i];
            }
            if ("+-*÷".indexOf(str[i])>=0)//遇到运算符
            {
                double x,y,n;
                x=Double.valueOf(Shed[Top]);//顺序出栈两个数字字符串，并转换为double类型的数字
                Top--;
                y=Double.valueOf(Shed[Top]);
                Top--;
                if ("-".indexOf(str[i])>=0)//下步骤根据运算符来进行运算

                {
                    n=y-x;
                    Top++;
                    Shed[Top]=String.valueOf(n);//将运算结果重新入栈

                }
                if ("+".indexOf(str[i])>=0)
                {
                    n=y+x;
                    Top++;
                    Shed[Top]=String.valueOf(n);
                }
                if ("*".indexOf(str[i])>=0)
                {
                    n=y*x;
                    Top++;
                    Shed[Top]=String.valueOf(n);
                }
                if ("÷".indexOf(str[i])>=0)
                {
                    if (x==0)
                    {
                        String s="ERROR";
                        return s;
                    }
                    else
                    {
                        n=y/x;
                        Top++;
                        Shed[Top]=String.valueOf(n);
                    }
                }
            }
        }
        return Shed[Top];
    }
    public static void main(String arg[])
    {
    	Calculator a=new Calculator();
    }
}
