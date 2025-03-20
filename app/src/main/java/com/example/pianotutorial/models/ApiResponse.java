package com.example.pianotutorial.models;

import java.util.List;

import java.util.List;

import java.util.List;

public class ApiResponse {
    private String message;
    private int statusCode;
    private SongData data;

    public SongData getData() {
        return data;
    }
}

class SongData {
    private List<ArtistResponse> songResponseByArtists;

    public List<ArtistResponse> getSongResponseByArtists() {
        return songResponseByArtists;
    }
}

class ArtistResponse {
    private List<Song> songs;

    public List<Song> getSongs() {
        return songs;
    }
}
