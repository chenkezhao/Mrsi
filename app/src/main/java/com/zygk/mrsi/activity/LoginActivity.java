package com.zygk.mrsi.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;

import com.zygk.mrsi.R;
import com.zygk.mrsi.adapter.ViewPagerAdapter;

public class LoginActivity extends BaseActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private String[] tabs = {"login...","setting"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initView();
        TabJoinViewPager();

    }

    private void TabJoinViewPager(){
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
        ViewPagerAdapter mAdapter = new ViewPagerAdapter(this,tabs);
        mTabLayout.setTabsFromPagerAdapter(mAdapter);
        //第三步
        final TabLayout.TabLayoutOnPageChangeListener listener = new TabLayout.TabLayoutOnPageChangeListener(mTabLayout);
        mViewPager.addOnPageChangeListener(listener);
        mViewPager.setAdapter(mAdapter);
    }

    private void initView(){
        mTabLayout = (TabLayout) findViewById(R.id.login_tl_tab);
        mViewPager = (ViewPager) findViewById(R.id.login_vp_pager);
    }
}
