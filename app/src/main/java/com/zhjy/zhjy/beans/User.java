package com.zhjy.zhjy.beans;

public class User {
    private String phone;       //手机号码
    private String nickname;    //昵称
    private int age;            //年龄
    private String  location;   // 城市
    private String hometown;     //老家
    private String heardpic_url; //头像地址
    private int ismarried;   // 婚否
    private int height;          //身高
    private int weight;          //体重
    private String work;         //职业
    private String salary;      //收入

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getHeardpic_url() {
        return heardpic_url;
    }

    public void setHeardpic_url(String heardpic_url) {
        this.heardpic_url = heardpic_url;
    }

    public int getIsmarried() {
        return ismarried;
    }

    public void setIsmarried(int ismarried) {
        this.ismarried = ismarried;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
