package com.zhjy.zhjy.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.zhjy.zhjy.beans.Response;
import com.zhjy.zhjy.beans.User;

import java.util.ArrayList;
import java.util.List;

public class ParseUserUtil {
    public int total=0;
    public  List<User> parsejson(String jsonString){
         List<User> list_users=new ArrayList<>();
        Gson gson=new Gson();
        Response response=gson.fromJson(jsonString,Response.class);
        int code=response.getCode();
        String message=response.getMessage();
        total =response.getTotal();
        if(code==200&&message.equals("success")){
            for(int i=0;i<response.getDatas().size();i++){
                User user=new User();
                user.setNickname(response.getDatas().get(i).getNickname());
                user.setAge(response.getDatas().get(i).getAge());
                user.setHeardpic_url(response.getDatas().get(i).getHeardpic_url());
                user.setLocation(response.getDatas().get(i).getLocation());
                user.setHometown(response.getDatas().get(i).getHometown());
                user.setPhone(response.getDatas().get(i).getPhone());
                user.setIsmarried(response.getDatas().get(i).isIsmarried());
                user.setHeight(response.getDatas().get(i).getHeight());
                user.setWeight(response.getDatas().get(i).getWeight());
                user.setWork(response.getDatas().get(i).getWork());
                user.setSalary(response.getDatas().get(i).getSalary());
                list_users.add(user);
            }
        }
        Log.e("TAG",list_users.size()+"--------");
        return list_users;
    }
}
