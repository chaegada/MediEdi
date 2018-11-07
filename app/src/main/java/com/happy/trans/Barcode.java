package com.happy.trans;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Barcode extends Activity {

    String result_number;
    String no_barcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barcode);

        Button bt_barcode_scan = findViewById(R.id.bt_barcode_scan);
        String st_barcode;

        SharedPreferences prefs = getSharedPreferences("AppName", Context.MODE_PRIVATE);
        String lang = prefs.getString("lang", "English");

        if (lang.equalsIgnoreCase("English")) {
            st_barcode = "Start Barcode Scan";
            no_barcode="There is no corresponding medication information for the barcode.";
        }
        else if (lang.equalsIgnoreCase("Viet")) {
            st_barcode = "Bắt đầu vụscandal của cậu ấy";
            no_barcode="Không có thông tin thuốc men tương ứng với Ba Lan.";
        }
        else if (lang.equalsIgnoreCase("Thai")) {
            st_barcode = "เริ่มสแกน";
            no_barcode="ไม่มียาใดๆที่เกี่ยวข้องกับบาร์โค้ด";
        }
        else {
            st_barcode = "条形码扫描开始";
            no_barcode="不存在有关条形码的医药品信息.";
        }

        bt_barcode_scan.setText(st_barcode);
    }


    public void scanBarcode(View view) {
        new IntentIntegrator((Activity)this).initiateScan();
    }

    public void scanBarcodeCustomOptions(View view) {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                result_number=result.getContents();
                String bar_code_number;


                if(result_number.substring(1,3).equalsIgnoreCase("01")) bar_code_number=result_number.substring(4,15);
                else bar_code_number=result_number.substring(0,11);

                Barcode_name barcode_n=new Barcode_name();
               String medicine_code=barcode_n.searchDrugcode(bar_code_number);

               if(medicine_code.equalsIgnoreCase("0"))
                Toast.makeText(this, no_barcode, Toast.LENGTH_LONG).show();

               else{
                Intent intent = new Intent( Barcode.this, Translation.class);
                intent.putExtra("medicine_code", medicine_code);
                startActivity(intent);
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public static class ScanFragment extends Fragment {
        private String toast;

        public ScanFragment() {
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            displayToast();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_scan, container, false);
            Button scan = (Button) view.findViewById(R.id.scan_from_fragment);
            scan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    scanFromFragment();
                }
            });
            return view;
        }

        public void scanFromFragment() {
            IntentIntegrator.forFragment(this).initiateScan();
        }

        private void displayToast() {
            if(getActivity() != null && toast != null) {
                Toast.makeText(getActivity(), toast, Toast.LENGTH_LONG).show();
                toast = null;
            }
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if(result != null) {
                if(result.getContents() == null) {
                    toast = "Cancelled from fragment";
                } else {
                    toast = "Scanned from fragment: " + result.getContents();
                }

                displayToast();
            }
        }
    }
}
