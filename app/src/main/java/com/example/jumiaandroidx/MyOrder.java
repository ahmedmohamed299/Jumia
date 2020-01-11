package com.example.jumiaandroidx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.jumiaandroidx.Interface.ItemClickListener;
import com.example.jumiaandroidx.Model.Product;
import com.example.jumiaandroidx.adapter.MyOrdersAdapter;
import com.example.jumiaandroidx.dao.ProductDEO;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyOrder extends AppCompatActivity implements ItemClickListener {

    MyOrdersAdapter adapter;
    LinearLayoutManager linearLayout;
    RecyclerView orderRecycler;
    List<Product> products = new ArrayList<>();
    ProductDEO productDEO = new ProductDEO();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        orderRecycler = findViewById(R.id.orderRecycler);
        final List<Product> products = new ArrayList<>();


        getProducts();


    }

    private void getProducts() {

        db.collection(Product.Contract.DOC)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {


                            for (QueryDocumentSnapshot document : task.getResult()) {


//                                DocumentReference category = (DocumentReference) document.getData().get("Category");
//                                DocumentReference Brand = (DocumentReference) document.getData().get("Brand");
//                                DocumentReference user = (DocumentReference) document.getData().get("user");
//                                String enName = (String) document.getData().get("name");
//                                String id = (String) document.getData().get("id");
//                                String arName = (String) document.getData().get("name_ar");
//                                String enDesc = (String) document.getData().get("Desc_en");
//                                String arDesc = (String) document.getData().get("Desc_ar");
//                                List enOptions = (List) document.getData().get("enOptions");
//                                List arOptions = (List) document.getData().get("arOptions");
//                                String Price = (String) document.getData().get("Price");
//                                String photo_url= (String) document.getData().get("photo_url");
//                                Product product = new Product(id, enName, arName, enDesc,arDesc, enOptions, arOptions, Price, category, Brand, user,photo_url);





                                db.collection(Product.Contract.DOC)
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull final Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {

                                                    products = new ArrayList<>();
                                                    for (QueryDocumentSnapshot document : task.getResult()) {

                                                        Map<String, Object> data = document.getData();
                                                        final Product product= new Product(document.getReference(), data);
                                                        products.add(product);
                                                        adapter = new MyOrdersAdapter(MyOrder.this, products, MyOrder.this);
                                                        orderRecycler.setAdapter(adapter);



                                                    }
//
//                                                    Review r = null;
//                                                    r.getProduct().getPhotoUrl();
                                                }
                                            }
                                        });

                            }


                        } else {
                        }
                    }
                });

    }

    @Override
    public void onClick(View view, int position) {

    }
}
