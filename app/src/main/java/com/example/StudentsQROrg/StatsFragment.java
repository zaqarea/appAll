package com.example.StudentsQROrg;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.StudentsQROrg.Adabter.AdabterStatsForDay;
import com.example.StudentsQROrg.Model.ModelStats;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StatsFragment extends Fragment {

    TabLayout tabLayout;
    RecyclerView recyclerView;
    AdabterStatsForDay adabterStatsForDay;
    ArrayList<ModelStats> arrayList = new ArrayList<>();
    TextView txtViewDay;

    private String employeeId;
    String currentMonth;

    public StatsFragment() {
        // Required empty public constructor
    }


    public static StatsFragment newInstance(String param1, String param2) {
        StatsFragment fragment = new StatsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_stats, container, false);

        tabLayout = v.findViewById(R.id.tabLayoutListStats);
        recyclerView = v.findViewById(R.id.recyclerStats);
        txtViewDay = v.findViewById(R.id.txtViewDay);

        OffsetDateTime currentDate = OffsetDateTime.now(ZoneOffset.UTC );
        currentMonth = currentDate.getMonthValue()+"";

        @SuppressLint("WrongConstant")
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(Login.FILE_CHECK_USER2, Context.MODE_PRIVATE);
        employeeId = sharedPreferences.getString("employeeId", "false");

        if (!employeeId.equals("false")) {
            getWorkScheduleByEmployeeId(employeeId);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0){
                    if (!employeeId.equals("false")) {
                        getWorkScheduleByEmployeeId(employeeId);
                    }
                }else if (tab.getPosition() == 1){

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return v;
    }

    private void getWorkScheduleByEmployeeId(String employeeId){
        RequestQueue requestQueue = Volley.newRequestQueue(requireActivity());
        requestQueue.getCache().clear();
        String url = "https://css4dev.com/QrCustomers/selectWorkSchedule.php";
        StringRequest strRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("resdddd",response);
                try {
                    JSONObject res = new JSONObject(response);
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = res.getJSONArray("absenceDays");
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
                            String exsitDate = hit.getString("exsitDate");
                            String entranceDate = hit.getString("entranceDate");
                            String isExsist = hit.getString("isExsist");

                            Log.d("vvvvvvvv",isExsist);
                            Log.d("vvvvvvvv2",entranceDate);

                            ModelStats modelStats = new ModelStats(exsitDate, entranceDate, isExsist);
                            arrayList.add(modelStats);

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setHasFixedSize(true);
                    adabterStatsForDay = new AdabterStatsForDay(arrayList, getActivity());
                    recyclerView.setAdapter(adabterStatsForDay);

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
                        Log.d("vvvvv2vvv",error.getMessage());
                        error.printStackTrace();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("month_number", "8");
                params.put("employeeId", employeeId);
                return params;
            }
        };

        strRequest.setShouldCache(false);
        requestQueue.add(strRequest);
    }

    private void selectMonthlyLateHoursForEmployee(String employeeId){
        RequestQueue requestQueue = Volley.newRequestQueue(requireActivity());
        requestQueue.getCache().clear();
        String url = "https://css4dev.com/QrCustomers/selectMonthlyLateHoursForEmployee.php";
        StringRequest strRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("resdddd",response);
                try {
                    JSONObject res = new JSONObject(response);
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = res.getJSONArray("lateHours");
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
                            String hours = hit.getString("hours");

//                            Log.d("vvvvvvvv",isExsist);
//                            Log.d("vvvvvvvv2",entranceDate);
                        }catch (JSONException e) {
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
                        Toast.makeText(getActivity(), "error.getMessage()", Toast.LENGTH_SHORT).show();
                        // progressBar.setVisibility(View.GONE);
                        Log.d("vvvvv2vvv",error.getMessage());
                        error.printStackTrace();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("month_number", "8");
                params.put("employeeId", employeeId);
                return params;
            }
        };

        strRequest.setShouldCache(false);
        requestQueue.add(strRequest);
    }


//    private void getWorkScheduleStats(){
//        RequestQueue requestQueue = Volley.newRequestQueue(requireActivity());
//        requestQueue.getCache().clear();
//        String url = "https://css4dev.com/QrCustomers/selectWorkSchedule.php";
//        StringRequest strRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d("res",response);
//                try {
//                    JSONObject res = new JSONObject(response);
//                    JSONArray jsonArray = null;
//                    try {
//                        jsonArray = res.getJSONArray("absenceDays");
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    arrayList.clear();
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject hit = null;
//                        try {
//                            hit = jsonArray.getJSONObject(i);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//
//                            String exsitDate = hit.getString("exsitDate");
//                            String absenceDays = hit.getString("absenceDays");
//                            String name = hit.getString("name");
//
//
//                                    ""=>$rs['entranceDate'], "isExsist"=>$rs['isExsist']
//
//                            ModelStats modelStats = new ModelStats(employee_id, absenceDays, name);
//                            arrayList.add(modelStats);
//
//                        }catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    adabterStatsForDay = new AdabterStatsForDay(arrayList, getActivity());
//                    recyclerStats.setAdapter(adabterStatsForDay);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        },
//                new Response.ErrorListener()
//                {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getActivity(), "error.getMessage()", Toast.LENGTH_SHORT).show();
//                        // progressBar.setVisibility(View.GONE);
//                        error.printStackTrace();
//                    }
//                })
//        {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<>();
//                params.put("month_number", currentMonth);
//                params.put("employeeId", employeeId);
//                return params;
//            }
//        };
//
//        strRequest.setShouldCache(false);
//        requestQueue.add(strRequest);
//    }


}