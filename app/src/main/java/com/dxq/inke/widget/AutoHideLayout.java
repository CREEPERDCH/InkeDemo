package com.dxq.inke.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/*
 * Created by CREEPER_D on 2017/8/25.
 */

public class AutoHideLayout extends RelativeLayout {

    private static final String TAG = "dxq";
    private View mChild;
    private int mHeight;

    public AutoHideLayout(Context context) {
        super(context);
    }

    public AutoHideLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoHideLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获得底部导航栏子控件以及它的高度
        mChild = getChildAt(1);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        mHeight = mChild.getMeasuredHeight();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    float mStartX = 0;
    float mStartY = 0;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //根据触摸的位移,来隐藏或显示子控件们
        //子控件的触摸逻辑不能受到影响
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mStartX = ev.getX();
                mStartY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float newX = ev.getX();
                float newY = ev.getY();

                float dx = newX - mStartX;
                float dy = newY - mStartY;
                //显示或隐藏子控件,需要子控件的高度
                int scrollY = mChild.getScrollY();
                Log.d(TAG, "dispatchTouchEvent: scrollY=" + scrollY + ",dy=" + dy + ",mHeight=" + mHeight);

                if (scrollY + dy > 0) {
                    //避免偏移量过大,超出边界
                    dy = 0 - scrollY;
                }
                if (scrollY + dy < -mHeight) {
                    //避免超过下边界
                    dy = -mHeight - scrollY;
                }

                mChild.scrollBy(0, (int) dy);
                mStartX = newX;
                mStartY = newY;
                break;
            case MotionEvent.ACTION_UP:
                //小于-mHeight的一半,就应该全部隐藏
                if (mChild.getScrollY() < -mHeight / 2) {
                    mChild.scrollBy(0, -mHeight);
                } else {
                    mChild.scrollBy(0, 0);
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
