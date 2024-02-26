package com.example.projectojose_y_angel.utils.Mappeds;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;

public class MapByteArrayToBitmap implements Map<byte[], Bitmap>{

    @Override
    public Bitmap map(byte[] bytes) throws IOException {
        return byteArrayToBitmap(bytes);
    }
    private Bitmap byteArrayToBitmap(byte[] bytes){
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    }
}
