package com.android.retrofitrecycleviewapp;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{

    private List<RetroPhoto> dataList;
    private Context context;

    public CustomAdapter(Context context,List<RetroPhoto> dataList){
        this.context=context;
        this.dataList=dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView coverImage;
        private TextView txtTitle;

        public CustomViewHolder(View itemView) {
            super(itemView);
            coverImage=itemView.findViewById(R.id.image);
            txtTitle=itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            Intent intent=new Intent(context,DetailsActivity.class);
            intent.putExtra(DetailsActivity.TM_URL,dataList.get(position).getThumbnailUrl());
            intent.putExtra(DetailsActivity.URL,dataList.get(position).getUrl());
            context.startActivity(intent);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.list_item,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        RetroPhoto retroPhoto=dataList.get(position);
        holder.txtTitle.setText(retroPhoto.getTitle());
        String tmUel=retroPhoto.getThumbnailUrl();

        Glide.with(context).load(tmUel).placeholder(R.drawable.placeholder).into(holder.coverImage);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }



}
