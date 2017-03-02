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
 * Created by hyj on 2017/3/2.
 */

public class BookPresenters extends BasePresenter {


    public void LoadBookInfo(Message message){
        addSubscrebe(Observable.just(message)
                .filter(msg -> msg != null)
                .map(msg -> LoadBookInfoImpl(msg))
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

    private Message LoadBookInfoImpl(Message msg){
        try {
            utils.get(msg,Constant.API_ADDRESS+"/api/book/info/"+String.valueOf(msg.arg1), BookInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
}
