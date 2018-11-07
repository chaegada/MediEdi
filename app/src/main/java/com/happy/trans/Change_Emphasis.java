package com.happy.trans;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Change_Emphasis extends AppCompatActivity {

    String saved;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_empahsis);

        TextView tvChangeEmphasis=(TextView)findViewById(R.id.tvChangeEmphasis);
        String st_change_emphasis;

        Button bold=(Button)findViewById(R.id.button_bold) ;
        Button color=(Button)findViewById(R.id.button_color);
        Button tilt=(Button)findViewById(R.id.button_tilt);
        Button underline=(Button)findViewById(R.id.button_underline);

        SharedPreferences prefs = getSharedPreferences("AppName", Context.MODE_PRIVATE);
        underline.setPaintFlags(underline.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        String lang = prefs.getString("lang", "English");

        if(lang.equalsIgnoreCase("English")) {
            bold.setText("bold");
            color.setText("red");
            tilt.setText("tilt");
            underline.setText("underline");
            saved="Saved";
            st_change_emphasis="Selecting the highlighting method";
        }

        else if(lang.equalsIgnoreCase("Thai")) {
            bold.setText("อย่างเข้มข้น");
            color.setText("สีแดง");
            tilt.setText("ความเอาใจใส่");
            underline.setText("เส้นใต้");
            saved="ได้แล้วครับ";
            st_change_emphasis="เลือกวิธีการเน้น";
        }

        else if(lang.equalsIgnoreCase("Viet")) {
            bold.setText("Một cách đậm đặc");
            color.setText("Màu đỏ");
            tilt.setText("Sự nghiêng nghiȇng ");
            underline.setText("Gạch dưới");
            saved="Đã lưu lại rồi.";
            st_change_emphasis="Sự lựa chọn phương pháp nhấn mạnh";
        }

        else {
            bold.setText("浓烈地");
            color.setText("红色");
            tilt.setText("倾斜");
            underline.setText("下划线");
            saved="保存了";
            st_change_emphasis="选择强调方法";
        }

        tvChangeEmphasis.setText(st_change_emphasis);

        bold.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
              apply("bold");
            }
        });
        color.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                apply("color");
            }
        });
        tilt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
               apply("tilt");
            }
        });
        underline.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                apply("underline");
            }
        });

    }

    public void apply(String type){
        SharedPreferences prefs = getSharedPreferences("AppName", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("emphasis_type", type);
        editor.apply();
        Toast.makeText(Change_Emphasis.this, saved, Toast.LENGTH_SHORT).show();
    }

}