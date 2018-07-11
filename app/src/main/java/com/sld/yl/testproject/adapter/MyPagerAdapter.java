package com.sld.yl.testproject.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sld.yl.testproject.fragment.PlusOneFragment;

/**
 * Created by luojianping on 2018/6/27
 * Describe 作者很懒什么都没有写
 * Package name com.sld.yl.testproject.adapter
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    private String[] bottomStr = {"警用车辆", "监控", "研判", "交通灯", "个人中心","测试1","测试2","测试4","测试5"};

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return PlusOneFragment.newInstance("1", i + "");
    }

    @Override
    public int getCount() {
        return bottomStr.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return bottomStr[position];
    }
}
