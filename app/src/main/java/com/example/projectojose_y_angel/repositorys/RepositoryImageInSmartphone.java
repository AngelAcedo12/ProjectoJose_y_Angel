package com.example.projectojose_y_angel.repositorys;

import com.example.projectojose_y_angel.models.Image;

import java.util.ArrayList;
import java.util.List;

public class RepositoryImageInSmartphone implements RepositoryImageInSmarphone {

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


    public List<Image> getImages() {
        return images;
    }


    public void setImages( List<Image> images) {
        this.images = images;
    }
}
