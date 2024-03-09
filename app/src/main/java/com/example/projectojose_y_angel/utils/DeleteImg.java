package com.example.projectojose_y_angel.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.projectojose_y_angel.R;
import com.example.projectojose_y_angel.models.DTO.DtoUserImg;
import com.squareup.picasso.Downloader;

import java.net.HttpURLConnection;
import java.net.URL;

public class DeleteImg extends AsyncTask<Integer, Integer, Boolean> {

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
    protected Boolean doInBackground(Integer... ids) {
        String direccion = "https://paginadejoseieshna.000webhostapp.com/Pages/Imagen/getallbyuser.php";
        boolean response = false;
        try{
            URL url = new URL(direccion);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setDoInput(true);
            response = true;
        }catch (Exception err){
            err.printStackTrace();
            response=false;
        }




        return response;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        progressDialog.dismiss();
        taskDeleteComplete.deleteTaskComplete(aBoolean);
    }

    public  interface TaskDeleteComplete{
        void deleteTaskComplete(boolean b);
    }

}
