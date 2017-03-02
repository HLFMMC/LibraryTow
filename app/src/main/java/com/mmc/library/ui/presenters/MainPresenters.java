package com.mmc.library.ui.presenters;

import com.mmc.library.ui.presenters.base.BasePresenter;
import com.mmc.library.ui.presenters.base.Message;
import com.mmc.library.utils.Constant;
import com.mmc.library.utils.utils;

import java.io.IOException;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hyj on 2017/3/2.
 */

public class MainPresenters extends BasePresenter {
    public void loginFinish(Message msg){
        msg.what = Constant.LOGIN_FINISH;
        msg.HandleMessageToTargetUnrecycle();
    }

    public void loadBookList(Message message){
        addSubscrebe(Observable.just(message)
                .filter(msg -> msg != null)
                .map(msg -> loadBookImpl(msg))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(msg -> {
                    if(msg.obj == null){
                        msg.what = Constant.LOAD_BOOK_FAILD_CODE;
                        msg.HandleMessageToTarget();
                    } else {
                        msg.what = Constant.LOAD_BOOK_SUCCUSE_CODE;
                        msg.HandleMessageToTarget();
                    }
                })
        );
    }

    private Message loadBookImpl(Message msg){
        try {
            utils.getOfList(msg,Constant.API_ADDRESS+"/api/books");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
}
