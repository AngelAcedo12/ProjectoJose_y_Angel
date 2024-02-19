package com.example.projectojose_y_angel.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.icu.text.UFormat;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;

import com.example.projectojose_y_angel.R;
import com.example.projectojose_y_angel.models.Image;
import com.example.projectojose_y_angel.repositorys.RepositoryImageInSmartphone;

import java.io.ByteArrayOutputStream;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class loaderImageInBackGround extends AsyncTask<Object, Integer, Boolean> {
    private HashMap<LocalDate,List<Image>> images = new HashMap<>();
    private final  Context context;

    private TaskCompleted taskCompleted;
    private ProgressDialog progressDialog;

    public loaderImageInBackGround(Context context, TaskCompleted taskCompleted) {
        this.context = context;
        this.taskCompleted = taskCompleted;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, context.getString(R.string.app_name), "Cargando", true);
    }


    @Override
    protected Boolean doInBackground(Object... objects) {
        int count = 0;
        try {

            int maxWidth = 300;
            int maxHeight = 300;

            String[] props = new String[]{
                    MediaStore.Images.Media.VOLUME_NAME,
                    MediaStore.Images.Media._ID,
                    MediaStore.Images.Media.DATE_TAKEN,
            };
            String sortOrder = MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC";
            Uri mediaQuery = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            Cursor cursor = context.getContentResolver().query(mediaQuery, props, null, null,sortOrder);
            LocalDate dateAnterior=null;
            List<Image> list = new ArrayList<>();
            while (cursor.moveToNext()) {
                Image image = new Image();
                image.setVolumeName(cursor.getString(0));
                image.setId(cursor.getInt(1));
                Long dateSinCoverted = cursor.getLong(2);
                DateTimeFormatter formatter=null;
                LocalDate localDate=null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                     localDate = Instant.ofEpochMilli(dateSinCoverted).atZone(ZoneId.systemDefault()).toLocalDate();
                    localDate=LocalDate.of( localDate.getYear(), localDate.getMonth(), 1);
                }
                image.setDate(localDate);


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    image.setUri(MediaStore.Images.Media.getContentUri(image.getVolumeName(),image.getId()));
                }
                if (localDate.getYear() != 1970) {
                    if (dateAnterior!=null && dateAnterior.equals(localDate)){
                        list.add(image);
                    }else {
                        dateAnterior=localDate;
                        images.put(localDate,list);
                        list=new ArrayList<>();
                    }
                    count++;
                }

            }
            RepositoryImageInSmartphone.getInstance().setImages(images);
            return true;
        } catch (Exception err) {
            err.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        taskCompleted.onTaskCompleded(true);
        progressDialog.dismiss();
    }

    public interface TaskCompleted {
        public void onTaskCompleded(boolean res);
    }
}