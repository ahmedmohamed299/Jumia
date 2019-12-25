package com.example.jumiaandroidx.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.jumiaandroidx.Model.FireStoreField;
import com.example.jumiaandroidx.Model.Review;
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

public class ReviewDEO {    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String path = "review";

    //CRUD Create, Retrieve, Update, Delete
    public void create(Review review) {
        Map<String, Object> reviewMap = new HashMap<>();
        try {
            Class c = review.getClass();
            Field[] fields = c.getFields();
            for (Field field : fields) {
                FireStoreField fieldAnnotation = field.getAnnotation(FireStoreField.class);
                if (fieldAnnotation != null) {
                    reviewMap.put(fieldAnnotation.value(), field.get(review));
                }
            }
        } catch (Exception e) {
            Log.i("ReviewDAO", "Exception happened");
        }


        db.collection(path)
                .add(reviewMap)
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

    public void delete(Review review) {
        db.collection(path).document(review.getId()).delete();
    }

    public void update(Review review) {
        Map<String, Object> reviewMap = new HashMap<>();
        try {
            Class c = review.getClass();
            Field[] fields = c.getFields();
            for (Field field : fields) {
                FireStoreField fieldAnnotation = field.getAnnotation(FireStoreField.class);
                if (fieldAnnotation != null) {
                    reviewMap.put(fieldAnnotation.value(), field.get(review));
                }
            }
        } catch (Exception e) {
            Log.i("ReviewDAO", "Exception happened");
        }
        DocumentReference docRef = db.collection(
                path).document(review.getId());
        docRef.update(reviewMap).addOnSuccessListener(
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

    public List<Review> get() {
        final List<Review> reviews= new ArrayList<>();
        db.collection(path)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Review review= new Review(document.getReference(),document.getData());
                                reviews.add(review);
                            }
                        } else {
                        }
                    }
                });
        return reviews;
    }

}
