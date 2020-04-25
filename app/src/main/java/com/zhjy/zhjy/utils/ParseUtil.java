package com.zhjy.zhjy.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.zhjy.zhjy.beans.ResponseTreads;
import com.zhjy.zhjy.beans.ResponseUsers;
import com.zhjy.zhjy.beans.Treads;
import com.zhjy.zhjy.beans.User;

import java.util.ArrayList;
import java.util.List;

public class ParseUtil {
    private static final String TAG="ParseUtil";
    public int user_total=0;
    public int treads_total=0;
    public  List<User> parseUserjson(String jsonString){
         List<User> list_users=new ArrayList<>();
        Gson gson=new Gson();
        ResponseUsers response=gson.fromJson(jsonString, ResponseUsers.class);
        int code=response.getCode();
        String message=response.getMessage();
        user_total =response.getTotal();
        if(code==200&&message.equals("success")){
            for(int i=0;i<response.getDatas().size();i++){
                User user=new User();
                user.setPhone(response.getDatas().get(i).getPhone());
                user.setNickname(response.getDatas().get(i).getNickname());
                user.setAge(response.getDatas().get(i).getAge());
                user.setHeardpic_url(response.getDatas().get(i).getHeardpic_url());
                user.setLocation(response.getDatas().get(i).getLocation());
                user.setHometown(response.getDatas().get(i).getHometown());
                user.setIsmarried(response.getDatas().get(i).getIsmarried());
                user.setHeight(response.getDatas().get(i).getHeight());
                user.setWeight(response.getDatas().get(i).getWeight());
                user.setWork(response.getDatas().get(i).getWork());
                user.setSalary(response.getDatas().get(i).getSalary());
                list_users.add(user);
            }
        }
        Log.e(TAG,list_users.size()+"--------");
        return list_users;
    }
    public List<Treads> parseTreadsjson(String jsonString){
        List<Treads> list_treads=new ArrayList<>();
        Gson gson=new Gson();
        ResponseTreads response=gson.fromJson(jsonString, ResponseTreads.class);
        int code=response.getCode();
        String message=response.getMessage();
        treads_total =response.getTotal();
        if(code==200&&message.equals("success")){
            for(int i=0;i<response.getDatas().size();i++){
                Treads Treads=new Treads();
                Treads.setHeardpic_url(response.getDatas().get(i).getHeardpic_url());
                Treads.setPhone(response.getDatas().get(i).getPhone());
                Treads.setNickname(response.getDatas().get(i).getNickname());
                Treads.setContent(response.getDatas().get(i).getContent());
                Treads.setAge(response.getDatas().get(i).getAge());
                Treads.setLocation(response.getDatas().get(i).getLocation());
                Treads.setHometown(response.getDatas().get(i).getHometown());
                Treads.setTreads_picture_url(response.getDatas().get(i).getTreads_picture_url());
                Treads.setDate_time(response.getDatas().get(i).getDate_time());

                list_treads.add(Treads);
            }
        }
        Log.e("TAG", list_treads.size()+"--------");
        return  list_treads;
    }
}
