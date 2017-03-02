package com.mmc.library.bean;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by luxingwen on 2017/3/1.
 */

public class Community {
    private int id;
    private String content;
    private int bookId;
    private User user;

     public void addCommunity()throws IOException{
         Map<String,Object> map=new HashMap<String,Object>();
         map.put("content",this.getContent());
         Gson gson=new Gson();
         String str= gson.toJson(map);
//         String res= utils.post(Constant.API_ADDRESS+"/api/book/info/"+String.valueOf(this.getBookId())+"/community",str,this.user.getToken());
//         Map<String,Object>  resMap= gson.fromJson(res,new TypeToken<Map<String,Object>>(){}.getType());
//         int code=(int)resMap.get("code");
     }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
