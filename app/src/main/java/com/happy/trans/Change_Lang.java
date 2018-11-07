package com.happy.trans;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Change_Lang extends AppCompatActivity {

    Button selectKorean;
    Button selectEnglish;
    Button selectThai;
    Button selectViet;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_lang);

        SharedPreferences prefs = getSharedPreferences("AppName", Context.MODE_PRIVATE);

        String lang=prefs.getString("lang", "English");;
        String st_change_lang;

        selectChina=(Button)findViewById(R.id.select_button_China);
        selectEnglish=(Button)findViewById(R.id.select_button_English);
        selectThai=(Button)findViewById(R.id.select_button_Thai);
        selectViet=(Button)findViewById(R.id.select_button_Viet);
        tv_change_lang=(TextView)findViewById(R.id.changeLang);

        if(lang.equalsIgnoreCase("English")) st_change_lang="select language";
        else if(lang.equalsIgnoreCase("Viet")) st_change_lang="lựa chọn ngôn ngữ";
        else if(lang.equalsIgnoreCase("Thai")) st_change_lang="เลือกภาษา";
        else st_change_lang="语言选择";

        tv_change_lang.setText(st_change_lang);

        selectChina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lang_changed("China");
            }
        });

        selectEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lang_changed("English");
            }
        });

        selectThai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lang_changed("Thai");
            }
        });

        selectViet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lang_changed("Viet");
            }
        });

    }

    void lang_changed(String lang){

        SharedPreferences prefs = getSharedPreferences("AppName", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        int first=prefs.getInt("first_start",0);

        editor.putString("lang", lang);
        if(first==0) editor.putInt("first_start", 1);
        editor.apply();

        Intent intent=new Intent(Change_Lang.this, Select_Function.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    }
