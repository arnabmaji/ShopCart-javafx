package io.github.arnabmaji19.model;

import com.mongodb.BasicDBList;

public class User {
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private BasicDBList cart;

    public User(){}

    public User(String name, String email, String phoneNumber, String password, BasicDBList dbList) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.cart = dbList;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public BasicDBList getCart() {
        return cart;
    }
}
