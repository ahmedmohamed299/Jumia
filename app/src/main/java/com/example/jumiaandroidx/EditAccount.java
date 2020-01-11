package com.example.jumiaandroidx;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.jumiaandroidx.Model.User;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.jumiaandroidx.SignUp.RESULT_LOAD_IMAGE;

public class EditAccount extends AppCompatActivity {

    private static final String TAG = "EditAccount";
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.edit)
    TextView edit;
    @BindView(R.id.companyName)
    EditText companyName;
    @BindView(R.id.firstName)
    EditText firstName;
    @BindView(R.id.lastName)
    EditText lastName;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.changePassword)
    TextView changePassword;
    @BindView(R.id.save)
    ImageView save;
    User user;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @BindView(R.id.addPhoto)
    ConstraintLayout addPhoto;
    @BindView(R.id.Phone)
    EditText Phone;
    @BindView(R.id.profilePic)
    ImageView profilePic;
    @BindView(R.id.addIcon)
    ImageView addIcon;
    Uri selectedImage;
    Map<String, Object> addUser = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        ButterKnife.bind(this);
        getUserDetails();


        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_IMAGE);


                Toast.makeText(EditAccount.this, "Photo added", Toast.LENGTH_SHORT).show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadData();

            }
        });

    }

    private void getUserDetails() {

        firstName.setText(User.currentUser.getFirstName());
        lastName.setText(User.currentUser.getSecondName());
        email.setText(User.currentUser.getEmail());
        password.setText(User.currentUser.getPassword());
        companyName.setText(User.currentUser.getCompanyName());
        address.setText(User.currentUser.getAddress());
        Phone.setText(User.currentUser.getPhone());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {

            selectedImage = data.getData();
            profilePic.setImageURI(selectedImage);


        } else {
            Toast.makeText(this, "hii", Toast.LENGTH_SHORT).show();
        }

    }


    private void UploadData() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        Date date = new Date();
        StorageReference storageReference = storage.getReference();
        final StorageReference ref = storageReference.child(User.Contract.STORAGE+"/" + "testPROFILE" + ".jpg");
        Log.i(TAG, "ref: " + ref + "    selectedImage: " + selectedImage);
        UploadTask uploadTask = ref.putFile(selectedImage);

        Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();


                }
                return ref.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {

            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    addUser.put(User.Contract.FIELD_FIRST_NAME, firstName.getText().toString());
                    addUser.put(User.Contract.FIELD_SECOND_NAME, lastName.getText().toString());
                    addUser.put(User.Contract.FIELD_COMPANY_NAME, companyName.getText().toString());
                    addUser.put(User.Contract.FIELD_EMAIL, email.getText().toString());
                    addUser.put(User.Contract.FIELD_PASSWORD, password.getText().toString());
                    addUser.put(User.Contract.FIELD_ADDRESS, address.getText().toString());
                    addUser.put(User.Contract.FIELD_PHONE, Phone.getText().toString());
                    addUser.put(User.Contract.FIELD_PHOTO, downloadUri.toString());
                    db.collection(User.Contract.DOC)
                            .document(User.currentUser.getReference().getId())
                            .set(addUser, SetOptions.merge());

                                    Intent intent =new Intent(EditAccount.this,Home.class);
                                    startActivity(intent);


                }

            }
        });
    }


}
