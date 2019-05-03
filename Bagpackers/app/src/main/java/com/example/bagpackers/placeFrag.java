package com.example.bagpackers;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bagpackers.Classes.Place;

import org.w3c.dom.Text;

import java.net.URI;
import java.util.ArrayList;


public class placeFrag extends Fragment {


   // private OnFragmentInteractionListener mListener;
   ArrayList<Place> placeArrayList;
    int index;
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
        placeArrayList= (ArrayList<Place>) getActivity().getIntent().getSerializableExtra("list");
        index= (int) getActivity().getIntent().getSerializableExtra("index");



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.place, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TextView t=getView().findViewById(R.id.textView4);
        t.setText(placeArrayList.get(index).name);

        ImageView a=getView().findViewById(R.id.imageView);
        Uri uri = Uri.parse(placeArrayList.get(index).picture);
        a.setImageURI(uri);
    }
}
