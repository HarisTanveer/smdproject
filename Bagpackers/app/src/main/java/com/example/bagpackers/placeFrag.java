package com.example.bagpackers;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class placeFrag extends Fragment {


   // private OnFragmentInteractionListener mListener;

    public placeFrag() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static placeFrag newInstance() {
        placeFrag fragment = new placeFrag();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.place, container, false);
    }
//zz
}
