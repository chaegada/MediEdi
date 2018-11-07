package com.happy.trans;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Link extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.link);

        SharedPreferences prefs = getSharedPreferences("AppName", Context.MODE_PRIVATE);
        String lang = prefs.getString("lang", "English");
        final String link;


        TextView tv_link=(TextView)findViewById(R.id.tv_link);
        String st_link, st_Danuri, st_embassy, st_information;
        Button embassy=(Button)findViewById(R.id.select_button_embassy);

        if(lang.equalsIgnoreCase("English")) {
            st_link="Internet Site";
            st_Danuri="This is a multi-cultural family support portal in Korea that provides information on life, education information, employment information, multi-cultural understanding, and multi-cultural family support center.";
            st_embassy= "This web site is the U.S. Embassy in Korea.";
            st_information="This site is an e-government for foreigners. They can handle tasks such as electronic civil servants. In addition, information on convenience, immigration, naturalization, basic law, immigration, etc. can be obtained.";
            link="https://kr.usembassy.gov/";
            embassy.setBackground(ContextCompat.getDrawable(this,R.drawable.english_img));
        }
        else if(lang.equalsIgnoreCase("Viet")) {
            st_link="Trên web";
            st_Danuri="Thông tin sinh hoạt, thông tin giáo dục, thông tin giáo dục, thông tin tuyển dụng lao động, văn hóa đa văn hóa, trung tâm hỗ trợ gia đình văn hóa đa văn hóa là portal.";
            st_embassy= "Là đại sứ quán của người dùng đặt tại Hàn Quốc.";
            st_information="Trang web này là chính phủ điện tử dành cho người nước ngoài. Có thể xử lý các nhiệm vụ như tiếp dân điện tử. Ngoài ra, bạn cũng có thể nhận được các thông tin như cuộc sống, lưu trú nhập cảnh, nhập cảnh, chế độ luật cơ bản, di dân..";
            link="https://vnembassy-seoul.mofa.gov.vn/vi-vn/Trang/default.aspx";
            embassy.setBackground(ContextCompat.getDrawable(this,R.drawable.vietnam_img));
        }
        else if(lang.equalsIgnoreCase("Thai")) {
            st_link="เว็บไซต์";
            st_Danuri="ข้อมูลการดําเนินชีวิต, ข้อมูล, ข้อมูลการจ้างงาน, วัฒนธรรมทั้งหมดเป็นพอร์ทัลสนับสนุนของครอบครัวเกาหลีทั้งหมดที่ให้บริการข้อมูลเช่นศูนย์สนับสนุนครอบครัวและวัฒนธรรมค่ะ";
            st_embassy= "เป็นสถานทูตของผู้ใช้งานที่อยู่ในเกาหลีค่ะ";
            st_information="เว็บไซต์นี้เป็นรัฐบาลอิเล็กทรอนิกส์สําหรับชาวต่างชาติ สามารถจัดการงานเช่นสมาชิกอิเล็กทรอนิกส์ค่ะ คุณยังสามารถรับข้อมูลเกี่ยวกับวิถีชีวิต, การตรวจคนเข้าเมือง, กฎหมายพื้นฐาน, การอพยพ และอื่นๆ อีกมากมายเว็บไซต์นี้เป็นรัฐบาลอิเล็กทรอนิกส์สําหรับชาวต่างชาติ สามารถจัดการงานเช่นสมาชิกอิเล็กทรอนิกส์ค่ะ คุณยังสามารถรับข้อมูลเกี่ยวกับวิถีชีวิต, การตรวจคนเข้าเมือง, กฎหมายพื้นฐาน, การอพยพ และอื่นๆ อีกมากมาย";
            link="http://www.thaiembassy.org/seoul/ko/home";
            embassy.setBackground(ContextCompat.getDrawable(this,R.drawable.thailand_img));
        }
        else {
            st_link="网站";
            st_Danuri="提供生活信息,教育信息,就业招聘信息,多文化理解,多文化家庭支援中心等信息的大韩民国多文化家庭支持门户网站.";
            st_embassy= "这里是韩国使用者的大使馆.";
            st_information="该网站是为外国人准备的电子政府. 可以处理电子信访等业务. 还可以获得生活便利,出入境滞留,入籍,基础法制度,移民等信息.";
            link="http://kr.china-embassy.org/chn/";
            embassy.setBackground(ContextCompat.getDrawable(this,R.drawable.china_img));
        }

        tv_link.setText(st_link);

        TextView tv_Danuri=(TextView)findViewById(R.id.tv_Danuri);
        TextView tv_embassy=(TextView)findViewById(R.id.tv_embassy);
        TextView tv_information=(TextView)findViewById(R.id.tv_information);

        Button Danuri=(Button)findViewById(R.id.select_button_D);
        Button information=(Button)findViewById(R.id.select_button_information);

        tv_Danuri.setText(st_Danuri);
        tv_information.setText(st_information);
        tv_embassy.setText(st_embassy);

        Danuri.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.liveinkorea.kr/portal/main/intro.do"));
                startActivity(intent);
            }
        });

        information.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hikorea.go.kr/pt/main_kr.pt"));
                startActivity(intent);
            }
        });

        embassy.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(intent);
            }
        });


    }
}