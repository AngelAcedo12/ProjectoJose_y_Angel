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


import java.util.List;

public class MyImageRecycleViewAdapter extends RecyclerView.Adapter<MyImageRecycleViewAdapter.MyViewHolder> {
    private Context ctx;
    int layout;
    List<Image> lista;
    private ItemClickListener mClickListener;

    public MyImageRecycleViewAdapter(List<Image> lista,Context context) {
        super();
        this.lista=lista;
        this.ctx=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_image_layout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Image image = lista.get(position);

            holder.render(image);

    }
    public void setCLickListener(ItemClickListener itemClickListener){
        this.mClickListener=itemClickListener;
    }
    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView=itemView.findViewById(R.id.imageGallery);
            itemView.setOnClickListener(this);


        }
        public void render(Image image) {

        Picasso.with(ctx).load(image.getUri()).centerCrop().resize(400,400).into(imageView);


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
}
