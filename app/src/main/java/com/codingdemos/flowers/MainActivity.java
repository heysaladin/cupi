package com.codingdemos.flowers;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.codingdemos.flowers.fragments.FragmentOne;
import com.codingdemos.flowers.fragments.ItemFourFragment;
import com.codingdemos.flowers.fragments.ItemOneFragment;
import com.codingdemos.flowers.fragments.ItemThreeFragment;
import com.codingdemos.flowers.fragments.ItemTwoFragment;

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
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.action_item1:
                        selectedFragment = ItemOneFragment.newInstance();
                        break;
                    case R.id.action_item2:
                        selectedFragment = ItemTwoFragment.newInstance();
                        break;
                    case R.id.action_item3:
                        selectedFragment = ItemThreeFragment.newInstance();
                        break;
                    case R.id.action_item4:
                        selectedFragment = ItemFourFragment.newInstance();
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
                return true;
            }
        });
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, ItemThreeFragment.newInstance());
        // transaction.replace(R.id.frame_layout, com.codingdemos.flowers.fragments.FragmentOne.newInstance());
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