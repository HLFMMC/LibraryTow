package com.mmc.library.ui.presenters;

import android.nfc.Tag;
import android.util.Log;

import com.mmc.library.bean.Book;
import com.mmc.library.bean.BookInfo;
import com.mmc.library.ui.presenters.base.BasePresenter;
import com.mmc.library.ui.presenters.base.Message;
import com.mmc.library.utils.Constant;
import com.mmc.library.utils.utils;

import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hyj on 2017/3/2.
 */

public class BookPresenters extends BasePresenter {

    private utils<Book> utils;

    public BookPresenters(){
        utils = new utils<>();
    }

    /**
     * 获取图书详情
     * @param message
     */
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
            Message getMsg = utils.get(Constant.API_ADDRESS+"/api/book/info/"+String.valueOf(msg.arg1), BookInfo.class);
            msg.obj = getMsg.obj;
        return msg;
    }

    /**
     * 获取图书列表
     * @param message
     */
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
        Log.d("loadBookImpl--->",msg.str+":"+msg.str1);
        if(msg.str==null){
            msg.str="";
        }
        if (msg.str1==null){
            msg.str1="";
        }
            Message getMsg = utils.getOfList(Constant.API_ADDRESS+"/api/books"+msg.str,Book.class);
            msg.obj = getMsg.obj;
        return msg;
    }

    /**
     * 获取我购买的图书列表
     * @param message
     */
    public void loadMyBookList(Message message){
        addSubscrebe(Observable.just(message)
                .filter(msg -> msg != null)
                .map(msg -> loadMyBooksImpl(msg))
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

    private Message loadMyBooksImpl(Message msg){
        try {
            Message getMsg = utils.getOfList(Constant.API_ADDRESS+"/api/mybook",msg.str,Book.class);
            msg.obj = getMsg.obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return msg;
    }

    public void buyBook(Message message){
        addSubscrebe(Observable.just(message)
                .filter(msg -> msg != null)
                .map(msg -> buyBookImpl(msg))
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

    private Message buyBookImpl(Message msg){
        try {
            return utils.post(msg, Constant.API_ADDRESS+"/api/book/buy",msg.str,null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return msg;
    }

    public void addShopCart(Message message){
        addSubscrebe(Observable.just(message)
                .filter(msg -> msg != null)
                .map(msg -> addShopCartImpl(msg))
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

    private Message addShopCartImpl(Message msg){
        try {
            Map<String,Object> map=( Map<String,Object>)msg.obj;
            String bookId=map.get("bookId").toString();
            Log.d("addShopCartImpl--->",bookId);
            return utils.post(msg, Constant.API_ADDRESS+"/api/shopcar/"+bookId,msg.str,null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return msg;
    }

    //

    public void replay(Message message){
        addSubscrebe(Observable.just(message)
                .filter(msg -> msg != null)
                .map(msg -> replayImpl(msg))
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

    private Message replayImpl(Message msg){
        try {
            Map<String,Object> map=( Map<String,Object>)msg.obj;
            String bookId=map.get("bookId").toString();

            Log.d("addShopCartImpl--->",bookId);
            return utils.post(msg, Constant.API_ADDRESS+"/api/book/info/"+bookId+"/community",msg.str,null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return msg;
    }

}
