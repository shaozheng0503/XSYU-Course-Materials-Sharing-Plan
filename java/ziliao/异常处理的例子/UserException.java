import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class UserException extends JFrame  implements ActionListener{         //定义主类
			Employee Emp;
			Label prompt1=new Label("请输入雇员姓名和工资初值：");
		    Label prompt2=new Label("请输入欲修改的工资：");
			TextField name,isal,nsal;
			JButton loginButton = new JButton("确定");
			String msg="";

			public UserException(){                           //构造方法中初始化界面
		Container container=getContentPane();
		container.setLayout(new FlowLayout());
			name=new TextField(5);
		     isal=new TextField(5);
		     nsal=new TextField(5);
		     container.add(prompt1);
		     container.add(name);
		     container.add(isal);
		     container.add(prompt2);
		     container.add(nsal);
		     container.add(loginButton);
		     loginButton.addActionListener(this);
		     pack();
	         setVisible(true);
	         System.out.println("界面构造完成并显示");
		}
public void CreateEmp(String en,double sa){   //创建Employee类对象
try{
Emp=new Employee(en,sa);            //用构造函数创建对象，可能抛出异常
msg=new String(Emp.toString());
}
catch(IllegalSalaryException ise){       //用catch捕捉非法工资异常
msg=new String(ise.toString());
System.out.println(msg);
}
}
public void ChangSal(double cs){              //修改员工工资
System.out.println("检查薪水及改动数据");
try{
				Emp.setEmpSalary(cs);         //调用修改函数修改工资，可能抛出两种异常
					msg=new String(Emp.toString());
				}
				catch(IllegalSalaryException ise){         //捕捉第一种非法工资异常
					msg=new String(ise.toString());
				}
				catch(IllegalSalaryChangeException isce){  //捕捉第二种非法改动异常
					msg=new String(Emp.toString()+isce.toString());
				}
			}
public void actionPerformed(ActionEvent evt) {
				String en;
				double es,cs;
System.out.println("开始检查数据");
				//用用户输入的雇员姓名和工资创建对象
					en=new String(name.getText());
					System.out.println(en);
					es=Double.valueOf(isal.getText()).doubleValue();
					System.out.println(es);
					CreateEmp(en,es);

                //用用户输入的新工资修改员工工资
					if(Emp!=null){
						cs=Double.valueOf(nsal.getText()).doubleValue();
						ChangSal(cs);
					}
					else
						msg=new String("请先输入雇员姓名工资并创建之");
						System.out.println(msg);
				}

		public static void main(String args[]){
		UserException ue=new UserException();
		ue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}