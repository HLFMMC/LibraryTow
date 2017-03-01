package com.mmc.library.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mmc.library.utils.Constant;
import com.mmc.library.utils.utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by luxingwen on 2017/3/1.
 */

public class BookInfo {
    private Book book;
    private List<Community> community;

    public BookInfo getBookInfo(int bookId)throws IOException{
        Gson gson=new Gson();
        String res= utils.get(Constant.API_ADDRESS+"/api/book/info/"+String.valueOf(bookId));
        Map<String,Object> resMap= gson.fromJson(res,new TypeToken<Map<String,Object>>(){}.getType());
        Object obj=resMap.get("data");
        BookInfo bookInfo=gson.fromJson(gson.toJson(obj),BookInfo.class);
        return bookInfo;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Community> getCommunity() {
        return community;
    }

    public void setCommunity(List<Community> community) {
        this.community = community;
    }
}
