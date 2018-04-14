package com.example.lenovo.bookswap.models;

/**
 * Created by Lenovo on 13.04.2018.
 */

public class MyBook {
    private Book book;
    private int status;
    private String returnDate, deadline;

    public MyBook(Book book, int status, String returnDate, String deadline) {
        this.book = book;
        this.status = status;
        this.returnDate = returnDate;
        this.deadline = deadline;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
