package com.mmc.library.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.mmc.library.R;

/**
 * Created by HM on 2017/2/28.
 */
public class PushBookActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pushbook);

        init();
    }

    private  void init() {
        btn_back = (RelativeLayout) findViewById(R.id.btn_back);

        btn_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back :
                finish();
                break;
        }
    }
}
