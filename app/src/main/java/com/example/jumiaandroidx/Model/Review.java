package com.example.jumiaandroidx.Model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

public class Review {
    private  User user;
    private DocumentReference reference;
    private String id;
    private String comment;
    private long rate;
    private Product product;
    private Client client;
    private Timestamp time;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DocumentReference getReference() {
        return reference;
    }

    public void setReference(DocumentReference reference) {
        this.reference = reference;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return (comment ==null)?"":comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getRate() {
        return rate;
    }

    public void setRate(long rate) {
        this.rate = rate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Review(DocumentReference reference) {
        this.reference = reference;
    }

    public Review(DocumentReference reference, String id, String comment, long rate, Product product, Client client, Timestamp time) {
        this(reference) ;
        this.id = id;
        this.comment = comment;
        this.rate = rate;
        this.product = product;
        this.client = client;
        this.time = time;
    }

    public Review(DocumentReference reference, Map <String,Object> data) {
        this (reference);
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            switch (key) {
                case Review.Contract.FIELD_RATE:
                    rate = (long) value;
                    break;
                case Review.Contract.FIELD_TIME:
                    time = (Timestamp) value;
                    break;
                    case Review.Contract.FIELD_COMMENT:
                    comment = (String) value;
                    break;
                    case Review.Contract.FIELD_CLIENT:
                    user = new User((DocumentReference) value);
                    break;
                    case Review.Contract.FIELD_PRODUCT:
                    product = new Product ((DocumentReference) value);
                    break;
                    case Review.Contract.FIELD_ID:
                    product = new Product ((DocumentReference) value);
                    break;    }
        }
    }
    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(Review.Contract.FIELD_RATE, rate);
        map.put(Review.Contract.FIELD_COMMENT, comment);
        map.put(Review.Contract.FIELD_TIME, time);
        map.put(Contract.FIELD_CLIENT, client.getReference());
        map.put(Review.Contract.FIELD_PRODUCT, product.getReference());
        return map;
    }
public static class Contract {
    public static final String DOC = "review";
    public static final String FIELD_RATE = "rate";
    public static final String FIELD_COMMENT = "comment";
    public static final String FIELD_PRODUCT = "product";
    public static final String DATE_PATTERN = "dd/MMM/yyyy";
    public static final String FIELD_TIME = "time" ;
    public static final String FIELD_ID = "id" ;
    public static final String FIELD_CLIENT = "client" ;
} }


