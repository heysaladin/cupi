/*
 * Copyright (c) 2017. Truiton (http://www.truiton.com/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 * Mohit Gupt (https://github.com/mohitgupt)
 *
 */

package com.codingdemos.flowers.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codingdemos.flowers.MainActivity;
import com.codingdemos.flowers.R;

import java.util.ArrayList;
import java.util.List;

public class ItemOneFragment extends Fragment {

    private  View view;

    public static ItemOneFragment newInstance() {
        ItemOneFragment fragment = new ItemOneFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /*
        Fragment selectedFragment = com.codingdemos.flowers.fragments.FragmentOne.newInstance();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, selectedFragment);
        transaction.commit();
        */

         view = inflater.inflate(R.layout.fragment_item_one, container, false);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);


        // ViewPagerAdapter adapter = new ViewPagerAdapter(((MainActivity) getActivity()).getSupportFragmentManager());
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new FragmentTwo(), "FRAG2");
         adapter.addFragment(new FragmentOne(), "FRAG1");
//        adapter.addFragment(com.codingdemos.flowers.fragments.FragmentOne.newInstance(), "FRAG1");
//        adapter.addFragment(com.codingdemos.flowers.fragments.FragmentTwo.newInstance(), "FRAG2");
        viewPager.setAdapter(adapter);

        // vpPager = (ViewPager)view.findViewById(R.id.vpPager);
        viewPager.setOffscreenPageLimit(2);
        //vpPager.setAdapter(pagerAdapter);
        // viewPager.addOnPageChangeListener(adapter);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        Log.d("LOG", "tabLayout.getSelectedTabPosition() = " + tabLayout.getSelectedTabPosition());
//        Log.d("LOG", "getParentFragment() = " + this.getParentFragment());
        Log.d("LOG", "viewPager.getCurrentItem() = " + viewPager.getCurrentItem());

//        Fragment fragment = ((MainActivity) getActivity()).getSupportFragmentManager().findFragmentById(R.id.container);
//        FragmentManager childFm = fragment.getChildFragmentManager();

//        Fragment selectedFragment = com.codingdemos.flowers.fragments.ItemOneFragment.newInstance();
//        FragmentTransaction transaction = ((MainActivity) getActivity()).getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.frame_layout, selectedFragment);
//        transaction.commit();

//        FragmentTransaction transaction = ((MainActivity) getActivity()).getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.pager, fragment);
//        transaction.commit();




        return view;
    }




    // Adapter for the viewpager using FragmentPagerAdapter
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

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
    public void onResume() {
        super.onResume();

    }
}
