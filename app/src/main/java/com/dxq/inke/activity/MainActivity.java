package com.dxq.inke.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.dxq.inke.R;
import com.dxq.inke.fragment.LiveFragment;
import com.dxq.inke.fragment.MineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String FRAG_LIVE = "live";
    private static final String FRAG_MINE = "mine";
    @BindView(R.id.iv_live)
    ImageView mIvLive;
    @BindView(R.id.iv_live_publish)
    ImageView mIvLivePublish;
    @BindView(R.id.iv_mine)
    ImageView mIvMine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //使用相关插件之前,需要确保已经导入了butterknife三方库
        ButterKnife.bind(this);
        //刚进来的时候,就默认去切换到LiveFragment
        switchLive();
    }

    @OnClick({R.id.iv_live, R.id.iv_live_publish, R.id.iv_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_live:
                switchLive();
                break;
            case R.id.iv_live_publish:
                Toast.makeText(MainActivity.this, "点击了iv_live_publish", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_mine:
                switchMine();
                break;
        }
    }

    private void switchLive() {
        mIvLive.setSelected(true);
        mIvMine.setSelected(false);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        LiveFragment liveFragment = (LiveFragment) getSupportFragmentManager().findFragmentByTag(FRAG_LIVE);
        MineFragment mineFragment = (MineFragment) getSupportFragmentManager().findFragmentByTag(FRAG_MINE);

        //如果FragmentManager中已经在展示MineFragment,就应该先隐藏掉它,再来展示LiveFragment
        if (mineFragment != null) {
            fragmentTransaction.hide(mineFragment);
        }
        //首次add
        if (liveFragment == null) {
            //事务中未添加过fragment
            fragmentTransaction.add(R.id.fl_frag, new LiveFragment(), FRAG_LIVE);
        } else {
            fragmentTransaction.show(liveFragment);
        }
        //最后commit
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void switchMine() {
        mIvLive.setSelected(false);
        mIvMine.setSelected(true);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        LiveFragment liveFragment = (LiveFragment) getSupportFragmentManager().findFragmentByTag(FRAG_LIVE);
        MineFragment mineFragment = (MineFragment) getSupportFragmentManager().findFragmentByTag(FRAG_MINE);

        //如果FragmentManager中已经在展示MineFragment,就应该先隐藏掉它,再来展示LiveFragment
        if (liveFragment != null) {
            fragmentTransaction.hide(liveFragment);
        }
        //首次add
        if (mineFragment == null) {
            //事务中未添加过fragment
            fragmentTransaction.add(R.id.fl_frag, new MineFragment(), FRAG_MINE);
        } else {
            fragmentTransaction.show(mineFragment);
        }
        //最后commit
        fragmentTransaction.commitAllowingStateLoss();
    }
}