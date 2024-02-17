package com.example.projectojose_y_angel.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.example.projectojose_y_angel.R;
import com.example.projectojose_y_angel.models.Image;

import java.util.ArrayList;
import java.util.List;
public class GalletyAdapter extends BaseAdapter {
    private Context ctx;

    private List<Image> imagensArray= new ArrayList<>();


    public GalletyAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return imagensArray.size();
    }

    @Override
    public Object getItem(int i) {
        return imagensArray.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void setImagensArray(List<Image> imagesList){
        imagensArray=imagesList;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(ctx);
        imageView.setLayoutParams(new GridView.LayoutParams(350, 350));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageBitmap(imagensArray.get(i).getImageCompress());
        imageView.setPadding(2,2,2,2);
        imageView.setBackground(ContextCompat.getDrawable(ctx,R.drawable.borderfotos));

        return imageView;
    }
}
