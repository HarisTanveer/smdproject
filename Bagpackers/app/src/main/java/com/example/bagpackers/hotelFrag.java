package com.example.bagpackers;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




public class hotelFrag extends Fragment {
    ;

    // TODO: Rename and change types of parameters


    // private OnFragmentInteractionListener mListener;

    public hotelFrag() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static hotelFrag newInstance() {
        hotelFrag fragment = new hotelFrag();

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
        return inflater.inflate(R.layout.hotel_layout, container, false);
       // return new View(new HotelListItem());
    }
}

