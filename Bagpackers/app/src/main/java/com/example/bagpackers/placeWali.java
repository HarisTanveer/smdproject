package com.example.bagpackers;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.bagpackers.Classes.Place;

import java.util.ArrayList;

public class placeWali extends AppCompatActivity {



    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {

                   case R.id.navigation_home:
                       selectedFragment = placeFrag.newInstance();
                 // mTextMessage.setText(R.string.title_home);
                       break;
                case R.id.navigation_hotel:
                    selectedFragment = HotelListItem.newInstance();
                   // Intent intent =new Intent(placeWali.this,hot);
                   /// startActivity(intent);
                   // mTextMessage.setText(R.string.title_hotel);

                   break;
               case R.id.navigation_maps:
                   selectedFragment = MapFrag.newInstance();
                   break;
                case R.id.navigation_weather:
                    selectedFragment = WeatherFrag.newInstance();
                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, selectedFragment);
            transaction.commit();
            return true;

        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_place_wali);
        mTextMessage = (TextView) findViewById(R.id.message);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if(savedInstanceState==null)
        {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, placeFrag.newInstance());
        transaction.commit();

    }
    }


}
