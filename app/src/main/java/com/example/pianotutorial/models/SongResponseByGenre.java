package com.example.pianotutorial.models;

import java.util.List;

public class SongResponseByGenre {
    private List<Song> songResponseByGenre;
    private int totalPage;
    private int pageNum;

    // Getters and Setters
    public List<Song> getSongResponseByGenre() {
        return songResponseByGenre;
    }

    public void setSongResponseByGenre(List<Song> songResponseByGenre) {
        this.songResponseByGenre = songResponseByGenre;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
