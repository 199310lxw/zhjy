package com.zhjy.zhjy.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.zhjy.zhjy.R;
import com.zhjy.zhjy.activity.UserDataDetailActivity;
import com.zhjy.zhjy.views.CircleImageView;

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
        viewHolder mViewHolder = new viewHolder(view,mContext);
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
    public static  class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView  dicover_recycler_item_title;
        RoundedImageView img_heardpic;
        Context mContext;

        public viewHolder(View itemView,Context mContext) {
            super(itemView);
            this.mContext=mContext;
            dicover_recycler_item_title=itemView.findViewById(R.id.dicover_recycler_item_title);
            img_heardpic=itemView.findViewById(R.id.img_heardpic);
            itemView.setOnClickListener(this);
            img_heardpic.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();


            switch (view.getId()){
                case R.id.img_heardpic:
                    //程序执行到此，会去执行具体实现的onItemClick()方法
                    Intent in_show=new Intent(mContext, UserDataDetailActivity.class);
                    mContext.startActivity(in_show);
                    break;
                case R.id.ll_discover_item_root:
                    Toast.makeText(mContext,"我点击了第"+position+"项",Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    }

}
