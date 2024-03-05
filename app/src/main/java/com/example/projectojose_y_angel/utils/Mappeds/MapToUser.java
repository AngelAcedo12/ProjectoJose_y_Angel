package com.example.projectojose_y_angel.utils.Mappeds;

import android.util.JsonReader;

import com.example.projectojose_y_angel.models.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Optional;

public class MapToUser implements Map<String, Optional<User>>{
    @Override
    public Optional<User> map(String res) {
        Optional<User> optionalUser = Optional.ofNullable(null);
        try{
             User user = new User();
                JSONObject jsonObject = new JSONObject(res);
                user.setName(jsonObject.getString("user"));
                user.setPassword(jsonObject.getString("password"));
                user.setEmail(jsonObject.getString("email"));


            return  optionalUser=Optional.of(user);
        }catch (Exception err){
           err.printStackTrace();
        }

        return optionalUser;
    }
}
