package com.line.android.memoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.line.android.memoapp.R;
import com.yanzhenjie.album.Album;

import java.util.List;

public class ImagePagerAdapter extends RecyclerView.Adapter<ImagePagerAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mList;

    public ImagePagerAdapter(Context context) {
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
        return new ImagePagerAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_image_pager, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Album.getAlbumConfig()
                .getAlbumLoader()
                .load(holder.iv_image, mList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    public void setImages(List<String> images){
        mList = images;
        notifyDataSetChanged();
    }
}
