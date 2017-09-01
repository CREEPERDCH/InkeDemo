package com.dxq.inke.adapter.live.base;
/*
 * Created by CREEPER_D on 2017/8/25.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public abstract class MyBaseAdapter<T> extends RecyclerView.Adapter<MyBaseViewHolder> {

    protected ArrayList<T> mList;

    public MyBaseAdapter(ArrayList<T> list) {
        mList = list;
    }

    @Override
    public void onBindViewHolder(MyBaseViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(view, position);
                }
            }
        });
        bindData(holder, position, mList.get(position));
    }

    protected abstract void bindData(MyBaseViewHolder holder, int position, T t);

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public ArrayList<T> getData() {
        return mList;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }
}
