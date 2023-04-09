package com.example.storiesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TrangChuActivity extends AppCompatActivity {
    Button btnBasic, btnMedium, btnAdvance, btnStartTrangChu;
    private String selectedLevelName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        //anh xa cac button

        btnBasic = (Button) findViewById(R.id.btnBasic);
        btnMedium = (Button) findViewById(R.id.btnMedium);
        btnAdvance = (Button) findViewById(R.id.btnAdvance);
        btnStartTrangChu = (Button) findViewById(R.id.btnStartTrangChu);
        //xet su kien click

        btnBasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedLevelName = "Basic";

                btnBasic.setBackgroundResource(R.drawable.round_back_white_stoke10);

                btnMedium.setBackgroundResource(R.drawable.round_back_white10);
                btnAdvance.setBackgroundResource(R.drawable.round_back_white10);

            }
        });
        btnMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedLevelName = "medium";

                btnMedium.setBackgroundResource(R.drawable.round_back_white_stoke10);

                btnBasic.setBackgroundResource(R.drawable.round_back_white10);
                btnAdvance.setBackgroundResource(R.drawable.round_back_white10);

            }
        });
        btnAdvance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedLevelName = "adstory";

                btnAdvance.setBackgroundResource(R.drawable.round_back_white_stoke10);

                btnBasic.setBackgroundResource(R.drawable.round_back_white10);
                btnMedium.setBackgroundResource(R.drawable.round_back_white10);

            }
        });

        btnStartTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedLevelName.isEmpty()){
                    Toast.makeText(TrangChuActivity.this, "Please select the Level learn", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(TrangChuActivity.this, StoryActivity.class);
                    intent.putExtra("selectedLevel", selectedLevelName);
                    startActivity(intent);
                }
            }
        });


    }
}