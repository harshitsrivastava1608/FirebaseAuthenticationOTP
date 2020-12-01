package com.example.labourassist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>{
    List<Integer> images;

    public ViewPagerAdapter(List<Integer> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public ViewPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_pager,parent,false);
        return new ViewPagerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerViewHolder holder, int position) {
    int curImage= images.get(position);
    holder.imageView.setImageResource(curImage);

    }

    @Override
    public int getItemCount() {
        return images.size();
    }


    public class ViewPagerViewHolder extends RecyclerView.ViewHolder{
ImageView imageView;
        public ViewPagerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.iv);
        }
    }
}
