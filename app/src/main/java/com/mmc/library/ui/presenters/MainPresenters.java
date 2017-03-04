package com.mmc.library.ui.presenters;

import com.mmc.library.ui.presenters.base.BasePresenter;
import com.mmc.library.ui.presenters.base.Message;
import com.mmc.library.utils.Constant;

/**
 * Created by hyj on 2017/3/2.
 */

public class MainPresenters extends BasePresenter {

    public void loginFinish(Message msg){
        msg.what = Constant.LOGIN_FINISH;
        msg.HandleMessageToTargetUnrecycle();
    }
}
