package com.mmc.library.ui.presenters;

import com.mmc.library.bean.User;
import com.mmc.library.ui.presenters.base.BasePresenter;
import com.mmc.library.ui.presenters.base.Message;
import com.mmc.library.utils.Constant;
import com.mmc.library.utils.utils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hyj on 2017/3/2.
 */

public class LoginPresenters<T> extends BasePresenter{

    utils<User> utils;
    public LoginPresenters(){
        utils = new utils<>();
    }

    public void login(Message message){
        addSubscrebe(Observable.just(message)
                .filter(msg -> msg != null)
                .map(msg -> loginImpl(msg))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(msg -> {
                    msg.what = Constant.DISMIIS_DIALOG;
                    msg.HandleMessageToTargetUnrecycle();
                    if(msg.obj == null) {
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
        return utils.post(msg, Constant.API_ADDRESS+"/api/login",User.class);
    }

    public void register(Message message) {
        addSubscrebe(Observable.just(message)
                .filter(msg -> msg != null)
                .map(msg -> registerImpl(msg))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(msg -> {
                    msg.what = Constant.DISMIIS_DIALOG;
                    msg.HandleMessageToTargetUnrecycle();
                    if(msg.obj == null){
                        msg.what = Constant.REGISTER_FAILD_CODE;
                        msg.HandleMessageToTarget();
                    } else {
                        msg.what = Constant.REGISTER_SUCCUSE_CODE;
                        msg.HandleMessageToTarget();
                    }
                })
        );
    }


    public Message registerImpl(Message msg){
        return utils.post(msg, Constant.API_ADDRESS+"/api/register",User.class);
    }

}
