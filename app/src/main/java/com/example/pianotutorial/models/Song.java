package com.example.pianotutorial.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Song {
    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("composer")
    private String composer;

    @SerializedName("genre")
    private String genre;

    @SerializedName("artistId")
    private int artistId;

    @SerializedName("artist")
    private Artist artist;

    @SerializedName("sheets")
    private List<Sheet> sheets;

    public Song(int id, String title, String composer, String genre, int artistId, Artist artist, List<Sheet> sheets) {
        this.id = id;
        this.title = title;
        this.composer = composer;
        this.genre = genre;
        this.artistId = artistId;
        this.artist = artist;
        this.sheets = sheets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<Sheet> getSheets() {
        return sheets;
    }

    public void setSheets(List<Sheet> sheets) {
        this.sheets = sheets;
    }
}
