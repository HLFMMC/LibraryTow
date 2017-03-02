package com.mmc.library.utils;

/**
 * Created by luxingwen on 2017/3/1.
 */

public interface Constant {
    String API_ADDRESS = "http://139.199.160.234:8000";
    //login
    int LOGIN_FAILD_CODE = 0x0001;
    int LOGIN_SUCCUSE_CODE = 0x0002;
    int LOGIN_FINISH = 0x0003;
    //register
    int REGISTER_FAILD_CODE = 0x0011;
    int REGISTER_SUCCUSE_CODE = 0x0012;
    int REGISTER_FINISH = 0x0013;
    //load book list
    int LOAD_BOOK_FAILD_CODE = 0x0021;
    int LOAD_BOOK_SUCCUSE_CODE = 0x0022;
    int LOAD_BOOK_FINISH = 0x0023;

    //dialog
    int DISMIIS_DIALOG = 0x1000;


    //缓存Key
    String LOGIN_USER_CACHE_KEY = "user_key";
}