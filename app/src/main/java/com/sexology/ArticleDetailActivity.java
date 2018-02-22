package com.sexology;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;

public class ArticleDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
    }
}
