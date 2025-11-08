class Employee{					       //Employee类
		String m_EmpName;
		double m_EmpSalary;               	  //雇员的姓名和工资

		Employee(String name,double initsal)throws IllegalSalaryException{     
//雇员类的构造函数，抛出异常
			m_EmpName=new String(name);
			if(initsal<800)
				throw(new IllegalSalaryException(this,initsal));
			m_EmpSalary=initsal;
	}
	public String getEmpName(){
			return m_EmpName;
	}
	public double getEmpSalary(){
			return m_EmpSalary;
	}
		public boolean setEmpSalary(double newsal)             //雇员工资修改函数
			throws IllegalSalaryException,IllegalSalaryChangeException
		{                                                      //抛出两个异常
			if(newsal<800)
				throw(new IllegalSalaryException(this,newsal));
			else if(Math.abs(newsal-getEmpSalary())/getEmpSalary()>=0.2)
				throw(new IllegalSalaryChangeException(this,newsal-getEmpSalary()));
			else{
				m_EmpSalary=newsal;
				return true;
			}
		}
		public String toString(){                              //给出类实例的信息
			String s;
			s="姓名："+m_EmpName+" 工资："+m_EmpSalary;
			return s;
		}
}
