package com.line.android.memoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.line.android.memoapp.R;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mList;

    public ImageAdapter(Context context) {
        this.mContext = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_image;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_image = itemView.findViewById(R.id.iv_image);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(mContext).load(mList.get(position))
                .error(R.color.colorPrimary)
                .into(holder.iv_image);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setImages(List<String> images){
        mList = images;
        notifyDataSetChanged();
    }
}
