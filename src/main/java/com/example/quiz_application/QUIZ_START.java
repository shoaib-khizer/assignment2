package com.example.quiz_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QUIZ_START extends AppCompatActivity {
    public int counter;

    CountDownTimer cTimer = null;
    public String[] select_option=new String[10];
    int i=0;
    private RadioGroup mcq_option;
    private RadioButton seleced_button_id;
    private int total_question;
    private TextView timer,question_no,correct_ans,wrong_ans;
    private Button submit;
//    String[] answers_of_questions = getResources().getStringArray(R.array.QUESTION_ANSWERS);
    String[] Questions_explained={"Which is private member functions access scope?","Which member can never be accessed by inherited classes?","How many private member functions are allowed in a class?","How to access a private member function of a class?","Which error will be produced if private members are accessed?","A C++ class is similar to","Which one of the following features of OOP is used to derive a class from another?","Which of the following operators always takes no argument if overloaded?","The keyword that is used that the variable can not change state?","A class can be identified from a statement by"};
    String[][] quesion_options={{"Member functions which can only be used within the class"," Member functions which can used outside the class","Member functions which are accessible in derived class","Member functions which can’t be accessed inside the class"},
            {"Private member function"," Public member function","Protected member function","All can be accessed"},
            {" Only 1","Only 7","Only 255","As many as required"},
            {"Using object of class","Using object pointer","Using address of member function","Using class address"},
            {"Can’t access private message","Code unreachable","Core dumped","Bad code"},
            {"Library File","Header File","Structure","None of these"},
            {"Encapsulation","Polymorphism","Data hiding","Inheritance"},
            {"++","/","—","+"},
            {"static","friend","private","const"},
            {"Adverb","Verb","Noun","Pronoun"}

    };
    String[] correct_answers={"A","D","C","A","C","C","B","A","A","D"};
    private int[] radiobutton_ids;
    private TextView question_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_u_i_z__s_t_a_r_t);
        Intent intent = getIntent();
        String Number_mcqs = intent.getStringExtra("NUMBER_MCQS");
        total_question=Integer.parseInt(Number_mcqs);
        radiobutton_ids=new int[]{R.id.ANS_A,R.id.ANS_B,R.id.ANS_C,R.id.ANS_D};
        question_text=findViewById(R.id.QUESTION_TEXT);
        timer=findViewById(R.id.TIMER_QUESTION);
        submit=findViewById(R.id.SUBMIT);
        mcq_option=findViewById(R.id.Question_answers);
        question_no=findViewById(R.id.question_no);
        correct_ans=findViewById(R.id.correct_answers);
        wrong_ans=findViewById(R.id.wrong_answers);
        quiz_questions();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check_selection()){
                    i++;
                    cTimer.cancel();
                    quiz_questions();
                }

            }
        });


    }
    public void quiz_questions(){
        Log.d("MCQS", "quiz_questions: "+i);
        mcq_option.clearCheck();
        if(i<total_question){
            question_no.setText("QUESTION "+(i+1));
            question_text.setText(Questions_explained[i]);
            for(int j=0;j<4;j++){
                seleced_button_id=findViewById(radiobutton_ids[j]);
                seleced_button_id.setText(quesion_options[i][j]);
            }
            timer();

        }
        else{
            question_no.setText("TEST FINISHED");
            timer.setText("FINISHED!!!");
            question_text.setText("TEST FINISHED SUBMIT TO SEE RESULT");
            for(int j=0;j<4;j++){
                seleced_button_id=findViewById(radiobutton_ids[j]);
                seleced_button_id.setVisibility(View.GONE);
            }
            check_mcqs();




        }

    }
    public void timer(){
        counter=60;
        cTimer=new CountDownTimer(60000, 1000){
            public void onTick(long millisUntilFinished){
                timer.setText(String.valueOf(counter));
                counter--;
            }
            public  void onFinish(){
                i++;
                timer.setText("FINISH!!");
                check_mcqs();
                quiz_questions();
            }
        }.start();

    }
    public boolean check_selection(){
        int selectedid=mcq_option.getCheckedRadioButtonId();
        if(selectedid==-1){
            Toast.makeText(this,"NO OPTION SELECTED",Toast.LENGTH_SHORT);
        }
        else {
            Log.d("MCQS", "check_selection: "+selectedid);
            if(selectedid==2131230721){
                select_option[i]="A";
            }
            else if(selectedid==2131230722){
                select_option[i]="B";
            }
            else if(selectedid==2131230723){
                select_option[i]="C";
            }
            else if(selectedid==2131230724){
                select_option[i]="D";
            }
            return true;

        }
        return false;


    }
    public void check_mcqs(){
        int correc_answers_n=0;
        for(int i=0;i<total_question;i++){
            Log.d("CORRECT_ANS", "check_mcqs: "+select_option[i]);
            if(correct_answers[i]==select_option[i]){
                correc_answers_n++;
            }
        }
        correct_ans.setVisibility(View.VISIBLE);
        wrong_ans.setVisibility(View.VISIBLE);
        correct_ans.setText("CORRECT : "+Integer.toString(correc_answers_n));
        wrong_ans.setText("WRONG :1"+Integer.toString(total_question-correc_answers_n));
    }
}