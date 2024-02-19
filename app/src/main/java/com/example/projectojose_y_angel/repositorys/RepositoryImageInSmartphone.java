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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class RepositoryImageInSmartphone implements Repository {

    private HashMap<LocalDate,List<Image>> images = new HashMap<>();
    private  static RepositoryImageInSmartphone repositoryImageInSmartphone = null;

    private RepositoryImageInSmartphone() {
    }

    public static RepositoryImageInSmartphone getInstance(){
        if (repositoryImageInSmartphone==null){
            return  repositoryImageInSmartphone=new RepositoryImageInSmartphone();
        }
        return  repositoryImageInSmartphone;
    }


    public HashMap<LocalDate,List<Image>> getImages() {
        return images;
    }


    public void setImages(HashMap<LocalDate, List<Image>> images) {
        this.images = images;
    }
}
