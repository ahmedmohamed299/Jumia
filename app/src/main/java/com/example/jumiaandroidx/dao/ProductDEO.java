package com.example.jumiaandroidx.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.jumiaandroidx.Model.Brand;
import com.example.jumiaandroidx.Model.Category;
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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDEO {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String path = "products";

    //CRUD Create, Retrieve, Update, Delete
    public void create(Product product) {
        Map<String, Object> productMap = new HashMap<>();
        try {
            Class c = product.getClass();
            Field[] fields = c.getFields();
            for (Field field : fields) {
                FireStoreField fieldAnnotation = field.getAnnotation(FireStoreField.class);
                if (fieldAnnotation != null) {
                    productMap.put(fieldAnnotation.value(), field.get(product));
                }
            }
        } catch (Exception e) {
            Log.i("productDAO", "Exception happened");
        }


        db.collection(path)
                .add(productMap)
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

    public void delete(Product product) {
        db.collection(path).document(product.getReference().getId()).delete();
    }

    public void update(Product product) {
        Map<String, Object> productMap = new HashMap<>();
        try {
            Class c = product.getClass();
            Field[] fields = c.getFields();
            for (Field field : fields) {
                FireStoreField fieldAnnotation = field.getAnnotation(FireStoreField.class);
                if (fieldAnnotation != null) {
                    productMap.put(fieldAnnotation.value(), field.get(product));
                }
            }
        } catch (Exception e) {
            Log.i("productDAO", "Exception happened");
        }
        DocumentReference docRef = db.collection(
                path).document(product.getReference().getId());
        docRef.update(productMap).addOnSuccessListener(
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

    public List<Product> get() {
        final List<Product> products= new ArrayList<>();
        db.collection(path)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Product product = new Product(document.getReference(), document.getData());
                                products.add(product);
                            }
                        } else {
                        }
                    }
                });
        return products;
    }

}
