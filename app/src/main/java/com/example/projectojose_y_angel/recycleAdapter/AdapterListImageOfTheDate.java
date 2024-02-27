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
import com.example.projectojose_y_angel.models.DTO.DtoUserImg;
import com.example.projectojose_y_angel.models.Image;
import com.squareup.picasso.Picasso;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdapterListImageOfTheDate extends RecyclerView.Adapter<AdapterListImageOfTheDate.MyViewHolder>{

    private Context ctx;
    int layout;
   List<Image> lista;
   private  int imgActived=0;
    private AdapterListImageOfTheDate.ItemClickListener mClickListener;
    private List<Image> selectedList;

    public AdapterListImageOfTheDate( List<Image> lista, Context context) {
        super();
        this.lista=lista;
        this.ctx=context;
        selectedList=new ArrayList<>();
    }

    public List<Image> getSelectedList(){
        return  selectedList;
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

    public int getImgActived(){
        return  imgActived;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {
        ImageView imageView;
        CheckBox checkBox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView=itemView.findViewById(R.id.imageGallery);
            this.checkBox=itemView.findViewById(R.id.checkBox);
            imageView.setPadding(2,2,2,2);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
        }
        public void render(Image image) {
            if (!image.isCheked()){
                checkBox.setVisibility(View.INVISIBLE);

            }else {
                checkBox.setVisibility(View.VISIBLE);
                checkBox.setChecked(true);
                checkBox.setClickable(false);
            }
            Picasso.with(ctx).load(image.getUri()).centerCrop().resize(400,400).onlyScaleDown().into(imageView);

        }


        @Override
        public boolean onLongClick(View view) {
            chainSelectedVisibiliteToCheckBox(getAdapterPosition());
            if(mClickListener!=null)
                mClickListener.onItemClick(view,getAdapterPosition());

            return  true;
        }

        private void chainSelectedVisibiliteToCheckBox(int position) {

            if(checkBox.getVisibility()== View.INVISIBLE && imgActived<=10){
                Image image = lista.get(position);
                selectedList.add(image);
                lista.get(position).setCheked(true);
                checkBox.setVisibility(View.VISIBLE);
                checkBox.setChecked(true);
                imgActived++;
            }else if(checkBox.getVisibility()==View.VISIBLE){
                Image image = lista.get(position);
                selectedList.remove(image);
                lista.get(position).setCheked(false);
                checkBox.setVisibility(View.INVISIBLE);
                checkBox.setChecked(false);
                imgActived--;
            }
        }

        @Override
        public void onClick(View view) {
            if (imgActived>0)
                chainSelectedVisibiliteToCheckBox(getAdapterPosition());
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
