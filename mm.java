package com.example.mm_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;


public class MainActivity extends Activity {
 private Button button;
 private EditText time;
 private TextView finalResult;

 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);
  time = (EditText) findViewById(R.id.et_time);
  button = (Button) findViewById(R.id.btn_do_it);
  finalResult = (TextView) findViewById(R.id.tv_result);
  button.setOnClickListener(new View.OnClickListener() {
   @Override
   public void onClick(View v) {
    AsyncTaskRunner runner = new AsyncTaskRunner();
    String sleepTime = time.getText().toString();
    runner.execute(sleepTime);
    //Toast.makeText(getApplicationContext(), "this is my Toast message!!! =)",
    //     Toast.LENGTH_LONG).show();
   }
  });
 }

 @Override
 public boolean onCreateOptionsMenu(Menu menu) {
  // Inflate the menu; this adds items to the action bar if it is present.
  getMenuInflater().inflate(R.menu.main, menu);
  return true;
 }

 private class AsyncTaskRunner extends AsyncTask<String, String, String> {

  private String resp;
  Context context = null;
  
  @Override
  protected String doInBackground(String... params) {
   publishProgress("Sleeping..."); // Calls onProgressUpdate()
   try {
    // Do your long operations here and return the result
     
     DatagramSocket ds;
     DatagramPacket dp;
     
     ds = new DatagramSocket();
     
     byte buff[];
     String mssg;
 
     /*WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
    WifiInfo wInfo = wifiManager.getConnectionInfo();
    String macAddress = wInfo.getMacAddress();
    int linkSpeed = wifiManager.getConnectionInfo().getRssi();
    String link = Integer.toString(linkSpeed);*/
     
     WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
     wifiManager.setWifiEnabled(true);
    
    List<ScanResult> results = wifiManager.getScanResults();
      ScanResult bestSignal = null;
      for (ScanResult result : results) {
        if (bestSignal == null
            || WifiManager.compareSignalLevel(bestSignal.level, result.level) < 0)
          bestSignal = result;
          double exp = (27.55 - (20 * Math.log10(2412)) + Math.abs(result.level)) / 20.0;
          double dist = Math.pow(10.0, exp);
          mssg = String.format("%s Network has signal strength of : %d. and is at a distance %.2f meters ",
               result.SSID, result.level,dist);
          
      buff = new byte[1024];
      buff = mssg.getBytes();
      dp = new DatagramPacket(buff,buff.length,InetAddress.getByName("192.168.137.255"),8001);
      ds.send(dp);
      }
   
    //String mssg = String.format("%s networks found. %s is the strongest.",results.size(), bestSignal.SSID);

      //ds = new DatagramSocket();

        buff = new byte[1024];
        //buff = macAddress.getBytes();
        //buff = (params[0]).getBytes(); 
        mssg = "" + "\n\n";
        buff = mssg.getBytes();
        
         //InetAddress address = InetAddress.getByName("192.168.1.106");
        dp = new DatagramPacket(buff,buff.length,InetAddress.getByName("192.168.137.255"),8001);
        ds.send(dp);

      ds.close();

      
    int time = Integer.parseInt(params[0]);    
    // Sleeping for given time period
    Thread.sleep(time);
    resp = "Slept for " + time + " milliseconds";
       
    
    //Socket s = new Socket("10.62.0.127",8000);

  //BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
  //PrintWriter rf = new PrintWriter(s.getOutputStream(),true);
  
  /*

  //String mssg;

    //mssg = params[0];
    rf.println(macAddress);
    
  s.close();
    
    /*
    WifiConfiguration conf = new WifiConfiguration();
    conf.SSID = "\"\"" + "ZTE" + "\"\"";
    conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
    WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE); 
    wifiManager.addNetwork(conf);

    List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
    for( WifiConfiguration i : list ) {
        if(i.SSID != null && i.SSID.equals("\"\"" + "TinyBox" + "\"\"")) {
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
    }*/
    
   } catch (InterruptedException e) {
    e.printStackTrace();
    resp = e.getMessage();
   } catch (Exception e) {
    e.printStackTrace();
    resp = e.getMessage();
   }
   return resp;
  }

  /*
   * (non-Javadoc)
   * 
   * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
   */
  @Override
  protected void onPostExecute(String result) {
   // execution of result of Long time consuming operation
   finalResult.setText(result);
  }

  /*
   * (non-Javadoc)
   * 
   * @see android.os.AsyncTask#onPreExecute()
   */
  @Override   
  protected void onPreExecute() {
   // Things to be done before execution of long running operation. For
   // example showing ProgessDialog
  }

  /*
   * (non-Javadoc)
   * 
   * @see android.os.AsyncTask#onProgressUpdate(Progress[])
   */
  @Override
  protected void onProgressUpdate(String... text) {
   finalResult.setText(text[0]);
   // Things to be done while execution of long running operation is in
   // progress. For example updating ProgessDialog
  }
 }
}