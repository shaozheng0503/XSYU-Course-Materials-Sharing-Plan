class IllegalSalaryException extends Exception{ //用户定义的工资不合法异常
		    private Employee m_ConcernedEmp;		      //产生异常时的Employee类的引用
		    private double m_IllegalSalary;
	
	    IllegalSalaryException(Employee emp,double isal){ //构造函数
			  super("工资低于最低工资");
			  m_ConcernedEmp=emp;
		  m_IllegalSalary=isal;
		    }
		    public String toString(){           //给出具体的错误信息
			   String s;

			   s="为雇员提供的工资不合法：雇员："+m_ConcernedEmp.getEmpName()
				+" 非法工资："+m_IllegalSalary
				+" 低于最低工资数额800元";
			   return s;
		   }
}
