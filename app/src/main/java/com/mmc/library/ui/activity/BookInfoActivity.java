package com.mmc.library.ui.activity;

import android.app.ProgressDialog;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mmc.library.R;
import com.mmc.library.adapter.BookInfoAdapter;
import com.mmc.library.base.BaseActivity;
import com.mmc.library.bean.BookInfo;
import com.mmc.library.bean.Community;
import com.mmc.library.ui.presenters.BookPresenters;
import com.mmc.library.ui.presenters.base.Message;
import com.mmc.library.ui.view.LoadView;
import com.mmc.library.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;

/**
 * Created by HM on 2017/2/28.
 */
public class BookInfoActivity  extends BaseActivity<BookPresenters> implements LoadView {
    private List<Community> community;
    private BookInfoAdapter adapter;
    private int bookId;

    @BindView(R.id.book_info_img_cover)
    ImageView book_info_img_cover;

    @BindView(R.id.book_info_add_cart)
    Button book_info_add_cart;

    @BindView(R.id.book_info_bookDesc)
    TextView book_info_bookDesc;


    @BindView(R.id.book_info_author)
    TextView book_info_author;

    @BindView(R.id.book_info_bookName)
    TextView book_info_bookName;

    @BindView(R.id.book_info_bookReplayList)
            ListView book_info_bookReplayList;
    @OnItemClick(R.id.book_info_bookReplayList)
    void item(int i){

    }

    @BindView(R.id.book_info_replayBtn)
    Button book_info_replayBtn;

    @BindView(R.id.book_info_buy)
    Button book_info_buy;

    @BindView(R.id.book_info_commit_editText)
    EditText book_info_commit_editText;


    @Override
    protected int getContentView() {
        return R.layout.activity_bookinfo;
    }

    @Override
    protected BookPresenters getPresenter() {
        return new BookPresenters();
    }

    ProgressDialog pdg = null;

    public void initData(){
        pdg = ProgressDialog.show(this,"","加载图书中。。。");
        bookId=getIntent().getIntExtra("bookId",-1);
        if(bookId == -1) {
            showMessage("加载图书信息失败");
            return;
        }
        community=new ArrayList<>();
        adapter=new BookInfoAdapter(BookInfoActivity.this,community);
        book_info_bookReplayList.setAdapter(adapter);
        mPresenter.LoadBookInfo(com.mmc.library.ui.presenters.base.Message.obtain(this,bookId,bookId));
    }

    @Override
    public void showMessage(String message) {
        showToast(message);
    }

    @Override
    public void handleMessage(com.mmc.library.ui.presenters.base.Message msg) {
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
        showMessage("加载图书信息失败");
    }

    @Override
    public void LoadSuccuse(String str) {

    }

    /**
     * 加载图书信息成功
     * @param msg
     */
    @Override
    public void LoadSuccuse(Message msg) {
        BookInfo bookInfo = (BookInfo) msg.obj;
        book_info_bookDesc.setText(bookInfo.getBook().getDesc());
        book_info_bookName.setText(bookInfo.getBook().getName());
        book_info_author.setText(bookInfo.getBook().getAutor());
        if(!TextUtils.isEmpty(bookInfo.getBook().getPic())){
            Glide.with(BookInfoActivity.this)
                    .load(bookInfo.getBook().getPic())
                    .centerCrop()
                    .placeholder(R.drawable.nocover)
                    .crossFade().into(book_info_img_cover);
        }
        if(bookInfo.getCommunity()!=null){
            if (bookInfo.getCommunity().size()>0){
                community.clear();
            }
            community.addAll(bookInfo.getCommunity());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void LoadFinish() {

    }

    @Override
    public void dismissDialog() {
        if(pdg != null)
            pdg.dismiss();
    }
}
