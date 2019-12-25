package com.example.jumiaandroidx.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.jumiaandroidx.Model.FireStoreField;
import com.example.jumiaandroidx.Model.PurchaseRequest;
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

public class PurchaseRequestDEO {    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String path = "PurchaseRequest";

    //CRUD Create, Retrieve, Update, Delete
    public void create(PurchaseRequest purchaseRequest) {
        Map<String, Object> purchaseRequestMap = new HashMap<>();
        try {
            Class c = purchaseRequest.getClass();
            Field[] fields = c.getFields();
            for (Field field : fields) {
                FireStoreField fieldAnnotation = field.getAnnotation(FireStoreField.class);
                if (fieldAnnotation != null) {
                    purchaseRequestMap.put(fieldAnnotation.value(), field.get(purchaseRequest));
                }
            }
        } catch (Exception e) {
            Log.i("purchaseRequestDAO", "Exception happened");
        }


        db.collection(path)
                .add(purchaseRequestMap)
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

    public void delete(PurchaseRequest purchaseRequest) {
        db.collection(path).document(purchaseRequest.getId()).delete();
    }

    public void update(PurchaseRequest purchaseRequest) {
        Map<String, Object> purchaseRequestMap = new HashMap<>();
        try {
            Class c = purchaseRequest.getClass();
            Field[] fields = c.getFields();
            for (Field field : fields) {
                FireStoreField fieldAnnotation = field.getAnnotation(FireStoreField.class);
                if (fieldAnnotation != null) {
                    purchaseRequestMap.put(fieldAnnotation.value(), field.get(purchaseRequest));
                }
            }
        } catch (Exception e) {
            Log.i("purchaseRequestDAO", "Exception happened");
        }
        DocumentReference docRef = db.collection(
                path).document(purchaseRequest.getId());
        docRef.update(purchaseRequestMap).addOnSuccessListener(
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

    public List<PurchaseRequest> get() {
        final List<PurchaseRequest> purchaseRequests= new ArrayList<>();
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
                                PurchaseRequest purchaseRequest= new PurchaseRequest();
                                purchaseRequests.add(purchaseRequest);
                            }
                        } else {
                        }
                    }
                });
        return purchaseRequests;
    }

}
