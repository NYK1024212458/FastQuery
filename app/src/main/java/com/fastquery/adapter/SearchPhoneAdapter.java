package com.fastquery.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fastquery.R;
import com.fastquery.bean.SearchPhoneBean;

import java.util.ArrayList;

public class SearchPhoneAdapter extends RecyclerView.Adapter<SearchPhoneAdapter.RecyclerHolder>{

Context context;
ArrayList<SearchPhoneBean> list;

public SearchPhoneAdapter(Context context,ArrayList<SearchPhoneBean> list){

    this.context=context;
    this.list=list;


}


    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_search_phone, null);


        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {



    holder.textView.setText(list.get(position).getContent());

        holder.imageView.setImageResource(list.get(position).getId());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RecyclerHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;


        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.iv_image);
            textView=itemView.findViewById(R.id.tv_content);
        }
    }


}
