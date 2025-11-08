import java.io.*;
import java.net.*;

class DatagramServerDemo{
	public static void main (String [] args) throws IOException{
		System.out.println ("Server starting ...\n");
		DatagramSocket s = new DatagramSocket (10000);
		byte [] data = new byte [100];
		DatagramPacket dgp = new DatagramPacket (data, data.length);
		while (true){
			s.receive (dgp);
			System.out.println (new String (data));
			s.send (dgp);
		}
	} }
