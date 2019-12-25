package com.example.jumiaandroidx;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.jumiaandroidx.Model.User;
import com.google.firebase.firestore.FirebaseFirestore;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditAccount extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        ButterKnife.bind(this);
        getUserDetails();



        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EditAccount.this, "Photo added", Toast.LENGTH_SHORT).show();
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


}
