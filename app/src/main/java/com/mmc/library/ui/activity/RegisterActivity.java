package com.mmc.library.ui.activity;

import android.app.ProgressDialog;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.mmc.library.R;
import com.mmc.library.base.BaseActivity;
import com.mmc.library.bean.User;
import com.mmc.library.ui.presenters.LoginPresenters;
import com.mmc.library.ui.presenters.base.Message;
import com.mmc.library.ui.view.LoadView;
import com.mmc.library.util.CheckUtil;
import com.mmc.library.utils.Constant;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by HM on 2017/2/21.
 */
public class RegisterActivity extends BaseActivity<LoginPresenters> implements LoadView{

    @BindView(R.id.register_account_reg)
    EditText register_account_reg;

    @BindView(R.id.register_password_reg)
    EditText register_password_reg;

    @BindView(R.id.register_password_again)
    EditText register_password_again;

    @BindView(R.id.register_email_reg)
    EditText register_email_reg;

    @BindView(R.id.register_registerBtn)
    Button register_registerBtn;
    @OnClick(R.id.register_registerBtn)
    void register(){
        Log.e("tag register","resgiter");
        String account = register_account_reg.getText().toString().trim();
        String email = register_email_reg.getText().toString().trim();
        String password = register_password_reg.getText().toString().trim();
        String password_again = register_password_again.getText().toString().trim();
        if(CheckUtil.isEmpty(account)) {
            showMessage("请填写账号！");
        }else if(CheckUtil.isEmpty(email)) {
            showMessage("请填写邮箱！");
        }else if(CheckUtil.isEmpty(password)) {
            showMessage("请填写密码！");
        }else if(CheckUtil.isEmpty(password_again)) {
            showMessage("请再次填写密码！");
        } else {
            registerUser(email,password,account);
        }
    }

    public void registerUser(String email,String pwd,String username){
        //采用bean实体方式，而非Hashmap方式
        pdg = ProgressDialog.show(this,"","正在注册。。。");
        User user = new User();
        user.setPassword(pwd);
        user.setEmail(email);
        user.setUsername(username);
        mPresenter.register(Message.obtain(this,user));
    }



    @Override
    protected int getContentView() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected LoginPresenters getPresenter() {
        return new LoginPresenters();
    }

    @Override
    public void LoadFailed() {

    }

    @Override
    public void LoadSuccuse(String str) {

    }

    @Override
    public void LoadSuccuse(Message msg) {

    }

    @Override
    public void LoadFinish() {

    }

    ProgressDialog pdg = null;
    @Override
    public void dismissDialog() {
        if(pdg != null)
            pdg.dismiss();
    }

    @Override
    public void showMessage(String message) {
        showToast(message);
    }

    @Override
    public void handleMessage(Message message) {
        switch (message.what){
            case Constant.DISMIIS_DIALOG:
                dismissDialog();
                break;
            case Constant.REGISTER_FAILD_CODE:
                showMessage("register failed");
                break;
            case Constant.REGISTER_SUCCUSE_CODE:
                finish();
                break;
        }
    }
}
