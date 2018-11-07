package com.happy.trans;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectSearch extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_search);

        TextView setting=(TextView)findViewById(R.id.setting);
         TextView tv_word_search=(TextView)findViewById(R.id.search_word);
        TextView tv_list_search=(TextView)findViewById(R.id.search_list);
        Button word_search=(Button)findViewById(R.id.button_search_word);
        Button list_search=(Button)findViewById(R.id.button_search_list);


        Intent intent=new Intent(this.getIntent());
        final String s=intent.getStringExtra("translate_result");
        
          SharedPreferences prefs = getSharedPreferences("AppName", Context.MODE_PRIVATE);
        String lang = prefs.getString("lang", "English");

        if(lang.equalsIgnoreCase("English")) {
          word_search.setText("Search for words");
          list_search.setText("Search for list");
          setting.setText("Select highlighting method");
        }

        else if(lang.equalsIgnoreCase("Thai")) {
            word_search.setText("การค้นหาคำศัพท์โดยใช้คำศัพท์");
            list_search.setText("การค้นหารายชื่อ");
            setting.setText("เลือกวิธีการเน้นย้ำ");
        }

        else if(lang.equalsIgnoreCase("Viet")) {
            word_search.setText("Tìm kiếm từ ngữ");
            list_search.setText("Tìm kiếm thông qua danh mục");
            setting.setText("Lựa chọn phương pháp nhấn mạnh");
        }

        else {
           tv_word_search.setText("用单词搜索");
            tv_list_search.setText("列表搜索");
            setting.setText("选择强调方法");
        }


        word_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SelectSearch.this, WordSearch.class);
                intent.putExtra("translate_result", s);
                intent.putExtra("lang", lang);
                startActivity(intent);
            }
        });

        list_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SelectSearch.this, Highlight.class);
                intent.putExtra("translate_result", s);
                intent.putExtra("lang", lang);
                startActivity(intent);
            }
        });

    }

    }

