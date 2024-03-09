package com.example.projectojose_y_angel.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.projectojose_y_angel.R;
import com.example.projectojose_y_angel.models.DTO.DtoUserImg;
import com.example.projectojose_y_angel.models.DTO.ImageDeleteDTO;
import com.squareup.picasso.Downloader;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DeleteImg extends AsyncTask<ImageDeleteDTO, Integer, ImageDeleteDTO> {

    private final Context context;
    private ProgressDialog progressDialog;

    private TaskDeleteComplete taskDeleteComplete;


    public DeleteImg(Context context, TaskDeleteComplete taskDeleteComplete) {
        this.context = context;
        this.taskDeleteComplete = taskDeleteComplete;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, context.getString(R.string.app_name), "Borrando imagenes", true);
    }

    @Override
    protected ImageDeleteDTO doInBackground(ImageDeleteDTO... imagesDTO) {
        ImageDeleteDTO imageDeleteDTO=imagesDTO[0];
        String direccion = "https://paginadejoseieshna.000webhostapp.com/Pages/Imagen/delete.php";
        String bodyParams = "id="+imageDeleteDTO.getId();
        OutputStream outputStream=null;
        try{
            URL url = new URL(direccion);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setDoInput(true);
            //fijamos el tamaño
            httpURLConnection.setFixedLengthStreamingMode(bodyParams.toString().getBytes().length);
            //Introducimos id
            outputStream= new BufferedOutputStream(httpURLConnection.getOutputStream());
            outputStream.write(bodyParams.toString().getBytes());
            outputStream.flush();
            outputStream.close();
            //obtenemos datos
            InputStream is = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line =reader.readLine();
            if(Boolean.parseBoolean(line)){
                imageDeleteDTO.setBorrado(true);
            }
            httpURLConnection.disconnect();
        }catch (Exception err){
            err.printStackTrace();
        }

        return imageDeleteDTO;
    }

    @Override
    protected void onPostExecute(ImageDeleteDTO imageDeleteDTO) {
        progressDialog.dismiss();
        taskDeleteComplete.deleteTaskComplete(imageDeleteDTO);
    }

    public  interface TaskDeleteComplete{
        void deleteTaskComplete(ImageDeleteDTO imageDeleteDTO);
    }

}
