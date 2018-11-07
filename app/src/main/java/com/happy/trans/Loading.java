package com.happy.trans;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class Loading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
        startLoading();
    }

    private void startLoading() {
        Handler handler = new Handler();

        String st_access;

        final InternetAccess in=new InternetAccess();
        boolean access=in.isOnline();

        SharedPreferences prefs = getSharedPreferences("AppName", Context.MODE_PRIVATE);
        String lang;

        if(prefs==null) lang="English";
        else lang= prefs.getString("lang", "English");

        if(lang.equalsIgnoreCase("English")) st_access="Please check your Internet connection.";
        else if(lang.equalsIgnoreCase("Viet")) st_access="Kiểm tra kết nối mạng nha";
        else if(lang.equalsIgnoreCase("Thai")) st_access="กรุณาตรวจสอบการเชื่อมต่ออินเทอร์เน็ตค่ะ";
        else st_access="请确认网络连接";

        if(access==true) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(Loading.this, Select_Function.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }, 2000);


        }
        else if(access==false) {
            Toast.makeText(this, st_access, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

}
