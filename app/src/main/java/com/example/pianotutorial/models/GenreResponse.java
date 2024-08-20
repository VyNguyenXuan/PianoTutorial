package com.example.pianotutorial.models;

import java.util.List;

public class GenreResponse {
    private String message;
    private int statusCode;
    private List<Genre> data;

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<Genre> getData() {
        return data;
    }

    public void setData(List<Genre> data) {
        this.data = data;
    }
}
