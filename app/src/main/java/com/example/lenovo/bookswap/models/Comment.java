package com.example.lenovo.bookswap.models;

/**
 * Created by Lenovo on 14.04.2018.
 */

public class Comment {
    private String text, userId, userName, profileUrl, date, bookId;

    public Comment(String text, String userId,String progfileUrl, String date, String userName) {
        this.text = text;
        this.userId = userId;
        this.date = date;
        this.profileUrl = progfileUrl;
        this.userName = userName;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String progfileUrl) {
        this.profileUrl = progfileUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
