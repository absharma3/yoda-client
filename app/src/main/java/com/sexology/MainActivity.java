package com.sexology;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.sexology.model.Question;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    private Question question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void addQuestion(View view) {
        //First call the server to add the question
        //Once the add question call to the server is complete get the id and set it to the button id
        EditText editText = findViewById(R.id.questionText);
        invokeAddQuestionApi(editText.getText().toString());
    }

    private void invokeAddQuestionApi(final String questionString) {

        final RequestQueue queue = Volley.newRequestQueue(this);
        //TODO remove hardcoded user id and also the server base address needs to be configurable
        String url = "http://172.16.49.126:8080/api/question/add/12345/" + questionString;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        question = new Gson().fromJson(response, Question.class);
                        addQuestionButton(question);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Lets handle this later

                error.getLocalizedMessage();
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    private void addQuestionButton(final Question question) {
        Button textView = new Button(this);
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        textView.setTag(question.getQuestionId());
        textView.setText(question.getQuestionString());
//        textView.setBackgroundColor(Color.parseColor("#FF039858"));
        linearLayout.addView(textView);
        final Intent intent = new Intent(this, QuestionDetailActivity.class);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("questionObj", question);
                startActivity(intent);
            }
        });

    }
}
