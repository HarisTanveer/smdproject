package com.example.bagpackers.Firebase;

import android.support.annotation.NonNull;

import com.example.bagpackers.Classes.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class FirebaseDB
{

    private static FirebaseDB obj;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private FirebaseDB()
    {

    }

    public static FirebaseDB getInstance()
    {
        if(obj==null)
        {
            obj=new FirebaseDB();
        }
        return obj;
    }

    public boolean Login(String email, String pass)
    {
        final boolean[] x = {false};
        CollectionReference docRef = db.collection("user");
        Query q= docRef.whereEqualTo("email",email).whereEqualTo("password",pass);
        Task<QuerySnapshot> querySnapshotTask = q.get();
        if(querySnapshotTask.isComplete())
        {
            return true;
        }
        else
            return false;

    }

}
