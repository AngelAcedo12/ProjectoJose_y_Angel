package com.example.projectojose_y_angel.models;

import android.graphics.Bitmap;
import android.net.Uri;

public class Image {
    private String volumeName;
    private int id;
    private Uri uri;

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


    public String getVolumeName() {
        return volumeName;
    }

    public void setVolumeName(String volumeName) {
        this.volumeName = volumeName;
    }
}
