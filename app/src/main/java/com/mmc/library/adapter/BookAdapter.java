package com.mmc.library.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mmc.library.R;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by HM on 2017/2/22.
 */
public class BookAdapter extends BaseAdapter {
    private Context mContext;
    private LinkedList<HashMap<String,String>> mList;

    private LayoutInflater inflater;
    public BookAdapter(Context context, LinkedList<HashMap<String, String>> list){
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
            convertView.setTag(holder);
        } else {
            holder = (Holder)convertView.getTag();
        }
        holder.tvBookName.setText(mList.get(position).get("bookName"));
        holder.tvBooKDesc.setText(mList.get(position).get("bookDesc"));
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
