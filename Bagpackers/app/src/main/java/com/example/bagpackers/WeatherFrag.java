package com.example.bagpackers;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bagpackers.Classes.Place;

import java.util.ArrayList;

public class WeatherFrag extends Fragment
{
    ArrayList<Place> placeArrayList;
    int index;
    public WeatherFrag() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static WeatherFrag newInstance() {
        WeatherFrag fragment = new WeatherFrag();

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
        return inflater.inflate(R.layout.weather, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final WebView myWebView = (WebView) getView().findViewById(R.id.webview);
        myWebView.setWebChromeClient (new WebChromeClient());

        myWebView.loadUrl("file:///android_asset/main.html");
        myWebView.getSettings().setJavaScriptEnabled(true);

        placeArrayList= (ArrayList<Place>) getActivity().getIntent().getSerializableExtra("list");
        index= (int) getActivity().getIntent().getSerializableExtra("index");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                myWebView.loadUrl("javascript:myfun('"+placeArrayList.get(index).name+"')");
            }
        },1000);


      //  myWebView.addJavascriptInterface(new WebAppInterface(getActivity()), "obj");
    }
    public class WebAppInterface {
        Context mContext;

        WebAppInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void toast(String message) {
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        }
    }


}
