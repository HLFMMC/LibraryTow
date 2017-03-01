package com.mmc.library.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mmc.library.utils.Constant;
import com.mmc.library.utils.utils;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luxingwen on 2017/3/1.
 */

public class Book implements Serializable{
    private int id;
    private String name;
    private String autor;
    private String pic;
    private float money;
    private String desc;
    private Category category;


    public void addBook(String token)throws IOException{
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("name",this.getName());
        map.put("author",this.getAutor());
        map.put("desc",this.getDesc());
        map.put("pic",this.getPic());
        map.put("category",this.category.getId());
        Gson gson=new Gson();
        String str= gson.toJson(map);

        String res= utils.post(Constant.API_ADDRESS+"/api/login",str,token);
        Map<String,Object>  resMap= gson.fromJson(res,new TypeToken<Map<String,Object>>(){}.getType());
        int code=(int)resMap.get("code");

    }

    public void updateBook(String token)throws IOException{
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("name",this.getName());
        map.put("author",this.getAutor());
        map.put("desc",this.getDesc());
        map.put("pic",this.getPic());
        map.put("category",this.category.getId());
        Gson gson=new Gson();
        String str= gson.toJson(map);

        String res= utils.put(Constant.API_ADDRESS+"/api/login",str,token);
        Map<String,Object>  resMap= gson.fromJson(res,new TypeToken<Map<String,Object>>(){}.getType());
        int code=(int)resMap.get("code");
    }
    public String allBook()throws IOException{
        Gson gson=new Gson();
        String res=utils.get(Constant.API_ADDRESS+"/api/books");
        Map<String,Object>  resMap= gson.fromJson(res,new TypeToken<Map<String,Object>>(){}.getType());
        Object obj=resMap.get("data");
        return gson.toJson(obj);
    }

    public List<Book> allBook(int tag)throws IOException{
        Gson gson=new Gson();
        String res=utils.get(Constant.API_ADDRESS+"/api/books/"+String.valueOf(tag));
        Map<String,Object>  resMap= gson.fromJson(res,new TypeToken<Map<String,Object>>(){}.getType());
        Object obj=resMap.get("data");
        List<Book> books=gson.fromJson(gson.toJson(obj),new TypeToken<List<Book>>(){}.getType());
        return books;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
