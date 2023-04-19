package com.example.storiesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PreTrangChu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_trang_chu);

       Button btnPreTrangChu= findViewById(R.id.btnPreTrangChu);

        btnPreTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(PreTrangChu.this, TrangChuActivity.class);
                startActivity(intent);
            }
        });
    }
}