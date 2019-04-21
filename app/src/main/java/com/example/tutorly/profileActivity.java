package com.example.tutorly;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

/*The profile screen for a user*/
public class profileActivity extends AppCompatActivity {

    /*Variable Declarations*/
    private static final int CHOOSE_IMAGE = 101;
    ImageView imageView; //Where user profile picture is displayed
    EditText name, university; //Where user name is displayed
    Uri uriProfileImage;
    String profileImageUrl;
    FirebaseAuth mAuth;
    FirebaseUser user;
    DatabaseReference databaseUsers;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        databaseUsers = FirebaseDatabase.getInstance().getReference("users"); //reference to user database

        name = (EditText) findViewById(R.id.editTextName); //EditText box for user's name
        imageView = (ImageView) findViewById(R.id.imageView); //ImageView for user's profile picture
        university = (EditText) findViewById(R.id.editTextUniversity);

        imageView.setOnClickListener(new View.OnClickListener() { //When profile picture is clicked on
            @Override
            public void onClick (View v) {
                showImageChooser(); //Show the phone's image selection screen
            }
        });

        Button btn = (Button)findViewById(R.id.save_profile_button); //Button to save profile information

        btn.setOnClickListener(new View.OnClickListener() { //When save button is pressed
            @Override
            public void onClick(View v) {
                saveUserInformation(); //Save the user's information
            }
        });

        loadUserInformation(); //Load a user's information from FireBase

        /*Adds bottom navigation bar to profile screen*/
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.profile_item: //Open Profile screen when button is pressed
                                startActivity(new Intent(profileActivity.this, profileActivity.class));
                                break;
                            case R.id.settings_item: //Open Settings screen when button is pressed
                                startActivity(new Intent(profileActivity.this, AccountActivity.class));
                                break;
                            case R.id.home_item: //Open Home screen when button is pressed
                                startActivity(new Intent(profileActivity.this, MainActivity.class));
                                break;
                            case R.id.scheduled_item: //Open Scheduled Sessions screen when button is pressed
                                startActivity(new Intent(profileActivity.this, SessionsScheduledActivity.class));
                                break;
                            case R.id.requested_item: //Open Requested Sessions screen when button is pressed
                                startActivity(new Intent(profileActivity.this, SessionRequestsActivity.class));
                                break;
                        }
                        return true;
                    }
                });

    }

    /*Loads a user's information from FireBase to the Profile Activity*/
    private void loadUserInformation() {

        user = mAuth.getCurrentUser(); //get the FireBase user

        if(user != null) {
            if (user.getPhotoUrl() != null) { //If user exists and they have a profile image
                Glide.with(this)
                        .load(user.getPhotoUrl().toString())
                        .into(imageView); //Load the profile image to the ImageView
            }
            if (user.getDisplayName() != null) { //If user exists and they have a display name
                name.setText(user.getDisplayName()); //Load the display name to the text box
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            uriProfileImage = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriProfileImage);
                imageView.setImageBitmap(bitmap);

                uploadImageToFirebaseStorage(); //Uploads the profile image to the database

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*Saves a user's information to FireBase*/
    private void saveUserInformation() {
        String displayName = name.getText().toString();

        user = mAuth.getCurrentUser();

        if(displayName.isEmpty()) { //If no name is entered, require a name
            name.setError("Name required");
            name.requestFocus();
            return;
        }

        if(user != null && profileImageUrl != null) { //If both fields have information
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setDisplayName(displayName)
                    .setPhotoUri(Uri.parse(profileImageUrl))
                    .build(); //Change the information in FireBase
            user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()) { //When profile information is updated
                        Toast.makeText(profileActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

/*Uploads an image to FireBase storage*/
    private void uploadImageToFirebaseStorage() {
        final StorageReference profileImageRef = FirebaseStorage.getInstance().getReference("profilepics/"+System.currentTimeMillis() + ".jpg"); //location of storage

        if(uriProfileImage != null) { //If profile image exists
            profileImageRef.putFile(uriProfileImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            profileImageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) { //If image is successfully uploaded
                                    profileImageUrl = uri.toString();
                                    Toast.makeText(getApplicationContext(), "Image Upload Successful", Toast.LENGTH_SHORT).show();
                                }
                            })
                                    .addOnFailureListener(new OnFailureListener() { //If image cannot be uploaded
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    });
                        }
                     })
                    .addOnFailureListener(new OnFailureListener() { //If image cannot be uploaded
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(profileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void showImageChooser() { //Opens the image selection screen
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Profile Image"), CHOOSE_IMAGE); //opens image selector activity
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (user.getDisplayName() == null) {
            databaseUsers.child(user.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    name.setText(snapshot.child("fullName").getValue().toString());
                    university.setText(snapshot.child("university").getValue().toString());
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.w(TAG, "Failed to read value.", databaseError.toException());
                }
            });
        }
    }
}
