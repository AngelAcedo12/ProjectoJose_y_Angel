package com.example.projectojose_y_angel.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;

import com.example.projectojose_y_angel.R;
import com.example.projectojose_y_angel.models.Image;
import com.example.projectojose_y_angel.repositorys.RepositoryImageInSmartphone;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class loaderImageInBackGround extends AsyncTask<Object, Integer, Boolean> {
    private List<Image> images = new ArrayList<>();
    private Context context;

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
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int maxWidth = 800;
            int maxHeight = 600;
            String[] props = new String[]{
                    MediaStore.Images.Media.VOLUME_NAME,
                    MediaStore.Images.Media._ID,


            };
            Uri mediaQuery = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            Cursor cursor = context.getContentResolver().query(mediaQuery, props, null, null, null);
            while (cursor.moveToNext() ) {
                Image image = new Image();
                image.setVolumeName(cursor.getString(0));
                image.setId(cursor.getInt(1));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(),
                            MediaStore.Images.Media.getContentUri(image.getVolumeName(), image.getId()));
                    image.setImage(bitmap);
                    if (bitmap.getWidth() > maxWidth || bitmap.getHeight() > maxHeight){
                        float ratio = Math.min((float) maxWidth / bitmap.getWidth(), (float) maxHeight / bitmap.getHeight());
                        int width = Math.round(ratio * bitmap.getWidth());
                        int height = Math.round(ratio * bitmap.getHeight());
                        bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
                    }
                    bitmap.compress(Bitmap.CompressFormat.WEBP, 25, outputStream);
                    image.setImageCompress(bitmap);
                }
                images.add(image);
                count++;
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