package com.example.jumiaandroidx.Model;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Brand {
    private DocumentReference reference;
    private String id;
    private String name;
    private String name_ar;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public Brand(DocumentReference reference) {
        this.reference = reference;
    }

    public Brand(DocumentReference reference, String id, String name , String name_ar) {
        this.reference = reference;
        this.id = id;
        this.name = name;
        this.name_ar = name_ar;
    }

    public Brand(DocumentReference reference, Map <String,Object> data) {
        this(reference);
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            switch (key) {
                case Brand.Contract.FIELD_NAME:
                    name = (String) value;
                    break;
                    case Brand.Contract.FIELD_NAME_AR:
                        name_ar = (String) value;
                    break;
                case Brand.Contract.FIELD_ID:
                    id = (String) value;
                    break;

            }
        }
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_ar() {
        return name_ar;
    }

    public void setName_ar(String name_ar) {
        this.name_ar = name_ar;
    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(Brand.Contract.FIELD_NAME, name);
        map.put(Contract.FIELD_ID, id);
        map.put(Contract.FIELD_NAME_AR, name_ar);


        return map;
    }

    public Object getReference() {
        return reference;
    }


    public static class Contract {
        public static final String DOC = "brands";
        public static final String FIELD_NAME = "name";
        public static final String FIELD_ID = "id" ;
        public static final String FIELD_NAME_AR = "name_ar" ;
    }
}



