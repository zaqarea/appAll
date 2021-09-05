package com.example.StudentsQROrg;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListFragment extends Fragment {
    private TextView btnInviteFriend, btnStats, btnEditProfile, btnSettings;
    private Button btnLogOut;
    private TextView userNameProfile;
    private TextView txtViewChangeImage;
    private CircleImageView imageProfile;
    CardView cardEditProfile, cardStats;

    private MainActivity mainActivity;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        userNameProfile = view.findViewById(R.id.btnUserNameProfileFragment);
        imageProfile = view.findViewById(R.id.imageProfile);
        txtViewChangeImage = view.findViewById(R.id.txtViewChangeImage);

        @SuppressLint("WrongConstant")
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(Login.FILE_CHECK_USER2, Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("studentId2", "false");

        gerUserById(userId);

        imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(mainActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(mainActivity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
                }else {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "اختر صورة الملف الشخصي"), 5);
                }
            }
        });
        txtViewChangeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(mainActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(mainActivity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
                }else {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "اختر صورة الملف الشخصي"), 5);
                }
            }
        });
        userNameProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(mainActivity, ProfileUsers.class);
//                startActivity(intent);
            }
        });

        return view;
    }

    private void shareUrlApp() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String urlApp = "تطبيق صحتنا أهم يهتم بصحتك ويمكنك من استشارة الطبيب من منزلك مجانا حمله من هنا: \n https://play.google.com/store/apps/details?id=com.ourhealthfirst1.diet";
        intent.putExtra(Intent.EXTRA_TEXT, urlApp);
        startActivity(Intent.createChooser(intent, getString(R.string.app_name)));
        btnInviteFriend.setEnabled(true);
    }

    private void gerUserById(String userId) {
        RequestQueue requestQueue = Volley.newRequestQueue(requireActivity());
        requestQueue.getCache().clear();
        String url = "https://css4dev.com/QrCustomers/getUserDetailsById.php";
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

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject hit = null;
                        try {
                            hit = jsonArray.getJSONObject(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            String id = hit.getString("id");
                            String name = hit.getString("name");
                            String email = hit.getString("email");
                            String password = hit.getString("password");
                            String age = hit.getString("age");
                            String phone = hit.getString("phone");

                            userNameProfile.setText(name);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
                        Toast.makeText(requireActivity(), "error.getMessage()", Toast.LENGTH_SHORT).show();
//                            progressBar.setVisibility(View.GONE);
                            error.printStackTrace();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("userId",userId);
                return params;
            }
        };

        strRequest.setShouldCache(false);
        requestQueue.add(strRequest);
    }


}