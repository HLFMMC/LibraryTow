package com.mmc.library.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import com.mmc.library.R;
import com.mmc.library.adapter.BookAdapter;
import com.mmc.library.base.BaseActivity;
import com.mmc.library.bean.Book;
import com.mmc.library.ui.presenters.BookPresenters;
import com.mmc.library.ui.presenters.base.Message;
import com.mmc.library.ui.view.LoadView;
import com.mmc.library.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;

/**
 * Created by luxingwen on 2017/3/2.
 */

public class MyBookActivity extends BaseActivity<BookPresenters> implements LoadView {
    private BookAdapter adapter;
    private ArrayList<Book> dataList;
    @BindView(R.id.myBookListView)
    ListView myBookListView;
    @OnItemClick(R.id.myBookListView)
    void itemClick(int position){
        Intent intent = new Intent(MyBookActivity.this, BookInfoActivity.class);
        Bundle mbundle = new Bundle();
        int bookId=dataList.get(position).getId();
        mbundle.putSerializable("bookId",bookId);
        intent.putExtras(mbundle);
        startActivity(intent);
    }
    @BindView(R.id.myBooktoolbar)
    Toolbar toolbar;

    @Override
    protected int getContentView() {
        return R.layout.activity_my_book;
    }

    @Override
    protected void initData() {
        setSupportActionBar(toolbar);
        dataList=new ArrayList<>();
        pdg = ProgressDialog.show(this,"","加载我的图书中。。。");
        mPresenter.loadMyBookList(Message.obtainStr(this,user.getToken()));
    }

    @Override
    protected BookPresenters getPresenter() {
        return new BookPresenters();
    }

    @Override
    public void LoadFailed() {

    }

    @Override
    public void LoadSuccuse(String str) {

    }

    @Override
    public void LoadSuccuse(Message msg) {
        Log.e("tag","here");
        List<Book> res = (ArrayList<Book>)msg.obj;
        if(res.size() == 0) {
            showMessage("没有图书");
            return;
        }
        dataList.clear();
        dataList.addAll(res);
        adapter = new BookAdapter(MyBookActivity.this,dataList);
        myBookListView.setAdapter(adapter);
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

    }

    @Override
    public void handleMessage(Message message) {
        switch (message.what) {
            case Constant.DISMIIS_DIALOG:
                dismissDialog();
                break;
            case Constant.LOAD_BOOK_FAILD_CODE:
                LoadFailed();
                break;
            case Constant.LOAD_BOOK_SUCCUSE_CODE:
                LoadSuccuse(message);
                break;
            case Constant.LOAD_BOOK_FINISH:
                LoadFinish();
                break;
        }
    }
}
