package com.example.quiz_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private Spinner number_question;
    public String option_Question;
    private Button starting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number_question = findViewById(R.id.number_of_mcqs);
        starting=findViewById(R.id.start_quiz);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.QUESTION_T, android.R.layout.simple_list_item_1);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        number_question.setAdapter(arrayAdapter);
        number_question.setSelection(0);
        number_question.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                option_Question=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        starting.setOnClickListener(this::start_quiz);

    }
    public void start_quiz(View view){
        Intent intent=new Intent(this,QUIZ_START.class);
        Log.d("NUMBER_MCQ", "start_quiz: "+option_Question);
        intent.putExtra("NUMBER_MCQS",option_Question);

        startActivity(intent);
    }

}