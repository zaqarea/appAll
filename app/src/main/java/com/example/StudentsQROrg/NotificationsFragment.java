package com.example.StudentsQROrg;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.StudentsQROrg.Adabter.AdabterNotifications;
import com.example.StudentsQROrg.Model.ModelNotifications;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NotificationsFragment extends Fragment {

    ActivityResultLauncher<Intent> launcher;

    RecyclerView recyclerView;
    AdabterNotifications adabterNotifications;
    ArrayList<ModelNotifications> arrayList = new ArrayList<>();
    FloatingActionButton floating_action_button;
    private String employeeId;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public NotificationsFragment() {
        // Required empty public constructor
    }

    public static NotificationsFragment newInstance(String param1, String param2) {
        NotificationsFragment fragment = new NotificationsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        recyclerView = view.findViewById(R.id.recyclerNotifications);
        floating_action_button = view.findViewById(R.id.floating_action_button);

        @SuppressLint("WrongConstant")
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(Login.FILE_CHECK_USER2, Context.MODE_PRIVATE);
        employeeId = sharedPreferences.getString("employeeId", "false");

        getAllNotificationsByUserId();

        floating_action_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanCode();
            }
        });

//        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//            @Override
//            public void onActivityResult(ActivityResult result) {
//                Toast.makeText(getActivity(), "onActivityResult", Toast.LENGTH_SHORT).show();
//                IntentResult result2 = IntentIntegrator.parseActivityResult(0, result.getResultCode(), result.getData());
//                if (result2 != null){
//                    Toast.makeText(getActivity(), "okkkk", Toast.LENGTH_SHORT).show();
//                    if (result2.getContents() != null){
//                        Toast.makeText(getActivity(), "Result", Toast.LENGTH_SHORT).show();
//                        showDialogAddStatus();
//                    }else {
//                        Toast.makeText(getActivity(), "No Result", Toast.LENGTH_SHORT).show();
//                    }
//                }else {
//
//                }
//            }
//        });

        return view;
    }

    private void getAllNotificationsByUserId(){
            RequestQueue requestQueue = Volley.newRequestQueue(requireActivity());
            requestQueue.getCache().clear();
            String url = "https://css4dev.com/QrCustomers/selectNotificationByUserId.php";
            StringRequest strRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("res",response);
                    try {
                        JSONObject res = new JSONObject(response);
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = res.getJSONArray("notifications");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        arrayList.clear();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject hit = null;
                            try {
                                hit = jsonArray.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                assert hit != null;
                                String id = hit.getString("Id");
                                String Title = hit.getString("Title");
                                String Body = hit.getString("Body");

                                ModelNotifications modelNotifications = new ModelNotifications(id, Body, null, null, Title);
                                arrayList.add(modelNotifications);

                            }catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recyclerView.setHasFixedSize(true);
                        adabterNotifications = new AdabterNotifications(arrayList, getActivity());
                        recyclerView.setAdapter(adabterNotifications);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                     //       Toast.makeText(getActivity(), "error.getMessage()", Toast.LENGTH_SHORT).show();
                           // progressBar.setVisibility(View.GONE);
                            error.printStackTrace();
                        }
                    })
            {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("userId", employeeId);
                    return params;
                }
            };

            strRequest.setShouldCache(false);
            requestQueue.add(strRequest);
    }

    private void scanCode() {
        IntentIntegrator.forSupportFragment(NotificationsFragment.this)
                .setCaptureActivity(CaptureAct.class)
                .setOrientationLocked(false)
                .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
                .setBeepEnabled(true)
                .setPrompt("Scanning code")
                .initiateScan();

//     //   FragmentIntentIntegrator integrator2 = new FragmentIntentIntegrator(NotificationsFragment.this);
//        IntentIntegrator integrator = new IntentIntegrator(getActivity());
//        integrator.setCaptureActivity(CaptureAct.class);
//        integrator.setOrientationLocked(false);
//        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
//        integrator.setPrompt("Scanning code");
//       // new IntentIntegrator(getActivity()).initiateScan();
//        integrator.forSupportFragment(NotificationsFragment.this).initiateScan();
//      //  integrator.initiateScan();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Toast.makeText(getActivity(), "onActivityResult", Toast.LENGTH_SHORT).show();
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null){
            Toast.makeText(getActivity(), "okkkk", Toast.LENGTH_SHORT).show();
            if (result.getContents() != null){
                Toast.makeText(getActivity(), "Result", Toast.LENGTH_SHORT).show();
                showDialogAddStatus();
            }else {
                Toast.makeText(getActivity(), "No Result", Toast.LENGTH_SHORT).show();
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    private void insertPresence(String studentId2, String entrance) {
        String url = "https://css4dev.com/QrCustomers/employeeLogin.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("scscscsc2",response);
                try {
                    if (response.trim().contains("true")) {
                        Toast.makeText(getActivity(), "تم تسجيل الرمز بنجاح", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "عذرا حدث خطأ ما.. يرجى اعادة المحاولة", Toast.LENGTH_SHORT).show();
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "عذرا حدث خطأ ما.. يرجى اعادة المحاولة", Toast.LENGTH_SHORT).show();
                Log.d("scscscsc",error.getMessage());
                // Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("employeeId", studentId2);
                params.put("entrance", entrance);
                return params;
            }
        };
        {

//            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
//                    30000,
//                    0,
//                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            RequestQueue requestQueue = Volley.newRequestQueue(requireActivity());
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    0,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(stringRequest);
        }
    }

    public void showDialogAddStatus() {
        Dialog dialogAddStatus = new Dialog(getActivity());
        dialogAddStatus.setContentView(R.layout.dialog_login);
        Button btnSignIn = dialogAddStatus.findViewById(R.id.btnSignIn);
        Button btnLogOut = dialogAddStatus.findViewById(R.id.btnLogOut);

        dialogAddStatus.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogAddStatus.getWindow().getAttributes().width = WindowManager.LayoutParams.MATCH_PARENT;

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddStatus.dismiss();
                insertPresence(employeeId, "0");
                Intent intent = new Intent(getActivity(), Stats.class);
                startActivity(intent);
                // finish();
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddStatus.dismiss();
                insertPresence(employeeId, "1");
                Intent intent = new Intent(getActivity(), Stats.class);
                startActivity(intent);
                //    finish();
            }
        });

        dialogAddStatus.show();
    }
}