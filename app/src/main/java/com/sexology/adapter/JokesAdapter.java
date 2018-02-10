package com.sexology.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sexology.JokesActivity;
import com.sexology.R;

import butterknife.ButterKnife;

/**
 * Created by piyush on 8/2/18.
 */

public class JokesAdapter extends RecyclerView.Adapter<JokesAdapter.MyViewHolder> {
    private final JokesActivity jokesActivity;

    public JokesAdapter(JokesActivity jokesActivity) {
        this.jokesActivity = jokesActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jokes_recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
