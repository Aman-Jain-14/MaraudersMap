import java.io.*;
import java.net.*;

public class android{
	public static void main(String[] args)throws Exception {
	WifiConfiguration conf = new WifiConfiguration();
	String name = "I-ON";
    conf.SSID = "\"\"" + name + "\"\"";
    conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
    WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE); 
    int linkSpeed = wifiManager.getConnectionInfo().getRssi(); // SIGNAL STRENGTH of Wifi !!!
    wifiManager.addNetwork(conf);

    List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
    for( WifiConfiguration i : list ) {
        if(i.SSID != null && i.SSID.equals("\"\"" + name + "\"\"")) {
            try {
                wifiManager.disconnect();
                wifiManager.enableNetwork(i.networkId, true);
                System.out.print("i.networkId " + i.networkId + "\n");
                wifiManager.reconnect();               
                break;
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }           
    }
	}
}