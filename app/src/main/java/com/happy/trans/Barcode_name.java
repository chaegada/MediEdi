package com.happy.trans;

import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.StrictMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


public class Barcode_name extends AppCompatActivity {

    static boolean item_name = false;
    static String item_name_val = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static String searchDrugcode(String itemBarcode) {

        String drug_code = "0";

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        String key = "";

        try {
            URL url = new URL("http://apis.data.go.kr/1471057/MdcinPrductPrmisnInfoService/getMdcinPrductItem?serviceKey="
                    + key + "&pageNo=1&startPage=1&numOfRows=3&pageSize=3" + "&bar_code=" + URLEncoder.encode(itemBarcode, "UTF-8"));

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);
            int parserEvent = parser.getEventType();

            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                switch (parserEvent) {
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equalsIgnoreCase("ITEM_NAME")) {
                            item_name = true;
                        }
                        break;

                    case XmlPullParser.TEXT:
                        if (item_name) {
                            item_name_val = parser.getText();
                            item_name = false;
                        }
                        break;
                }
                parserEvent = parser.next();
            }
            if(item_name_val.indexOf("(")!= -1)
                item_name_val = item_name_val.substring(0,item_name_val.indexOf("("));
            else
                item_name_val = item_name_val.replaceAll("밀리그람","");

            drug_code = new getCode().execute("http://localapi.health.kr:8090/totalProduceY.localapi?search_word=" +
                    URLEncoder.encode(item_name_val, "UTF-8") + "&search_flag=all&sunb_count=&callback=&_=").get();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return drug_code;
    }

    private static class getCode extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... search) {
            String contents = "0";
            String result = "0";

            boolean check = FALSE;
            try {
                ConnectServer connectServer = new ConnectServer();
                contents = connectServer.requestGet(search[0]);
                check = TRUE;
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!check) return contents;
            try {
                JSONArray jarray = new JSONArray(contents);
                for (int i = 0; i < jarray.length(); i++) {
                    JSONObject jObject = null;
                    jObject = jarray.getJSONObject(i);
                    if(jObject.getString("drug_name").contains(item_name_val))
                        result = jObject.getString("drug_code");
                }
            }catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }
    }
}
