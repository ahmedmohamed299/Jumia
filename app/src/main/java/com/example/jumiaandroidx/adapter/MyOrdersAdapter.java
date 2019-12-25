package com.example.jumiaandroidx.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jumiaandroidx.GlideApp;
import com.example.jumiaandroidx.Interface.ItemClickListener;
import com.example.jumiaandroidx.Model.Product;
import com.example.jumiaandroidx.Model.User;
import com.example.jumiaandroidx.R;
import com.example.jumiaandroidx.dao.UserDAO;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.model.value.ReferenceValue;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.Holder> {
    Context context;
    List<Product> products ;
    ItemClickListener itemClickListener;
    User user=new User();
    UserDAO userDAO=new UserDAO();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public MyOrdersAdapter(Context context, List<Product> products, ItemClickListener itemClickListener) {
        this.context = context;
        this.products = products;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.item_order, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        Product product = products.get(position);
//        DocumentReference messageRef = db
//                .collection("users").document();

//        userDAO.get(product.getUsers()).getFullName();
        Toast.makeText(context, ""+product.getPrice(), Toast.LENGTH_SHORT).show();
        if (product.getEnName() != null) {
            holder.itemName.setText(product.getEnName());
        } else {
            Toast.makeText(context, "get Name is null", Toast.LENGTH_SHORT).show();
        }
        if (product.getUser() != null) {
            if (user.getFirstName() != null) {
                user = new UserDAO().get(product.getUser().getReference());
                holder.checkPerson.setText(user.getFirstName()+" "+user.getSecondName());
            }
            else {
                Toast.makeText(context, "name null", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "get USer is null", Toast.LENGTH_SHORT).show();
        }



//        Picasso
//                .get()
//                .load("https://firebasestorage.googleapis.com/v0/b/jumia-9e4f4.appspot.com/o/images%2Fahmed.jpg?alt=media&token=c366f26f-9ca5-4f9c-be57-bfdeb8efb39f")
//                .into(holder.itemImage);

        Glide
                .with(context)
                .load(product.getPhotoURL())
                .into(holder.itemImage);

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView itemImage, more;
        TextView itemName, orderDate, checkPerson;
        ConstraintLayout layout;

        public Holder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            more = itemView.findViewById(R.id.more);
            itemName = itemView.findViewById(R.id.itemName);
            orderDate = itemView.findViewById(R.id.orderDate);
            checkPerson = itemView.findViewById(R.id.checkPerson);
            layout = itemView.findViewById(R.id.layout);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition());
        }
    }
}
