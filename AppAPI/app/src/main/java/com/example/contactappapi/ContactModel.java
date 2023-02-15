package com.example.contactappapi;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
public class ContactModel implements Serializable {
    private AvatarModel avatar;
    private String name;
    private String username;
    private String email;
    private String phone;
    private AddressModel address;
    public ContactModel(JSONObject json){
        try {
            this.avatar = new AvatarModel(new JSONObject(json.getString("avatar")));
            this.name = json.getString("name");
            this.username = json.getString("username");
            this.email = json.getString("email");
            this.phone = json.getString("phone");
            this.address = new AddressModel(new JSONObject(json.getString("address")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public AvatarModel getAvatar() {
        return avatar;
    }

    public void setAvatar(AvatarModel avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ContactModel{" +
                "avatar=" + avatar.toString() +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address=" + address.toString() +
                '}';
    }
}
