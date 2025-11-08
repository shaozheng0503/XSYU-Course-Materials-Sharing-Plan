import java.io.*;
import java.net.*;
import java.util.*;

class ServerDemo{
	public static void main (String [] args) throws IOException{
		System.out.println ("Server starting...\n"); 
		ServerSocket server = new ServerSocket (10000);
		while (true)		{
			Socket s = server.accept ();
			System.out.println ("Accepting Connection...\n");
			new ServerThread (s).start ();
		}
	}
}

class ServerThread extends Thread{
	private Socket s;
	
	ServerThread (Socket s){
		this.s = s;
	}
	public void run (){
		BufferedReader br = null;
		PrintWriter pw = null;
		try{
			InputStreamReader isr;
			isr = new InputStreamReader (s.getInputStream ());
			br = new BufferedReader (isr);
			pw = new PrintWriter (s.getOutputStream (), true);
			Calendar c = Calendar.getInstance ();
			do{
				String cmd = br.readLine ();
				if (cmd == null)
					break;
				cmd = cmd.toUpperCase ();
				if (cmd.startsWith ("BYE"))
					break;
				if (cmd.startsWith ("DATE") || cmd.startsWith ("TIME"))
					pw.println (c.getTime ().toString ());
				if (cmd.startsWith ("DOM"))
					pw.println ("" + c.get (Calendar.DAY_OF_MONTH));
				if (cmd.startsWith ("DOW"))
					switch (c.get (Calendar.DAY_OF_WEEK))	{
						case Calendar.SUNDAY : pw.println ("SUNDAY");
						break;
						case Calendar.MONDAY : pw.println ("MONDAY");
						break;
						case Calendar.TUESDAY : pw.println ("TUESDAY");
						break;
						case Calendar.WEDNESDAY: pw.println ("WEDNESDAY");
						break;
						case Calendar.THURSDAY : pw.println ("THURSDAY");
						break;
						case Calendar.FRIDAY : pw.println ("FRIDAY");
						break;
						case Calendar.SATURDAY : pw.println ("SATURDAY");
					}
				if (cmd.startsWith ("DOY"))
					pw.println ("" + c.get (Calendar.DAY_OF_YEAR));
				if (cmd.startsWith ("PAUSE"))
					try{
						Thread.sleep (3000);
					}
					catch (InterruptedException e){}
			} while (true);
		}
		catch (IOException e){
			System.out.println (e.toString ());
		}
		finally{
			System.out.println ("Closing Connection...\n");
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
