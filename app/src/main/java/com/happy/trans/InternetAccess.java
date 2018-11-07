package com.happy.trans;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.net.HttpURLConnection;
import java.net.URL;

public class InternetAccess extends AppCompatActivity {

    public static final String WIFI_STATE = "WIFE";
    public static final String MOBILE_STATE = "MOBILE";
    public static final String NONE_STATE = "NONE";
    public static final String CONNECTION_CONFIRM_CLIENT_URL = "http://clients3.google.com/generate_204";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private static class CheckConnect extends Thread{
        private boolean success;
        private String host;

        public CheckConnect(String host){
            this.host = host;
        }

        @Override
        public void run() {

            HttpURLConnection conn = null;
            try {
                conn = (HttpURLConnection)new URL(host).openConnection();
                conn.setRequestProperty("User-Agent","Android");
                conn.setConnectTimeout(1000);
                conn.connect();
                int responseCode = conn.getResponseCode();
                if(responseCode == 204) success = true;
                else success = false;
            }
            catch (Exception e) {
                e.printStackTrace();
                success = false;
            }
            if(conn != null){
                conn.disconnect();
            }
        }

        public boolean isSuccess(){
            return success;
        }

    }

    public static boolean isOnline() {
        CheckConnect cc = new CheckConnect(CONNECTION_CONFIRM_CLIENT_URL);
        cc.start();
        try{
            cc.join();
            return cc.isSuccess();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
