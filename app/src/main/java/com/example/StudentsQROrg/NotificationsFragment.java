package com.example.StudentsQROrg;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.StudentsQROrg.Adabter.AdabterNotifications;
import com.example.StudentsQROrg.Model.ModelNotifications;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NotificationsFragment extends Fragment {

    RecyclerView recyclerView;
    AdabterNotifications adabterNotifications;
    ArrayList<ModelNotifications> arrayList = new ArrayList<>();
    String userId;

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

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(Login.FILE_CHECK_USER2, Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("studentId2", "false");

        getAllNotificationsByUserId();

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

                                Log.d("saas1",id);
                                Log.d("saas2",Title);
                                Log.d("saas3",Body);

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
                            Toast.makeText(getActivity(), "error.getMessage()", Toast.LENGTH_SHORT).show();
                           // progressBar.setVisibility(View.GONE);
                            error.printStackTrace();
                        }
                    })
            {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("userId", userId);
                    return params;
                }
            };

            strRequest.setShouldCache(false);
            requestQueue.add(strRequest);
    }
}