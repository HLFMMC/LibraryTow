package com.mmc.library.ui.presenters.base;

/**
 * Created by hyj on 2017/2/28.
 */

public interface BaseView {
    /**
     * 显示信息
     */
    void showMessage(String message);


    /**
     * 处理消息,这里面和handler的原理一样,通过swith(what),做不同的操作
     * @param message
     */
    void handleMessage(Message message);
}
