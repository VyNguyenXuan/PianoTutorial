package com.example.pianotutorial.models;

public class SongResponse {
    private String message;
    private int statusCode;
    private SongResponseByGenre data;

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

    public SongResponseByGenre getData() {
        return data;
    }

    public void setData(SongResponseByGenre data) {
        this.data = data;
    }
}