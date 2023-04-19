package com.example.storiesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizResults extends AppCompatActivity {
    Button btnNewQuiz;
    TextView correctAnswer, incorrectAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);
        btnNewQuiz = findViewById(R.id.btnNewQuiz);
        correctAnswer = findViewById(R.id.correctAnswer);
        incorrectAnswer = findViewById(R.id.incorrectAnswer);


        final int getCorrectAnswer = getIntent().getIntExtra("correct", 0);
        final int getInCorrectAnswer = getIntent().getIntExtra("incorrect", 0);

        correctAnswer.setText(String.valueOf(getCorrectAnswer));
        incorrectAnswer.setText(String.valueOf(getInCorrectAnswer));

        //xet su kien click
        btnNewQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuizResults.this, TrangChuActivity.class));
                finish();
            }
        });
    }
}