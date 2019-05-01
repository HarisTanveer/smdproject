package com.example.bagpackers;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bagpackers.Classes.User;
import com.example.bagpackers.Firebase.FirebaseDB;
import com.example.bagpackers.RoomDB.AppDatabase;

public class login extends AppCompatActivity {

    private TextView email;
    private TextView password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionbar=getSupportActionBar();
        actionbar.hide();
        email=(TextView)findViewById(R.id.editText);
        password=(TextView)findViewById(R.id.editText2);

    }

    public void onLogin(View v)
    {
        new LoginFirebase().execute();
    }


    //Local db se check kry ga using room
    public class LoginTask extends AsyncTask<Void,Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {

            AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "user").build();
            User log=db.userDao().login(email.getText().toString(),password.getText().toString());
            if(log!=null)
                return true;
            return false;

        }

        @Override
        protected void onPostExecute(Boolean v) {
            if(v)
            {
                Intent intent =new Intent(login.this,signup.class);
                startActivity(intent);
            }
            else
                {

                    Toast.makeText(getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_SHORT);
                }
        }
    }


    public class LoginFirebase extends AsyncTask<Void,Void,Boolean>
    {

        @Override
        protected Boolean doInBackground(Void... voids) {

            FirebaseDB db=FirebaseDB.getInstance();
            boolean x=db.Login(email.getText().toString(),password.getText().toString());
            return x;

        }

        @Override
        protected void onPostExecute(Boolean b) {
            if(b)
            {
                Intent intent =new Intent(login.this,signup.class);
                startActivity(intent);
            }
            else
            {

                Toast.makeText(getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_SHORT);
            }
        }
    }

}
