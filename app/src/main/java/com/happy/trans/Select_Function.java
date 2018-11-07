package com.happy.trans;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Select_Function extends AppCompatActivity {

    Button selectTranslation;
    Button selectHospital;
    Button selectLink;
    Button selectSetting;
    
    TextView tvTranslation;
    TextView tvHospital;
    TextView tvLink;
    TextView tvSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_function);

        selectTranslation=(Button)findViewById(R.id.select_button_translation);
        selectHospital=(Button)findViewById(R.id.select_button_hospital);
        selectLink=(Button)findViewById(R.id.select_button_link);
        selectSetting=(Button)findViewById(R.id.select_button_setting);

        tvTranslation=(TextView)findViewById(R.id.select_translation);
        tvHospital=(TextView)findViewById(R.id.select_hospital);
        tvLink=(TextView)findViewById(R.id.select_link);
        tvSetting=(TextView) findViewById(R.id.select_setting);
        
        SharedPreferences prefs = getSharedPreferences("AppName", Context.MODE_PRIVATE);
        int first=prefs.getInt("first_start",0);

        if(first==0){
            Intent intent=new Intent(Select_Function.this, Change_Lang.class);
            startActivity(intent);
            finish();
        }

        final String lang = prefs.getString("lang", "English");

        TextView textView=(TextView)findViewById(R.id.selected_lang);
        textView.setText(lang);


        if(s.equalsIgnoreCase("English")) {
            selectSetting.setText("Setting");
            selectTranslation.setText("Medical translation");
            selectLink.setText("Link for information");
            selectHospital.setText("Locate hospital");
        }

        else if(s.equalsIgnoreCase("Thai")) {
            selectSetting.setText("การตั้งค่าสิ่งแวดล้อม");
            selectTranslation.setText("การแปลยา");
            selectLink.setText("เว็บไซต์");
            selectHospital.setText("การหาตำแหน่งในโรงพยาบาล");
        }

        else if(s.equalsIgnoreCase("Viet")) {
            selectSetting.setText("Thiết lập hoàn cảnh");
            selectTranslation.setText(" phiên dịch");
            selectLink.setText("Trang web");
            selectHospital.setText("Tìm vị trí của bệnh viện");
        }

        else {
                  tvSetting.setText("配置环境");
            tvTranslation.setText("医药品翻译");
            tvLink.setText("多文化家庭之间");
            tvHospital.setText("医院定位");
        }

         selectTranslation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Select_Function.this, SelectTranslation.class);
                intent.putExtra("lang", lang);
                startActivity(intent);
            }
        });

        selectSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Select_Function.this, Setting.class);
                intent.putExtra("lang", lang);
                startActivity(intent);
            }
        });

        selectHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(Select_Function.this, HospitalMap.class);
                 intent.putExtra("lang", lang);
                startActivity(intent);
            }
        });

        selectLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Select_Function.this, Link.class);
                intent.putExtra("lang", lang);
                startActivity(intent);
            }
        });
    }
    }
    
