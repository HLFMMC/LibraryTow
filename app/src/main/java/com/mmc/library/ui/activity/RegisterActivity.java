package com.mmc.library.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mmc.library.R;

/**
 * Created by HM on 2017/2/21.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_register;
    private EditText edt_account;
    private EditText edt_password;
    private EditText edt_password_again;

    private RelativeLayout btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init() {
        btn_register = (Button) this.findViewById(R.id.register_btn);
        btn_back = (RelativeLayout) this.findViewById(R.id.btn_back);

        edt_account = (EditText) this.findViewById(R.id.account_reg);
        edt_password = (EditText) this.findViewById(R.id.password_reg);

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
        String password = edt_password.getText().toString().trim();

        if(account.equals("")) {
        }
        if(password.equals("")) {

        }

        // 注册成功后关闭
        if(registerRun(account,password)){
            finish();
        }
    }

    // 执行注册的方法
    private boolean registerRun(String account,String password) {

        return true;
    }

}
