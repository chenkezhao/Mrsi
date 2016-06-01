package com.zygk.mrsi.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.autolayout.AutoRelativeLayout;
import com.zygk.mrsi.R;

/**
 * Created by 陈科肇 on 2016/5/20.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private String[] titles = null;

    public ViewPagerAdapter(Context context, String[] titles) {
        this.titles = titles;
        this.mContext = context;
    }

    //开始调用显示页面
    @Override
    public void startUpdate(ViewGroup container) {
        super.startUpdate(container);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    //决定是否与一个特定的页面视图返回的关键对象
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);//删除选项卡
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        int layout = 0;
        if(position==0){
            layout = R.layout.viewpager_login_login;
        }else{
            layout = R.layout.viewpager_login_setting;
        }
        AutoRelativeLayout rl = (AutoRelativeLayout) LayoutInflater.from(mContext).inflate(layout, null);
        container.addView(rl);//添加页卡
        return rl;
    }
}
