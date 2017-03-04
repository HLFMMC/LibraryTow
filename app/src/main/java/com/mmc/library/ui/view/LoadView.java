package com.mmc.library.ui.view;

import com.mmc.library.ui.presenters.base.BaseView;
import com.mmc.library.ui.presenters.base.Message;

/**
 * Created by hyj on 2017/3/2.
 */

public interface LoadView extends BaseView{
    void LoadFailed();
    void LoadSuccuse(String str);//结果返回是json数据的时候用这个方法
    void LoadSuccuse(Message msg);//结果返回是实体类/Message的时候用这个方法
    void LoadFinish();
    void dismissDialog();
}
