package com.dxq.inke.adapter.live;
/*
 * Created by CREEPER_D on 2017/8/28.
 */

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dxq.inke.R;
import com.dxq.inke.adapter.live.base.MyBaseAdapter;
import com.dxq.inke.adapter.live.base.MyBaseViewHolder;
import com.dxq.inke.bean.HotLiveBean;
import com.dxq.inke.bean.LiveNodesBean;
import com.dxq.inke.bean.TypeBean;
import com.dxq.inke.bean.UsersBean;
import com.dxq.inke.utils.Constant;
import com.dxq.inke.utils.UserConstant;

import java.util.ArrayList;
import java.util.List;

import static com.dxq.inke.bean.TypeBean.TYPE_SEARCH_ANCHOR_TYPES;
import static com.dxq.inke.bean.TypeBean.TYPE_SEARCH_RECOMMEND;

public class SearchAdapter extends MyBaseAdapter<TypeBean> {

    onSearchClickListener searchClickListener;

    public void setOnClickListener(onSearchClickListener listener) {
        this.searchClickListener = listener;
    }

    public interface onSearchClickListener {

        void onClickSearchItem(int index);

        void onClickSearchOnePicture(View view, int position, HotLiveBean lives);
    }

    public SearchAdapter(ArrayList<TypeBean> data) {
        super(data);
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getType();
    }

    @Override
    protected void bindData(MyBaseViewHolder holder, int position, TypeBean typeBean) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == TYPE_SEARCH_RECOMMEND) {
            UsersBean bean = (UsersBean) typeBean;
            holder.setText(R.id.name, bean.getUser().getNick());
            holder.setText(R.id.type, bean.getReason());
            int sexResId = bean.getUser().getSex() == 0 ? R.drawable.global_icon_male : R.drawable.global_icon_female;
            holder.setImageRes(R.id.sex, sexResId);
            holder.setImageRes(R.id.rank, UserConstant.getRank(bean.getUser().getLevel()));
            //是否在线
            boolean isLive = !TextUtils.isEmpty(bean.getLive_id());
            if (isLive) {
                holder.getView(R.id.live).setVisibility(View.VISIBLE);
            } else {
                holder.getView(R.id.live).setVisibility(View.GONE);
            }
            //用户头像50x50
            holder.loadImage(R.id.icon, bean.getUser().getPortrait());
            //todo 设置点击时跳转到对应的主播直播房间
        } else if (itemViewType == TYPE_SEARCH_ANCHOR_TYPES) {
            LiveNodesBean bean = (LiveNodesBean) typeBean;
            List<HotLiveBean> lives = bean.getLives();
            holder.loadImage(R.id.one, lives.get(0).getCreator().getPortrait());
            //todo 设置1点击事件
            holder.setText(R.id.one_number, lives.get(0).getOnline_users() + "人");
            holder.loadImage(R.id.two, lives.get(1).getCreator().getPortrait());
            //todo 设置2点击事件
            holder.setText(R.id.two_number, lives.get(1).getOnline_users() + "人");
            holder.loadImage(R.id.three, lives.get(2).getCreator().getPortrait());
            //todo 设置3点击事件
            holder.setText(R.id.three_number, lives.get(2).getOnline_users() + "人");
            //设置标题
            holder.setText(R.id.title, bean.getTitle());
            //todo 设置更多的点击事件
        } else {
            holder.setText(R.id.title, "今日推荐");
        }
    }

    @Override
    public MyBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_SEARCH_RECOMMEND) {
            //每日推荐
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_recommend, parent, false);
        } else if (viewType == TYPE_SEARCH_ANCHOR_TYPES) {
            //好声音、游戏达人，用户分组类型
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_type, parent, false);
        } else {
            //标题（今日推荐）
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_title, parent, false);
        }
        return new MyBaseViewHolder(view);
    }

    public void updateDatas(ArrayList<TypeBean> datas) {
        if (datas == null || datas.size() == 0) {
            return;
        }
        if (mList == null) {
            mList = datas;
        } else {
            mList.clear();
            mList.addAll(datas);
        }
        //通知更新
        notifyDataSetChanged();
    }
}
