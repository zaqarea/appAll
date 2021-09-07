package com.example.StudentsQROrg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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
    ProgressBar progress_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTxtEmail = findViewById(R.id.editTxtEmail);
        editTxtPassword   = findViewById(R.id.editTxtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        progress_bar = findViewById(R.id.progress_bar);

        @SuppressLint("WrongConstant")
        SharedPreferences sharedPreferences = getSharedPreferences(FILE_CHECK_USER, Context.MODE_PRIVATE);
        String userData = sharedPreferences.getString("checkUser", "false");

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

                if (!editTxtEmail.getText().toString().isEmpty() && !editTxtPassword.getText().toString().isEmpty()) {
                    checkEmployee(email, password);
                }else {
                    Toast.makeText(Login.this, "لايمكن ان يكون احد الحقول فارغ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void checkEmployee(String email, String password) {

        progress_bar.setVisibility(View.VISIBLE);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.getCache().clear();
        String url = "https://css4dev.com/QrCustomers/checkUser.php";
        StringRequest strRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progress_bar.setVisibility(View.GONE);
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
                        sharedPref.edit().putString("checkUser", "true").apply();
                        SharedPreferences sharedPref2 = getSharedPreferences(FILE_CHECK_USER2, Context.MODE_PRIVATE);
                        sharedPref2.edit().putString("employeeId", id).apply();

                        Toast.makeText(Login.this, "welcome", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        progress_bar.setVisibility(View.GONE);
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
                        progress_bar.setVisibility(View.GONE);
                        error.printStackTrace();
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