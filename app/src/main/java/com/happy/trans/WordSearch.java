package com.happy.trans;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WordSearch extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_search);

        Intent intent=new Intent(this.getIntent());
        final String emphasis1 = intent.getStringExtra("translate_result");

        SharedPreferences prefs = getSharedPreferences("AppName", Context.MODE_PRIVATE);
        String lang = prefs.getString("lang", "English");

        TextView result_Translate=(TextView)findViewById(R.id.result_translate1);
        result_Translate.setMovementMethod(new ScrollingMovementMethod());

        result_Translate.setText(emphasis1);

        Button search_button=(Button)findViewById(R.id.search1);

        if(lang.equalsIgnoreCase("English")) {
            search_button.setText("search");
        }

        else if(lang.equalsIgnoreCase("Thai")) {
            search_button.setText("การสืบค้น");
        }

        else if(lang.equalsIgnoreCase("Viet")) {
            search_button.setText("Tìm kiếm");
        }

        else {
            search_button.setText("检索");
        }

        search_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                TextView result_Emphasis=(TextView)findViewById(R.id.result_emphasis1);
                result_Emphasis.setMovementMethod(new ScrollingMovementMethod());

                EditText edit_text=(EditText)findViewById(R.id.edit_text1);
                String emphasis=emphasis1;
                String emphasis_word=edit_text.getText().toString();

                SharedPreferences prefs = getSharedPreferences("AppName", Context.MODE_PRIVATE);
                String type = prefs.getString("emphasis_type", "bold");

                emphasis =Emphasis(emphasis, emphasis_word, type);

                result_Emphasis.setText(Html.fromHtml(emphasis, 1));
            }

        });
    }

    public String Emphasis(String resource, String word_emphasis, String type) {
        String start;
        String end;

        if(type.equalsIgnoreCase("bold")){
            start="<b>";
            end="</b>";
        }

        else if(type.equalsIgnoreCase("color")){
            start="<font color='#EE0000'>";
            end="</font>";
        }

        else if(type.equalsIgnoreCase("underline")){
            start="<u>";
            end="</u>";
        }

        else{//기울임
            start="<i>";
            end="</i>";
        }

        String result=resource.replaceAll("(?i)"+word_emphasis, start+word_emphasis+end);
        return result;
    }

}
