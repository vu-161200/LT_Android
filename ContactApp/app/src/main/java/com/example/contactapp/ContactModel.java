package com.example.contactapp;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.UUID;

import io.bloco.faker.Faker;

public class ContactModel implements Serializable {
    private String id;
    private String name;
    private String phoneNumber;
    private String email;

    public ContactModel() {
        Faker faker = new Faker();
        this.id = UUID.randomUUID().toString();
        this.name = faker.name.name();
        this.phoneNumber = faker.phoneNumber.cellPhone();
        this.email = faker.internet.email();
    }

    public ContactModel(String id, String name, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    @Override
    public String toString() {
        return "ContactModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


}
