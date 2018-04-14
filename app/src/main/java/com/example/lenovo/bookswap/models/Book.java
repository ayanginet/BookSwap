package com.example.lenovo.bookswap.models;


import java.io.Serializable;

/**
 * Created by Lenovo on 12.04.2018.
 */

public class Book implements Serializable {
    private String author, info, coverUrl, title, userID,  year, tags;
    private int status;

    public Book(){

    }

    /*public Book(String title, String author, int status, String year, String image, String userID, String info) {
        this.title = title;
        this.author = author;
        this.status = status;
        this.year = year;
        this.profileImg = image;
        this.userID = userID;
        this.info = info;
    }*/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String profileImg) {
        this.coverUrl = profileImg;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTags() {
        return tags;
    }

    public void setTag(String tags) {
        this.tags = tags;
    }

    public String toString(){
        String out = "{"+getAuthor()+", "+getStatus()+", "+getTitle()+", "+ getCoverUrl()+", "+getYear()+"} ";
        return out;
    }
}
