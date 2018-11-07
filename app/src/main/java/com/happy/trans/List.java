package com.happy.trans;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Set;

public class List extends AppCompatActivity {

     String st_button="";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        ArrayList<String> saved_items= getListFromLocal("emphasis_words");
        final ArrayList<String> items;

        if(saved_items==null) items = new ArrayList<String>();
        else items=saved_items;

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, items);

        final ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);
        final EditText edittext=(EditText)findViewById(R.id.editText);

        adapter.notifyDataSetChanged();

        Button addButton = (Button) findViewById(R.id.add);
        Button modifyButton = (Button) findViewById(R.id.modify);
        final Button deleteButton = (Button) findViewById(R.id.delete);
        final Button saveButton=(Button)findViewById(R.id.send);
        final LinearLayout list_layout=(LinearLayout)findViewById(R.id.list_layout);
        final LinearLayout button_layout=(LinearLayout)findViewById(R.id.linear_layout_button);

        button_layout.setVisibility(View.VISIBLE);
        list_layout.setVisibility(View.GONE);

        Intent intent=new Intent(this.getIntent());

        SharedPreferences prefs = getSharedPreferences("AppName", Context.MODE_PRIVATE);
        String lang = prefs.getString("lang", "English");

        final String empty;
        final String overlap, delete_string;
        final String add, modify, delete, save;

        if(lang.equalsIgnoreCase("English")) {
            add="add";
            modify="modify";
            delete="delete";
            save="save";
            empty="Please enter an item to emphasize";
            overlap="There is already the same item.";
            delete_string="Check the terms you want to delete and press the Delete button.";
        }

        else if(lang.equalsIgnoreCase("Thai")) {
            add="การเพิ่ม";
            modify="การแก้ไข";
            delete="การกำจัด";
            save="การเก็บ";
            empty="กรุณาระบุรายการที่จะเน้น";
            overlap="มีรายการเดียวกัน";
            delete_string="หลังจากตรวจสอบรายการที่คุณต้องการลบ กรุณากดปุ่มลบค่ะ";
        }

        else if(lang.equalsIgnoreCase("Viet")) {
            add="Cộng thêm";
            modify="Biên tập";
            delete="Sự loại bỏ";
            save="Lưu giữ";
            empty="Vui lòng nhập mục tiêu nhấn mạnh.";
            overlap="Đã có một hạng mục như vậy";
            delete_string="Sau khi kiểm tra hạng mục muốn xóa thì nhấn nút xóa.";
        }

        else {
            add="追加";
            modify="修整";
            delete="删除";
            save="贮藏";
            empty="请输入需要强调的项目";
            overlap="已经有相同的项目了";
            delete_string="确认想要删除的项目后按删除按钮.";
        }

        deleteButton.setText(delete);
        addButton.setText(add);
        modifyButton.setText(modify);

        addButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                list_layout.setVisibility(View.VISIBLE);
                edittext.setVisibility(View.VISIBLE);
                st_button="add";
                saveButton.setText(add);
                button_layout.setVisibility(View.GONE);
            }
        });


        modifyButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                list_layout.setVisibility(View.VISIBLE);
                edittext.setVisibility(View.VISIBLE);
                st_button="modify";
                saveButton.setText(modify);
                button_layout.setVisibility(View.GONE);
            }
        });


        deleteButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                int count, checked;
                count = adapter.getCount();
                list_layout.setVisibility(View.VISIBLE);
                edittext.setVisibility(View.GONE);
                deleteButton.setGravity(Gravity.CENTER);
                saveButton.setText(delete);
                st_button="delete";
                Toast.makeText(List.this, delete_string, Toast.LENGTH_LONG).show();
                button_layout.setVisibility(View.GONE);
            }

        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text=edittext.getText().toString();
                int count, checked;
                count = adapter.getCount();

                boolean bool_add=st_button.equalsIgnoreCase("add");
                boolean bool_modify=st_button.equalsIgnoreCase("modify");

                if(bool_add&bool_modify) {

                    if (text.equalsIgnoreCase("")) {
                        Toast.makeText(List.this, empty, Toast.LENGTH_SHORT).show();
                        edittext.requestFocus();
                        return;
                    }

                    for (int j = items.size() - 1; j >= 0; j--)
                        if (items.get(j).equals(text)) {
                            Toast.makeText(List.this, overlap, Toast.LENGTH_SHORT).show();
                            edittext.requestFocus();
                            return;
                        }
                }

                if (bool_add) {
                    items.add(text);
                } else if (bool_modify) {
                    if (count > 0) {
                        checked = listview.getCheckedItemPosition();
                        if (checked > -1 && checked < count) {
                            items.set(checked, text);
                        }
                    }
                }


                else if(st_button.equalsIgnoreCase("delete")){
                    if (count > 0) {
                        checked = listview.getCheckedItemPosition();
                        if (checked > -1 && checked < count) {
                            items.remove(checked);
                            listview.clearChoices();
                            adapter.notifyDataSetChanged();
                        }
                    }
                }

                adapter.notifyDataSetChanged();
                final ArrayList<String> sending_items = items;
                saveListInLocal(sending_items, "emphasis_words");
                button_layout.setVisibility(View.VISIBLE);
                list_layout.setVisibility(View.GONE);
                edittext.setText("");
            }
        });

    }

    public void saveListInLocal(ArrayList<String> list, String key) {

        SharedPreferences prefs = getSharedPreferences("AppName", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();

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
