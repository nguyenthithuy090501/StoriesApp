package com.example.storiesapp;

//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
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

public class NextActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

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
        String t = getIntent().getStringExtra("key");
        txt.setText(t);

        String test1 = getIntent().getStringExtra("test1");
        String test2 = getIntent().getStringExtra("test2");
        textToSpeech = new TextToSpeech( this, this);
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

    @Override
    public void onInit(int i) {
        textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {

            //called when speaking starts
            @Override
            public void onStart(String utteranceId) {
                Log.i("TTS", "utterance started");
            }

            //called when speaking is finished.
            @Override
            public void onDone(String utteranceId) {
                Log.i("TTS", "utterance done");
            }

            //called when an error has occurred during processing.
            @Override
            public void onError(String utteranceId) {
                Log.i("TTS", "utterance error");
            }

            //called when the TTS service is about to speak
            //the specified range of the utterance with the given utteranceId.
            @Override
            public void onRangeStart(String utteranceId,
                                     final int start,
                                     final int end,
                                     int frame) {
                Log.i("TTS", "onRangeStart() ... utteranceId: " + utteranceId + ", start: " + start
                        + ", end: " + end + ", frame: " + frame);


                // onRangeStart (and all UtteranceProgressListener callbacks) do not run on main thread
                // so we explicitly manipulate views on the main thread.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String t = txt.getText().toString();
                        Spannable textWithHighlights = new SpannableString(t);
                        textWithHighlights.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        txt.setText(textWithHighlights);

                    }
                });

            }

        });
    }
    public void startClicked(View ignored) {
        String toSpeak = txt.getText().toString();
        textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, "UNIQUE_UTTERANCE_ID");

    }
    @Override
    public void onDestroy() {
        //don't forget to shutdown tts
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}