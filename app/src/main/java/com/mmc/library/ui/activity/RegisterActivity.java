package com.mmc.library.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mmc.library.R;
import com.mmc.library.base.BaseActivity;
import com.mmc.library.ui.presenters.LoginPresenters;

/**
 * Created by HM on 2017/2/21.
 */
public class RegisterActivity extends BaseActivity<LoginPresenters> implements View.OnClickListener{
    private Button btn_register;
    private EditText edt_account;
    private EditText edt_password;
    private EditText edt_password_again;
    private EditText edt_email;

    private RelativeLayout btn_back;

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

    private void init() {
        btn_register = (Button) this.findViewById(R.id.register_btn);
        btn_back = (RelativeLayout) this.findViewById(R.id.btn_back);

        edt_account = (EditText) this.findViewById(R.id.account_reg);
        edt_email = (EditText) this.findViewById(R.id.email_reg);
        edt_password = (EditText) this.findViewById(R.id.password_reg);
        edt_password_again = (EditText) this.findViewById(R.id.password_again);

        btn_register.setOnClickListener(this);
        btn_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_btn:
                register();
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }
    private void register() {
        String account = edt_account.getText().toString().trim();
        String email = edt_email.getText().toString().trim();
        String password = edt_password.getText().toString().trim();
        String password_again = edt_password_again.getText().toString().trim();

//        if(CheckUtil.isEmpty(account)) {
//            Toast.makeText(this,"账号错误！",Toast.LENGTH_LONG).show();
//        }else if(!CheckUtil.isMail(email)) {
//            Toast.makeText(this,"邮箱错误！",Toast.LENGTH_LONG).show();
//        }else if(!CheckUtil.isPassword(password)) {
//            Toast.makeText(this,"密码错误！",Toast.LENGTH_LONG).show();
//        }else if(password.equals(password_again)) {
//            Toast.makeText(this,"两次密码不一致！",Toast.LENGTH_LONG).show();
//        } else
        if(registerRun(account,password)){
            finish();// 注册成功后关闭
        } else {
            Toast.makeText(this,"注册失败！",Toast.LENGTH_LONG).show();
        }
    }

    // 执行注册的方法
    private boolean registerRun(String account,String password) {

        return true;
    }

}
