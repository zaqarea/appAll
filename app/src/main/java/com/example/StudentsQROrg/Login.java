package com.example.StudentsQROrg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    public static final String FILE_CHECK_USER = "fileCheckUser";
    public static final String FILE_CHECK_USER2 = "fileCheckUser";
    TextInputEditText editTxtEmail, editTxtPassword;
    Button btnLogin;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTxtEmail = findViewById(R.id.editTxtEmail);
        editTxtPassword   = findViewById(R.id.editTxtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        toolbar = findViewById(R.id.toolbarLogin);

        setSupportActionBar(toolbar);

        @SuppressLint("WrongConstant")
        SharedPreferences sharedPreferences = getSharedPreferences(FILE_CHECK_USER, Context.MODE_PRIVATE);
        String userData = sharedPreferences.getString("studentId", "false");

        if (userData.equals("true")){
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTxtEmail.getText().toString().trim();
                String password = editTxtPassword.getText().toString().trim();
                checkStudent(email, password);
            }
        });
    }

//    private void checkStudent(String email, String password){
//    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//    String url = "https://css4dev.com/QrCustomers/checkUser.php";
//    final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null, new com.android.volley.Response.Listener<JSONObject>() {
//        @Override
//        public void onResponse(JSONObject response) {
//            Log.d("ffffffdfsfsa", url);
//            Log.d("ffffffdfsfsa2", response.toString());
//            JSONArray jsonArray = null;
//            try {
//                jsonArray = response.getJSONArray("Users");
//                Log.d("dsadaf45safsaf", jsonArray.length()+"");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            String id = null;
//            String name = null;
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject hit = null;
//                try {
//                    hit = jsonArray.getJSONObject(i);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    id = hit.getString("id");
//                    name = hit.getString("name");
//                    String age = hit.getString("age");
//                    String phone = hit.getString("phone");
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (jsonArray.length() > 0){
//                SharedPreferences sharedPref = getSharedPreferences(FILE_CHECK_USER, Context.MODE_PRIVATE);
//                sharedPref.edit().putString("studentId", "true").apply();
//                SharedPreferences sharedPref2 = getSharedPreferences(FILE_CHECK_USER2, Context.MODE_PRIVATE);
//                sharedPref2.edit().putString("studentId2", id).apply();
//
//                Toast.makeText(Login.this, "welcome", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(Login.this, MainActivity.class);
//                intent.putExtra("studentId", id);
//                startActivity(intent);
//                finish();
//            }else {
//                Toast.makeText(Login.this, "كلمة السر او البريد الالكتروني غير صحيح", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }, new com.android.volley.Response.ErrorListener() {
//        @Override
//        public void onErrorResponse(VolleyError error) {
//            Log.d("dsadafsafsaf", error.getMessage());
//            Toast.makeText(Login.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//        })
//    {
//        @Override
//        protected Map<String, String> getParams() {
//            Map<String, String> params = new HashMap<>();
//            params.put("email", email);
//            params.put("password",password);
//            return params;
//        }
//    };
//
//    request.setRetryPolicy(new RetryPolicy() {
//        @Override
//        public int getCurrentTimeout() {
//            return 500000;
//        }
//
//        @Override
//        public int getCurrentRetryCount() {
//            return 3;
//        }
//
//        @Override
//        public void retry(VolleyError error) throws VolleyError {
//
//        }
//    });
//    requestQueue.add(request);
//}

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void checkStudent(String email, String password) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.getCache().clear();
        String url = "https://css4dev.com/QrCustomers/checkUser.php";
        StringRequest strRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("res",response);
                try {
                    JSONObject res = new JSONObject(response);
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = res.getJSONArray("Users");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String id = null;
                    String name = null;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject hit = null;
                        try {
                            hit = jsonArray.getJSONObject(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            id = hit.getString("id");
                            name = hit.getString("name");
                            String age = hit.getString("age");
                            String phone = hit.getString("phone");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    if (jsonArray.length() > 0){
                        SharedPreferences sharedPref = getSharedPreferences(FILE_CHECK_USER, Context.MODE_PRIVATE);
                        sharedPref.edit().putString("studentId", "true").apply();
                        SharedPreferences sharedPref2 = getSharedPreferences(FILE_CHECK_USER2, Context.MODE_PRIVATE);
                        sharedPref2.edit().putString("studentId2", id).apply();

                        Toast.makeText(Login.this, "welcome", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        intent.putExtra("studentId", id);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(Login.this, "كلمة السر او البريد الالكتروني غير صحيح", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Login.this, "error.getMessage()", Toast.LENGTH_SHORT).show();
//                            progressBar.setVisibility(View.GONE);
//                            error.printStackTrace();
                    }
                })
        {
        @Override
        protected Map<String, String> getParams() {
            Map<String, String> params = new HashMap<>();
            params.put("email", email);
            params.put("password",password);
            return params;
        }
    };

        strRequest.setShouldCache(false);
        requestQueue.add(strRequest);
    }
}