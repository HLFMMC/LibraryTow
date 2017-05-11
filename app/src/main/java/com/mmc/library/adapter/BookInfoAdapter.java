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
import com.mmc.library.bean.Community;
import com.mmc.library.utils.Constant;

import java.util.List;

/**
 * Created by luxingwen on 2017/3/2.
 */

public class BookInfoAdapter extends BaseAdapter{

    private Context mContext;
    private LayoutInflater inflater;
    private List<Community> mData;

    public BookInfoAdapter(Context context,List<Community> list){
        this.mContext=context;
        this.mData=list;
        this.inflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.community_item,null);
            holder=new Holder();
            holder.userimg= (ImageView) convertView.findViewById(R.id.bookinfo_user_pic);
            holder.username= (TextView) convertView.findViewById(R.id.bookinfo_user_name);
            holder.content= (TextView) convertView.findViewById(R.id.bookinfo_community);
            convertView.setTag(holder);
        }else{
            holder=(Holder)convertView.getTag();
        }
        if(mData.get(position).getUser().getPic()!=""){
            String pic=mData.get(position).getUser().getPic();
            if (pic.contains("upload/")){
                pic= Constant.API_ADDRESS+"/"+pic;
            }
            Glide.with(mContext).load(pic).centerCrop().placeholder(R.drawable.nocover).crossFade().into(holder.userimg);
        }else{
            holder.userimg.setImageResource(R.drawable.nocover);
        }
        holder.username.setText(mData.get(position).getUser().getUsername());
        holder.content.setText(mData.get(position).getContent());
        return convertView;
    }

    private class Holder{
        ImageView userimg;
        TextView username;
        TextView content;
    }

}
