package com.codingdemos.vacapedia;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.codingdemos.flowers.R;
import com.codingdemos.vacapedia.fragments.VacaplanFragment;
import com.codingdemos.vacapedia.fragments.PagesFragment;
import com.codingdemos.vacapedia.fragments.HomeFragment;
import com.codingdemos.vacapedia.fragments.AccountFragment;
import com.codingdemos.vacapedia.handlers.BottomNavigationViewBehavior;
import com.codingdemos.vacapedia.handlers.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity {

    private int fragment = 0;

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_status:
                Toast.makeText(this, "Notes", Toast.LENGTH_SHORT).show();
                Intent mIntentNotes = new Intent(this, NotesActivity.class);
                this.startActivity(mIntentNotes);
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "Notifications", Toast.LENGTH_SHORT).show();
                Intent mIntentNotificstions = new Intent(this, NotificationsActivity.class);
                this.startActivity(mIntentNotificstions);
                return true;
            case R.id.action_add:
                Toast.makeText(this, "AddDestinationActivity", Toast.LENGTH_SHORT).show();
                Intent mIntentDestinationAdd = new Intent(this, AddDestinationActivity.class);
                this.startActivity(mIntentDestinationAdd);
                return true;
            case R.id.action_add_slide:
                Toast.makeText(this, "AddSlideActivity", Toast.LENGTH_SHORT).show();
                Intent mIntentDestinationAddSlide = new Intent(this, AddSlideActivity.class);
                this.startActivity(mIntentDestinationAddSlide);
                return true;
            case R.id.action_add_promo:
                Toast.makeText(this, "AddPromoActivity", Toast.LENGTH_SHORT).show();
                Intent mIntentDestinationAddPromo = new Intent(this, AddPromoActivity.class);
                this.startActivity(mIntentDestinationAddPromo);
                return true;
            case R.id.action_list_slides:
                Toast.makeText(this, "ListSlideActivity", Toast.LENGTH_SHORT).show();
                Intent mIntentDestinationAddSlides = new Intent(this, ListSlidesActivity.class);
                this.startActivity(mIntentDestinationAddSlides);
                return true;
            case R.id.action_list_promos:
                Toast.makeText(this, "ListPromoActivity", Toast.LENGTH_SHORT).show();
                Intent mIntentDestinationAddPromos = new Intent(this, ListPromosActivity.class);
                this.startActivity(mIntentDestinationAddPromos);
                return true;
            case R.id.action_scroll:
                Toast.makeText(this, "ListPromoActivity", Toast.LENGTH_SHORT).show();
                Intent mIntentDestinationAddScroll = new Intent(this, CheeseDetailActivity.class);
                this.startActivity(mIntentDestinationAddScroll);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AppBarLayout appBar = (AppBarLayout) findViewById(R.id.appBar);
            appBar.setOutlineProvider(null);
        }
        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.action_item1:
                        selectedFragment = PagesFragment.newInstance();
                        break;
                    case R.id.action_item2:
                        selectedFragment = AccountFragment.newInstance();
                        break;
                    case R.id.action_item3:
                        selectedFragment = HomeFragment.newInstance();
                        break;
                    case R.id.action_item4:
                        selectedFragment = VacaplanFragment.newInstance();
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
                return true;
            }
        });
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);


        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationViewBehavior());


        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, HomeFragment.newInstance());
        transaction.commit();
    }

    public int getFragment() {
        return fragment;
    }

    public void setFragment(int fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        actionBar.show(); // or even hide the actionbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}