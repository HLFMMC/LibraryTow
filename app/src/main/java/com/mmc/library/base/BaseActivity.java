package com.mmc.library.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.mmc.library.bean.User;
import com.mmc.library.ui.presenters.base.IPresenter;
import com.mmc.library.utils.Cache;
import com.mmc.library.utils.Constant;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by hyj on 2017/2/28.
 */

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity {

    protected final String TAG = this.getClass().getSimpleName();

    public Cache cache;
    //Presenter基本接口
    protected P mPresenter;

    private Unbinder mUnbinder;

    public boolean isLogin;

    public Context context;

    public User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取继承该ac的实际对象Presenter
        mPresenter = getPresenter();
        context = this;
        //设置主要布局
        setContentView(getContentView());
        //注册ButterKnife
        mUnbinder = ButterKnife.bind(this);
        cache = Cache.get(this);
        isLogin = checkUserIsLogin();
        //初始化数据
        this.user=(User)cache.getAsObject(Constant.LOGIN_USER_CACHE_KEY);
        initData();

    }

    public Boolean checkUserIsLogin(){
        User user = (User)cache.getAsObject(Constant.LOGIN_USER_CACHE_KEY);
        return user != null;
    }

    public void onClickBack(View view){
        finish();
    }
    public void showToast(String v){
        Toast.makeText(context,v,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.onDestroy();//释放presenter
        if (mUnbinder != Unbinder.EMPTY) mUnbinder.unbind();//反注册butterknife
        this.mPresenter = null;
        this.mUnbinder = null;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (mPresenter == null){
            //恢复mPresenter对象
            mPresenter = getPresenter();
        }
    }

    /**
     * 是否使用eventBus,默认为使用(true)，
     *
     * @return
     */

    protected abstract int getContentView();

    protected abstract void initData();

    protected abstract P getPresenter();

}
