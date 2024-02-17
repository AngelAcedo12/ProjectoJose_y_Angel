package com.example.projectojose_y_angel.recycleAdapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectojose_y_angel.R;
import com.example.projectojose_y_angel.models.Image;

import java.util.List;

public class MyImageRecycleViewAdapter extends RecyclerView.Adapter<MyImageRecycleViewAdapter.MyViewHolder> {
    int layout;
    List<Image> lista;
    private ItemClickListener mClickListener;
    public MyImageRecycleViewAdapter(List<Image> lista) {
        super();
        this.lista=lista;
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
        public void render(Image image){
            this.imageView.setImageBitmap(image.getImage());
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
