package com.example.projectojose_y_angel.repositorys;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;

import com.example.projectojose_y_angel.models.Image;
import com.example.projectojose_y_angel.utils.loaderImageInBackGround;

import java.util.ArrayList;
import java.util.List;

public class RepositoryImageInSmartphone implements Repository {

    private List<Image> images = new ArrayList<>();
    private  static RepositoryImageInSmartphone repositoryImageInSmartphone = null;

    private RepositoryImageInSmartphone() {
    }

    public static RepositoryImageInSmartphone getInstance(){
        if (repositoryImageInSmartphone==null){
            return  repositoryImageInSmartphone=new RepositoryImageInSmartphone();
        }
        return  repositoryImageInSmartphone;
    }

    @Override
    public boolean load(Context context) {

    return true ;

    }

    public List<Image> getImages() {
        return images;
    }

    public void compress(){

    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
