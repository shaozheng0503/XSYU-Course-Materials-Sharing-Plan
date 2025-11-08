import java.io.*;
import java.text.*;

public class ReadDouble{
public static void main(String args[]){
InputStreamReader isr=new InputStreamReader(System.in);
		  BufferedReader br=new BufferedReader(isr);
		  String s="";
		  try{
		    	s=br.readLine();
		  }
		  catch(IOException ie){
		  	System.out.println("readLine err");
		  }
		  try{                        //通过子类DecimalFormat创建一个NumberFormat类对象
		  NumberFormat df=new DecimalFormat();
			double x=(df.parse(s)).doubleValue();
			System.out.println(x); 
		  }catch(ParseException e){     //若输入的字符无法转换，则抛出ParseException异常
			System.out.println(e.toString());	
		  }
	  }
}
