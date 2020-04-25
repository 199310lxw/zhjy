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

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zhjy.zhjy.R;
import com.zhjy.zhjy.activity.UserDataDetailActivity;
import com.zhjy.zhjy.beans.Treads;
import com.zhjy.zhjy.views.CircleImageView;

import java.util.List;

public class DiscoverAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private Context mContext;
    private List<Treads> mList;
    public DiscoverAdapter(Context mContext, List<Treads> mList){
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
        ((viewHolder) holder).discover_item_tv_nickname.setText(mList.get(position).getNickname());
        ((viewHolder) holder).discover_item_tv_age.setText(mList.get(position).getAge()+"");
        ((viewHolder) holder).discover_item_tv_location.setText(mList.get(position).getLocation());
        ((viewHolder) holder).discover_item_tv_hometown.setText(mList.get(position).getHometown());
        ((viewHolder) holder).discover_item_tv_content.setText("\t\t\t"+mList.get(position).getContent());
        ((viewHolder) holder).discover_item_tv_time.setText("\t\t\t"+mList.get(position).getDate_time());
        Glide.with(mContext).load(mList.get(position).getTreads_picture_url()).error(R.mipmap.ic_launcher).into(((viewHolder) holder).discover_item_img_treadspic);
    }

    @Override
    public int getItemCount() {
        return mList.size()>0?mList.size():0;
    }
    public static  class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
         CircleImageView img_heardpic;
        TextView  discover_item_tv_nickname;
        TextView discover_item_tv_age;
        TextView discover_item_tv_location;
        TextView discover_item_tv_hometown;
        TextView discover_item_tv_content;
        ImageView discover_item_img_treadspic;
        TextView discover_item_tv_time;
        Context mContext;

        public viewHolder(View itemView,Context mContext) {
            super(itemView);
            this.mContext=mContext;
            discover_item_tv_nickname=itemView.findViewById(R.id.discover_recycler_item_title);
            discover_item_tv_age=itemView.findViewById(R.id.discover_item_tv_age);
            discover_item_tv_location=itemView.findViewById(R.id.discover_item_tv_location);
            discover_item_tv_hometown=itemView.findViewById(R.id.discover_item_tv_hometown);
            discover_item_tv_content=itemView.findViewById(R.id.discover_item_tv_content);
            discover_item_img_treadspic=itemView.findViewById(R.id.discover_item_img_treadspic);
            discover_item_tv_time=itemView.findViewById(R.id.discover_item_tv_time);

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
