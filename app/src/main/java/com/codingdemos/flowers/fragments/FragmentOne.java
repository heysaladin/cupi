package com.codingdemos.flowers.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codingdemos.flowers.MainActivity;
import com.codingdemos.flowers.R;
import com.codingdemos.flowers.SliderAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class FragmentOne extends Fragment {

    ViewPager viewPager;
    TabLayout indicator;

    List<Integer> color;
    List<String> colorName;


    public static FragmentOne newInstance() {
        FragmentOne fragment = new FragmentOne();
        return fragment;
    }

//    private OnFragmentInteractionListener mListener;

    private int fragment = 0;

    public int getFragment() {
        return fragment;
    }

    public void setFragment(int fragment) {
        this.fragment = fragment;
    }

    public FragmentOne() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LOG", "onCreate one");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("LOG", "onCreateView one");


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        viewPager=(ViewPager)view.findViewById(R.id.viewPager);
        indicator=(TabLayout)view.findViewById(R.id.indicator);
        color = new ArrayList<>();
        color.add(Color.RED);
        color.add(Color.GREEN);
        color.add(Color.BLUE);

        colorName = new ArrayList<>();
        colorName.add("RED");
        colorName.add("GREEN");
        colorName.add("BLUE");

        viewPager.setAdapter(new SliderAdapter(this.getContext(), color, colorName));
        indicator.setupWithViewPager(viewPager, true);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);




//        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
//        toolbar.setTitle("One");

        // getActivity().setTitle("one title");

//        getActivity().setTitle("Team A");




        return view;
    }

//    public void setUserVisibleHint(){
//
//    }

    public void onResume(){
        super.onResume();
        ((MainActivity) getActivity()).setActionBarTitle("one title");
        ((MainActivity) getActivity()).setFragment(1);
        this.setFragment(1);
        Log.d("LOG", "RESUME one");




        // Set title bar
//        ((MainActivity) getActivity()).setActionBarTitle("one title");
//        ((MainActivity) getActivity()).getSupportActionBar().setTitle("one title");

    }


//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    public interface OnFragmentInteractionListener {
//        public void onFragmentInteraction(String title);
//    }


    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            if(this.getFragment() != 0) {
             ((MainActivity) getActivity()).setActionBarTitle("one title");
                ((MainActivity) getActivity()).setFragment(1);
            }
            Log.d("LOG", "one");


        }
    }


    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            ((MainActivity) getActivity()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() < color.size() - 1) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }



}
