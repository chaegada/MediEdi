package com.happy.trans;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Setting extends AppCompatActivity {

    Button change_lang;
    Button change_disease;
    Button change_emphasis;

    TextView tv_change_lang;
    TextView tv_change_disease;
    TextView tv_change_emphasis;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        SharedPreferences prefs = getSharedPreferences("AppName", Context.MODE_PRIVATE);
        String lang = prefs.getString("lang", "English");

        change_lang=(Button)findViewById(R.id.button_change_lang);
        change_disease=(Button)findViewById(R.id.button_change_disease);
        change_emphasis=(Button)findViewById(R.id.button_change_emphasis);

        tv_change_lang=(TextView) findViewById(R.id.change_lang);
        tv_change_disease=(TextView)findViewById(R.id.change_disease);
        tv_change_emphasis=(TextView)findViewById(R.id.change_emphasis);

        TextView tvSetting=(TextView)findViewById(R.id.tvSetting);

        if(lang.equalsIgnoreCase("English")) {
            tv_change_disease.setText("Set Highlight List");
            tv_change_lang.setText("Language change");
            tvSetting.setText("Setting");
            tv_change_emphasis.setText("Highlighting format settings");
        }

        else if(lang.equalsIgnoreCase("Thai")) {
            tv_change_disease.setText("การตั้งค่ารายการรายชื่อ");
            tv_change_lang.setText("การเปลี่ยนภาษา");
            tvSetting.setText("การตั้งราคา");
            tv_change_emphasis.setText("การตั้งค่ารูปแบบการเน้นย้ำ");
        }

        else if(lang.equalsIgnoreCase("Viet")) {
            tv_change_disease.setText("Lập danh sách nhấn mạnh");
            tv_change_lang.setText("Thay đổi ngôn ngữ");
            tvSetting.setText("Sự thiết lập");
            tv_change_emphasis.setText("Thiết lập hình thức nhấn mạnh");
        }

        else {
            tv_change_disease.setText("设置强调目录");
            tv_change_lang.setText("更改语言");
            tvSetting.setText("配置环境");
            tv_change_emphasis.setText("强调形式设置");
        }

        change_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Setting.this, Change_Lang.class);
                startActivity(intent);
            }
        });

        change_disease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Setting.this, List.class);
                startActivity(intent);
            }
        });

        change_emphasis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Setting.this, Change_Emphasis.class);
                startActivity(intent);
            }
        });
    }
}
