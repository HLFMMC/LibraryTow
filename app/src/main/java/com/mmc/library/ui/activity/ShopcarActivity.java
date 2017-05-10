package com.mmc.library.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.mmc.library.R;
import com.mmc.library.adapter.BookAdapter;
import com.mmc.library.base.BaseActivity;
import com.mmc.library.bean.Book;
import com.mmc.library.ui.presenters.BookPresenters;
import com.mmc.library.ui.presenters.ShopcarPresenters;
import com.mmc.library.ui.presenters.base.Message;
import com.mmc.library.ui.view.LoadView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;

/**
 * Created by luxingwen on 2017/5/9.
 */

public class ShopcarActivity  extends BaseActivity<ShopcarPresenters> implements LoadView {
    private BookAdapter adapter;
    private ArrayList<Book> dataList;
    @BindView(R.id.bookTitle)
    TextView bookTitle;
    @BindView(R.id.myBookListView)
    ListView myBookListView;
    @OnItemClick(R.id.myBookListView)
    void itemClick(int position){
        Intent intent = new Intent(ShopcarActivity.this, BookInfoActivity.class);
        Bundle mbundle = new Bundle();
        int bookId=dataList.get(position).getId();
        mbundle.putSerializable("bookId",bookId);
        intent.putExtras(mbundle);
        startActivity(intent);
    }
    @Override
    public void showMessage(String message) {

    }

    @Override
    public void handleMessage(Message message) {

    }

    @Override
    public void LoadFailed() {

    }

    @Override
    public void LoadSuccuse(String str) {

    }

    @Override
    public void LoadSuccuse(Message msg) {
        List<Book> res = (ArrayList<Book>)msg.obj;
        if(res.size() == 0) {
            showMessage("没有图书");
            return;
        }
        dataList.clear();
        dataList.addAll(res);
        adapter = new BookAdapter(ShopcarActivity.this,dataList);
        myBookListView.setAdapter(adapter);
    }

    @Override
    public void LoadFinish() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_my_book;
    }

    @Override
    protected void initData() {
        bookTitle.setText("购物车");
      //  setSupportActionBar(toolbar);
        dataList=new ArrayList<>();
       // pdg = ProgressDialog.show(this,"","加载我的图书中。。。");
        mPresenter.loadShopcarList(Message.obtainStr(this,user.getToken()));
    }

    @Override
    protected ShopcarPresenters getPresenter() {
        return new ShopcarPresenters();
    }
}
