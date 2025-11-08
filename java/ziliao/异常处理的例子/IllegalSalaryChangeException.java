class IllegalSalaryChangeException extends Exception{//用户定义的工资变动不合法异常
		    private Employee m_ConcernedEmp;                //产生异常时的Employee类的引用
		    private double m_IllegalSalaryChange;

		    IllegalSalaryChangeException(Employee emp,double csal){ //构造函数
			  super("工资变动太大");
			  m_ConcernedEmp=emp;
			  m_IllegalSalaryChange=csal;
		    }
		    public String toString(){              //给出具体的错误信息
			  String s;

s="为雇员提供的工资变动不合法:雇员："+m_ConcernedEmp.getEmpName()
				+" 非法变动工资变化："+m_IllegalSalaryChange
				+"高于原工资的20％";
			  return s;
		    }
}
