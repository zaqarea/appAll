package com.example.StudentsQROrg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class MainActivity extends AppCompatActivity {
        Button btnScan;
        Toolbar toolbar;
        private String studentId;
    @SuppressLint("StaticFieldLeak")
    public MeowBottomNavigation meowBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbarMain);
        btnScan = findViewById(R.id.btnScan);
        meowBottomNavigation = findViewById(R.id.bottomNavMain);
        setSupportActionBar(toolbar);

        @SuppressLint("WrongConstant")
        SharedPreferences sharedPreferences = getSharedPreferences(Login.FILE_CHECK_USER2, Context.MODE_PRIVATE);
        studentId = sharedPreferences.getString("studentId2", "false");

        meowBottomNavigation.show(1, true);
        meowBottomNavigation.setSelected(true);

        meowBottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_notifications));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_account));
       // meowBottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_calender));

        meowBottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case 1:
                        toolbar.setTitle("الاشعارات");
                        FragmentManager fm1 = getSupportFragmentManager();
                        FragmentTransaction ft1 = fm1.beginTransaction();
                        NotificationsFragment fragment1 = new NotificationsFragment();
                        ft1.replace(R.id.rel1536, fragment1);
                        ft1.commit();
                        break;
                    case 2:
                        toolbar.setTitle("القائمة");
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ListFragment fragment = new ListFragment();
                        ft.replace(R.id.rel1536, fragment);
                        ft.commit();
                        //toolbar.getMenu().findItem(R.id.menu_search).setVisible(true);
                       // toolbar.getMenu().findItem(R.id.menu_search).setVisible(false);
                        break;

                }
            }
        });
        meowBottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

            }
        });
        meowBottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

            }
        });

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScanQR.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_main) {
//            Intent intent = new Intent(MainActivity.this, ListMyPresence.class);
//            startActivity(intent);
//            return true;
        }else if (item.getItemId() == R.id.menu_scan_qr){
            Intent intent = new Intent(MainActivity.this, ScanQR.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}