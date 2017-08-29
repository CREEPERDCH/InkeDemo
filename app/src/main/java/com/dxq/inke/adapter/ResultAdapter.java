package com.dxq.inke.adapter;
/*
 * Created by CREEPER_D on 2017/8/28.
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dxq.inke.R;
import com.dxq.inke.adapter.live.base.MyBaseAdapter;
import com.dxq.inke.adapter.live.base.MyBaseViewHolder;
import com.dxq.inke.bean.UserBean;
import com.dxq.inke.bean.UsersBean;
import com.dxq.inke.utils.Constant;
import com.dxq.inke.utils.UserConstant;

import java.util.ArrayList;

public class ResultAdapter extends MyBaseAdapter<UsersBean> {
    public ResultAdapter(ArrayList<UsersBean> data) {
        super(data);
    }

    @Override
    protected void bindData(MyBaseViewHolder holder, int position, UsersBean usersBean) {
        UserBean user = usersBean.getUser();
        holder.setText(R.id.name, user.getNick());
        holder.setText(R.id.describe, user.getDescription());
        holder.setImageRes(R.id.sex, user.getSex() == 0 ? R.drawable.global_icon_male : R.drawable.global_icon_female);
        holder.setImageRes(R.id.rank, UserConstant.getRank(user.getLevel()));
        //头像
        holder.loadImage(R.id.icon, user.getPortrait());
    }

    @Override
    public MyBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_result, parent, false);
        return new MyBaseViewHolder(inflate);
    }

    public void updateData(ArrayList<UsersBean> resultList) {
        if (resultList == null || resultList.size() == 0) {
            return;
        }
        if (mList == null) {
            mList = resultList;
        } else {
            mList.clear();
            mList.addAll(resultList);
        }
        notifyDataSetChanged();
    }
}
