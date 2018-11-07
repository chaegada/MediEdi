package com.happy.trans;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class HospitalInfo extends Activity {
    @Override
    protected void onCreate(Bundle saverInstanceState){
        super.onCreate(saverInstanceState);
        setContentView(R.layout.hospital_info);

        Intent getHosInfo = getIntent();

        String TABLE[] = getHosInfo.getExtras().getStringArray("TABLE");
        String add_array="";

    }
}