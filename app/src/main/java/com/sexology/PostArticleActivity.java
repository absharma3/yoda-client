package com.sexology;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.linchaolong.android.imagepicker.ImagePicker;
import com.linchaolong.android.imagepicker.cropper.CropImage;
import com.linchaolong.android.imagepicker.cropper.CropImageView;
import com.sexology.adapter.AttachmentsRecyclerAdapter;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostArticleActivity extends AppCompatActivity {
    private ImagePicker imagePicker = new ImagePicker();
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    ArrayList<String> filePaths = new ArrayList<>();
    private AttachmentsRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_article);
        ButterKnife.bind(this);
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mAdapter = new AttachmentsRecyclerAdapter(this, filePaths);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }

    @OnClick({R.id.close, R.id.publish_article_btn})
    public void close(View view) {
        if (view.getId() == R.id.close)
            finish();
        else if (view.getId() == R.id.publish_article_btn) {
        }
    }

    @OnClick(R.id.fab_attachment)
    public void attachementCicked() {
        startImagePicker();
    }

    private void startImagePicker() {
        imagePicker.setTitle("Pick Image");
        imagePicker.setCropImage(true);
        imagePicker.startChooser(this, new ImagePicker.Callback() {
            @Override
            public void onPickImage(Uri imageUri) {

            }

            @Override
            public void onCropImage(Uri imageUri) {
                Log.d("test", "onCropImage: " + imageUri);
                setImageInContainer(imageUri);
            }

            private void setImageInContainer(Uri imageUri) {
                String filePath = imageUri.toString().replace("file://", "");
                File file = new File(filePath);
                mAdapter.addImage(filePath);
            }

            @Override
            public void cropConfig(CropImage.ActivityBuilder builder) {
                builder
                        .setMultiTouchEnabled(false)
                        .setGuidelines(CropImageView.Guidelines.OFF)
                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                        .setRequestedSize(1080, 540)
                        .setAspectRatio(16, 9);
            }

            @Override
            public void onPermissionDenied(int requestCode, String[] permissions, int[] grantResults) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imagePicker.onActivityResult(this, requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        imagePicker.onRequestPermissionsResult(PostArticleActivity.this, requestCode, permissions, grantResults);
    }
}
