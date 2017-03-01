package com.mmc.library.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mmc.library.R;
import com.mmc.library.bean.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by HM on 2017/2/22.
 */
public class BookAdapter extends BaseAdapter {
    private Context mContext;
    private List<Book> mList;

    private LayoutInflater inflater;
    public BookAdapter(Context context,List<Book> list){
        this.mContext = context;
        this.mList = list;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.books_view,null);
            holder = new Holder();
            holder.bookCover = (ImageView)convertView.findViewById(R.id.book_cover);
            holder.tvBookName = (TextView)convertView.findViewById(R.id.bookName);
            holder.tvBooKDesc = (TextView)convertView.findViewById(R.id.bookDesc);
            holder.tvAuthor=(TextView)convertView.findViewById(R.id.bookAuthor);
            convertView.setTag(holder);
        } else {
            holder = (Holder)convertView.getTag();
        }

        if(mList.get(position).getPic()!=""){
            Glide.with(mContext).load(mList.get(position).getPic()).centerCrop().placeholder(R.drawable.nocover).crossFade().into(holder.bookCover);
        }else{
            holder.bookCover.setImageResource(R.drawable.nocover);
        }
        holder.tvBookName.setText(mList.get(position).getName());
        holder.tvBooKDesc.setText(mList.get(position).getDesc());
        holder.tvAuthor.setText(mList.get(position).getAutor());
//        holder.bookCover.setBackgroundResource(Integer.parseInt(mList.get(position).get("bookCover")));
        return convertView;
    }

    private class Holder {
        private TextView tvBookName;
        private TextView tvBooKDesc;
        private TextView tvAuthor;
        private ImageView bookCover;
    }
}
