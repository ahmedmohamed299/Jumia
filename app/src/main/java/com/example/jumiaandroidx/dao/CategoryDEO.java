package com.example.jumiaandroidx.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.jumiaandroidx.Model.Category;
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

public class CategoryDEO {    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String path = "Category";

    //CRUD Create, Retrieve, Update, Delete
    public void create(Category category) {
        Map<String, Object> categoryMap = new HashMap<>();
        try {
            Class c = category.getClass();
            Field[] fields = c.getFields();
            for (Field field : fields) {
                FireStoreField fieldAnnotation = field.getAnnotation(FireStoreField.class);
                if (fieldAnnotation != null) {
                    categoryMap.put(fieldAnnotation.value(), field.get(category));
                }
            }
        } catch (Exception e) {
            Log.i("categoryDAO", "Exception happened");
        }


        db.collection(path)
                .add(categoryMap)
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

    public void delete(Category category) {
        db.collection(path).document(category.getId()).delete();
    }

    public void update(Category category) {
        Map<String, Object> categoryMap = new HashMap<>();
        try {
            Class c = category.getClass();
            Field[] fields = c.getFields();
            for (Field field : fields) {
                FireStoreField fieldAnnotation = field.getAnnotation(FireStoreField.class);
                if (fieldAnnotation != null) {
                    categoryMap.put(fieldAnnotation.value(), field.get(category));
                }
            }
        } catch (Exception e) {
            Log.i("categoryDAO", "Exception happened");
        }
        DocumentReference docRef = db.collection(
                path).document(category.getId());
        docRef.update(categoryMap).addOnSuccessListener(
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

    public List<Category> get() {
        final List<Category> categorys = new ArrayList<>();
        db.collection(path)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Category category = new Category(document.getReference(),document.getData());
                                categorys.add(category);
                            }
                        } else {
                        }
                    }
                });
        return categorys;
    }

}
