package com.example.jumiaandroidx.Model;

import com.example.jumiaandroidx.Model.Brand;
import com.example.jumiaandroidx.Model.Category;
import com.example.jumiaandroidx.Model.User;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Product {

    private String enName;
    private String arName;
    private String enDesc;
    private String arDesc;
    private List<String> enOptions;
    private List<String> arOptions;
    private double price;
    private Category category;
    private Brand brand;
    private User user;
    private String photoURL ;
    private DocumentReference reference;


    public Product(DocumentReference reference) {
        this.reference = reference;
    }

    public Product(String enName, String arName, String enDesc, String arDesc, List<String> enOptions, List<String> arOptions, double price, Category category, Brand brand, User user, String photoURL, DocumentReference reference) {

        this(reference);
        this.enName = enName;
        this.arName = arName;
        this.enDesc = enDesc;
        this.arDesc = arDesc;
        this.enOptions = enOptions;
        this.arOptions = arOptions;
        this.price = price;
        this.category = category;
        this.brand = brand;
        this.user = user;
        this.photoURL = photoURL;
    }




    // //





    public static class Contract {
        public static final String DOC = "products";
        public static final String STORAGE = "products/images";
        public static final String FIELD_NAME_En = "en_name";
        public static final String FIELD_NAME_AR = "ar_name";
        public static final String FIELD_Desc_En = "en_desc";
        public static final String FIELD_Desc_AR = "ar_desc";
        public static final String FIELD_PRICE = "price";
        public static final String FIELD_Options_EN = "en_options";
        public static final String FIELD_Options_AR = "ar_options";
        public static final String FIELD_PHOTO_URL = "photo_url";
        public static final String FIELD_brand = "brand";
        public static final String FIELD_category = "category";
        public static final String FIELD_user = "user";
    }








    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(Contract.FIELD_NAME_AR,arName);
        map.put(Contract.FIELD_NAME_En,enName);
        map.put(Contract.FIELD_Desc_En,enDesc);
        map.put(Contract.FIELD_Desc_AR,arDesc);
        map.put(Contract.FIELD_Options_EN,enOptions);
        map.put(Contract.FIELD_Options_AR,arOptions);
        map.put(Contract.FIELD_PRICE, price);
        map.put(Contract.FIELD_category,category);
        map.put(Contract.FIELD_brand,brand);
        map.put(Contract.FIELD_user,user);
        map.put(Contract.FIELD_PHOTO_URL,photoURL);

        return map;
    }



    public Product(DocumentReference reference, Map<String, Object> data) {
        this(reference);
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            switch (key) {
                case Contract.FIELD_NAME_AR:
                    arName = (String) value;
                    break;
                case  Contract.FIELD_NAME_En:
                    enName= (String) value ;
                    break;
                case  Contract.FIELD_Desc_AR:
                    arDesc= (String) value ;
                    break;
                case  Contract.FIELD_Desc_En:
                    enDesc= (String) value ;
                    break;
                case  Contract.FIELD_Options_AR:
                    arOptions= (List<String>) value ;
                    break;
                case  Contract.FIELD_Options_EN:
                    enOptions= (List<String>) value ;
                    break;
                case Contract.FIELD_PRICE:
                    price = (double) value;
                    break;
                case Contract.FIELD_PHOTO_URL:
                    photoURL = (String) value;
                    break;
                case Contract.FIELD_brand:
                    brand= new Brand((DocumentReference) value);
                    break;
                case Contract.FIELD_category:
                    category= new Category((DocumentReference) value);
                    break;
                case Contract.FIELD_user:
                    user= new User((DocumentReference) value);
                    break;

            }
        }
    }






    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getArName() {
        return arName;
    }

    public void setArName(String arName) {
        this.arName = arName;
    }

    public String getEnDesc() {
        return enDesc;
    }

    public void setEnDesc(String enDesc) {
        this.enDesc = enDesc;
    }

    public String getArDesc() {
        return arDesc;
    }

    public void setArDesc(String arDesc) {
        this.arDesc = arDesc;
    }

    public List<String> getEnOptions() {
        return enOptions;
    }

    public void setEnOptions(List<String> enOptions) {
        this.enOptions = enOptions;
    }

    public List<String> getArOptions() {
        return arOptions;
    }

    public void setArOptions(List<String> arOptions) {
        this.arOptions = arOptions;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public DocumentReference getReference() {
        return reference;
    }

    public void setReference(DocumentReference reference) {
        this.reference = reference;
    }








}
