package com.example.projectojose_y_angel.models;

import android.graphics.Bitmap;
import android.net.Uri;

public class Image {
    private String volumeName;
    private int id;
    private Bitmap image;
    private Bitmap imageCompress;

    public Image(String volumeName, int id) {
        this.volumeName = volumeName;
        this.id = id;
    }

    public Image() {
    }

    public Bitmap getImage() {
        return image;
    }


    public Bitmap getImageCompress() {
        return imageCompress;
    }

    public void setImageCompress(Bitmap imageCompress) {
        this.imageCompress = imageCompress;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Image(String volumeName) {
        this.volumeName = volumeName;
    }

    public String getVolumeName() {
        return volumeName;
    }

    public void setVolumeName(String volumeName) {
        this.volumeName = volumeName;
    }
}
