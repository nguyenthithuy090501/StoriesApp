package com.example.storiesapp;

//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.Locale;

public class NextActivity extends AppCompatActivity {

    TextView txt;
    Button btnOnTap;
    ImageView imgViewVolume;
    TextToSpeech textToSpeech;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_activity);


        txt = findViewById(R.id.nxt);
        btnOnTap =(Button) findViewById(R.id.btnOnTap);
        imgViewVolume = (ImageView) findViewById(R.id.imgViewVolume);


        //String t = getIntent().getStringExtra("key");

        String t = getIntent().getStringExtra("key");

        txt.setText(t);

        String test1 = getIntent().getStringExtra("test1");
        String test2 = getIntent().getStringExtra("test2");


        //Volume
        textToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });

        imgViewVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = txt.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        //xet skien click
        btnOnTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NextActivity.this, QuizActivity.class);
                intent.putExtra("test1", test1);
                intent.putExtra("test2", test2);
                startActivity(intent);
            }
        });

    }
    public void onPause(){
        if(textToSpeech !=null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }
}