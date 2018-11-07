package com.happy.trans;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;

public class HospitalList extends AppCompatActivity {
    private static final String TAG ="url check" ;
    static String KEY = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public static String HosLocations(String city, String distriction) throws IOException {

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        boolean lat = false;
        boolean lon = false;
        boolean name = false;
        boolean eryn = false;
        boolean addr = false;
        boolean tell = false;
        boolean time1s = false;
        boolean time1c = false;
        boolean time2s = false;
        boolean time2c = false;
        boolean time3s = false;
        boolean time3c = false;
        boolean time4s = false;
        boolean time4c = false;
        boolean time5s = false;
        boolean time5c = false;
        boolean time6s = false;
        boolean time6c = false;
        boolean time7s = false;
        boolean time7c = false;
        boolean time8s = false;
        boolean time8c = false;

        String printing = null;
        String hoslat = null;
        String hoslon = null;
        String hosname = null;
        String hoseryn = null;
        String hosaddr = null;
        String hostell = null;
        String hostime1s = null;
        String hostime1c = null;
        String hostime2s = null;
        String hostime2c = null;
        String hostime3s = null;
        String hostime3c = null;
        String hostime4s = null;
        String hostime4c = null;
        String hostime5s = null;
        String hostime5c = null;
        String hostime6s = null;
        String hostime6c = null;
        String hostime7s = null;
        String hostime7c = null;
        String hostime8s = null;
        String hostime8c = null;
            URL url = new URL("http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlMdcncListInfoInqire"
                    + "?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + KEY
                    + "&" + URLEncoder.encode("Q0", "UTF-8") + "=" + URLEncoder.encode(city, "UTF-8")
                    + "&" + URLEncoder.encode("Q1", "UTF-8") + "=" + URLEncoder.encode(distriction, "UTF-8")
                    + "&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")
                    + "&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("100", "UTF-8"));

            String stringURL = url.toString();

            try {

                XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
                XmlPullParser parser = parserCreator.newPullParser();

                parser.setInput(url.openStream(), "UTF-8");
                int parserEvent = parser.getEventType();


                while (parserEvent != XmlPullParser.END_DOCUMENT) {
                    switch (parserEvent) {
                        case XmlPullParser.START_TAG:
                            if (parser.getName().equalsIgnoreCase("wgs84Lat")) {
                                lat = true;
                            }
                            if (parser.getName().equalsIgnoreCase("wgs84Lon")) {
                                lon = true;
                            }
                            if (parser.getName().equalsIgnoreCase("dutyName")) {
                                name = true;
                            }
                            if (parser.getName().equalsIgnoreCase("dutyEryn")) {
                                eryn = true;
                            }
                            if (parser.getName().equalsIgnoreCase("dutyAddr")) {
                                addr = true;
                            }
                            if (parser.getName().equalsIgnoreCase("dutyTel1")) {
                                tell = true;
                            }
                            if (parser.getName().equalsIgnoreCase("dutyTime1s")) {
                                time1s = true;
                            }
                            if (parser.getName().equalsIgnoreCase("dutyTime1c")) {
                                time1c = true;
                            }
                            if (parser.getName().equalsIgnoreCase("dutyTime2s")) {
                                time2s = true;
                            }
                            if (parser.getName().equalsIgnoreCase("dutyTime2c")) {
                                time2c = true;
                            }
                            if (parser.getName().equalsIgnoreCase("dutyTime3s")) {
                                time3s = true;
                            }
                            if (parser.getName().equalsIgnoreCase("dutyTime3c")) {
                                time3c = true;
                            }
                            if (parser.getName().equalsIgnoreCase("dutyTime4s")) {
                                time4s = true;
                            }
                            if (parser.getName().equalsIgnoreCase("dutyTime4c")) {
                                time4c = true;
                            }
                            if (parser.getName().equalsIgnoreCase("dutyTime5s")) {
                                time5s = true;
                            }
                            if (parser.getName().equalsIgnoreCase("dutyTime5c")) {
                                time5c = true;
                            }
                            if (parser.getName().equalsIgnoreCase("dutyTime6s")) {
                                time6s = true;
                            }
                            if (parser.getName().equalsIgnoreCase("dutyTime6c")) {
                                time6c = true;
                            }
                            if (parser.getName().equalsIgnoreCase("dutyTime7s")) {
                                time7s = true;
                            }
                            if (parser.getName().equalsIgnoreCase("dutyTime7c")) {
                                time7c = true;
                            }
                            if (parser.getName().equalsIgnoreCase("dutyTime8s")) {
                                time8s = true;
                            }
                            if (parser.getName().equalsIgnoreCase("dutyTime8c")) {
                                time8c = true;
                            }

                            break;

                        case XmlPullParser.TEXT:
                            if (lat) {
                                hoslat = parser.getText();
                                lat = false;
                            }
                            if (lon) {
                                hoslon = parser.getText();
                                lon = false;
                            }
                            if (name) {
                                hosname = parser.getText();
                                name = false;
                            }
                            if (eryn) {
                                hoseryn = parser.getText();
                                eryn = false;
                            }
                            if (addr) {
                                hosaddr = parser.getText();
                                addr = false;
                            }
                            if (tell) {
                                hostell = parser.getText();
                                tell = false;
                            }
                            if (time1s) {
                                hostime1s = parser.getText();
                                time1s = false;
                            }
                            if (time1c) {
                                hostime1c = parser.getText();
                                time1c = false;
                            }
                            if (time2s) {
                                hostime2s = parser.getText();
                                time2s = false;
                            }
                            if (time2c) {
                                hostime2c = parser.getText();
                                time2c = false;
                            }
                            if (time3s) {
                                hostime3s = parser.getText();
                                time3s = false;
                            }
                            if (time3c) {
                                hostime3c = parser.getText();
                                time3c = false;
                            }
                            if (time4s) {
                                hostime4s = parser.getText();
                                time4s = false;
                            }
                            if (time4c) {
                                hostime4c = parser.getText();
                                time4c = false;
                            }
                            if (time5s) {
                                hostime5s = parser.getText();
                                time5s = false;
                            }
                            if (time5c) {
                                hostime5c = parser.getText();
                                time5c = false;
                            }
                            if (time6s) {
                                hostime6s = parser.getText();
                                time6s = false;
                            }
                            if (time6c) {
                                hostime6c = parser.getText();
                                time6c = false;
                            }
                            if (time7s) {
                                hostime7s = parser.getText();
                                time7s = false;
                            }
                            if (time7c) {
                                hostime7c = parser.getText();
                                time7c = false;
                            }
                            if (time8s) {
                                hostime8s = parser.getText();
                                time8s = false;
                            }
                            if (time8c) {
                                hostime8c = parser.getText();
                                time8c = false;
                            }
                            break;
                    }
                    printing = printing + "$";
                    parserEvent = parser.next();
                }


                if (hoslat == null && hoslon == null &&
                        hosname == null && hoseryn == null &&
                        hosaddr == null && hostell == null &&
                        hostime1s == null && hostime1c == null &&
                        hostime2s == null && hostime2c == null &&
                        hostime3s == null && hostime3c == null &&
                        hostime4s == null && hostime4c == null &&
                        hostime5s == null && hostime5c == null &&
                        hostime6s == null && hostime6c == null &&
                        hostime7s == null && hostime7c == null &&
                        hostime8s == null && hostime8c == null
                        ) {
                    System.out.println("병원자료 없음");
                } else {
                    printing = hoslat + "#" + hoslon + "#" +
                            hosname + "#" + hoseryn + "#" +
                            hosaddr + "#" + hostell + "#" +
                            hostime1s + "#" + hostime1c + "#" +
                            hostime2s + "#" + hostime2c + "#" +
                            hostime3s + "#" + hostime3c + "#" +
                            hostime4s + "#" + hostime4c + "#" +
                            hostime5s + "#" + hostime5c + "#" +
                            hostime6s + "#" + hostime6c + "#" +
                            hostime7s + "#" + hostime7c + "#" +
                            hostime8s + "#" + hostime8c + "#";
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        return printing;
    }
}

