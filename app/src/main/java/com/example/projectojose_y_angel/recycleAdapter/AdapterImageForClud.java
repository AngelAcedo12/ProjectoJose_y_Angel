package com.example.projectojose_y_angel.recycleAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectojose_y_angel.R;
import com.example.projectojose_y_angel.models.Image;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterImageForClud extends RecyclerView.Adapter<AdapterImageForClud.MyViewHolder> {
    private Context ctx;
    int layout;
    static List<Image> lista;
    private int imgActived = 0;
    private AdapterImageForClud.ItemClickListener mClickListener;
    private List<Image> selectedList;

    public AdapterImageForClud(Context ctx, List<Image> lista) {
        this.ctx = ctx;
        this.lista = lista;
        selectedList=new ArrayList<>();
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
    public int getImgActived(){
        return  imgActived;
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterImageForClud.MyViewHolder holder, int position) {
        holder.render(lista.get(position));
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        ImageView imageView;
        CheckBox checkBox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageGallery);
            imageView.setPadding(2, 2, 2, 2);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            this.checkBox=itemView.findViewById(R.id.checkBox);
            itemView.setOnLongClickListener(this);
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
            if (imgActived>0){

                chainSelectedVisibiliteToCheckBox(getAdapterPosition());
            }

        }



        private void loadInPicaso(Image image, int count) {

        }

        @Override
        public boolean onLongClick(View view) {
            T
            chainSelectedVisibiliteToCheckBox(getAdapterPosition());
            if(mClickListener!=null)
                mClickListener.onLongItemClick(view,getAdapterPosition());

            return  true;
        }

        private void chainSelectedVisibiliteToCheckBox(int position) {

            if(checkBox.getVisibility()== View.INVISIBLE && imgActived<5){
                Image image = lista.get(position);
                selectedList.add(image);
                lista.get(position).setChecked(true);
                checkBox.setVisibility(View.VISIBLE);
                checkBox.setChecked(true);
                imgActived++;
            }else if(checkBox.getVisibility()==View.VISIBLE){
                Image image = lista.get(position);
                selectedList.remove(image);
                lista.get(position).setChecked(false);
                checkBox.setVisibility(View.INVISIBLE);
                checkBox.setChecked(false);
                imgActived--;
            }
        }
    }

    public static Image getImageInPosition(int pos){
        return lista.get(pos);
    };
    public interface ItemClickListener{
        void onLongItemClick(View activista, int position);
        void onItemClick(View activista, int position);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
