package com.wishland.www.aicaipiao2.ui.customView;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by gerry on 2017/5/2.
 */
public class ProhibitScrollViewPager extends ViewPager {

    public ProhibitScrollViewPager(Context context) {
        super(context);
    }

    public ProhibitScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * viewpage的onTouchEvent屏蔽
     */
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return false;
    }
}