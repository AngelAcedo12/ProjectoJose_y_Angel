package com.example.projectojose_y_angel.recycleAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectojose_y_angel.R;
import com.example.projectojose_y_angel.models.Image;
import com.squareup.picasso.Picasso;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class AdapterListImageOfTheDate extends RecyclerView.Adapter<AdapterListImageOfTheDate.MyViewHolder>{

    private Context ctx;
    int layout;
   List<Image> lista;
    private AdapterListImageOfTheDate.ItemClickListener mClickListener;

    public AdapterListImageOfTheDate( List<Image> lista, Context context) {
        super();
        this.lista=lista;
        this.ctx=context;
    }

    @NonNull
    @Override
    public AdapterListImageOfTheDate.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_image_layout,parent,false);

        return new AdapterListImageOfTheDate.MyViewHolder(view);
    }


    public void setCLickListener(AdapterListImageOfTheDate.ItemClickListener itemClickListener){
        this.mClickListener=itemClickListener;
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterListImageOfTheDate.MyViewHolder holder, int position) {
        holder.render(lista.get(position));
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView=itemView.findViewById(R.id.imageGallery);
            imageView.setPadding(2,2,2,2);
            imageView.setBackgroundResource(R.drawable.borderfotos);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            itemView.setOnClickListener(this);


        }
        public void render(Image image) {

            Picasso.with(ctx).load(image.getUri()).centerCrop().resize(350,360).onlyScaleDown().into(imageView);

        }

        @Override
        public void onClick(View v) {
            if(mClickListener!=null)
                mClickListener.onItemClick(v,getAdapterPosition());
        }
    }
    public interface ItemClickListener{
        void onItemClick(View activista, int position);
    }


    @Override
    public int getItemCount() {
        return lista.size();
    }
}
