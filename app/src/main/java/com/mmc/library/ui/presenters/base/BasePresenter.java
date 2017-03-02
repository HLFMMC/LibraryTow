package com.mmc.library.ui.presenters.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by hyj on 2017/2/28.
 */

public class BasePresenter implements IPresenter {

    protected CompositeSubscription mCompositeSubscription;

    public BasePresenter(){
        onStart();
    }

    @Override
    public void onStart() {
        //注册bus
    }

    @Override
    public void onDestroy() {
        mCompositeSubscription.unsubscribe();//解除Rx订阅
        this.mCompositeSubscription = null;//释放资源
    }

    protected void addSubscrebe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);//将所有subscription放入,集中处理
    }

    /**
     * 是否使用eventBus,默认为使用(true)，
     *
     * @return
     */
    protected boolean useEventBus() {
        return true;
    }

    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();//保证activity结束时取消所有正在执行的订阅
        }
    }
}
