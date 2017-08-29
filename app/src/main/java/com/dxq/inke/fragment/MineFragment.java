package com.dxq.inke.fragment;
/*
 * Created by CREEPER_D on 2017/8/25.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dxq.inke.R;

public class MineFragment extends LogFragment {
    @Override
    public View onChildCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_mine, container, false);
    }

    @Override
    protected void initData() {

    }
}
