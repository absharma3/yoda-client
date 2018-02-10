package com.sexology.adapter;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sexology.ArticleDetailActivity;
import com.sexology.ArticlesActivity;
import com.sexology.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by piyush on 8/2/18.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.MyViewHolder> {
    private final ArticlesActivity articlesActivity;

    public ArticlesAdapter(ArticlesActivity articlesActivity) {
        this.articlesActivity = articlesActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.articleImage)
        ImageView articleImage;
        @BindView(R.id.articleTitle)
        TextView articleTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(articlesActivity, ArticleDetailActivity.class);
                    if (Build.VERSION.SDK_INT >= 21) {
                        Pair<View, String> p1 = Pair.create((View) articleImage, ViewCompat.getTransitionName(articleImage));
                        Pair<View, String> p2 = Pair.create((View) articleTitle, ViewCompat.getTransitionName(articleTitle));
                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(articlesActivity, p1, p2);
                        articlesActivity.startActivity(intent, options.toBundle());
                    } else
                        articlesActivity.startActivity(intent);
                }
            });
        }
    }
}
