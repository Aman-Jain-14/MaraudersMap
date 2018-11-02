import java.io.*;
import java.net.*;

public class server{
	public static void main(String[] args)throws Exception {
		ServerSocket s = new ServerSocket(8000);
		Socket ss = s.accept();

		BufferedReader read = new BufferedReader(new InputStreamReader(ss.getInputStream()));

		while(true){
			String mssg;
			mssg = read.readLine();
			if(mssg.equals("10.62.0.127")) System.out.println("Message meant for me !!");
			if(mssg.equals("end")) break;
		}
	}
}