package com.happy.trans;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Translation extends AppCompatActivity {

    TextView tvResult;
    TextView tvOriginal;
    Button btEmphasis;
    static String translate_result;
    String lang;
    String medicine_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translation);

        SharedPreferences prefs = getSharedPreferences("AppName", Context.MODE_PRIVATE);
        lang = prefs.getString("lang", "English");

        tvResult = (TextView) findViewById(R.id.result_translation3);
        tvOriginal = (TextView) findViewById(R.id.original);
        btEmphasis = (Button) findViewById(R.id.button_emphasis);

        Intent intent = new Intent(this.getIntent());
        medicine_code = intent.getStringExtra("medicine_code");

        tvOriginal.setMovementMethod(new ScrollingMovementMethod());
        tvResult.setMovementMethod(new ScrollingMovementMethod());

        btEmphasis.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Translation.this, SelectSearch.class);
                intent.putExtra("lang", lang);
                intent.putExtra("translate_result", translate_result);
                startActivity(intent);
            }
        });

        final String target_lang;

        if (lang.equalsIgnoreCase("English")) {
            target_lang = "en";
            btEmphasis.setText("emphasis");
        } else if (lang.equalsIgnoreCase("Thai")) {
            target_lang = "th";
            btEmphasis.setText("การเน้นย้ำ");
        } else if (lang.equalsIgnoreCase("Viet")) {
            target_lang = "vi";
            btEmphasis.setText("Sự nhấn mạnh");
        } else {
            target_lang = "zh-CN";
            btEmphasis.setText("强调");
        }

        NaverTranslateTask asyncTask = new NaverTranslateTask();
        String sText = Medicine.searchMedicine(medicine_code);
        String edit_sText = sText.replaceAll("[a-zA-Z]", "");

        tvOriginal.setText(edit_sText);
        asyncTask.targetLang = target_lang;
        asyncTask.result_view = tvResult;

        asyncTask.execute(edit_sText);
    }

    public static class NaverTranslateTask extends AsyncTask<String, Void, String> {

        TextView result_view;
        String clientId = "";
        String clientSecret = "";
        String sourceLang = "ko";
        String targetLang;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String sourceText = strings[0];

            try {
                String text = URLEncoder.encode(sourceText, "UTF-8");
                String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
                URL url = new URL(apiURL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("X-Naver-Client-Id", clientId);
                con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
                String postParams = "source=" + sourceLang + "&target=" + targetLang + "&text=" + text;
                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(postParams);
                wr.flush();
                wr.close();
                int responseCode = con.getResponseCode();
                BufferedReader br;
                if (responseCode == 200) {
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                } else {
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                return response.toString();

            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Gson gson = new GsonBuilder().create();
            JsonParser parser = new JsonParser();
            JsonElement rootObj = parser.parse(s.toString())
                    .getAsJsonObject().get("message")
                    .getAsJsonObject().get("result");
            TranslatedItem items = gson.fromJson(rootObj.toString(), TranslatedItem.class);
            translate_result = items.getTranslatedText();
            result_view.setText(translate_result);
        }

        private class TranslatedItem {
            String translatedText;

            public String getTranslatedText() {
                return translatedText;
            }
        }
    }
}
