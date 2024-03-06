package com.example.projectojose_y_angel.utils.Mappeds;

import com.example.projectojose_y_angel.models.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapJSONToListImagen  implements  Map<String, List<Image>>{
    @Override
    public List<Image> map(String s) throws IOException {
        List<Image> images=new ArrayList<>();
        try {
            JSONArray jsonArray  = new JSONArray(s);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Image image = new Image();
                image.setBitmap(new MapByteArrayToBitmap().map(new MapStringToBiteArray().map((String) jsonObject.get("imagenBytes"))));
                images.add(image);

            }


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        return images;
    }
}
