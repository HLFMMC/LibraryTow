package com.mmc.library.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mmc.library.R;
import com.mmc.library.adapter.BookAdapter;
import com.mmc.library.base.BaseActivity;
import com.mmc.library.bean.Book;
import com.mmc.library.ui.presenters.MainPresenters;
import com.mmc.library.ui.presenters.base.Message;
import com.mmc.library.ui.view.LoadView;
import com.mmc.library.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;


public class MainActivity extends BaseActivity<MainPresenters> implements LoadView {
    private BookAdapter adapter;
    private ArrayList<Book> dataList;

    @BindView(R.id.main_bookList)
            ListView main_bookList;
    @OnItemClick(R.id.main_bookList)
    void itemClick(int position){
        Intent intent = new Intent(MainActivity.this, BookInfoActivity.class);
        Bundle mbundle = new Bundle();
        mbundle.putSerializable("bookId",position);
        intent.putExtras(mbundle);
        startActivity(intent);
    }
    @BindView(R.id.main_category)
            Spinner main_category;
    @BindView(R.id.main_fab)
            FloatingActionButton main_fab;
    @OnClick(R.id.main_fab)
    void showFAB(){
        Intent intent = new Intent(MainActivity.this,PushBookActivity.class);
        startActivity(intent);
    }

    @BindView(R.id.main_searchBtn)
    Button main_searchBtn;
    @BindView(R.id.toolbar)
            Toolbar toolbar;

    @BindView(R.id.categoryLy)
            LinearLayout categoryLy;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    public void initData(){
        setSupportActionBar(toolbar);
        dataList=new ArrayList<>();
        adapter=new BookAdapter(MainActivity.this,dataList);
        main_bookList.setAdapter(adapter);
        LoadAllBook();
    }

    public void LoadAllBook(){
        mPresenter.loadBookList(com.mmc.library.ui.presenters.base.Message.obtain(this));
    }

    @Override
    protected MainPresenters getPresenter() {
        return new MainPresenters();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.e("tag","onCreateOptionsMenu");
        if(!checkUserIsLogin())
            getMenuInflater().inflate(R.menu.menu_main, menu);
        else
            getMenuInflater().inflate(R.menu.menu_logout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private boolean isNeedUpdateMenu = false;
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(!isNeedUpdateMenu)
        Log.e("tag","onPrepareOptionsMenu");
        menu.clear();
        if(!checkUserIsLogin())
            getMenuInflater().inflate(R.menu.menu_main, menu);
        else
            getMenuInflater().inflate(R.menu.menu_logout, menu);
        isNeedUpdateMenu = !isNeedUpdateMenu;
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.login) {
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }else if(id == R.id.logout){
            cache.remove(Constant.LOGIN_USER_CACHE_KEY);
            showMessage("登出成功！");
            isNeedUpdateMenu = true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showMessage(String message) {
        showToast(message);
    }

    @Override
    public void handleMessage(com.mmc.library.ui.presenters.base.Message msg){
        switch (msg.what) {
            case Constant.LOGIN_FINISH:
                isNeedUpdateMenu = true;
                break;
            case Constant.LOAD_BOOK_FAILD_CODE:
                LoadFailed();
                break;
            case Constant.LOAD_BOOK_SUCCUSE_CODE:
                LoadSuccuse((String)msg.obj);
                break;
            case Constant.LOAD_BOOK_FINISH:
                LoadFinish();
                break;
        }
    }

    public void LoadFailed(){
        main_bookList.setVisibility(View.GONE);
        showMessage("加载图书失败");
    }
    public void LoadSuccuse(String str){
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

    public void LoadFinish(){

    }

    @Override
    public void dismissDialog() {
    }
}
