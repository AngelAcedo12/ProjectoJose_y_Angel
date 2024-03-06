package com.example.projectojose_y_angel.models.DTO;

import android.graphics.Bitmap;

public class DtoUserImg {
    private String user;
    private Bitmap bitmap;

    public DtoUserImg(String user, Bitmap bitmap) {
        this.user = user;
        this.bitmap = bitmap;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public String toString() {
        return "DtoUserImg{" +
                "user='" + user + '\'' +
                ", bitmap=" + bitmap.getByteCount() +
                '}';
    }
}
