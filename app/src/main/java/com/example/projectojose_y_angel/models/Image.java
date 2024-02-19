package com.example.projectojose_y_angel.models;

import android.graphics.Bitmap;
import android.net.Uri;

import java.time.LocalDate;
import java.util.Date;

public class Image {
    private String volumeName;
    private int id;
    private Uri uri;
    public LocalDate date;
    public Image() {
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getVolumeName() {
        return volumeName;
    }

    public void setVolumeName(String volumeName) {
        this.volumeName = volumeName;
    }
}
