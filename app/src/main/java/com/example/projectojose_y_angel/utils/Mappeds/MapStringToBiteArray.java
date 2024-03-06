package com.example.projectojose_y_angel.utils.Mappeds;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Base64;

public class MapStringToBiteArray implements Map<String,byte[]> {
    @Override
    public byte[] map(String blob) throws IOException {
        byte[] bytes=null;
        try{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                bytes = Base64.getUrlDecoder().decode(blob);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return bytes;
    }
}
