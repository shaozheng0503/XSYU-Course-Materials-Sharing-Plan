import java.io.*;

public class FileInputDemo2{
public static void main(String args[]){
				try{
					FileInputStream fstream = new FileInputStream("myfile.txt");
					DataInputStream in = new DataInputStream(fstream);
					while(in.available() !=0)
						System.out.println (in.readLine());
					in.close();
				}catch(Exception e){
					System.err.println("File input error");
				}
			}
}
