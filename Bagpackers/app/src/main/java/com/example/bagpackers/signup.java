package com.example.bagpackers;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bagpackers.Classes.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class signup extends AppCompatActivity {

    public static final String TAG = "Singup";
    private EditText email;
    private EditText password;
    private EditText name;
    private EditText number;
    private ImageView img;
    private static final int PICK_IMAGE_GALLERY_REQUEST_CODE = 0;
    Uri currentPhotoPath ;

    StorageReference storageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();
       // FirebaseApp.initializeApp(getApplicationContext());
        storageRef = FirebaseStorage.getInstance().getReference();
        email = findViewById(R.id.editText7);
        password = findViewById(R.id.editText9);
        name = findViewById(R.id.editText3);
        number = findViewById(R.id.editText8);
        img = findViewById(R.id.imageView90);

    }


    public void uploadPicture(View v) {
        Intent pictureIntent = new Intent(Intent.ACTION_GET_CONTENT);
        pictureIntent.setType("image/*");  // 1
        pictureIntent.addCategory(Intent.CATEGORY_OPENABLE);  // 2
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            String[] mimeTypes = new String[]{"image/jpeg", "image/png"};  // 3
            pictureIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        }
        startActivityForResult(Intent.createChooser(pictureIntent,"Select Picture"), PICK_IMAGE_GALLERY_REQUEST_CODE);  // 4
    }


    public void onSignup(View v) {
        new SignupFirebaseTask().execute();
    }


    class SignupFirebaseTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            SignupTask();

            return null;
        }
    }


    public void SignupTask() {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            FirebaseAuth.getInstance().signOut();
                            uploadPic();


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(signup.this, task.getException().toString(),
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK)
        {
            if (requestCode == PICK_IMAGE_GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null)
            {
            Uri sourceUri = data.getData();
            //img.setImageURI(sourceUri);
            //File file = getImageFile(); // 2
            //Uri destinationUri = Uri.fromFile(file);  // 3
            startCropActivity(sourceUri);  // 4
            }
            else if (requestCode == UCrop.REQUEST_CROP) {
                handleCropResult(data);
            }
        }
    }




    private void startCropActivity(@NonNull Uri uri) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = sdf.format(d);

        String destinationFileName = s + ".jpg";

        UCrop uCrop = UCrop.of(uri, Uri.fromFile(new File(getCacheDir(), destinationFileName)));

        uCrop = uCrop.useSourceImageAspectRatio();

        uCrop.start(signup.this);
    }

    private void handleCropResult(@NonNull Intent result) {
        final Uri resultUri = UCrop.getOutput(result);
        if (resultUri != null) {
           String mCurrentPhotoStr = resultUri.getPath();
            Bitmap mPhotoBitmap = BitmapFactory.decodeFile(resultUri.getPath());
            img.setImageBitmap(mPhotoBitmap);
            currentPhotoPath=resultUri;
        }


    }

   void uploadPic ()
    {
        Uri file=currentPhotoPath;
        StorageReference riversRef = storageRef.child("images/"+email.getText().toString());
        UploadTask uploadTask = riversRef.putFile(file);

// Register observers to listen for when the download is done or i f it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
                saveUserinFireStore();
                Intent intent = new Intent(signup.this, login.class);
                startActivity(intent);
            }
        });
    }
    
    public void saveUserinFireStore()
    {
        User u=new User();
        u.email=email.getText().toString();
        u.name=name.getText().toString();
        u.number=number.getText().toString();
        u.password=password.getText().toString();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
// Add a new document with a generated ID
        db.collection("user")
                .add(u)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("dasd", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("asd", "Error adding document", e);
            }
        });
    }


}

