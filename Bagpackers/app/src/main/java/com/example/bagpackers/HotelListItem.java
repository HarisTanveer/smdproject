package com.example.bagpackers;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bagpackers.Classes.Hotels;
import com.example.bagpackers.Classes.Place;
import com.example.bagpackers.RoomDB.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class HotelListItem extends Fragment {

    ArrayList<Hotels> arr;
    ArrayList<Place> x;
    List<Hotels> lis;
    EditText text;
    ListView list;
    HotelListAdapter adapter;
    int selectedItem;
    LinearLayout layout;





    private EditText createText(){
        text = new EditText(getContext());
        text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        text.setHint("Search");
        text.addTextChangedListener(new TextWatcher(){

            @Override
            public void afterTextChanged(Editable arg0) { }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,int arg2, int arg3) { }

            @Override
            public void onTextChanged(CharSequence text, int start, int before,int count) {
                String check=text.toString();
                adapter.getFilter().filter(check);
            }

        });

        return text;
    }


    private ListView createList(){
        list = new ListView(getContext());

        adapter = new HotelListAdapter(getContext(),arr);
        list.setAdapter(adapter);


        return list;
    }


    public void createView(){


        layout.addView(createText());
        layout.addView(createList());

      //  getActivity().setContentView(layout);
    }

    public class getHotels extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids)
        {
            asyncUtil();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            createView();
        }
    }


    void asyncUtil()
    {

        String location=x.get(selectedItem).name;

        AppDatabase db = Room.databaseBuilder(getContext(), AppDatabase.class, "user").build();

        lis= db.hotelDao().loadAllByIds(location);
        int x=lis.size();
        arr=new ArrayList<Hotels>();
        for (int i=0; i<x;i++)
        {
            arr.add(lis.get(i));
        }

    }

    public HotelListItem() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static HotelListItem newInstance() {
        HotelListItem fragment = new HotelListItem();

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
         layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setFocusable(true);
        layout.setFocusableInTouchMode(true);

       x= (ArrayList<Place>)getActivity().getIntent().getSerializableExtra("list");
        selectedItem= (int)getActivity().getIntent().getSerializableExtra("index");
        new getHotels().execute();



        return layout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}


