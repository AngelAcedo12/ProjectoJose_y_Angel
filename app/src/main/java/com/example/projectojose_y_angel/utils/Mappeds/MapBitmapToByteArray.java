package com.example.projectojose_y_angel.utils.Mappeds;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MapBitmapToByteArray implements Map<Bitmap,byte[]>{
    @Override
    public byte[] map(Bitmap bitmap) throws IOException {
        return bitmapToByteArray(bitmap);
    }
    private byte[] bitmapToByteArray(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.WEBP,80,byteArrayOutputStream);
        return  byteArrayOutputStream.toByteArray();
    }
}
