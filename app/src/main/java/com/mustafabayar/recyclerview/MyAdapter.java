package com.mustafabayar.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> implements Filterable {

    Context c;
    ArrayList<Model> models,filterList;
    CustomFilter filter;

    MyAdapter(Context c, ArrayList<Model> models) {
        this.c = c;
        this.models = models;
        this.filterList = models;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //convert xml to view obj
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.model,null);

        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        //bind data to our views
        holder.mTitleTv.setText(models.get(position).getTitle());
        holder.mDescrTv.setText(models.get(position).getDescription());
        holder.mimageIv.setImageResource(models.get(position).getImg());

        //animation
        Animation animation = AnimationUtils.loadAnimation(c, android.R.anim.slide_in_left);
        holder.itemView.startAnimation(animation);

        /*Use when you want to put each item data to same activity*/
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //get data from item clicked
                String title = models.get(pos).getTitle();
                String descr = models.get(pos).getDescription();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) holder.mimageIv.getDrawable();
                //get Bitmap from drawable
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                //compress image
                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                //convert to bytes array
                byte[] bytes = stream.toByteArray();

                //intent,put data to intent,start activity
                Intent intent = new Intent(c,DetailActivity.class);
                intent.putExtra("iTitle",title);
                intent.putExtra("iDescr",descr);
                intent.putExtra("iImage",bytes);
                c.startActivity(intent);
            }
        });
        

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null){
            filter = new CustomFilter(filterList,this);
        }
        return filter;
    }
}
