package com.mmc.library.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mmc.library.utils.Constant;
import com.mmc.library.utils.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by luxingwen on 2017/2/28.
 */

public class User {
    private int id;
    private String username;
    private String password;
    private String pic;
    private String phone;
    private Float money;
    private String token;

    public User login() throws IOException {
        Map<String,String> map=new HashMap<String,String>();
        map.put("username",this.getUsername());
        map.put("password",this.getPassword());
        Gson gson=new Gson();
        String str= gson.toJson(map);

        String res= utils.post(Constant.API_ADDRESS+"/api/login",str);
        Map<String,Object>  resMap= gson.fromJson(res,new TypeToken<Map<String,Object>>(){}.getType());
        Object obj=resMap.get("data");
        User user =gson.fromJson(gson.toJson(obj),User.class);
        return user;
    }

    public User register()throws IOException{
        Gson gson=new Gson();
        String str =gson.toJson(this);
        String res=utils.post(Constant.API_ADDRESS+"/api/register",str);
        User user= gson.fromJson(res,User.class);
        return user;
    }

    public User update()throws IOException{
        Gson gson=new Gson();
        String str =gson.toJson(this);
        String res=utils.put(Constant.API_ADDRESS+"/api/user",str,this.getToken());
        User user= gson.fromJson(res,User.class);
        return user;
    }

    public User getUserInfo()throws IOException{
        Gson gson=new Gson();
        String res=utils.get(Constant.API_ADDRESS+"/api/user",this.getToken());
        User user= gson.fromJson(res,User.class);
        return user;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return  new Gson().toJson(this);
    }
}