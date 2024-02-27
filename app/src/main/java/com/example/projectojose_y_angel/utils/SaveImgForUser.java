package com.example.projectojose_y_angel.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.JsonWriter;
import android.util.Log;

import com.example.projectojose_y_angel.models.DTO.DtoUserImg;
import com.example.projectojose_y_angel.utils.Mappeds.MapBitmapToByteArray;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public class SaveImgForUser extends AsyncTask<DtoUserImg, Integer, Boolean> {

    private Context ctx;
    private TaskCompletedUpImg taskCompleted;
    private ProgressDialog progressDialog;
    private List<DtoUserImg> dtoUserImgs;
    private final String baseUrl = "https://paginadejoseieshna.000webhostapp.com/Pages/Imagen/guardar.php";

    public SaveImgForUser(Context ctx, List<DtoUserImg> dtoUserImgs,TaskCompletedUpImg taskCompleted) {
        this.ctx = ctx;
        this.dtoUserImgs = dtoUserImgs;
        this.taskCompleted=taskCompleted;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = ProgressDialog.show(ctx, "Subiendo imagenes", "Espere un momento", true);

    }

    @Override
    protected Boolean doInBackground(DtoUserImg... lists) {
        boolean response = false;
        String responseMessage = "";
        try {
            URL url = new URL(baseUrl);

            for (DtoUserImg dtoUserImg : dtoUserImgs) {
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                byte[] bytes = new MapBitmapToByteArray().map(dtoUserImg.getBitmap());
                String imagenString = Base64.encodeToString(bytes, Base64.DEFAULT);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(URLEncoder.encode("user", "UTF-8"));
                stringBuilder.append("=");
                stringBuilder.append(URLEncoder.encode(dtoUserImg.getUser(), "UTF-8"));
                stringBuilder.append("&");
                stringBuilder.append(URLEncoder.encode("image", "UTF-8"));
                stringBuilder.append("=");
                stringBuilder.append(URLEncoder.encode(imagenString, "UTF-8"));
                OutputStream outputStream = httpURLConnection.getOutputStream();
                byte[] postDataBytes = stringBuilder.toString().getBytes("UTF-8");
                outputStream.write(postDataBytes);
                outputStream.flush();


                int resesposeCode = httpURLConnection.getResponseCode();
                Log.i("ResposeImg", String.valueOf(resesposeCode));
                httpURLConnection.disconnect();

            }
            response = true;
        } catch (Exception err) {
            err.printStackTrace();

        }

        return response;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        progressDialog.dismiss();
        taskCompleted.onTaskComplededUpImg(true);
    }

    public interface TaskCompletedUpImg {
        public void onTaskComplededUpImg(boolean res);


    }

    private String byteToBase64(byte[] bytes) {
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }
}
