package com.example.jumiaandroidx.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.jumiaandroidx.Model.Brand;
import com.example.jumiaandroidx.Model.FireStoreField;
import com.example.jumiaandroidx.Model.Product;
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

public class BrandDEO {    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String path = "brand";

    //CRUD Create, Retrieve, Update, Delete
    public void create(Brand brand) {
        Map<String, Object> brandMap = new HashMap<>();
        try {
            Class c = brand.getClass();
            Field[] fields = c.getFields();
            for (Field field : fields) {
                FireStoreField fieldAnnotation = field.getAnnotation(FireStoreField.class);
                if (fieldAnnotation != null) {
                    brandMap.put(fieldAnnotation.value(), field.get(brand));
                }
            }
        } catch (Exception e) {
            Log.i("brandDAO", "Exception happened");
        }


        db.collection(path)
                .add(brandMap)
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

    public void delete(Brand brand) {
        db.collection(path).document(brand.getId()).delete();
    }

    public void update(Brand brand) {
        Map<String, Object> brandMap = new HashMap<>();
        try {
            Class c = brand.getClass();
            Field[] fields = c.getFields();
            for (Field field : fields) {
                FireStoreField fieldAnnotation = field.getAnnotation(FireStoreField.class);
                if (fieldAnnotation != null) {
                    brandMap.put(fieldAnnotation.value(), field.get(brand));
                }
            }
        } catch (Exception e) {
            Log.i("brandDAO", "Exception happened");
        }
        DocumentReference docRef = db.collection(
                path).document(brand.getId());
        docRef.update(brandMap).addOnSuccessListener(
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

    public List<Brand> get() {
        final List<Brand> brands = new ArrayList<>();
        db.collection(path)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Brand brand= new Brand(document.getReference(),document.getData());
                                brands.add(brand);
                            }
                        } else {
                        }
                    }
                });
        return brands;
    }

}
