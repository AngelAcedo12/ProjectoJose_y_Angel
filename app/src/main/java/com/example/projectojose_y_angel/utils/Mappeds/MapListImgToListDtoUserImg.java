package com.example.projectojose_y_angel.utils.Mappeds;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.example.projectojose_y_angel.models.DTO.DtoUserImg;
import com.example.projectojose_y_angel.models.Image;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MapListImgToListDtoUserImg implements Map<List<Image> ,List<DtoUserImg>>{
    private String user = "";
    private Context context;
    public MapListImgToListDtoUserImg(String user,Context context) {
        this.user = user;
        this.context=context;
    }

    @Override
    public List<DtoUserImg> map(List<Image> images) throws IOException {
      return   images.stream().map(e -> new DtoUserImg(user, loadBitmapFromUri(context,e.getUri())
        )).collect(Collectors.toList());


    }
    private Bitmap loadBitmapFromUri(Context context, Uri uri){
        Bitmap bitmap=null;
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(uri);
            bitmap = BitmapFactory.decodeStream(inputStream);

        }catch (Exception err){
            err.printStackTrace();
        }

        return bitmap;
    }
}
