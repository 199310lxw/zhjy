package com.zhjy.zhjy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhjy.zhjy.R;

import java.util.List;

public class DiscoverAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private Context mContext;
    private List<String> mList;
    public DiscoverAdapter(Context mContext, List<String> mList){
        this.mContext=mContext;
        this.mList=mList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_discover_item,parent, false);
        viewHolder mViewHolder = new viewHolder(view);
        return mViewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((viewHolder) holder).dicover_recycler_item_title.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size()>0?mList.size():0;
    }
    public static  class viewHolder extends RecyclerView.ViewHolder {
        TextView  dicover_recycler_item_title;

        public viewHolder(View itemView) {
            super(itemView);
            dicover_recycler_item_title=itemView.findViewById(R.id.dicover_recycler_item_title);
        }
    }

}
