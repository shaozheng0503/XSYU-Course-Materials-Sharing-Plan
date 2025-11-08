import java.io.*;

public class FileOutputDemo {
public static void main(String args[]){
FileOutputStream out;
DataOutputStream p;
try {
out=new FileOutputStream("myfile.dat");
p = new DataOutputStream(out);
p.writeDouble(3.0);
p.writeDouble(4.0);
p.writeDouble(5.0);
p.close();
}catch(Exception e){
System.err.println ("Error writing to file");
}
	}
}
