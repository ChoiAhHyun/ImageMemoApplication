package com.line.android.memoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.line.android.memoapp.R;
import com.line.android.memoapp.model.MemoModel;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<MemoModel> mList;

    public ListAdapter(Context context, ArrayList<MemoModel> list) {
        this.mContext = context;
        this.mList = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_image;
        TextView tv_title;
        TextView tv_content;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_image = itemView.findViewById(R.id.iv_image);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_content = itemView.findViewById(R.id.tv_content);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final MemoModel item = mList.get(position);
        holder.iv_image.setImageDrawable(null);
        if (item.getImage() != null)
            Glide.with(mContext).load(item.getImage().get(1))
                    .override(500, 500)
                    .error(R.color.colorPrimary)
                    .into(holder.iv_image);
        holder.tv_title.setText(item.getTitle());
        holder.tv_content.setText(item.getContent());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(mContext, position + "번째 클릭", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        super.onViewRecycled(holder);

    }

}
