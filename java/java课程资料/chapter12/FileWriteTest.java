import java.io.*;

public class FileWriteTest{
			public static void main (String[] args){
				FileWriteTest t = new FileWriteTest();
				t.WriteMyFile();
			}
			void WriteMyFile(){
				try {
					FileWriter fw = new FileWriter("mydata.txt");
					PrintWriter out = new PrintWriter(fw);
					out.print("hi,this will be wirte into the file!");
					out.close();
					fw.close();
				}catch(IOException e){
					System.out.println("Uh oh, got an IOException error!");
					e.printStackTrace();
				}
			}
}
