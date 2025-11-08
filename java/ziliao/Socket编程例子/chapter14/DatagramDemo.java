import java.io.*;
import java.net.*;

class DatagramDemo{
	public static void main (String [] args){
		String host = "localhost";
		if (args.length == 1)
			host = args [0];
		DatagramSocket s = null;
		try{
			s = new DatagramSocket ();
			byte [] buffer;
			buffer = new String ("Send me a datagram").getBytes ();
			InetAddress ia = InetAddress.getByName (host);
			DatagramPacket dgp = new DatagramPacket 
(buffer, buffer.length, ia, 10000);
			s.send (dgp);
			byte [] buffer2 = new byte [100];
			dgp = new DatagramPacket (buffer2, buffer.length, ia, 10000);
			s.receive (dgp);
			System.out.println (new String (dgp.getData ()));
		}
		catch (IOException e){
			System.out.println (e.toString ());
		}
		finally{
			if (s != null)
				s.close ();
		}
	} }
