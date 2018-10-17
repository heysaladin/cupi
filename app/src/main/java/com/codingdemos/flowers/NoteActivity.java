package com.codingdemos.flowers;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class NoteActivity extends AppCompatActivity {

    Toolbar mToolbar;
    ImageView mFlower;
    TextView mDescription;
    TextView mTitle;

    private String imageUrl = null;
    private String title = null;
    private String note = null;

        ImageView edit_iv, delete_iv;

    private void getIntentData() {
        Intent intent = getIntent();
        imageUrl = intent.getStringExtra("Image");
        title = intent.getStringExtra("Title");
        note = intent.getStringExtra("Note");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        getIntentData();

        mToolbar = findViewById(R.id.toolbar);
//        mFlower = findViewById(R.id.ivImage);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mDescription = findViewById(R.id.tvDescription);
        mTitle = findViewById(R.id.tvTitle);
        mToolbar.setTitle("Note");


            edit_iv = (ImageView) findViewById(R.id.edit_iv);
            delete_iv = (ImageView) findViewById(R.id.delete_iv);

                edit_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(NoteActivity.this, AddNoteActivity.class);
//                mIntent.putExtra("Title", nameList[holder.getAdapterPosition()]);
//                mIntent.putExtra("Note", noteList[holder.getAdapterPosition()]);
                //mIntent.putExtra("Image", imgList[holder.getAdapterPosition()]);
                NoteActivity.this.startActivity(mIntent);
            }
        });
        delete_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                mFlowerList.remove(position);
                mAdapter.notifyItemRemoved(position);
                mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());
                */
            }
        });




    Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            // mToolbar.setTitle(mBundle.getString("Title"));
//            if (imageUrl != null) {
//                Glide.with(this)
//                        .load(imageUrl)
//                        .into(mFlower);
//            } else {
//                mFlower.setImageResource(mBundle.getInt("Image"));
//            }
            mDescription.setText(mBundle.getString("Note"));
            mTitle.setText(mBundle.getString("Title"));

            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_account_box_black_24dp);
            mToolbar.setOverflowIcon(drawable);

        }
    }
}