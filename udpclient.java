import java.io.*;
import java.net.*;

public class udpclient{
	public static void main(String[] args)throws Exception {
	DatagramSocket ds;
	DatagramPacket dp;

	BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

	String mssg;
	byte buff[];

		ds = new DatagramSocket();
		while(true){
			buff = new byte[1024];
			System.out.println("ENTER MESSAGE TO SEND :: ");
			mssg = read.readLine();
			buff = mssg.getBytes();
			InetAddress address = InetAddress.getByName("255.255.255.255");
			dp = new DatagramPacket(buff,buff.length,address,8001);
			ds.send(dp);
			if(mssg.equals("end")) break;
		}
	}
}