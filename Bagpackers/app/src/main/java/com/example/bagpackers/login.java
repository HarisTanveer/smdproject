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
        new LoginTask().execute();
    }


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

                    Toast.makeText(getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_LONG);
                }
        }
    }

}
