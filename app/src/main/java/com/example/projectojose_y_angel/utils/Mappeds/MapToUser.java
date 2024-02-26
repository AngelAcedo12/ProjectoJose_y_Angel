package com.example.projectojose_y_angel.utils.Mappeds;

import android.util.JsonReader;

import com.example.projectojose_y_angel.models.User;

import java.io.IOException;
import java.util.Optional;

public class MapToUser implements Map<JsonReader, Optional<User>>{
    @Override
    public Optional<User> map(JsonReader jsonReader) {
        Optional<User> optionalUser = Optional.of(null);
        try{
            User user = new User();
            jsonReader.beginObject();
            while (jsonReader.hasNext()){
                String key = jsonReader.nextName();
                String value = jsonReader.nextString();
                if (key.equals("user")){
                    user.setName(value);
                } else if (key.equals("email")) {
                    user.setEmail(value);
                }else if(key.equals("password")){
                    user.setPassword(value);
                }
            }
            jsonReader.endObject();
            jsonReader.close();
            return  optionalUser=Optional.of(user);
        }catch (Exception err){
           err.printStackTrace();
        }

        return optionalUser;
    }
}
