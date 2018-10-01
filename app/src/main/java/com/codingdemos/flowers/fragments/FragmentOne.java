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



    public static FragmentOne newInstance() {
        FragmentOne fragment = new FragmentOne();
        return fragment;
    }

//    private OnFragmentInteractionListener mListener;


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
        Log.d("LOG", "RESUME one");

        // Set title bar
//        ((MainActivity) getActivity()).setActionBarTitle("two title");
//        ((MainActivity) getActivity()).getSupportActionBar().setTitle("two title");

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
            ((MainActivity) getActivity()).setActionBarTitle("one title");
//            ((MainActivity) getActivity()).setFragment(2);
            Log.d("LOG", "one");
        }
    }




}
