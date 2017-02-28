package com.mmc.library.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.mmc.library.R;
import com.mmc.library.adapter.BookAdapter;
import com.mmc.library.ui.activity.BookInfoActivity;
import com.mmc.library.ui.activity.LoginActivity;
import com.mmc.library.ui.activity.PushBookActivity;
import com.mmc.library.ui.activity.RegisterActivity;

import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    private  Toolbar toolbar;
    private FloatingActionButton fab;
    private ListView bookList;
    private BookAdapter adapter;
    private LinearLayout categoryLy;
    private Spinner category;

    LinkedList<HashMap<String,String>> list;
    private ArrayAdapter<String> categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {

        list = new LinkedList<>();
        for (int i = 0;i<10;i++) {
            HashMap<String,String> map = new HashMap<>();
            map.put("bookName","bookName"+i);
            map.put("bookDesc","bookDesc"+i);
            list.add(map);
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        bookList = (ListView) findViewById(R.id.bookList);
        categoryLy = (LinearLayout) findViewById(R.id.categoryLy);
        category = (Spinner) findViewById(R.id.category);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        LinkedList<String> arry = new LinkedList<>();
        arry.add("北京");
        arry.add("上海");
        arry.add("广州");
        arry.add("深圳");
        categoryAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arry);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(categoryAdapter);

        setSupportActionBar(toolbar);
        fab.setOnClickListener(this);
        adapter = new BookAdapter(MainActivity.this,list);
        bookList.setAdapter(adapter);
        bookList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
//                        categoryLy.setVisibility(View.GONE);
                        fab.hide();
                        break;
                    case MotionEvent.ACTION_UP:
//                        categoryLy.setVisibility(View.VISIBLE);
                        fab.show();
                        break;
                }
                return false;
            }
        });
        bookList.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this,PushBookActivity.class);
        startActivity(intent);
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
