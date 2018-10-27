package com.codingdemos.vacapedia;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.codingdemos.flowers.R;

import java.util.Objects;

public class VacaplanActivity extends AppCompatActivity {

    Toolbar mToolbar;
    ImageView mFlower;
    TextView mDescription;

    private String name = null;
    private String image = null;

    private void getIntentData() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        image = intent.getStringExtra("image");
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detail);
//
//        getIntentData();
//
//        mToolbar = findViewById(R.id.toolbar);
//        mFlower = findViewById(R.id.ivImage);
//        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });
//        mDescription = findViewById(R.id.tvDescription);
//
//        Bundle mBundle = getIntent().getExtras();
//        if (mBundle != null) {
//            mToolbar.setTitle(mBundle.getString("Title"));
//            if (imageUrl != null) {
//                Glide.with(this)
//                        .load(imageUrl)
//                        .into(mFlower);
//            } else {
//                mFlower.setImageResource(mBundle.getInt("Image"));
//            }
//            mDescription.setText(mBundle.getString("Description"));
//
//            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_account_box_black_24dp);
//            mToolbar.setOverflowIcon(drawable);
//
//        }
//    }



    public static final String EXTRA_NAME = "cheese_name";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_vacaplan);
        // Back button
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayHomeAsUpEnabled(true)

        getIntentData();

        Intent intent = getIntent();
        final String cheeseName = intent.getStringExtra(EXTRA_NAME);

        final Toolbar toolbar = findViewById(R.id.toolbar_collapse);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(name);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(cheeseName);

        final LinearLayout overlay = findViewById(R.id.overlay);
        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (Math.abs(verticalOffset)-appBarLayout.getTotalScrollRange() == 0)
                {
                    // Collapsed
                    overlay.setVisibility(View.GONE);
                }
                else
                {
                    // Expanded
                    overlay.setVisibility(View.VISIBLE);
                }
            }
        });


        loadBackdrop();
    }

    private void loadBackdrop() {
        final ImageView imageView = findViewById(R.id.backdrop);
        Glide.with(this).load(image).into(imageView);
//        Glide.with(this).load(Cheeses.getRandomCheeseDrawable()).apply(RequestOptions.centerCropTransform()).into(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}