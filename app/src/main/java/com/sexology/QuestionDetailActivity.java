package com.sexology;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sexology.model.Question;

public class QuestionDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_detail);
        Intent intent = getIntent();
        Question question = (Question) intent.getSerializableExtra("questionObj");
        TextView questionTextView = findViewById(R.id.questionText);
        questionTextView.setText(question.getQuestionString());
        String answer = question.getAnswerString();
        TextView answerTextView = findViewById(R.id.answerText);
        if (!(answer == null || answer.isEmpty())) {

            answerTextView.setText(answer);
        }else{
            answerTextView.setText("Not yet answered");
        }
    }
}
