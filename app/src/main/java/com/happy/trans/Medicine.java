package com.happy.trans;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.StrictMode;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.net.URL;
import java.net.URLEncoder;

public class Medicine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static String searchMedicine(String name){
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        String key = "L%2B%2BeR%2FYwxRHLHAFUrX%2Fn8kURTNYPt4Ba44UyufSd4vkAYpMAYuPUgOn%2Fa7wq84jd5ErGrfSO4BdBFVDdKFCTjg%3D%3D";

        String[] Operation = {"getSpcifyAgrdeTabooInfoList", "getPwnmTabooInfoList",
                "getCpctyAtentInfoList", "getMdctnPdAtentInfoList", "getOdsnAtentInfoList", "getDurPrdlstInfoList"};
        String[] typeName = {"특정연령대금기", "임부금기", "용량주의", "투여기간주의", "노인주의", "품목정보"};
        String itemName = name;

        boolean s = false;
        boolean t = false;
        boolean prohbt_content = false;

        String st = null;
        String tt = null;
        String printing = "약품명 : " + itemName;
        String prohbt_content_val = null;

        for (int i = 5; i >= 0; i--) {
            prohbt_content_val = "없음";

            try {
                URL url = new URL("http://apis.data.go.kr/1470000/DURPrdlstInfoService/" + URLEncoder.encode(Operation[i], "UTF-8")
                        + "?ServiceKey=" + key + "&typeName=" + URLEncoder.encode(typeName[i], "UTF-8") +
                        "&itemName=" + URLEncoder.encode(itemName, "UTF-8") + "&numOfRows=3&pageNo=1"); //검색 URL부분

                XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
                XmlPullParser parser = parserCreator.newPullParser();

                parser.setInput(url.openStream(), null);
                int parserEvent = parser.getEventType();

                while (parserEvent != XmlPullParser.END_DOCUMENT) {
                    switch (parserEvent) {
                        case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                            if (parser.getName().equalsIgnoreCase("STORAGE_METHOD")) {
                                s = true;
                            }
                            if (parser.getName().equalsIgnoreCase("VALID_TERM")) {
                                t = true;
                            }
                            if (parser.getName().equalsIgnoreCase("PROHBT_CONTENT")) {
                                prohbt_content = true;
                            }
                            break;

                        case XmlPullParser.TEXT://parser가 내용에 접근했을때
                            if (s) { //true일 때 태그의 내용을 저장.
                                st = parser.getText();
                                s = false;
                            }
                            if (t) {
                                tt = parser.getText();
                                t = false;
                            }
                            if (prohbt_content) {
                                prohbt_content_val = parser.getText();
                                prohbt_content = false;
                            }

                            break;
                    }
                    parserEvent = parser.next();
                }
                if (i == 5) {
                    if (st == null && tt == null) {
                        printing = "해당하는 약품이 존재하지 않습니다.\n다시 입력해주세요.";
                        break;
                    } else printing += "\n\n저장방법 : " + st + "\n\n유효기간: " + tt;
                } else {
                    printing += "\n\n" + typeName[i] + " : " + prohbt_content_val.replaceAll("\"", "").replaceAll("\\.", " ");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return printing;
    }
}
