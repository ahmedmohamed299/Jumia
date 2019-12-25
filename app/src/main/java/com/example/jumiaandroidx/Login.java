package com.example.jumiaandroidx;

import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.jumiaandroidx.Model.Product;
import com.example.jumiaandroidx.Model.User;
import com.example.jumiaandroidx.adapter.MyOrdersAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends AppCompatActivity {

    Animation animation;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.passWord)
    EditText passWord;
    @BindView(R.id.forget)
    TextView forget;
    @BindView(R.id.signUp)
    TextView signUp;
    @BindView(R.id.skip)
    TextView skip;
    @BindView(R.id.container)
    ConstraintLayout container;
    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.layout)
    ConstraintLayout layout;
    @BindView(R.id.login)
    ImageView login;

    boolean check = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        startAnim();


    }

    private void startAnim() {
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slidedownup);
                logo.startAnimation(animation);


                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        TransitionDrawable transition = (TransitionDrawable) layout.getBackground();
                        transition.startTransition(2000);
                        logo.setClickable(false);

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        container.setVisibility(View.VISIBLE);
                        logo.setClickable(false);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
    }

    public void signUp(View view) {
        Intent intent=new Intent(Login.this,SignUp.class);
        startActivity(intent);
    }

    private void LoginUser() {
        db.collection(User.Contract.DOC)
                .whereEqualTo(User.Contract.FIELD_EMAIL, email.getText().toString())
                .whereEqualTo(User.Contract.FIELD_PASSWORD, passWord.getText().toString())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                check=true;
                                Map<String, Object> data = document.getData();
                                User.currentUser = new User(document.getReference(),data);
                                Intent intent = new Intent(Login.this, EditAccount.class);
                                startActivity(intent);


                            }
                        }
                        if (check==false){
                            Toast.makeText(Login.this, "Please Try Again ", Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }

    public void skip(View view) {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);

    }

    public void forgetPassword(View view) {

    }


    @Override
    protected void onPause() {
        super.onPause();
        check=false;
        Login.this.finish();
    }

    public void login(View view) {
        LoginUser();

    }
}
