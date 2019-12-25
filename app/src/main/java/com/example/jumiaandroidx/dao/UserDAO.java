package com.example.jumiaandroidx.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.jumiaandroidx.Model.FireStoreField;
import com.example.jumiaandroidx.Model.Product;
import com.example.jumiaandroidx.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAO {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String path = "users";
    User users;

    //CRUD Create, Retrieve, Update, Delete
    public void create(User user) {
        Map<String, Object> userMap = new HashMap<>();
        try {
            Class c = user.getClass();
            Field[] fields = c.getFields();
            for (Field field : fields) {
                FireStoreField fieldAnnotation = field.getAnnotation(FireStoreField.class);
                if (fieldAnnotation != null) {
                    userMap.put(fieldAnnotation.value(), field.get(user));
                }
            }
        } catch (Exception e) {
            Log.i("userDAO", "Exception happened");
        }


        db.collection(path)
                .add(userMap)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }

    public void delete(User user) {
        db.collection(path).document(user.getId()).delete();
    }

    public void update(User user) {
        Map<String, Object> userMap = new HashMap<>();
        try {
            Class c = user.getClass();
            Field[] fields = c.getFields();
            for (Field field : fields) {
                FireStoreField fieldAnnotation = field.getAnnotation(FireStoreField.class);
                if (fieldAnnotation != null) {
                    userMap.put(fieldAnnotation.value(), field.get(user));
                }
            }
        } catch (Exception e) {
            Log.i("userDAO", "Exception happened");
        }
        DocumentReference docRef = db.collection(
                path).document(user.getId());
        docRef.update(userMap).addOnSuccessListener(
                new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });

    }

    public List<User> get(String path) {
        final List<User> users = new ArrayList<>();
        db.collection(this.path)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String id = document.getId();
                                String firstName = (String) document.getData().get("firstName");
                                String secondName = (String) document.getData().get("secondName");
                                String phone = (String) document.getData().get("phone");
                                String email = (String) document.getData().get("email");
                                String address = (String) document.getData().get("address");
                                String companyName = (String) document.getData().get("company_name");
                                String photo = (String) document.getData().get("photo");
                                String password = (String) document.getData().get("password");
                                User user = new User(id,firstName,secondName, companyName, photo, phone, address, email, password,document.getReference());
                                users.add(user);
                            }
                        } else {
                        }
                    }
                });
        return users;
    }

    public User get(DocumentReference reference) {

        final List<User> user=new ArrayList<>();

        db.collection(reference.getPath())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                user.setId(document.getId());
//                                user.setFullName((String) document.getData().get("fullName"));
                                String id =(String) document.getData().get("id");
                                String firstName=(String) document.getData().get("firstName");
                                String secondName=(String) document.getData().get("secondName");
                                String phone = (String) document.getData().get("phone");
                                String email = (String) document.getData().get("email");
                                String address = (String) document.getData().get("address");
                                String companyName = (String) document.getData().get("company_name");
                                String photo = (String) document.getData().get("photo");
                                String password = (String) document.getData().get("password");
                                users = new User(id,firstName,secondName, companyName, photo, phone, address, email, password,document.getReference());
                                user.add(users);

                            }
                        } else {
                        }
                    }
                });
        return users;
    }

}
