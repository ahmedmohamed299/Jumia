package com.example.jumiaandroidx.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.jumiaandroidx.Model.Client;
import com.example.jumiaandroidx.Model.FireStoreField;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientDEO {    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String path = "Client";

    //CRUD Create, Retrieve, Update, Delete
    public void create(Client client) {
        Map<String, Object> clientMap = new HashMap<>();
        try {
            Class c = client.getClass();
            Field[] fields = c.getFields();
            for (Field field : fields) {
                FireStoreField fieldAnnotation = field.getAnnotation(FireStoreField.class);
                if (fieldAnnotation != null) {
                    clientMap.put(fieldAnnotation.value(), field.get(client));
                }
            }
        } catch (Exception e) {
            Log.i("clientDAO", "Exception happened");
        }


        db.collection(path)
                .add(clientMap)
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

    public void delete(Client client) {
        db.collection(path).document(client.getId()).delete();
    }

    public void update(Client client) {
        Map<String, Object> clientMap = new HashMap<>();
        try {
            Class c = client.getClass();
            Field[] fields = c.getFields();
            for (Field field : fields) {
                FireStoreField fieldAnnotation = field.getAnnotation(FireStoreField.class);
                if (fieldAnnotation != null) {
                    clientMap.put(fieldAnnotation.value(), field.get(client));
                }
            }
        } catch (Exception e) {
            Log.i("clientDAO", "Exception happened");
        }
        DocumentReference docRef = db.collection(
                path).document(client.getId());
        docRef.update(clientMap).addOnSuccessListener(
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

    public List<Client> get() {
        final List<Client> clients = new ArrayList<>();
        db.collection(path)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String id = document.getId();
                                String fullName = (String) document.getData().get("full_name");
                                String phone = (String) document.getData().get("phone");
                                String email = (String) document.getData().get("email");
                                String address = (String) document.getData().get("address");
                                String companyName = (String) document.getData().get("company_name");
                                String photo = (String) document.getData().get("photo");
                                String password = (String) document.getData().get("password");
                                Client client= new Client();
                                clients.add(client);
                            }
                        } else {
                        }
                    }
                });
        return clients;
    }

}
