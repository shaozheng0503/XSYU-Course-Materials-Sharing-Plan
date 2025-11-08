import java.text.*;

public class OutputTest{
public static void main(String args[]){
		double x=3333.333;
		String numberfx,currencyfx,percentfx;
		NumberFormat numbernf=NumberFormat.getNumberInstance();
		NumberFormat currencynf=NumberFormat.getCurrencyInstance();
		NumberFormat percentnf=NumberFormat.getPercentInstance();
		numberfx=numbernf.format(x);
		currencyfx=currencynf.format(x);
		percentfx=percentnf.format(x);
		System.out.println(numberfx);
		System.out.println(currencyfx);
		System.out.println(percentfx); 
	 }
}
