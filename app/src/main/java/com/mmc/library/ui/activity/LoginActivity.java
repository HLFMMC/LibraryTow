package com.mmc.library.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mmc.library.R;
import com.mmc.library.ui.MainActivity;

/**
 * Created by HM on 2017/2/21.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_login;
    private EditText edt_account;
    private EditText edt_password;

    private TextView btn_forget;
    private TextView btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }
    private void init(){
        btn_login = (Button)this.findViewById(R.id.btn_login);
        btn_forget = (TextView) this.findViewById(R.id.btn_forget);
        btn_register = (TextView) this.findViewById(R.id.btn_register);

        edt_account = (EditText) this.findViewById(R.id.account_login);
        edt_password = (EditText) this.findViewById(R.id.password_login);

        btn_login.setOnClickListener(this);
        btn_forget.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_forget:
                forget();
                break;
            case R.id.btn_register:
                register();
                break;
        }
    }

    //登录
    private void login() {
        String account = edt_account.getText().toString().trim();
        String password = edt_password.getText().toString().trim();

        if(account.equals("")) {
        }
        if(password.equals("")) {

        }

        //登录成功后跳到主页
        if(loginRun(account,password)) {
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            this.finish();
        }

    }

    private boolean loginRun(String account,String password) {
        boolean flag = false;
        /*登录的方法*/
//        return  flag;
        return true;
    }
    /*注册*/
    private void register() {
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
    // 忘记密码
    private void forget() {

    }
}
