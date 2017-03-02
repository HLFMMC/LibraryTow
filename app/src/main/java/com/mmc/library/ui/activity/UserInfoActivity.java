package com.mmc.library.ui.activity;

import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mmc.library.R;
import com.mmc.library.base.BaseActivity;
import com.mmc.library.ui.presenters.UserInfoPresenters;
import com.mmc.library.ui.presenters.base.Message;
import com.mmc.library.ui.view.LoadView;

import butterknife.BindView;

/**
 * Created by luxingwen on 2017/3/3.
 */

public class UserInfoActivity extends BaseActivity<UserInfoPresenters> implements LoadView {

//    @BindView(R.id.user_profile_img)
//    ImageView user_profile_img;

    @BindView(R.id.user_profile_username_text)
    TextView user_profile_username_text;

    @BindView(R.id.user_profile_username)
    LinearLayout user_profile_username;

    @BindView(R.id.user_profile_email_text)
    TextView user_profile_email_text;

    @BindView(R.id.user_profile_sex_text)
    TextView user_profile_sex_text;

    @BindView(R.id.user_profile_money)
    TextView user_profile_money;

    @Override
    protected int getContentView() {
        return R.layout.activity_userinfo;
    }

    @Override
    protected void initData() {
        Log.e(TAG,"user---->"+user.toString());
//        if(!TextUtils.isEmpty(user.getPic())){
//            Glide.with(UserInfoActivity.this)
//                    .load(user.getPic())
//                    .centerCrop()
//                    .placeholder(R.drawable.nocover)
//                    .crossFade().into(user_profile_img);
//        }
        user_profile_username_text.setText(user.getUsername());
        user_profile_email_text.setText(user.getEmail());

    }

    @Override
    protected UserInfoPresenters getPresenter() {
        return new UserInfoPresenters();
    }

    @Override
    public void LoadFailed() {

    }

    @Override
    public void LoadSuccuse(String str) {

    }

    @Override
    public void LoadSuccese(Message msg) {

    }

    @Override
    public void LoadFinish() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void handleMessage(Message message) {

    }
}
