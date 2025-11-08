public class DepositTest{
public static void main(String args[]){
	       DepositThread first,second;        //两个存款线程
		  Account myAccount=new Account(3000);
		  first=new DepositThread("this first thread",myAccount,2000);
		  second=new DepositThread("the second thread",myAccount,1500);
		  System.out.println("the account now is"+myAccount.get());
		  first.start();
		  second.start();                   //两个存款线程分别启动
		  try{
			first.join();                //等候此线程中止运行
			second.join();
		  }
		  catch(Exception e){
			System.out.println(e.toString());
		  }
		  System.out.println("the account after two thrad is "+myAccount.get());
	   }
}

class Account{                                  //用户的账户类
int currentaccount;
	
	   public Account(int currentaccount){
	       this.currentaccount=currentaccount;
	   }
	   public int get(){
		 return this.currentaccount;
	   }
public int get(String threadName){  //取存款余额等待时间是5s
		  System.out.println(threadName+"try to get...");
		  try{
			Thread.sleep(5000);
		  }
		  catch(Exception e){}
		  System.out.println(threadName+"get the account"+currentaccount);
		  return currentaccount;
	   }
	   public void set(String threadName,int newaccount){ //设置新的存款余额，时间//也是5s
		  System.out.println(threadName+"try to set..."); 
		  try{
			Thread.sleep(5000);
		  }
		  catch(Exception e){}
		  currentaccount=newaccount;
		  System.out.println(threadName+"set the account"+currentaccount);
	   }
public void deposit(String threadName,int amount){   //完成一次存款操作
		  System.out.println(threadName+"begin to deposit"+amount);
		  set(threadName,get(threadName)+amount);
	   }
}

class DepositThread extends Thread{                 //用户的线程类
	   String name;
	   Account myAccount;
int amount;
	
	   public DepositThread(String name,Account myAccount,int amount){
		 this.name=name;
		 this.myAccount=myAccount;
		 this.amount=amount;
}
public void run(){
		 myAccount.deposit(name,amount);
}
}
