package com.example.StudentsQROrg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.StudentsQROrg.Adabter.AdabterCourse;
import com.example.StudentsQROrg.Adabter.AdabterLecture;
import com.example.StudentsQROrg.Adabter.AdabterNotifications;
import com.example.StudentsQROrg.Adabter.AdabterStats;
import com.example.StudentsQROrg.Model.ModelStats;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Stats extends AppCompatActivity {

    TabLayout tabLayout;
    RecyclerView recyclerView;
    AdabterStats adabterStats;
    ArrayList<ModelStats> statsArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        tabLayout = findViewById(R.id.tabLayoutListStats);
        recyclerView = findViewById(R.id.recyclerStats);

        recyclerView.setLayoutManager(new LinearLayoutManager(Stats.this));
        recyclerView.setHasFixedSize(true);
        adabterStats = new AdabterStats(statsArrayList, Stats.this);
        recyclerView.setAdapter(adabterStats);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0){

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
    }
}