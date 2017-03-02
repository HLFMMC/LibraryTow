package com.mmc.library.ui.presenters;

import com.mmc.library.bean.BookInfo;
import com.mmc.library.ui.presenters.base.BasePresenter;
import com.mmc.library.ui.presenters.base.Message;
import com.mmc.library.utils.Constant;
import com.mmc.library.utils.utils;

import java.io.IOException;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by luxingwen on 2017/3/3.
 */

public class UserInfoPresenters extends BasePresenter {

    public void LoadUserInfo(Message message){
        addSubscrebe(Observable.just(message)
                .filter(msg -> msg != null)
                .map(msg -> LoadUserInfoImpl(msg))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(msg -> {
                    msg.what = Constant.DISMIIS_DIALOG;
                    msg.HandleMessageToTargetUnrecycle();
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

    private Message LoadUserInfoImpl(Message msg){
        return msg;
    }
}
