package com.example.bagpackers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class signup extends AppCompatActivity {

    public static final String TAG="Singup";
    private EditText email;
    private EditText password;
    private EditText name;
    private EditText number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        email=findViewById(R.id.editText7);
        password=findViewById(R.id.editText9);
        name=findViewById(R.id.editText3);
        number=findViewById(R.id.editText8);

    }

    public void onSignup(View v)
    {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user =  FirebaseAuth.getInstance().getCurrentUser();


                    } else {
                        // If sign in fails, display a message to the user.
                        Log.d(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(signup.this, task.getException().toString(),
                                Toast.LENGTH_SHORT).show();

                    }

                }
            });
    }

}

