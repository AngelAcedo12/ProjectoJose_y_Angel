package com.example.projectojose_y_angel.recycleAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectojose_y_angel.R;
import com.example.projectojose_y_angel.models.Image;
import com.squareup.picasso.Picasso;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class MyImageRecycleViewAdapter extends RecyclerView.Adapter<MyImageRecycleViewAdapter.MyViewHolder> {
    private Context ctx;
    int layout;
    HashMap<LocalDate, List<Image>>  lista;
    private ItemClickListener mClickListener;
    private int positionList=0;
    public MyImageRecycleViewAdapter(HashMap<LocalDate, List<Image>> lista, Context context) {
        super();
        this.lista=lista;
        this.ctx=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_img_date,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
     //   Image image = lista.get(position);
        LocalDate[] keys = lista.keySet().toArray(new LocalDate[0]);
        holder.textView.setText(keys[positionList].toString());
        List<Image> listImg = lista.get(keys[positionList]);
        AdapterListImageOfTheDate adapterImg=new AdapterListImageOfTheDate(listImg,ctx);
        holder.recyclerView.setAdapter(adapterImg);
        holder.recyclerView.setLayoutManager(new GridLayoutManager(ctx,3));

    }
    public void setCLickListener(ItemClickListener itemClickListener){
        this.mClickListener=itemClickListener;
    }
    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
       RecyclerView recyclerView;
       TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView=itemView.findViewById(R.id.recyclerImg);
            textView=itemView.findViewById(R.id.textDate);


            positionList++;
        }
        public void render(Image image) {


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
