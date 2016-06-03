package com.zygk.mrsi.activity;

import android.animation.AnimatorSet;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.zygk.mrsi.R;
import com.zygk.mrsi.adapter.ViewPagerAdapter;

public class LoginActivity extends BaseActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private String[] tabs = {"Sign in", "settings"};
    public FloatingActionButton mFabLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initView();
        TabJoinViewPager();

    }

    private void TabJoinViewPager() {
        //第一步
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //第二步
        ViewPagerAdapter mAdapter = new ViewPagerAdapter(this, tabs);
        mTabLayout.setTabsFromPagerAdapter(mAdapter);
        //第三步
        final TabLayout.TabLayoutOnPageChangeListener listener = new TabLayout.TabLayoutOnPageChangeListener(mTabLayout);
        mViewPager.addOnPageChangeListener(listener);
        mViewPager.setAdapter(mAdapter);
    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.login_tl_tab);
        mViewPager = (ViewPager) findViewById(R.id.login_vp_pager);
        mFabLogin = (FloatingActionButton) findViewById(R.id.login_fab_dome);


    }


}
