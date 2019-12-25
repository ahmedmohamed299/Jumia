package com.example.jumiaandroidx.Model;

import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

public class Category {
    private DocumentReference reference;
    private String id;
    private String name;
    private String nameAr;

    public Category(DocumentReference reference) {
        this.reference = reference;
    }

    public Category(DocumentReference reference, String id, String name, String nameAr) {
        this.reference = reference;
        this.id = id;
        this.name = name;
        this.nameAr = nameAr;
    }
    public Category(DocumentReference reference, Map<String,Object> data) {
        this(reference);
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            switch (key) {
                case Category.Contract.FIELD_NAME:
                    name = (String) value;
                    break;
                    case Contract.FIELD_NAME_AR:
                    nameAr = (String) value;
                    break;
                case Category.Contract.FIELD_ID:
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

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(Category.Contract.FIELD_NAME, name);
        map.put(Contract.FIELD_ID, id);
        map.put(Contract.FIELD_NAME_AR, nameAr);

        return map;
    }

    public Object getReference() {
        return reference;
    }
        public static class Contract {
            public static final String DOC = "categories";
            public static final String FIELD_NAME = "name";
            public static final String FIELD_NAME_AR = "nameAr";
            public static final String FIELD_ID = "id" ;

        }
    }