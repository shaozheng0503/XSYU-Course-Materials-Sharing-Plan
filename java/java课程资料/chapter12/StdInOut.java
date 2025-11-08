import java.io.*;

public class StdInOut{
	  public static void main(String args[]){
		char c='a';
		int i,count=0;
		byte buffer[]=new byte[100];
		
		System.out.print("Input a char:");
		try{
			c=(char)System.in.read();   //输入一个字节并转化为字符c
			System.in.skip(2);         //跳过输入字符后的回车
		}
		catch(IOException ioe){
			System.err.println(ioe.toString());
		}
		System.out.println(c);
		
		System.out.print("Input a String:");
		try{
			count=System.in.read(buffer); //输入若干字节到字节数组buffer
		}
		catch(IOException ioe){
			System.err.println(ioe.toString());
		}
		for(i=0;i<=count-1;i++)
			System.out.print((char)buffer[i]);
	  }
}
