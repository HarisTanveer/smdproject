package com.example.bagpackers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.bagpackers.Classes.Place;
import com.example.bagpackers.MainActivity;
import com.example.bagpackers.R;
import com.example.bagpackers.TodoListAdapter;

import java.util.ArrayList;

public class note_list_item extends AppCompatActivity {

    ArrayList<Place> arr;
    EditText text;
    ListView list;
    TodoListAdapter adapter;
    int selectedItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        arr=new ArrayList<>();
        Place p=new Place();
        p.picture="android.resource://com.example.bagpackers/drawable/gilgit";
        p.name="Place 1";

        Place x=new Place();
        x.picture="android.resource://com.example.bagpackers/drawable/ksmr";
        x.name="Place 2";


        Place y=new Place();
        y.picture="android.resource://com.example.bagpackers/drawable/ksmr";
        y.name="Place 2";

        arr.add(p);
        arr.add(x);
        arr.add(y);



        createView();
    }

    private EditText createText(){
        text = new EditText(this);
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
        list = new ListView(this);

        adapter = new TodoListAdapter(this,arr);
        list.setAdapter(adapter);

        return list;
    }


    public void createView(){
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setFocusable(true);
        layout.setFocusableInTouchMode(true);


        layout.addView(createText());
        layout.addView(createList());

        setContentView(layout);
    }
}


