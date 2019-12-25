package com.example.jumiaandroidx.Model;

import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

public class User {

    private String id;
    @FireStoreField("firstName")
    private String firstName;
    @FireStoreField("secondName")
    private String secondName;
    @FireStoreField("company_name")
    private String companyName;
    @FireStoreField("photo")
    private String photo;
    @FireStoreField("phone")
    private String phone;
    @FireStoreField("address")
    private String address;
    @FireStoreField("email")
    private String email;
    @FireStoreField("password")
    private String password;
    public static User currentUser;
    private DocumentReference reference;


    public User() {
    }

    public User(DocumentReference reference) {
        this.reference = reference;
    }

    public User(String id, String firstName,String secondName, String companyName, String photo, String phone, String address, String email, String password, DocumentReference reference) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.companyName = companyName;
        this.photo = photo;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;
        this.reference = reference;
    }


    public User(DocumentReference reference, Map<String, Object> data) {
        this(reference);
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            switch (key) {
                case Contract.FIELD_FIRST_NAME:
                    firstName = (String) value;
                    break;
                case Contract.FIELD_SECOND_NAME:
                    secondName = (String) value;
                    break;
                case Contract.FIELD_PHONE:
                    phone = (String) value;
                    break;
                case    Contract.FIELD_ADDRESS:
                    address = (String) value;
                    break;
                case    Contract.FIELD_EMAIL:
                    email = (String) value;
                    break;
                case    Contract.FIELD_PHOTO:
                    photo = (String) value;
                    break;
                case    Contract.FIELD_PASSWORD:
                    password = (String) value;
                    break;
                case    Contract.FIELD_COMPANY_NAME:
                    companyName = (String) value;
                    break;
            }
        }
    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(Contract.FIELD_FIRST_NAME, firstName);
        map.put(Contract.FIELD_PHOTO, photo);
        map.put(Contract.FIELD_PHONE, phone);
        map.put(Contract.FIELD_ADDRESS, address);
        map.put(Contract.FIELD_EMAIL, email);
        map.put(Contract.FIELD_PASSWORD, password);
        map.put(Contract.FIELD_COMPANY_NAME, companyName);
        return map;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
    public String getSecondName() {
        return secondName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DocumentReference getReference() {
        return reference;
    }

    public void setReference(DocumentReference reference) {
        this.reference = reference;
    }

    public static class Contract {
        public static final String DOC = "users";
        public static final String STORAGE = "users/images";
        public static final String FIELD_FIRST_NAME = "firstName";
        public static final String FIELD_SECOND_NAME = "secondName";
        public static final String FIELD_PHOTO = "photo";
        public static final String FIELD_PHONE = "phone";
        public static final String FIELD_ADDRESS = "address";
        public static final String FIELD_EMAIL = "email";
        public static final String FIELD_PASSWORD = "password";
        public static final String FIELD_COMPANY_NAME = "company_name";


    }






}
