package com.codingdemos.flowers;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.codingdemos.flowers.fragments.FragmentOne;
import com.codingdemos.flowers.fragments.FragmentTwo;
import com.codingdemos.flowers.fragments.ItemOneFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

     private int fragment = 0;

//    private Toolbar toolbar;

//    public void setActionBarTitle(String title){
//        toolbar.setTit
//    }

    public void setActionBarTitle(String title) {
         getSupportActionBar().setTitle(title);
//        toolbar.setTitle(title);
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



            // Find the toolbar view inside the activity layout
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            // Sets the Toolbar to act as the ActionBar for this Activity window.
            // Make sure the toolbar exists in the activity and is not null
            setSupportActionBar(toolbar);

//            ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
//            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
//            adapter.addFragment(new FragmentOne(), "FRAG1");
//            adapter.addFragment(new FragmentTwo(), "FRAG2");
//            viewPager.setAdapter(adapter);
//
//            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
//            tabLayout.setupWithViewPager(viewPager);

            // toolbar = (Toolbar) findViewById(R.id.toolbar);
//            setSupportActionBar(toolbar);

//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);






            BottomNavigationView bottomNavigationView = (BottomNavigationView)
                    findViewById(R.id.navigation);

            bottomNavigationView.setOnNavigationItemSelectedListener
                    (new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            Fragment selectedFragment = null;
                            switch (item.getItemId()) {
                                case R.id.action_item1:
                                    selectedFragment = com.codingdemos.flowers.fragments.ItemOneFragment.newInstance();
                                    break;
                                case R.id.action_item2:
                                    selectedFragment = com.codingdemos.flowers.fragments.ItemTwoFragment.newInstance();
                                    break;
                                case R.id.action_item3:
                                    selectedFragment = com.codingdemos.flowers.fragments.ItemThreeFragment.newInstance();
                                    break;
                            }
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.frame_layout, selectedFragment);
                            transaction.commit();
                            return true;
                        }
                    });

            //Manually displaying the first fragment - one time only
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, com.codingdemos.flowers.fragments.ItemThreeFragment.newInstance());
            // transaction.replace(R.id.frame_layout, com.codingdemos.flowers.fragments.FragmentOne.newInstance());
            transaction.commit();

            //Used to select an item programmatically
            //bottomNavigationView.getMenu().getItem(2).setChecked(true);







        }

    public int getFragment() {
        return fragment;
    }

    public void setFragment(int fragment) {
        this.fragment = fragment;
    }

//
//    // Adapter for the viewpager using FragmentPagerAdapter
//        class ViewPagerAdapter extends FragmentPagerAdapter {
//            private final List<Fragment> mFragmentList = new ArrayList<>();
//            private final List<String> mFragmentTitleList = new ArrayList<>();
//
//            public ViewPagerAdapter(FragmentManager manager) {
//                super(manager);
//            }
//
//            @Override
//            public Fragment getItem(int position) {
//                return mFragmentList.get(position);
//            }
//
//            @Override
//            public int getCount() {
//                return mFragmentList.size();
//            }
//
//            public void addFragment(Fragment fragment, String title) {
//                mFragmentList.add(fragment);
//                mFragmentTitleList.add(title);
//            }
//
//            @Override
//            public CharSequence getPageTitle(int position) {
//                return mFragmentTitleList.get(position);
//            }
//        }

    @Override
    public void onResume() {
        super.onResume();
//        AppCompatActivity activity = (AppCompatActivity) MainActivity.this.getActivity();
//        ActionBar actionBar = activity.getSupportActionBar();
//        actionBar.setTitle(R.string.my_fragment_title);


        ActionBar actionBar = getSupportActionBar(); // or getActionBar();

//        getSupportActionBar().setTitle("one title");

        // getSupportActionBar().setTitle("My new title"); // set the top title
//        String title = actionBar.getTitle().toString(); // get the title
        actionBar.show(); // or even hide the actionbar

        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);


    }


//    @Override
//    public void onFragmentInteraction(String title) {
//        getSupportActionBar().setTitle(title);
//    }













}
