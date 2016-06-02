package com.zygk.mrsi.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.zhy.autolayout.AutoRelativeLayout;
import com.zygk.mrsi.R;
import com.zygk.mrsi.activity.LoginActivity;

/**
 * Created by 陈科肇 on 2016/5/20.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private LoginActivity mLoginActivity;
    private String[] titles = null;

    private View v_user, v_settings;
    private EditText u_name;
    private EditText u_password;

    public ViewPagerAdapter(LoginActivity loginActivity, Context context, String[] titles) {
        this.titles = titles;
        this.mContext = context;
        this.mLoginActivity = loginActivity;
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
        if (position == 0) {
            v_user = LayoutInflater.from(mContext).inflate(R.layout.viewpager_login_login, null);


            v_user = LayoutInflater.from(mContext).inflate(R.layout.viewpager_login_login, null);
            u_name = (EditText) v_user.findViewById(R.id.login_et_username);
            u_password = (EditText) v_user.findViewById(R.id.login_et_password);
            u_name.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    checkUserInfo();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            u_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    checkUserInfo();
                }
            });
            u_password.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    checkUserInfo();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            u_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    checkUserInfo();
                }
            });


            mLoginActivity.mFabLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!checkUserInfo()) {
                        return;
                    }
                    switch (v.getId()) {
                        case R.id.login_fab_dome:
                            ViewCompat.animate(mLoginActivity.mFabLogin)
                                    .scaleX(0)
                                    .scaleY(0)
                                    .rotation(360f)
                                    .setDuration(2000)
                                    .setInterpolator(new FastOutSlowInInterpolator())
                                    .withEndAction(new Runnable() {
                                        @Override
                                        public void run() {
                                         /*ViewCompat
                                        .animate(mFabLogin)
                                        .scaleX(1.0F)
                                        .scaleY(1.0F)
                                        .rotation(-360f)
                                        .setDuration(2000)
                                        .setInterpolator(new FastOutSlowInInterpolator())
                                        .start();*/
                                        }
                                    })
                                    .start();

                            break;
                        default:
                            throw new UnsupportedOperationException(
                                    "The onClick method has not been implemented for " + mContext.getResources()
                                            .getResourceEntryName(v.getId()));
                    }
                }
            });


            container.addView(v_user);//添加页卡
            return v_user;
        } else {
            v_settings = LayoutInflater.from(mContext).inflate(R.layout.viewpager_login_setting, null);
            container.addView(v_settings);//添加页卡
            return v_settings;
        }
    }


    private boolean checkUserInfo() {
        String name = u_name.getText().toString().trim();
        String password = u_password.getText().toString().trim();
        Log.i("xx", name.length() + "");
        Log.i("xx", password.length() + "");
        boolean isRun = true;
        if (name.length() > 0 && name.length() <= 5) {
            isRun = true;
        } else {
            isRun = false;
            u_name.setError("非法用户名");
        }
        if (password.length() > 0 && password.length() <= 18) {
            isRun = true;
        } else {
            isRun = false;
            u_password.setError("用户名或密码不正确");
        }
        if (isRun) {
            ViewCompat
                    .animate(mLoginActivity.mFabLogin)
                    .scaleX(1.0F)
                    .scaleY(1.0F)
                    .rotation(360f)
                    .setDuration(2000)
                    .setInterpolator(new FastOutSlowInInterpolator())
                    .withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            mLoginActivity.mFabLogin.setImageResource(R.drawable.ic_check_black);
                        }
                    })
                    .start();
            return true;
        } else {
            ViewCompat
                    .animate(mLoginActivity.mFabLogin)
                    .scaleX(1.0F)
                    .scaleY(1.0F)
                    .rotation(-360f)
                    .setDuration(2000)
                    .setInterpolator(new FastOutSlowInInterpolator())
                    .withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            mLoginActivity.mFabLogin.setImageResource(R.drawable.ic_close_black);
                        }
                    })
                    .start();
            return false;
        }
    }
}
