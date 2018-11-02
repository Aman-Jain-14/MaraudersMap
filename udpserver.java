import java.io.*;
import java.net.*;

public class udpserver{
	DatagramSocket ds;
	DatagramPacket dp;

	String mssg;
	byte buff[];

	void receive()throws Exception{
		String mssg;
		ds = new DatagramSocket(8001);
		while(true){
			buff = new byte[1024];
			dp = new DatagramPacket(buff,buff.length);
			ds.receive(dp);
			mssg = new String(dp.getData(),0,0,dp.getLength());
			System.out.println("MESSAGE :: "+mssg);
			if(mssg.equals("end")) break;
		}
	}
	public static void main(String[] args)throws Exception {
		udpserver s = new udpserver();
		s.receive();
	}
}