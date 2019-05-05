package com.example.bagpackers;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bagpackers.Classes.Place;
import com.example.bagpackers.Classes.User;
import com.example.bagpackers.MainActivity;
import com.example.bagpackers.R;
import com.example.bagpackers.RoomDB.AppDatabase;
import com.example.bagpackers.TodoListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class note_list_item extends AppCompatActivity {

    ArrayList<Place> arr;
    List<Place> lis;
    EditText text;
    ListView list;
    TodoListAdapter adapter;
    int selectedItem;
    String loc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



       new getPlaces().execute();


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

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent=new Intent(note_list_item.this,placeWali.class);
                intent.putExtra("list",arr);
                intent.putExtra("index",position);

                startActivity(intent);

            }
        });
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

    public class getPlaces extends AsyncTask<Void,Void,Void>
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
        Intent intent=getIntent();
        String location=intent.getStringExtra("Province");
        loc=location;
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "user").build();
        lis= db.placeDao().getAllbyProvince(location);
        int x=lis.size();
        arr=new ArrayList<>();
        for (int i=0; i<x;i++)
        {
            arr.add(lis.get(i));
        }

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        onCreate(savedInstanceState);

    }



}


