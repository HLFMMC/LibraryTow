package com.mmc.library.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mmc.library.R;
import com.mmc.library.adapter.BookAdapter;
import com.mmc.library.base.BaseActivity;
import com.mmc.library.bean.Book;
import com.mmc.library.ui.presenters.MainPresenters;
import com.mmc.library.ui.presenters.MyBookPresenters;
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

public class MyBookActivity extends BaseActivity<MyBookPresenters> implements LoadView {
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
        dataList=new ArrayList<Book>();
        adapter=new BookAdapter(MyBookActivity.this,dataList);
        myBookListView.setAdapter(adapter);
        mPresenter.loadMyBookList(com.mmc.library.ui.presenters.base.Message.obtain(this,user.getToken(),user.getToken()));
    }

    @Override
    protected MyBookPresenters getPresenter() {
        return new MyBookPresenters();
    }

    @Override
    public void LoadFailed() {

    }

    @Override
    public void LoadSuccuse(String str) {
        List<Book> res=new Gson().fromJson(str,new TypeToken<ArrayList<Book>>(){}.getType());
        if(res.size()>0){
            dataList.clear();
        }
        dataList.addAll(res);
        adapter.notifyDataSetChanged();
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
        switch (message.what) {

            case Constant.LOAD_BOOK_FAILD_CODE:
                LoadFailed();
                break;
            case Constant.LOAD_BOOK_SUCCUSE_CODE:
                LoadSuccuse((String)message.obj);
                break;
            case Constant.LOAD_BOOK_FINISH:
                LoadFinish();
                break;
        }
    }
}
