package com.codingdemos.flowers.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.codingdemos.flowers.MainActivity;
import com.codingdemos.flowers.R;

import java.util.ArrayList;
import java.util.List;

public class ItemOneFragment extends Fragment {

    private View view;

    public static ItemOneFragment newInstance() {
        ItemOneFragment fragment = new ItemOneFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_item_one, container, false);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new FragmentTwo(), "Destinations");
        adapter.addFragment(new FragmentOne(), "Edit Data");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        Log.d("LOG", "tabLayout.getSelectedTabPosition() = " + tabLayout.getSelectedTabPosition());
        Log.d("LOG", "viewPager.getCurrentItem() = " + viewPager.getCurrentItem());
        return view;
    }

    // Adapter for the viewpager using FragmentPagerAdapter
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List < Fragment > mFragmentList = new ArrayList < > ();
        private final List < String > mFragmentTitleList = new ArrayList < > ();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
            Log.d("LOG", "manager = " + manager);
        }

        @Override
        public Fragment getItem(int position) {
            Log.d("LOG", "position = " + position);
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            Log.d("LOG", "fragment = " + fragment);
            Log.d("LOG", "title = " + title);
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Log.d("LOG", "getPageTitle position = " + position);
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle clicks
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_one, menu);
    }

    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setActionBarTitle("Destinations");
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            ((MainActivity) getActivity()).setActionBarTitle("Destinations");
            Log.d("LOG", "one");
        }
    }

}