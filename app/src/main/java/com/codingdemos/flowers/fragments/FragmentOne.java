package com.codingdemos.flowers.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codingdemos.flowers.MainActivity;
import com.codingdemos.flowers.R;

public class FragmentOne extends Fragment {

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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

}
