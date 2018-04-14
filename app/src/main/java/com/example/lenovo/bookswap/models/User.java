package com.example.lenovo.bookswap.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 13.04.2018.
 */

public class User {
    private String name, email, profileUrl, id;
    private List<MyBook> books;


    public User(){

    }
    public User(String id, String name, String email, String profileUrl){
        this.name = name;
        this.email = email;
        this.books = new ArrayList<>();
        this.profileUrl = profileUrl;
        this.id =  id;
    }
    public User(String name,  String email, String profileUrl, List<MyBook> books, String id) {
        this.name = name;
        this.email = email;
        this.books = books;
        this.profileUrl = profileUrl;
        this.id =  id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public List<MyBook> getBooks() {
        return books;
    }

    public void setBooks(List<MyBook> books) {
        this.books = books;
    }
}
