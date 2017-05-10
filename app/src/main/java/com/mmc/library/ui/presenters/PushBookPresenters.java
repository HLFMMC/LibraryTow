package com.mmc.library.ui.presenters;

import com.mmc.library.bean.Book;
import com.mmc.library.bean.Pic;
import com.mmc.library.bean.Result;
import com.mmc.library.bean.User;
import com.mmc.library.ui.presenters.base.BasePresenter;
import com.mmc.library.ui.presenters.base.Message;
import com.mmc.library.utils.Constant;
import com.mmc.library.utils.utils;

import java.io.IOException;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by luxingwen on 2017/5/9.
 */

public class PushBookPresenters extends BasePresenter {

    public void postFile(Message message) {
        addSubscrebe(Observable.just(message)
                .filter(msg -> msg != null)
                .map(msg -> postFileImpl(msg))
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


    public Message postFileImpl(Message msg){
        //= =
        utils utils = new utils();
        try {
            return utils.postFile(msg, Constant.API_ADDRESS+"/api/file2",msg.str1,msg.str, Pic.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }



    public void pushbook(Message message) {
        addSubscrebe(Observable.just(message)
                .filter(msg -> msg != null)
                .map(msg -> pushbookImpl(msg))
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

    public Message pushbookImpl(Message msg){
        utils utils = new utils();
        try {
            return utils.post(msg, Constant.API_ADDRESS+"/api/book",msg.str,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }

}
