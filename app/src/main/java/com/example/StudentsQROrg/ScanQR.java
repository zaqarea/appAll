package com.example.StudentsQROrg;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ScanQR extends AppCompatActivity {

    String studentId;
    ArrayList<String> arrayList2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_q_r);

        @SuppressLint("WrongConstant")
        SharedPreferences sharedPreferences = getSharedPreferences(Login.FILE_CHECK_USER2, Context.MODE_PRIVATE);
        studentId = sharedPreferences.getString("studentId2", "false");
        scanCode();
    }

    private void scanCode() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning code");
        integrator.initiateScan();
    }
    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
// @SuppressLint("SimpleDateFormat")
//        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
//        String currentDateAndTime = sdf.format(new Date());
        if (result != null){
            if (result.getContents() != null){
                String str2= result.getContents();
                String[] str = str2.split("\\.");
                try {
                    arrayList2.addAll(Arrays.asList(str));
                    insertPresence(studentId, arrayList2.get(0), arrayList2.get(1));
                }catch (Exception ignored){
                }
                Intent intent = new Intent(ScanQR.this, ListMyPresence.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(ScanQR.this, "No Result", Toast.LENGTH_SHORT).show();
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    private void insertPresence(String studentId2, String courseId, String lectureId) {
        String url = getResources().getString(R.string.syncAddress);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("responseFromAddProduct", response);
                    if (response.trim().contains("true")) {
                        Toast.makeText(ScanQR.this, "تم تسجيل الرمز بنجاح", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ScanQR.this, "عذرا حدث خطأ ما.. يرجى اعادة المحاولة", Toast.LENGTH_SHORT).show();
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Add Product Error", error.getMessage());
                Toast.makeText(ScanQR.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("studentId", studentId2);
                params.put("courseId", courseId);
                params.put("lectureId", lectureId);
                return params;
            }
        };
        {

//            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
//                    30000,
//                    0,
//                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    0,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(stringRequest);
        }


    }
}