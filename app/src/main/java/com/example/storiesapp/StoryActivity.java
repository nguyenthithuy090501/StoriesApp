package com.example.storiesapp;

import android.annotation.SuppressLint;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StoryActivity extends AppCompatActivity {


    ListView listView;
    DatabaseReference databaseReference;
    List<String> title_lt, storylt;
    ArrayAdapter<String> adapter;
    MyStory myStory;
    TextView levelName;
    ImageView btnBack;

    String dem = "";
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://storiesapp-112b7-default-rtdb.firebaseio.com/");
        myStory = new MyStory();
        title_lt = new ArrayList<>();
        storylt = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, R.layout.demo, R.id.item_txt, title_lt);
        levelName = findViewById(R.id.txtlevelName);
        btnBack = findViewById(R.id.btnBackTrangChu);
        //get Level name and user name from TrangChu
        final String getSelectedLevelName = getIntent().getStringExtra("selectedLevel");
        levelName.setText(getSelectedLevelName);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.child(getSelectedLevelName).getChildren()){

                    myStory = ds.getValue(MyStory.class);
                    if(myStory !=null){
                        title_lt.add(myStory.getTitle());
                    }
                    if(myStory != null){
                        storylt.add(myStory.getStory());
                    }
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Intent intent = new Intent(StoryActivity.this, NextActivity.class);
                            String p = storylt.get(position);
                            intent.putExtra("key", p);
                            position ++;
                            String number = String.valueOf(position);
                            //
                            intent.putExtra("test1", number);
                            intent.putExtra("test2", getSelectedLevelName);

                            startActivity(intent);

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //xet su kien cick vào bTnbACK
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StoryActivity.this, TrangChuActivity.class));
                finish();
            }
        });

    }
}