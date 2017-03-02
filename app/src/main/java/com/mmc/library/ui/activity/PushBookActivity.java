package com.mmc.library.ui.activity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.mmc.library.R;
import com.mmc.library.base.BaseActivity;
import com.mmc.library.ui.presenters.BookPresenters;

/**
 * Created by HM on 2017/2/28.
 */
public class PushBookActivity extends BaseActivity<BookPresenters> {
    private RelativeLayout btn_back;
    private Button btn_File;
    private Button btn_Push;

    private EditText edt_bookName;
    private EditText edt_bookDesc;
    private EditText edt_bookPic;
    private EditText edt_bookPrice;


    @Override
    protected int getContentView() {
        return R.layout.activity_pushbook;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected BookPresenters getPresenter() {
        return new BookPresenters();
    }

    private  void init() {
//        btn_back = (RelativeLayout) findViewById(R.id.btn_back);
//        btn_File = (Button) findViewById(R.id.btn_File);
//        btn_Push = (Button) findViewById(R.id.btn_Push);
//
//        edt_bookName = (EditText) findViewById(R.id.edt_bookName);
//        edt_bookDesc = (EditText) findViewById(R.id.edt_bookDesc);
//        edt_bookPic = (EditText) findViewById(R.id.edt_bookPic);
//        edt_bookPrice = (EditText) findViewById(R.id.edt_bookPrice);

//        btn_back.setOnClickListener(this);
//        btn_File.setOnClickListener(this);
//        btn_Push.setOnClickListener(this);
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_back :
//                finish();
//                break;
////            case R.id.btn_File:
////                checkFile();
////                break;
////            case R.id.btn_Push:
////                bookPush();
////                break;
//        }
//    }

    /**
     * 选择文件
     */
    private void checkFile() {
        /**
         * 选择文件
         */

        uploadPic();
    }

    /**
     * 上传文件的方法
     */
    private void  uploadPic(){
//        上传图片
    }

    private void bookPush() {
        String bookName  = edt_bookName .getText().toString().trim();
        String bookDesc  = edt_bookDesc .getText().toString().trim();
        String bookPic   = edt_bookPic  .getText().toString().trim();
        String bookPrice = edt_bookPrice.getText().toString().trim();

        if(saveBookInfo(bookName,bookDesc,bookPic,bookPrice)) {

        }
    }


    /**
     * 保存信息方法
     * @param bookName
     * @param bookDesc
     * @param bookPic
     * @param bookPrice
     * @return
     */
    private boolean saveBookInfo(String bookName,String bookDesc,String bookPic,String bookPrice) {
        boolean flag = false;
        return  true;
    }
}
