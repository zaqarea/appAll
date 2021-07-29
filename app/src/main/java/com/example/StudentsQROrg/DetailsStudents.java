package com.example.StudentsQROrg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailsStudents extends AppCompatActivity {

    private RecyclerView recyclerView;
    private final ArrayList<ModelLecture> arrayListLectures = new ArrayList<>();
    private AdabterLecture adabterLecture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_students);

        recyclerView = findViewById(R.id.recyclerDetailsStudents);

        ModelCourse modelCourse = (ModelCourse) getIntent().getSerializableExtra(AdabterCourse.COURSE);
        selectLecturesByCourseId(modelCourse.getCourseId());
//
//        ModelStudents modelStudents = new ModelStudents("1", modelLecture.getLectureId(), "khaled", "2:26", "true");
//        arrayList.add(modelStudents);

//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        AdabterStudents adabterStudents = new AdabterStudents(arrayList, DetailsStudents.this);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(adabterStudents);


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

                        String id = hit.getString("id");
                        String title = hit.getString("title");
                        String courseId = hit.getString("courseId");
                        String startTime = hit.getString("startTime");
                        String endTime = hit.getString("endTime");
                        String hours = hit.getString("hours");
                        String courseName = hit.getString("courseName");

                        ModelLecture modelLecture = new ModelLecture(courseId, id, title, startTime, endTime,courseName, hours);
                        arrayListLectures.add(modelLecture);

                        Log.d("sfsdfsdfsfsafsasa", arrayListLectures.get(0).getCourseName());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                // progressBar.setVisibility(View.GONE);
                adabterLecture = new AdabterLecture(arrayListLectures, DetailsStudents.this);
                recyclerView.setHasFixedSize(false);
                recyclerView.setLayoutManager(new LinearLayoutManager(DetailsStudents.this));
                recyclerView.setAdapter(adabterLecture);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("fgdgsss", error.getMessage());
                Toast.makeText(DetailsStudents.this, "حدث خطأ ما يرجى اعادة المحاولة", Toast.LENGTH_SHORT).show();
            }
        });

        request.setShouldCache(false);
        requestQueue.add(request);
    }

}