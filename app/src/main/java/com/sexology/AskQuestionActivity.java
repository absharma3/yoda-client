package com.sexology;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AskQuestionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.close)
    public void close() {
        finish();
    }
}
