package com.happy.trans;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class Highlight extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highlight);

        Intent intent=new Intent(this.getIntent());
        final String emphasis1 = intent.getStringExtra("translate_result");

        TextView result_Translate=(TextView)findViewById(R.id.result_translate);
        result_Translate.setText(emphasis1);

        TextView result_Emphasis=(TextView)findViewById(R.id.result_emphasis);

        result_Emphasis.setMovementMethod(new ScrollingMovementMethod());
        result_Translate.setMovementMethod(new ScrollingMovementMethod());

        String emphasis=emphasis1;

        ArrayList<String> emphasis_word=getListFromLocal("emphasis_words");

        SharedPreferences prefs = getSharedPreferences("AppName", Context.MODE_PRIVATE);
        String lang=prefs.getString("lang", "English");
        String type = prefs.getString("emphasis_type", "bold");
        String empty;

        if(emphasis_word==null){
            if(lang.equalsIgnoreCase("English")) empty="List is empty.";
            else if(lang.equalsIgnoreCase("Viet")) empty="Danh sách bị bỏ trống.";
            else if(lang.equalsIgnoreCase("Thai")) empty="ยังไม่มีรายการว่าง";
            else empty="目录空着";

            Toast.makeText(Highlight.this, empty, Toast.LENGTH_LONG).show();
        }

        else {
            int count = emphasis_word.size() - 1;
            for (int i = count; i >= 0; i--)
                emphasis = Emphasis(emphasis, emphasis_word.get(i), type);

            result_Emphasis.setText(Html.fromHtml(emphasis, 1));
        }
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

    public ArrayList<String> getListFromLocal(String key)
    {
        SharedPreferences prefs = getSharedPreferences("AppName", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);

    }

}


