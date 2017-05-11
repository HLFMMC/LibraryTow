package com.mmc.library.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.mmc.library.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;

/**
 * Created by luxingwen on 2017/5/9.
 */

public class ShopcarActivity  extends BaseActivity<ShopcarPresenters> implements LoadView {
    private BookAdapter adapter;//
    private ArrayList<Book> dataList;//图书数据列表
    @BindView(R.id.bookTitle)
    TextView bookTitle;
    @BindView(R.id.myBookListView)
    ListView myBookListView;
    @OnItemClick(R.id.myBookListView)
    void itemClick(int position){//图书item点击事件
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
    public void handleMessage(Message msg) {//获取message
        switch (msg.what){
            case Constant.DISMIIS_DIALOG:
                dismissDialog();
                break;
            case Constant.LOAD_BOOK_FAILD_CODE:
                LoadFailed();
                break;
            case Constant.LOAD_BOOK_SUCCUSE_CODE:
                LoadSuccuse(msg);
                break;
        }
    }

    @Override
    public void LoadFailed() {

    }

    @Override
    public void LoadSuccuse(String str) {

    }

    @Override
    public void LoadSuccuse(Message msg) {
        Log.d("tag","LoadSuccuse-->");
        List<Book> res = (ArrayList<Book>)msg.obj;
        Log.d(TAG,"--->"+res);
        if(res.size() == 0) {
            showMessage("没有图书");
            return;
        }

        dataList.clear();
        dataList.addAll(res);
        adapter.notifyDataSetChanged();//通知适配器，有数据更新
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
      // setSupportActionBar(toolbar);
        dataList=new ArrayList<>();
        adapter = new BookAdapter(ShopcarActivity.this,dataList);
        myBookListView.setAdapter(adapter);
       // pdg = ProgressDialog.show(this,"","加载我的图书中。。。");
        mPresenter.loadShopcarList(Message.obtainStr(this,user.getToken()));
        Log.d("tag","购物车init");
    }

    @Override
    protected ShopcarPresenters getPresenter() {
        return new ShopcarPresenters();
    }
}
