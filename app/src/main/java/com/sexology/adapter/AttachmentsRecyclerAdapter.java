package com.sexology.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sexology.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by piyush on 7/2/18.
 */

public class AttachmentsRecyclerAdapter extends RecyclerView.Adapter<AttachmentsRecyclerAdapter.MyViewHolder> {
    private final Context context;
    private final ArrayList<String> filePaths;

    public AttachmentsRecyclerAdapter(Context context, ArrayList<String> filePaths) {
        this.context = context;
        this.filePaths = filePaths;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attachment_recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String path = filePaths.get(position);
//        Glide.with(context).load(path).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return filePaths.size();
    }

    public void addImage(String filePath) {
        filePaths.add(filePath);
        notifyItemInserted(filePaths.size() - 1);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(true, itemView);
        }
    }
}
