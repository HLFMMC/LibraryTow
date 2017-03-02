package com.mmc.library.ui.presenters;

import com.mmc.library.bean.User;
import com.mmc.library.ui.presenters.base.BasePresenter;
import com.mmc.library.ui.presenters.base.Message;
import com.mmc.library.utils.Constant;
import com.mmc.library.utils.utils;

import java.util.HashMap;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hyj on 2017/3/2.
 */

public class LoginPresenters extends BasePresenter{

    public void login(Message message){
        addSubscrebe(Observable.just(message)
                .filter(msg -> msg != null)
                .map(msg -> loginImpl(msg))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(msg -> {
                    if(msg.obj == null){
                        msg.what = Constant.LOGIN_FAILD_CODE;
                        msg.HandleMessageToTarget();
                    } else {
                        msg.what = Constant.LOGIN_SUCCUSE_CODE;
                        msg.HandleMessageToTarget();
                    }
                })
        );
    }
    private Message loginImpl(Message msg) {
        HashMap<String,String> map=new HashMap<String,String>();
        map.put("username",msg.str);
        map.put("password",msg.str1);
        utils.post(msg,map, Constant.API_ADDRESS+"/api/login",User.class);
        return msg;
    }

//    public User register()throws IOException {
//        Gson gson=new Gson();
//        String str =gson.toJson(this);
//        String res=utils.post(Constant.API_ADDRESS+"/api/register",str);
//        return user;
//    }
}
