package com.line.android.memoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.line.android.memoapp.R;
import com.yanzhenjie.album.Album;

import java.util.ArrayList;
import java.util.List;

public class ImageBoxAdapter extends RecyclerView.Adapter<ImageBoxAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mList = new ArrayList<>();

    public ImageBoxAdapter(Context context) {
        this.mContext = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_image;
        Button btn_cancel;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_image = itemView.findViewById(R.id.iv_image);
            btn_cancel = itemView.findViewById(R.id.btn_cancel);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageBoxAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_image_box, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Album.getAlbumConfig()
                .getAlbumLoader()
                .load(holder.iv_image, mList.get(position));

        holder.btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mList.remove(position);
                notifyDataSetChanged();
            }
        });
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

    public void addImage(String image){
        mList.add(image);
        notifyDataSetChanged();
    }

    public List<String> getImages(){
        return mList;
    }
}
