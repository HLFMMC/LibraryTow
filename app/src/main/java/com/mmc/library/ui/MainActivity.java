package com.mmc.library.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mmc.library.R;
import com.mmc.library.adapter.BookAdapter;
import com.mmc.library.bean.Book;
import com.mmc.library.ui.activity.BookInfoActivity;
import com.mmc.library.ui.activity.LoginActivity;
import com.mmc.library.ui.activity.PushBookActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    private  Toolbar toolbar;
    private FloatingActionButton fab;
    private ListView bookList;
    private BookAdapter adapter;
    private LinearLayout categoryLy;
    private Spinner category;
    private List<Book> dataList;


    LinkedList<HashMap<String,String>> list;
    private ArrayAdapter<String> categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initData();
        new Thread(networtTask).start();
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        bookList = (ListView) findViewById(R.id.bookList);
        categoryLy = (LinearLayout) findViewById(R.id.categoryLy);
        category = (Spinner) findViewById(R.id.category);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        setSupportActionBar(toolbar);
        fab.setOnClickListener(this);
    }


    public void initData(){
        dataList=new ArrayList<Book>();
        adapter=new BookAdapter(MainActivity.this,dataList);
        bookList.setAdapter(adapter);
        bookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("mylog info","----->info--->"+position);
                Intent intent=new Intent(MainActivity.this,BookInfoActivity.class);
                intent.putExtra("bookId",dataList.get(position).getId());
                startActivity(intent);
            }
        });
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data=msg.getData();
            String str=data.getString("datalist");

            List<Book> res=new Gson().fromJson(str,new TypeToken<ArrayList<Book>>(){}.getType());
            if(res.size()>0){
                dataList.clear();
            }
            dataList.addAll(res);
            System.out.println("datalist size.."+dataList.size());
            for (int i=0;i<dataList.size();i++){
                System.out.println(dataList.get(i).toString());
            }
            adapter.notifyDataSetChanged();
        }
    };

    Runnable networtTask=new Runnable() {
        Message msg = new Message();
        Bundle data = new Bundle();
        String res="";
        @Override
        public void run() {

            try{
                res =new Book().allBook();
               data.putString("datalist",res);

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
           case R.id.fab:
               Intent intent = new Intent(MainActivity.this,PushBookActivity.class);
               startActivity(intent);
               break;
       }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(MainActivity.this, BookInfoActivity.class);
        Bundle mbundle = new Bundle();
        mbundle.putSerializable("book",list.get(position));
        intent.putExtras(mbundle);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.login) {
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }
}
