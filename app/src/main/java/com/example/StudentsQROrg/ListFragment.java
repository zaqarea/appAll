package com.example.StudentsQROrg;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ListFragment extends Fragment {
    private TextView btnInviteFriend, btnStats, btnEditProfile, btnSettings, btnRebortProblem;
    private Button btnLogOut;
    private Button userNameProfile;
    private TextView txtViewChangeImage;
    private ImageView imageProfile;

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
       // progressBar = view.findViewById(R.id.progressBarMenuFragment);
        btnLogOut = view.findViewById(R.id.btnLogOut);
        btnInviteFriend = view.findViewById(R.id.btnInviteFriend);
        btnStats = view.findViewById(R.id.btnStats);
        btnEditProfile = view.findViewById(R.id.btnEditProfile);
        btnSettings = view.findViewById(R.id.btnSettings);
        btnRebortProblem = view.findViewById(R.id.btnRebortProblem);
        txtViewChangeImage = view.findViewById(R.id.txtViewChangeImage);

        btnRebortProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              Intent intent = new Intent(mainActivity, ReportProblem.class);
//              startActivity(intent);

                Toast.makeText(mainActivity, "غير متاح حاليا", Toast.LENGTH_LONG).show();
            }
        });
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity, EditInfoUser.class);
                startActivity(intent);
            }
        });
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(mainActivity, Settings.class);
//                startActivity(intent);
            }
        });
        btnInviteFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnInviteFriend.setEnabled(false);
                shareUrlApp();
            }
        });
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
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLogOut.setEnabled(false);
            }
        });
        btnStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity, Stats.class);
                startActivity(intent);
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

}