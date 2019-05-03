package com.example.bagpackers;

import android.content.BroadcastReceiver;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {

    boolean flag=true;

    public NetworkChangeReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent arg1) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            flag=true;
            Toast toast;
            toast = Toast.makeText(context, "Connected", Toast.LENGTH_SHORT);
            toast.show();
        }
        else
            {

            flag=false;
            //buildDialog(context).show();
            Toast toast;
            toast = Toast.makeText(context, "Not Connected. App functionalities will not work", Toast.LENGTH_LONG);
            toast.show();
        }
    }
    public boolean check()
    {
        if (flag==true)
            return true;
        else
            return false;
    }

}
