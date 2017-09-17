package com.wishland.www.aicaipiao2.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.wishland.www.aicaipiao2.R;
import com.wishland.www.aicaipiao2.base.BaseActivity;
import com.wishland.www.aicaipiao2.model.TabEntity;
import com.wishland.www.aicaipiao2.ui.adapter.FragmentAdapter;
import com.wishland.www.aicaipiao2.ui.customView.ProhibitScrollViewPager;
import com.wishland.www.aicaipiao2.ui.fragment.FragmentFind;
import com.wishland.www.aicaipiao2.ui.fragment.FragmentMain;
import com.wishland.www.aicaipiao2.ui.fragment.FragmentMind;
import com.wishland.www.aicaipiao2.ui.fragment.FragmentRecommend;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.vp_main)
    ProhibitScrollViewPager vpMain;
    @BindView(R.id.tl_main)
    CommonTabLayout tlMain;

    private String[] mTitles = {"首页", "推荐", "发现", "我的"};

    private int[] mIconUnselectIds = {
            R.drawable.home_table0_un,
            R.drawable.home_table1_un,
            R.drawable.home_table2_un,
            R.drawable.home_table3_un
    };
    private int[] mIconSelectIds = {
            R.drawable.home_table0,
            R.drawable.home_table1,
            R.drawable.home_table2,
            R.drawable.home_table3
    };
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initTable();
        initViewPager();
    }

    private void initViewPager() {
        fragmentList.add(new FragmentMain());
        fragmentList.add(new FragmentRecommend());
        fragmentList.add(new FragmentFind());
        fragmentList.add(new FragmentMind());
        vpMain.setOffscreenPageLimit(10);
        vpMain.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragmentList));
        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tlMain.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vpMain.setCurrentItem(0);
    }

    private void initTable() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tlMain.setTabData(mTabEntities);
        tlMain.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vpMain.setCurrentItem(position, false);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 1000) {
                Toast.makeText(getApplicationContext(), "再按一次后退键退出应用程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
