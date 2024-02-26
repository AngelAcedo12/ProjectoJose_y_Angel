package com.example.projectojose_y_angel.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;

import com.example.projectojose_y_angel.models.User;
import com.example.projectojose_y_angel.utils.Mappeds.MapToUser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

public class GetUserByName extends AsyncTask<String,Integer, Optional<User>>  {
    private final Context context;

    private TaskCompleteLogin taskCompleteLogin;
    private ProgressDialog progressDialog;


    public GetUserByName(Context context) {
        this.context = context;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = ProgressDialog.show(context, "Iniciando Sesion", "Verificando usuario", true);

    }

    @Override
    protected  Optional<User> doInBackground(String... objects) {
        String params = objects[0];
        String direccion = "RUTA BASE?user="+params;
        Optional<User> user = Optional.of(null);
        try{
            URL url = new URL(direccion);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            InputStream is = httpURLConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            JsonReader jsonReader = new JsonReader(reader);
            user= new MapToUser().map(jsonReader);
            is.close();
            reader.close();
            httpURLConnection.disconnect();

        }catch (Exception err){
            err.printStackTrace();
        }
        return user;
    }

    @Override
    protected void onPostExecute(Optional<User> user) {
        super.onPostExecute(user);
        taskCompleteLogin.onTaskCompleteLogin();
    }

    public  interface TaskCompleteLogin{
        public Boolean onTaskCompleteLogin();
    }
}
