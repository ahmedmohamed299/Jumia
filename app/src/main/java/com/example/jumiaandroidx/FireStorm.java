package com.example.jumiaandroidx;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class FireStorm {
    FirebaseFirestore db= FirebaseFirestore.getInstance();

    public void SetData(Map<String,Object> user, String path , final Activity activity){
      db.collection(path)
              .add(user)
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

public void getData(String path){

    db.collection(path)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                            }
                        }
                        else {

                        }
                    }
                });
}


}
