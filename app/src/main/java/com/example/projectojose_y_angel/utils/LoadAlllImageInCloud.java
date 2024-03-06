package com.example.projectojose_y_angel.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.projectojose_y_angel.R;
import com.example.projectojose_y_angel.models.Image;
import com.example.projectojose_y_angel.models.User;
import com.example.projectojose_y_angel.utils.Mappeds.MapJSONToListImagen;
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
import java.util.ArrayList;
import java.util.List;

public class LoadAlllImageInCloud extends AsyncTask<User, Integer, List<Image>>  {

    private Context ctx;
    private ProgressDialog progressDialog;

    private ToaskImageComplete toaskImageComplete;


    public LoadAlllImageInCloud(Context ctx,ToaskImageComplete toaskImageComplete) {
        this.ctx = ctx;
        this.toaskImageComplete = toaskImageComplete;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

            progressDialog = ProgressDialog.show(ctx, ctx.getString(R.string.app_name), "Cargando imagenes en la nube", true);

    }

    @Override
    protected List<Image> doInBackground(User... objects) {
        User data = objects[0];
        List<Image> images=new ArrayList<>();
        String direccion = "https://paginadejoseieshna.000webhostapp.com/Pages/Imagen/getallbyuser.php";
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
            images= new MapJSONToListImagen().map(line);
            is.close();
            reader.close();
            httpURLConnection.disconnect();


        }catch (Exception err){
        err.printStackTrace();
        }

        return images;
    }

    @Override
    protected void onPostExecute(List<Image> images) {
        super.onPostExecute(images);
        progressDialog.dismiss();
        toaskImageComplete.ToaskImageLoadComplete(images);
    }




    public  interface  ToaskImageComplete{
       void ToaskImageLoadComplete(List<Image> list);
    }

}
