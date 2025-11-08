public class Test{
		public static void main(String argv[]){
			ShareData s=new ShareData();
			new Consumer(s).start();
			new Producer(s).start();
		}
}

class ShareData{
		private char c;
		private boolean writeable = true;  // 通知变量

		public synchronized void setShareChar(char c){
			if (!writeable){
   			try{       // 未消费等待
 			    wait();
   			}catch(InterruptedException e){}
  		}
  
  		this.c = c;   // 标记已经生产
 		writeable = false;
        notify();    // 通知消费者已经生产，可以消费
 	}
 
 	public synchronized char getShareChar(){
  		if (writeable){
   			try{      // 未生产等待
		    	wait();
   			}catch(InterruptedException e){}  
  		}
  		writeable = true;  // 标记已经消费
  		notify();    // 通知需要生产
  		return this.c;
 	}
}

class Producer extends Thread{  // 生产者线程
 	private ShareData s;
 
 	Producer(ShareData s){
  		this.s = s;
 	}
 	public void run(){
 	 	for (char ch = 'A'; ch <= 'Z'; ch++){
   			try{
    			Thread.sleep((int)Math.random() * 400);
   			}catch(InterruptedException e){}
   			s.setShareChar(ch);
   			System.out.println(ch + " producer by producer.");
  		}
 	}
}

class Consumer extends Thread{  // 消费者线程
 	private ShareData s;
 
 	Consumer(ShareData s){
  		this.s = s;
		}
 	public void run(){
  		char ch;
  
  		do{
   			try{
    			Thread.sleep((int)Math.random() * 400);
   			}catch(InterruptedException e){}
   			ch = s.getShareChar();
   			System.out.println(ch + " consumer by consumer.**");
  		}while (ch != 'Z');
 	}
}
