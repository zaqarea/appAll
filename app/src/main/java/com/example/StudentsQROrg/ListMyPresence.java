package com.example.StudentsQROrg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.StudentsQROrg.Adabter.AdabterCourse;
import com.example.StudentsQROrg.Adabter.AdabterLecture;
import com.example.StudentsQROrg.Model.ModelCourse;
import com.example.StudentsQROrg.Model.ModelLecture;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListMyPresence extends AppCompatActivity {

    public static final String LECTURE_MODEL = "lectureModel";
    private RecyclerView recyclerView;
    private final ArrayList<ModelCourse> courseArrayList = new ArrayList<>();
    private final ArrayList<ModelLecture> lectureArrayList = new ArrayList<>();
   // private final ArrayList<String> arrayListLectureId = new ArrayList<>();
    AdabterCourse adabterCourse;
    AdabterLecture adabterLecture;
    TabLayout tabLayout;
    private String studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_my_presence);

        recyclerView = findViewById(R.id.recyclerMyPresence);
        tabLayout = findViewById(R.id.tabLayoutListAll);

        @SuppressLint("WrongConstant")
        SharedPreferences sharedPreferences = getSharedPreferences(Login.FILE_CHECK_USER2, Context.MODE_PRIVATE);
        studentId = sharedPreferences.getString("studentId2", "false");

        selectAllCourses();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0){
                    lectureArrayList.clear();
                    courseArrayList.clear();
                    selectAllCourses();
                    adabterCourse = new AdabterCourse(courseArrayList, ListMyPresence.this);
                    recyclerView.setHasFixedSize(false);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ListMyPresence.this));
                    recyclerView.setAdapter(adabterCourse);
                }else if (tab.getPosition() == 1){
                    courseArrayList.clear();
                    lectureArrayList.clear();
                    selectLectureByStudentId(studentId);
                    adabterLecture = new AdabterLecture(lectureArrayList, ListMyPresence.this);
                    recyclerView.setHasFixedSize(false);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ListMyPresence.this));
                    recyclerView.setAdapter(adabterLecture);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


//        AdabterPresence adabterPresence = new AdabterPresence(presenceArrayList, ListMyPresence.this);
//        recyclerView.setLayoutManager(new LinearLayoutManager(ListMyPresence.this));
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(adabterPresence);


//        scanCode();
//
//        contentModel = modelLecture.getLectureId() + modelLecture.getLectureTitle() + modelLecture.getStartTime() + modelLecture.getEndTime() + modelLecture.getStudentsActive() + modelLecture.getStudentsMore();
//        LectureTitle.setText(modelLecture.getLectureTitle());
//        startTime.setText(modelLecture.getStartTime());
//        endTime.setText(modelLecture.getEndTime());
//
//        if (!contentModel.isEmpty() && contentModel != null) {
//            WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
//            Display display = manager.getDefaultDisplay();
//            Point point = new Point();
//            display.getSize(point);
//            int width = point.x;
//            int height = point.y;
//            int dimen = width < height ? width : height;
//            dimen = dimen * 3 / 4;
//            qrgEncoder = new QRGEncoder(contentModel, null, QRGContents.Type.TEXT, dimen);
//            try {
//                bitmap = qrgEncoder.encodeAsBitmap();
//                imageViewQr.setImageBitmap(bitmap);
//            } catch (WriterException e) {
//                Log.e("Tag", e.toString());
//            }
//        }
//
//        cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ListMyPresence.this, DetailsStudents.class);
//                intent.putExtra(LECTURE_MODEL, modelLecture);
//                startActivity(intent);
//            }
//        });
//    }
//
//    private void scanCode() {
//        IntentIntegrator integrator = new IntentIntegrator(this);
//        integrator.setCaptureActivity(CaptureAct.class);
//        integrator.setOrientationLocked(false);
//        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
//        integrator.setPrompt("Scanning code");
//        integrator.initiateScan();
//    }
//
//    @SuppressLint("SetTextI18n")
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        @SuppressLint("SimpleDateFormat")
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//        String currentDateAndTime = sdf.format(new Date());
//        if (result != null){
//            if (result.getContents() != null){
//                if (result.getContents().equals(contentModel)) {
//                    txtCurrentTime.setText(currentDateAndTime);
//                    txtOk.setText("الكود مطابق");
//                    ModelStudents modelStudents = new ModelStudents(System.currentTimeMillis() + "", modelLecture.getLectureId(), "khaled", currentDateAndTime, "true");
//                    arrayList.add(modelStudents);
//                    finish();
//                }else {
//                    Toast.makeText(this, "هذا الكود غير مطابق لكود المحاضرة الحالية", Toast.LENGTH_SHORT).show();
//                    scanCode();
//                }
//            }else {
//                Toast.makeText(this, "No Result", Toast.LENGTH_SHORT).show();
//            }
//        }else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//    }s
    }

    public void selectAllCourses(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.getCache().clear();
        String url = "https://css4dev.com/QrStudents/selectAllCourses.php";
        Log.d("url",url);
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("res",response.toString());
                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("COURSES");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                assert jsonArray != null;
                Log.d("AboHafslength", jsonArray.length() + "");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject hit = null;
                    try {
                        hit = jsonArray.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {

                        assert hit != null;
                        String id = hit.getString("id");
                        String courseName = hit.getString("courseName");
                        String isActive = hit.getString("isActive");
                        String coachId = hit.getString("coachId");
                        String courseDateStart = hit.getString("courseDateStart");
                        String courseDateEnd = hit.getString("courseDateEnd");
                        String numberHour = hit.getString("numberHour");

                        ModelCourse modelCourse = new ModelCourse(id, courseDateStart, courseDateEnd, courseName, coachId, isActive, numberHour);
                        courseArrayList.add(modelCourse);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
               // progressBar.setVisibility(View.GONE);
                adabterCourse = new AdabterCourse(courseArrayList, ListMyPresence.this);
                recyclerView.setHasFixedSize(false);
                recyclerView.setLayoutManager(new LinearLayoutManager(ListMyPresence.this));
                recyclerView.setAdapter(adabterCourse);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("fgdgsfgfss", error.getMessage());
                Toast.makeText(ListMyPresence.this, "حدث خطأ ما يرجى اعادة المحاولة", Toast.LENGTH_SHORT).show();
            }
        });

        request.setShouldCache(false);
        requestQueue.add(request);
    }

    public void selectLectureByStudentId(String studentId){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.getCache().clear();
        String url = "https://css4dev.com/QrStudents/selectPresencesByUserId.php?studentId="+studentId;
        Log.d("url",url);
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("redfgdf45s",response.toString());
                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("Presence");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                assert jsonArray != null;
                Log.d("AboHafslength", jsonArray.length() + "");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject hit = null;
                    try {
                        hit = jsonArray.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {

                        assert hit != null;
                        String id = hit.getString("id");
                        String title = hit.getString("title");
                        String courseId = hit.getString("courseId");
                        String startTime = hit.getString("startTime");
                        String endTime = hit.getString("endTime");
                        String hours = hit.getString("hours");
                        String date = hit.getString("date");
                        String courseName = hit.getString("courseName");

                        ModelLecture modelLecture = new ModelLecture(id, title, courseId, courseName, startTime, endTime, date, hours);
                        lectureArrayList.add(modelLecture);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                // progressBar.setVisibility(View.GONE);
                adabterLecture = new AdabterLecture(lectureArrayList, ListMyPresence.this);
                recyclerView.setHasFixedSize(false);
                recyclerView.setLayoutManager(new LinearLayoutManager(ListMyPresence.this));
                recyclerView.setAdapter(adabterLecture);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("fgdgsss", error.getMessage());
                Toast.makeText(ListMyPresence.this, "حدث خطأ ما يرجى اعادة المحاولة", Toast.LENGTH_SHORT).show();
            }
        });

        request.setShouldCache(false);
        requestQueue.add(request);
    }

    public void selectLecturesByCourseId(String courseId){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.getCache().clear();
        String url = "https://css4dev.com/QrStudents/selectLecturesByCourseId.php?courseId="+courseId;
        Log.d("url",url);
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("res",response.toString());
                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("lectures");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                assert jsonArray != null;
                Log.d("AboHafslength", jsonArray.length() + "");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject hit = null;
                    try {
                        hit = jsonArray.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {

                        assert hit != null;
                        String id = hit.getString("id");
                        String title = hit.getString("title");
                        String courseId = hit.getString("courseId");
                        String startTime = hit.getString("startTime");
                        String endTime = hit.getString("endTime");
                        String hours = hit.getString("hours");
                        String date = hit.getString("date");
                        String courseName = hit.getString("courseName");

                        ModelLecture modelLecture = new ModelLecture(id, title, courseId, courseName, startTime, endTime, date, hours);
                        lectureArrayList.add(modelLecture);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                // progressBar.setVisibility(View.GONE);
                adabterLecture = new AdabterLecture(lectureArrayList, ListMyPresence.this);
                recyclerView.setHasFixedSize(false);
                recyclerView.setLayoutManager(new LinearLayoutManager(ListMyPresence.this));
                recyclerView.setAdapter(adabterLecture);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("fgdgsss", error.getMessage());
                Toast.makeText(ListMyPresence.this, "حدث خطأ ما يرجى اعادة المحاولة", Toast.LENGTH_SHORT).show();
            }
        });

        request.setShouldCache(false);
        requestQueue.add(request);
    }


}