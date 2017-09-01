package com.dxq.inke.adapter.live;
/*
 * Created by CREEPER_D on 2017/8/31.
 */

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dxq.inke.R;
import com.dxq.inke.adapter.live.base.MyBaseAdapter;
import com.dxq.inke.adapter.live.base.MyBaseViewHolder;
import com.dxq.inke.bean.GiftBean;

import java.util.ArrayList;

public class GiftGridAdapter extends MyBaseAdapter<GiftBean> {

    public GiftGridAdapter(ArrayList<GiftBean> currentPageGift) {
        super(currentPageGift);

    }

    @Override
    protected void bindData(MyBaseViewHolder holder, int position, GiftBean giftBean) {
        //如果javaBean为空,所有控件都设为GONE
        if (giftBean == null || TextUtils.isEmpty(giftBean.getName())) {
            holder.getView(R.id.ll_item).setVisibility(View.GONE);
        } else {
            holder.getView(R.id.ll_item).setVisibility(View.VISIBLE);
            holder.setText(R.id.tv_price, String.valueOf(giftBean.getGold()));
            holder.setText(R.id.tv_name, giftBean.getName());
            holder.loadImage(R.id.sdv_gift, giftBean.getImage());

            //如果是被选中,改个背景
            if (giftBean.isSelected()) {
                holder.setBackground(R.id.ll_item, R.drawable.bg_gridview_item_selected);
            } else {
                holder.setBackground(R.id.ll_item, R.drawable.bg_gridview_item);
            }
        }
    }

    @Override
    public MyBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gift_shop, parent, false);
        return new MyBaseViewHolder(inflate);
    }

    public void updateSelect(int position) {
        //让对应的位置的javaBean更新一下,然后notify
        GiftBean giftBean = mList.get(position);
        giftBean.setSelected(true);
        notifyDataSetChanged();
    }

    //取消Adapter当中的数据的选中
    public void cancelAllSelect() {
        for (int i = 0; i < mList.size(); i++) {
            GiftBean giftBean = mList.get(i);
            if (giftBean.isSelected()) {
                giftBean.setSelected(false);
            }
        }
        notifyDataSetChanged();
    }
}
