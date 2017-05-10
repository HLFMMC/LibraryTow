package com.mmc.library.ui.presenters;

import com.mmc.library.bean.Book;
import com.mmc.library.ui.presenters.base.BasePresenter;
import com.mmc.library.ui.presenters.base.Message;
import com.mmc.library.utils.Constant;
import com.mmc.library.utils.utils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by luxingwen on 2017/5/10.
 */

public class ShopcarPresenters  extends BasePresenter {
    public void loadShopcarList(Message message){
        addSubscrebe(Observable.just(message)
                .filter(msg -> msg != null)
                .map(msg -> loadShopcarListImpl(msg))
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

    private Message loadShopcarListImpl(Message msg){
        utils utils=new utils();
        try {
            Message getMsg = utils.getOfList(Constant.API_ADDRESS+"/api/shopcar",Book.class);
            msg.obj = getMsg.obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return msg;
    }
}
