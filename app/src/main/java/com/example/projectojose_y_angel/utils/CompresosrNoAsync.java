package com.example.projectojose_y_angel.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompresosrNoAsync {
    private Context context;
    public CompresosrNoAsync(Context context) {
        this.context = context;
    }

    public Bitmap uriToCompressBitMapp(Uri uri) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int maxWidth = 400;
        int maxHeight = 400;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(),uri);
            // image.setImage(bitmap);
            if (bitmap.getWidth() > maxWidth || bitmap.getHeight() > maxHeight) {
                float ratio = Math.min((float) maxWidth / bitmap.getWidth(), (float) maxHeight / bitmap.getHeight());
                int width = Math.round(ratio * bitmap.getWidth());
                int height = Math.round(ratio * bitmap.getHeight());
                bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
            }

            bitmap.compress(Bitmap.CompressFormat.WEBP, 25, outputStream);

            return bitmap;
        }
        return null;
    }
}