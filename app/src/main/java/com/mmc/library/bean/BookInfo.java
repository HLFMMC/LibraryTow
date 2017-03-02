package com.mmc.library.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by luxingwen on 2017/3/1.
 */

public class BookInfo implements Serializable{
    private Book book;
    private List<Community> community;

//    public BookInfo getBookInfo(int bookId)throws IOException{
//        Gson gson=new Gson();
//        String res= utils.get(Constant.API_ADDRESS+"/api/book/info/"+String.valueOf(bookId));
//        Map<String,Object> resMap= gson.fromJson(res,new TypeToken<Map<String,Object>>(){}.getType());
//        Object obj=resMap.get("data");
//        System.out.println("resdata---->"+res+"bookId"+bookId);
//        BookInfo bookInfo=gson.fromJson(gson.toJson(obj),BookInfo.class);
//        return bookInfo;
//    }

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
