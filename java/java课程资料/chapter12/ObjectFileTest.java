import java.io.*;
import java.util.*;

public class ObjectFileTest{
			public static void main(String[] args){
				try{
					Employee[] staff = new Employee[3];//创建数组对象
      		   		staff[0] = new Employee("Harry Hacker",35000,new Date(1989,10,1));
      		  		staff[1] = new Employee ("Carl Cracker", 75000,new Date(1987,12,15));
        			 	staff[2] = new Employee("Tony Tester", 38000,new Date(1990,3,15));
      				int i;
      		
      				System.out.println("Before writeObject:");
      				for(i=0;i<staff.length;i++)
      					staff[i].print();
          			ObjectOutputStream out = new ObjectOutputStream(
new FileOutputStream("employee.dat"));
         				out.writeObject(staff);     //写入数组数据
         				out.close();

         				ObjectInputStream in = new ObjectInputStream(
new FileInputStream("employee.dat"));
        				Employee[] newStaff = (Employee[])in.readObject();//读回数据
        	
         				for (i = 0; i < newStaff.length; i++)
            				newStaff[i].raiseSalary(100);
           			 System.out.println("After readObject:");
         				for (i = 0; i < newStaff.length; i++)
            				newStaff[i].print();
      			}
      			catch(Exception e){
      				System.out.print("Error: " + e);
        			 	System.exit(1);
      			}
   			}
}
class Employee implements Serializable{//员工类
			private String name;
   			private double salary;
   			private Date hireDay;
   	
			public Employee(String n, double s, Date d){
				name = n;
     		 	salary = s;
     		 	hireDay = d;
   			}
   			public Employee(){}
   			public void print(){
   				System.out.println(name + " " + salary + " " + hireYear());
   			}
   			public void raiseSalary(double byPercent){
   				salary *= 1 + byPercent / 100;
   			}
   			public int hireYear(){
   				return hireDay.getYear();
   			}
}
