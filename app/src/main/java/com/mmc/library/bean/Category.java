package com.mmc.library.bean;

/**
 * Created by luxingwen on 2017/3/1.
 */

public class Category {
    private int id;
    private String content;
    private int fatherId;

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

    public int getFatherId() {
        return fatherId;
    }

    public void setFatherId(int fatherId) {
        this.fatherId = fatherId;
    }
}
