package com.mmc.library.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mmc.library.R;

import java.util.HashMap;

/**
 * Created by HM on 2017/2/28.
 */
public class BookInfoActivity  extends AppCompatActivity implements View.OnClickListener {
    private TextView bookName;
    private TextView bookDesc;
    private RelativeLayout btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookinfo);
        init();
    }
    private void init(){
        bookDesc = (TextView) findViewById(R.id.bookDesc);
        bookName = (TextView) findViewById(R.id.bookName);
        btn_back = (RelativeLayout) findViewById(R.id.btn_back);

        HashMap<String,String> map = (HashMap<String,String>)getIntent().getSerializableExtra("book");
        bookName.setText(map.get("bookName"));
        bookDesc.setText(map.get("bookDesc"));

        btn_back.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
