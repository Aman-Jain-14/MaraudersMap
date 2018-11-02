import java.io.*;
import java.net.*;

public class client{
	public static void main(String[] args)throws Exception {
		Socket s = new Socket("10.62.0.255",8000);

		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter rf = new PrintWriter(s.getOutputStream(),true);

		String mssg;

		while(true){
			mssg = read.readLine();
			rf.println(mssg);
			if(mssg.equals("end")) break;
		}
	}
}