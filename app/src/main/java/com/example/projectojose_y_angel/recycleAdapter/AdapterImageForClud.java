package com.example.projectojose_y_angel.recycleAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectojose_y_angel.R;
import com.example.projectojose_y_angel.models.Image;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterImageForClud extends RecyclerView.Adapter<AdapterImageForClud.MyViewHolder> {
    private Context ctx;
    int layout;
    static List<Image> lista;
    private int imgActived = 0;
    private AdapterImageForClud.ItemClickListener mClickListener;

    public AdapterImageForClud(Context ctx, List<Image> lista) {
        this.ctx = ctx;
        this.lista = lista;
    }

    @NonNull
    @Override
    public AdapterImageForClud.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_image_layout, parent, false);

        return new AdapterImageForClud.MyViewHolder(view);
    }
    public void setCLickListener(AdapterImageForClud.ItemClickListener itemClickListener){
        this.mClickListener=itemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterImageForClud.MyViewHolder holder, int position) {
        holder.render(lista.get(position));
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageGallery);
            imageView.setPadding(2, 2, 2, 2);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            itemView.setOnClickListener(this);
        }

        public void render(Image image) {
            imageView.setImageBitmap(image.getBitmap());
        }
        @Override
        public void onClick(View view) {
            if (mClickListener!=null){
                mClickListener.onItemClick(view,getAdapterPosition());
            }

        }



        private void loadInPicaso(Image image, int count) {

        }

    }

    public static Image getImageInPosition(int pos){
        return lista.get(pos);
    };
    public interface ItemClickListener{
        void onItemClick(View activista, int position);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
