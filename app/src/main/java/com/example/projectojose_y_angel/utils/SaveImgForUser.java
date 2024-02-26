package com.example.projectojose_y_angel.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.JsonWriter;
import android.util.Log;

import com.example.projectojose_y_angel.models.DTO.DtoUserImg;
import com.example.projectojose_y_angel.utils.Mappeds.MapBitmapToByteArray;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SaveImgForUser extends AsyncTask<DtoUserImg,Integer,Boolean> {

    private Context ctx;
    private TaskCompletedUpImg taskCompleted;
    private ProgressDialog progressDialog;

    private final String baseUrl ="https://paginadejoseieshna.000webhostapp.com/Pages/Imagen/guardar.php";
    public SaveImgForUser(Context ctx) {
        this.ctx = ctx;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = ProgressDialog.show(ctx, "Subiendo imagenes", "Espere un momento", true);




    }

    @Override
    protected Boolean doInBackground(DtoUserImg... lists) {
        boolean response = false;
        try{
            URL url = new URL(baseUrl);

            for(DtoUserImg dtoUserImg : lists){
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                byte[] bytes = new MapBitmapToByteArray().map(dtoUserImg.getBitmap());
                JsonWriter jsonWriter = new JsonWriter(
                        new OutputStreamWriter(httpURLConnection.getOutputStream(),"UTF-8"));
                jsonWriter.beginObject();
                jsonWriter.name("user").value(dtoUserImg.getUser());
                jsonWriter.name("image").value(byteToBase64(bytes));
                jsonWriter.endObject();
                jsonWriter.close();
                int resesposeCode = httpURLConnection.getResponseCode();

                Log.i("ResposeImg", String.valueOf(resesposeCode));
                httpURLConnection.disconnect();
            }
            response=true;
             return  response;
        }catch (Exception err){
            err.printStackTrace();

        }
        return response;
    }
    public interface TaskCompletedUpImg {
        public void onTaskComplededUpImg(boolean res);


    }
    private String byteToBase64(byte[] bytes){
        return Base64.encodeToString(bytes,Base64.DEFAULT);
    }
}
