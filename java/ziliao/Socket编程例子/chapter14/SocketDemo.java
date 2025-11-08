import java.io.*;
import java.net.*; 

class SocketDemo{
	public static void main (String [] args){
		String host = "localhost";
		if (args.length == 1)
			host = args [0];
		BufferedReader br = null;
		PrintWriter pw = null;
		Socket s = null;
		
		try{
			s = new Socket (host, 10000);
			InputStreamReader isr;
			isr = new InputStreamReader (s.getInputStream ());
			br = new BufferedReader (isr);
			pw = new PrintWriter (s.getOutputStream (), true);
			pw.println ("DATE");
			System.out.println (br.readLine ());
			pw.println ("PAUSE");
			pw.println ("DOW");
			System.out.println (br.readLine ());
			
			pw.println ("DOM");
			System.out.println (br.readLine ());
			pw.println ("DOY");
			System.out.println (br.readLine ());
		}
		catch (IOException e){
			System.out.println (e.toString ());
		}
		finally{
			try{
				if (br != null)
				br.close ();
				if (pw != null)
				pw.close ();
				if (s != null)
				s.close ();
			}
			catch (IOException e){}
		}
	} }
