package com.dxq.inke.adapter.live;
/*
 * Created by CREEPER_D on 2017/8/26.
 */

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dxq.inke.R;
import com.dxq.inke.adapter.live.base.MyBaseAdapter;
import com.dxq.inke.adapter.live.base.MyBaseViewHolder;
import com.dxq.inke.bean.BannerListBean;
import com.dxq.inke.bean.HotLiveBean;
import com.dxq.inke.bean.LabelBean;
import com.dxq.inke.bean.TypeBean;
import com.dxq.inke.utils.Constant;

import java.util.ArrayList;
import java.util.Iterator;

import static com.dxq.inke.bean.TypeBean.TYPE_BANNER;

public class HotListAdapter extends MyBaseAdapter<TypeBean> {

    private static final String TAG = "dxq";

    public HotListAdapter(ArrayList<TypeBean> list) {
        super(list);
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getType();
    }

    @Override
    protected void bindData(MyBaseViewHolder holder, int position, TypeBean typeBean) {
        //判断type类型
        if (getItemViewType(position) == TYPE_BANNER) {
            //要设置轮播图的数据
            BannerListBean bannerListBean = (BannerListBean) typeBean;
            holder.setBanner(R.id.banner, bannerListBean.getTicker());
        } else {
            //要设置主播展示的相关信息
            //需要强转一下
            HotLiveBean hotLiveBean = (HotLiveBean) typeBean;
            holder.setText(R.id.name, hotLiveBean.getCreator().getNick());
            holder.setText(R.id.viewCount, hotLiveBean.getOnline_users());
            //取出主播设置的那些tag粗来作为一个List集合
            ArrayList<LabelBean> label = hotLiveBean.getExtra().getLabel();
            ArrayList<String> tags = new ArrayList<>();
            for (int i = 0; i < label.size(); i++) {
                LabelBean labelBean = label.get(i);
                String tab_key = labelBean.getTab_key();
                tags.add(tab_key);
            }
            holder.setTags(R.id.ll_tag, tags);
            holder.loadImage(R.id.src, hotLiveBean.getCreator().getPortrait());
            holder.loadImage(R.id.icon, hotLiveBean.getCreator().getPortrait());
        }
    }

    //专门更新数据用的方法
    public void updateData(ArrayList<HotLiveBean> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        //怎么更新除了轮播图以外的数据呢?
        //遍历mList,将以前的除了轮播图以外的数据统统删除
        //遍历集合的时候,不能直接用集合的remove来删除,不然可能会出现并发异常ConcurrentModificationException
        Iterator<TypeBean> iterator = mList.iterator();
        while (iterator.hasNext()) {
            TypeBean next = iterator.next();
            if (next.getType() != TYPE_BANNER) {
                iterator.remove();
            }
        }
        mList.addAll(list);
        notifyDataSetChanged();
    }

    //更新轮播图的数据
    public void updateBannerData(BannerListBean body) {
        //需要先把以前的轮播图给移除掉
        //遍历集合的时候,不能直接用集合的remove来删除,不然可能会出现并发异常ConcurrentModificationException
        Iterator<TypeBean> iterator = mList.iterator();
        while (iterator.hasNext()) {
            TypeBean next = iterator.next();
            if (next.getType() == TYPE_BANNER) {
                iterator.remove();
            }
        }
        mList.add(0, body);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        int itemCount = super.getItemCount();
        Log.d(TAG, "getItemCount: " + itemCount);
        return itemCount;
    }

    //viewType代表当前item位置上的type类型
    @Override
    public MyBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_BANNER) {
            //要做一个轮播图相关的view出来,对应的viewHolder
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner, parent, false);

        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hot_live, parent, false);
        }
        return new MyBaseViewHolder(view);
    }
}
