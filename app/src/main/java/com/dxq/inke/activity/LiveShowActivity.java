package com.dxq.inke.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dxq.inke.R;
import com.dxq.inke.adapter.live.MyAdapter;
import com.dxq.inke.bean.HotLiveBean;
import com.dxq.inke.fragment.live.RoomFragment;
import com.dxq.inke.http.IViewerServices;
import com.dxq.inke.http.ServiceGenerator;
import com.dxq.inke.listener.OnSoftKeyBoardChangedListener;
import com.dxq.inke.widget.media.IjkVideoView;

import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.castorflex.android.verticalviewpager.VerticalViewPager;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

import static com.dxq.inke.fragment.live.RoomFragment.LIVE_BEAN;

public class LiveShowActivity extends AppCompatActivity {

    public static final String LIVE_LIST = "LIVE_LIST";
    public static final String LIVE_INDEX = "LIVE_INDEX";
    public static final String TAG_ROOM = "TAG_ROOM";
    @BindView(R.id.viewPager)
    VerticalViewPager mViewPager;
    private int mIndex;//as current page of viewPager
    private ArrayList<HotLiveBean> mLiveBeanList;
    private View mView_video;
    private IjkVideoView mVideoView;
    private int mLastLoadIndex = -1;
    private int rootViewVisibleHeight;//根视图显示高度
    private OnSoftKeyBoardChangedListener mOnSoftKeyBoardChangedListener;

    public void setOnSoftKeyBoardChangedListener(OnSoftKeyBoardChangedListener listener) {
        mOnSoftKeyBoardChangedListener = listener;
    }

    /**
     * 监听软键盘是否打开，通过设置回调用以监听
     *
     * @param activity
     * @param onSoftKeyBoardChangedListener
     */
    public void setListener(Activity activity, final OnSoftKeyBoardChangedListener onSoftKeyBoardChangedListener) {
        //获取Activity的根视图
        final View rootView;
        rootView = activity.getWindow().getDecorView();
        //监听视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //获取当前根视图在屏幕上显示的大小
                Rect rect = new Rect();
                rootView.getWindowVisibleDisplayFrame(rect);
                int visibleHeight = rect.height();
                if (rootViewVisibleHeight == 0) {
                    rootViewVisibleHeight = visibleHeight;
                    return;
                }
                //根视图显示高度没有变化，可以看作软键盘显示 or 隐藏状态没有改变
                if (rootViewVisibleHeight == visibleHeight) {
                    return;
                }
                //根视图显示高度变小超过200，可以看作软键盘显示了
                if (rootViewVisibleHeight - visibleHeight > 200) {
                    if (onSoftKeyBoardChangedListener != null) {
                        onSoftKeyBoardChangedListener.keyBoardShow(rootViewVisibleHeight - visibleHeight);
                    }
                    rootViewVisibleHeight = visibleHeight;
                    return;
                }
                //根视图显示高度变大超过200，可以看作软键盘隐藏了
                if (visibleHeight - rootViewVisibleHeight > 200) {
                    if (onSoftKeyBoardChangedListener != null) {
                        onSoftKeyBoardChangedListener.keyBoardHide(visibleHeight - rootViewVisibleHeight);
                    }
                    rootViewVisibleHeight = visibleHeight;
                }
            }
        });
        setOnSoftKeyBoardChangedListener(onSoftKeyBoardChangedListener);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_show);
        //init ijkPlayer
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");

        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent != null) {
            mIndex = intent.getIntExtra(LIVE_INDEX, 0);
            mLiveBeanList = (ArrayList<HotLiveBean>) intent.getSerializableExtra(LIVE_LIST);
            HotLiveBean hotLiveBean = mLiveBeanList.get(mIndex);
        }
        initView();
        initData();
        initListener();
    }

    private void initView() {
        mView_video = View.inflate(getApplicationContext(), R.layout.view_video, null);
        mVideoView = mView_video.findViewById(R.id.videoView);
        //surfaceView is default, if android 4.0 or above,can use TextureView
        mVideoView.setRender(IjkVideoView.RENDER_TEXTURE_VIEW);
    }

    private void initData() {
        //when viewPager scroll,video widgets scroll also
        ArrayList<RelativeLayout> relativeLayouts = new ArrayList<>();
        for (int i = 0; i < mLiveBeanList.size(); i++) {
            RelativeLayout relativeLayout = new RelativeLayout(getApplicationContext());
            relativeLayout.setBackgroundResource(R.drawable.room_change_bg);
            relativeLayouts.add(relativeLayout);
        }
        MyAdapter adapter = new MyAdapter(relativeLayouts);
        mViewPager.setAdapter(adapter);
    }

    private void initListener() {
        //翻页时,需要复用这个视频组件
        //当前页如果被翻页翻的看不见,那么这一页上的视频组件就要被移除掉
        //再将视频组件给添加到新展示的item上来
        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                int currentItem = mViewPager.getCurrentItem();
                //只要view的position变为0.0了,就意味着它正完全处于当前页在展示
                //就应该让这个view去添加视频组件
                //并且ViewPager当前选中的item位置不等于上一次加载视频组件的item位置才去加载
                if (position == 0 && currentItem != mLastLoadIndex) {
                    mLastLoadIndex = currentItem;
                    //先把视频组件从它以前的父布局中移除掉,不然会报错的
                    ViewParent parent = mView_video.getParent();
                    if (parent != null) {
                        //第一次移除的时候,父布局可能是空的,所以需要加Null判断
                        ((ViewGroup) parent).removeView(mView_video);
                    }
                    //再来添加视频组件
                    ((ViewGroup) page).addView(mView_video);
                    //todo update data sources, fix play address
                    loadVideo();
                }
            }
        });
        //设置好监听以后,让ViewPager翻到对应的位置上
        mViewPager.setCurrentItem(mIndex);
    }

    private void loadVideo() {
        HotLiveBean hotLiveBean = mLiveBeanList.get(mLastLoadIndex);

        //check ups status
        checkLiveStatus(hotLiveBean);

        //1.动态的展示Fragment
        //2.第一次使用事务来动态添加
        //3.不是第一次就拿之前的fragment更新下数据,再刷新显示
        RoomFragment roomFragment = (RoomFragment) getSupportFragmentManager().findFragmentByTag(TAG_ROOM);
        if (roomFragment == null) {
            roomFragment = new RoomFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(LIVE_BEAN, hotLiveBean);
            roomFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().add(R.id.fl_show_frag, roomFragment, TAG_ROOM).commitAllowingStateLoss();
        } else {
            //每次都会去设置数据
            roomFragment.setUI(hotLiveBean);
        }

        String urls = hotLiveBean.getStream_addr();
        //如果在播放,就先停止,然后再来播放
        mVideoView.stopPlayback();
        mVideoView.setVideoURI(Uri.parse(urls));
        mVideoView.start();
    }

    private void checkLiveStatus(HotLiveBean hotLiveBean) {
        String id = hotLiveBean.getId();
        IViewerServices statusServices = ServiceGenerator.getSingleTon().create(IViewerServices.class);
        Call<ResponseBody> call = statusServices.getLiveStatus(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String jsonStr = response.body().string();
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    int alive = jsonObject.getInt("alive");
                    if (alive != 1) {
                        Toast.makeText(LiveShowActivity.this, "主播正在赶来的路上...", Toast.LENGTH_SHORT).show();
                        SystemClock.sleep(1000);
                        finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        RoomFragment fragment = (RoomFragment) getSupportFragmentManager().findFragmentByTag(TAG_ROOM);
        if (fragment != null) {
            boolean needFinishActivity = fragment.onBackPressed();
            if (!needFinishActivity) {
                return;
            }
        }
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mVideoView.stopPlayback();
        mVideoView.release(true);
        mVideoView.stopBackgroundPlay();
        IjkMediaPlayer.native_profileEnd();
    }
}
