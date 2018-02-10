package com.sexology;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostAnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_answer);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.close)
    public void close() {
        finish();
    }

    @OnClick(R.id.publish_answer_btn)
    public void publishAnswer() {

    }

}
