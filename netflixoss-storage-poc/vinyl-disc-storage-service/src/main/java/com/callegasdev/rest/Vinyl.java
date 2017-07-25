package com.callegasdev.rest;

public class Vinyl {
    private String album;
    private String artist;

    public Vinyl(String album, String artist) {
        this.album = album;
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return  "{album='" + album + '\'' +
                ", artist='" + artist + '\'' +
                '}';
    }
}
