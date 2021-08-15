package com.example.StudentsQROrg;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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
                String str2 = result.getContents();
               // String[] str = str2.split("\\.");
                try {
                   // arrayList2.addAll(Arrays.asList(str));


                }catch (Exception ignored){
                }

                showDialogAddStatus();

            }else {
                Toast.makeText(ScanQR.this, "No Result", Toast.LENGTH_SHORT).show();
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    private void insertPresence(String studentId2, String enterance) {
        String url = "https://css4dev.com/QrCustomers/insertQr.php";
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
                params.put("employee_id", studentId2);
                params.put("is_enterance", enterance);
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

    public void showDialogAddStatus() {
        Dialog dialogAddStatus = new Dialog(ScanQR.this);
        dialogAddStatus.setContentView(R.layout.dialog_login);
        Button btnSignIn = dialogAddStatus.findViewById(R.id.btnSignIn);
        Button btnCancel = dialogAddStatus.findViewById(R.id.btnCancel);
//        CardView cardAddStatPrivate = dialogAddStatus.findViewById(R.id.cardAddStatPrivate);
//        Button btnFinish = dialogAddStatus.findViewById(R.id.btnFinishDialog);

        //  dialogAddStatus.getWindow().getAttributes().windowAnimations = R.style.DialogDown;
        dialogAddStatus.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogAddStatus.getWindow().getAttributes().width = WindowManager.LayoutParams.MATCH_PARENT;
        // dialogAddStatus.getWindow().getAttributes().gravity = 75;

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddStatus.dismiss();
                insertPresence(studentId, "0");
                Intent intent = new Intent(ScanQR.this, Stats.class);
                startActivity(intent);
                finish();
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddStatus.dismiss();
                insertPresence(studentId, "1");
                Intent intent = new Intent(ScanQR.this, Stats.class);
                startActivity(intent);
                finish();
            }
        });
//        cardAddStatPublic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mainActivity, AddPostMain.class);
//                startActivity(intent);
//                dialogAddStatus.dismiss();
//            }
//        });
//        btnFinish.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialogAddStatus.dismiss();
//            }
//        });
        dialogAddStatus.show();
    }
}