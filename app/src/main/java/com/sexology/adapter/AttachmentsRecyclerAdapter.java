package com.sexology.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sexology.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by piyush on 7/2/18.
 */

public class AttachmentsRecyclerAdapter extends RecyclerView.Adapter<AttachmentsRecyclerAdapter.MyViewHolder> {
    private final Context context;
    private final ArrayList<Uri> imagesUris;
    private String TAG = "test";

    public AttachmentsRecyclerAdapter(Context context, ArrayList<Uri> filePaths) {
        this.context = context;
        this.imagesUris = filePaths;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attachment_recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Uri uri = imagesUris.get(position);
        Log.d(TAG, "onBindViewHolder: " + position);
        Log.d(TAG, "filepaths: " + uri);
        Picasso.with(context).load(uri).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imagesUris.size();
    }

    public void addImage(Uri filePath) {
        imagesUris.add(filePath);
        notifyItemInserted(imagesUris.size() - 1);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.removeAttachment)
        public void removeAttachmentClick() {
            imagesUris.remove(getAdapterPosition());
            notifyItemRemoved(getAdapterPosition());
        }
    }
}
