package com.example.bagpackers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

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
                    return true;
                case R.id.navigation_hotel:
                    selectedFragment = hotelFrag.newInstance();
                   // Intent intent =new Intent(placeWali.this,hot);
                   /// startActivity(intent);
                   // mTextMessage.setText(R.string.title_hotel);

                   return true;
               case R.id.navigation_maps:
                    //mTextMessage.setText(R.string.title_maps);
                   return true;
                case R.id.navigation_weather:
                    //mTextMessage.setText(R.string.title_weather);
                    return true;
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
        setContentView(R.layout.activity_place_wali);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, placeFrag.newInstance());
        transaction.commit();
    }

}
