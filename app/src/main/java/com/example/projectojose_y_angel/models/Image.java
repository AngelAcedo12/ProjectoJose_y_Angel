package com.example.projectojose_y_angel.models;

import android.graphics.Bitmap;
import android.net.Uri;

import java.time.LocalDate;
import java.util.Date;

public class Image  {
    private String volumeName;

    private int id;
    private Uri uri;
    private Bitmap bitmap;

    public LocalDate date;
    private boolean checked;
    private String type;
    public Image() {
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Image(Bitmap bitmap, String type) {
        this.bitmap = bitmap;
        this.type = type;
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
