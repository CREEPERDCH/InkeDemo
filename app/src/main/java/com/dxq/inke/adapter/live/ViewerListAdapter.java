package com.dxq.inke.adapter.live;
/*
 * Created by CREEPER_D on 2017/8/31.
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dxq.inke.R;
import com.dxq.inke.adapter.live.base.MyBaseAdapter;
import com.dxq.inke.adapter.live.base.MyBaseViewHolder;
import com.dxq.inke.bean.UserBean;
import com.dxq.inke.bean.ViewerBean;

import java.util.ArrayList;

public class ViewerListAdapter extends MyBaseAdapter<ViewerBean> {

    public ViewerListAdapter(ArrayList<ViewerBean> list) {
        super(list);
    }

    @Override
    protected void bindData(MyBaseViewHolder holder, int position, ViewerBean viewerBean) {
        ViewerBean.ExtBean ext = viewerBean.getExt();
        holder.loadImage(R.id.sDraweeView_viewer_icon, viewerBean.getPortrait());
        if (position < 3 && ext != null) {
            //only care about 3 backgrounds
            holder.loadImage(R.id.sDraweeView_bg, ext.getLight());
        } else {
            //避免复用产生问题,所以其他的view也需要设置一下,设置为透明的背景
            holder.setImageRes(R.id.sDraweeView_bg, R.drawable.bg_transparent);
        }
    }

    @Override
    public MyBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewer_icon, parent, false);
        MyBaseViewHolder viewHolder = new MyBaseViewHolder(inflate);
        return viewHolder;
    }

    public void updateData(ArrayList<ViewerBean> users) {
        if (users == null || users.size() == 0) {
            return;
        }
        mList.clear();
        mList.addAll(users);
        notifyDataSetChanged();
    }
}
