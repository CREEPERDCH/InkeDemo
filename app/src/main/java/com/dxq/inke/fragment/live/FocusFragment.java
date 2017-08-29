package com.dxq.inke.fragment.live;
/*
 * Created by CREEPER_D on 2017/8/25.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dxq.inke.fragment.LogFragment;

public class FocusFragment extends LogFragment {
    @Override
    public View onChildCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getContext());
        textView.setTextSize(50);
        textView.setText(getClass().getSimpleName());
        return textView;
    }

    @Override
    protected void initData() {

    }
}
