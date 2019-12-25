package com.example.jumiaandroidx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jumiaandroidx.Model.Review;
import com.example.jumiaandroidx.Model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    final static int RESULT_LOAD_IMAGE=55;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FireStorm fireStorm =new FireStorm();
    User user;
    EditText companyName,firstName,lastName,email,password,address,Phone;
    ImageView save;
    ProgressBar progressBar;
    Spinner spinner;
    Map<String,Object> addUser =new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        companyName=findViewById(R.id.companyName);
        firstName=findViewById(R.id.firstName);
        lastName=findViewById(R.id.lastName);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        address=findViewById(R.id.address);
        Phone=findViewById(R.id.Phone);
        progressBar=findViewById(R.id.progressBar);
        save=findViewById(R.id.save);


    }

    private void addUser() {
        progressBar.setVisibility(View.VISIBLE);
        addUser.put(User.Contract.FIELD_FIRST_NAME,firstName.getText().toString());
        addUser.put(User.Contract.FIELD_SECOND_NAME,lastName.getText().toString());
        addUser.put(User.Contract.FIELD_COMPANY_NAME,companyName.getText().toString());
        addUser.put(User.Contract.FIELD_EMAIL,email.getText().toString());
        addUser.put(User.Contract.FIELD_PASSWORD,password.getText().toString());
        addUser.put(User.Contract.FIELD_ADDRESS,address.getText().toString());
        addUser.put(User.Contract.FIELD_PHONE,Phone.getText().toString());


        db.collection(User.Contract.DOC)
                .add(addUser)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Intent intent =new Intent(SignUp.this,Home.class);
                        startActivity(intent);
                        progressBar.setVisibility(View.GONE);
                        user.setReference(documentReference);
                        User.currentUser=new User(documentReference,addUser);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(SignUp.this, "Please try again", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void signUp(View view) {
        addUser();

    }
}
