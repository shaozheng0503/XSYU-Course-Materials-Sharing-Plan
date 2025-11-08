import java.io.*;

public class FileOutputDemo2{
			public static void main(String args[]){
				FileOutputStream out;
				PrintStream p;
				try{
					out = new FileOutputStream("myfile.txt");
					p = new PrintStream( out );
					p.println ("This is written to a file");
					p.close();
				}catch (Exception e){
					System.err.println ("Error writing to file");
				}
			}
}
