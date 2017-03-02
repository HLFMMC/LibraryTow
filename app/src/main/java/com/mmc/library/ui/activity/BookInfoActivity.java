package com.mmc.library.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mmc.library.R;
import com.mmc.library.adapter.BookInfoAdapter;
import com.mmc.library.bean.BookInfo;
import com.mmc.library.bean.Community;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HM on 2017/2/28.
 */
public class BookInfoActivity  extends AppCompatActivity implements View.OnClickListener {
    private TextView bookName;
    private TextView bookDesc;
    private RelativeLayout btn_back;
    private BookInfo bookInfo;
    private TextView bookAuthor;
    private ImageView bookImg;
    private List<Community> community;
    private BookInfoAdapter adapter;
    private  int bookId;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookinfo);
        init();
        inidData();
        new Thread(networtTask).start();
    }
    private void init(){
        bookAuthor= (TextView) findViewById(R.id.bookInfo_author);
        bookImg= (ImageView) findViewById(R.id.book_cover);
        bookDesc = (TextView) findViewById(R.id.bookDesc);
        bookName = (TextView) findViewById(R.id.bookName);
        btn_back = (RelativeLayout) findViewById(R.id.btn_back);
        listView= (ListView) findViewById(R.id.bookReplay);
        btn_back.setOnClickListener(this);
    }


    public void inidData(){
        bookId=getIntent().getIntExtra("bookId",0);
        community=new ArrayList<Community>();
        adapter=new BookInfoAdapter(BookInfoActivity.this,community);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data=msg.getData();
            BookInfo book=(BookInfo) data.getSerializable("bookinfo");
            bookDesc.setText(book.getBook().getDesc());
            bookName.setText(book.getBook().getName());
            bookAuthor.setText(book.getBook().getAutor());
            if(book.getBook().getPic()!=""){
                Glide.with(BookInfoActivity.this).load(book.getBook().getPic()).centerCrop().placeholder(R.drawable.nocover).crossFade().into(bookImg);
            }
            if(book.getCommunity()!=null){
                if (book.getCommunity().size()>0){
                    community.clear();
                }
                community.addAll(book.getCommunity());
                adapter.notifyDataSetChanged();
            }

        }
    };

    Runnable networtTask=new Runnable() {
        Message msg = new Message();
        Bundle data = new Bundle();
        String res="";
        @Override
        public void run() {
            try{
                BookInfo res =new BookInfo().getBookInfo(bookId);

               data.putSerializable("bookinfo",res);

            }catch (Exception e){
                Log.e("mylog ","mylog----->"+e.toString());
            }

            msg.setData(data);
            handler.sendMessage(msg);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
