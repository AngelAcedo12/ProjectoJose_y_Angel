package com.example.projectojose_y_angel.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;

import com.example.projectojose_y_angel.models.User;
import com.example.projectojose_y_angel.utils.Mappeds.MapToUser;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Optional;

public class GetUserByName extends AsyncTask<User,Integer, Optional<User>>  {
    private final Context context;

    private TaskCompleteLogin taskCompleteLogin;
    private ProgressDialog progressDialog;


    public GetUserByName(Context context,TaskCompleteLogin taskCompleteLogin) {
        this.context = context;
        this.taskCompleteLogin=taskCompleteLogin;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = ProgressDialog.show(context, "Iniciando Sesion", "Verificando usuario", true);

    }

    @Override
    protected  Optional<User> doInBackground(User... objects) {
        User data = objects[0];
        String direccion = "https://paginadejoseieshna.000webhostapp.com/Pages/user/byuser.php";
        Optional<User> user = Optional.ofNullable(null);
        OutputStream outputStream=null;
        try{
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(URLEncoder.encode("user", "UTF-8"));
            stringBuilder.append("=");
            stringBuilder.append(URLEncoder.encode(data.getUser(), "UTF-8"));


            URL url = new URL(direccion);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setDoInput(true);
            //cifrado de los datos
            outputStream= new BufferedOutputStream(httpURLConnection.getOutputStream());
            outputStream.write(stringBuilder.toString().getBytes());
            outputStream.flush();
            outputStream.close();
            InputStream is = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line =reader.readLine();
            user= new MapToUser().map(line);
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
        progressDialog.dismiss();
        taskCompleteLogin.onTaskCompleteLogin(user);
    }

    public  interface TaskCompleteLogin{
        public void onTaskCompleteLogin(Optional<User> optionalUser);
    }
}
