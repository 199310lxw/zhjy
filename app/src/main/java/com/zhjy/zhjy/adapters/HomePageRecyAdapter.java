package com.zhjy.zhjy.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zhjy.zhjy.R;
import com.zhjy.zhjy.activity.UserDataDetailActivity;
import com.zhjy.zhjy.beans.User;

import java.util.List;

public class HomePageRecyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private Context mContext;
    private List<User> mList;
    public HomePageRecyAdapter(Context mContext,List<User> mList){
        this.mContext=mContext;
        this.mList=mList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_homepage_item,parent, false);
        viewHolder mViewHolder = new viewHolder(view,mList,mContext);
        return mViewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((viewHolder) holder).homepage_item_tv_title.setText(mList.get(position).getNickname());
        ((viewHolder) holder).homepage_item_tv_age.setText(mList.get(position).getAge()+"");
        ((viewHolder) holder).homepage_item_tv_location.setText(mList.get(position).getLocation());
        ((viewHolder) holder).homepage_item_tv_hometown.setText(mList.get(position).getHometown());
//        ((viewHolder) holder).img_heard_pic.setImageResource(R.mipmap.one);
        Glide.with(mContext).load(mList.get(position).getHeardpic_url()).error(R.mipmap.ic_launcher).into(((viewHolder) holder).img_heard_pic);

    }

    @Override
    public int getItemCount() {
        return mList.size()>0?mList.size():0;
    }
    public static  class viewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        TextView  homepage_item_tv_title;
        TextView homepage_item_tv_age;
        TextView homepage_item_tv_location;
        TextView homepage_item_tv_hometown;
        RoundedImageView img_heard_pic;
        Context mContext;
        List<User> mList;
        public viewHolder(View itemView, List<User> mList, Context mContext) {
            super(itemView);
            this.mContext=mContext;
            this.mList=mList;
            homepage_item_tv_title=itemView.findViewById(R.id.homepage_item_tv_title);
            homepage_item_tv_age=itemView.findViewById(R.id.homepage_item_tv_age);
            homepage_item_tv_location=itemView.findViewById(R.id.homepage_item_tv_location);
            homepage_item_tv_hometown=itemView.findViewById(R.id.homepage_item_tv_hometown);
            img_heard_pic=itemView.findViewById(R.id.img_heardpic);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            //程序执行到此，会去执行具体实现的onItemClick()方法
            Intent in_show=new Intent(mContext, UserDataDetailActivity.class);
            in_show.putExtra("nickname",mList.get(position).getNickname());
            in_show.putExtra("age",mList.get(position).getAge());
            in_show.putExtra("location",mList.get(position).getLocation());
            in_show.putExtra("hometown",mList.get(position).getHometown());
            in_show.putExtra("ismarried",mList.get(position).getIsmarried());
            in_show.putExtra("height",mList.get(position).getHeight());
            in_show.putExtra("weight",mList.get(position).getWeight());
            in_show.putExtra("work",mList.get(position).getWork());
            in_show.putExtra("salary",mList.get(position).getSalary());
            in_show.putExtra("heardpic_url",mList.get(position).getHeardpic_url());

            mContext.startActivity(in_show);
        }
    }

}
