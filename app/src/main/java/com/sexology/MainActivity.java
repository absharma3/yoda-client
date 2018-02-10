package com.sexology;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.gson.Gson;
import com.sexology.adapter.AttachmentsRecyclerAdapter;
import com.sexology.adapter.QuestionsAdapter;
import com.sexology.model.Question;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private Question question;
    @BindView(R.id.fabMenu)
    FloatingActionsMenu floatingActionsMenu;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.contentMainLayout)
    RelativeLayout contentMainLayout;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        contentMainLayout.setVisibility(View.GONE);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        QuestionsAdapter mAdapter = new QuestionsAdapter(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
    }

    @OnClick({R.id.fab_post_question, R.id.fab_post_joke, R.id.fab_post_article})
    public void onClicked(View view) {
        floatingActionsMenu.collapse();
        switch (view.getId()) {
            case R.id.fab_post_question:
                startActivity(new Intent(MainActivity.this, AskQuestionActivity.class));
                break;
            case R.id.fab_post_joke:
                startActivity(new Intent(MainActivity.this, PostJokeActivity.class));
                break;
            case R.id.fab_post_article:
                startActivity(new Intent(MainActivity.this, PostArticleActivity.class));
                break;
        }
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
//                        addQuestionButton(question);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (floatingActionsMenu.isExpanded()) floatingActionsMenu.collapse();
        if (drawer.isDrawerOpen(GravityCompat.START)) drawer.closeDrawer(GravityCompat.START);
        if (item.getItemId() == R.id.nav_articles)
            startActivity(new Intent(MainActivity.this, ArticlesActivity.class));
        return false;
    }
/*

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

    }*/

    @OnClick(R.id.ask_question_btn)
    public void askQuestionBtnClick() {
        if (floatingActionsMenu.isExpanded()) floatingActionsMenu.collapse();
        startActivity(new Intent(MainActivity.this, AskQuestionActivity.class));
    }
}
