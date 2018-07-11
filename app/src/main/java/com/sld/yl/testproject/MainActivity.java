package com.sld.yl.testproject;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.androidkun.xtablayout.XTabLayout;
import com.sld.yl.testproject.adapter.MyPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewPage)
    ViewPager viewPage;
    @BindView(R.id.tabLayout)
    XTabLayout tabLayout;
    @BindView(R.id.more)
    ImageView more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initVew();
    }

    private void initVew() {
        final int[] imageId = {R.mipmap.cheliang, R.mipmap.jiankong, R.mipmap.chazhao, R.mipmap.jtd,
                R.mipmap.geren, R.mipmap.cha_out, R.mipmap.ic_launcher, R.mipmap.jiankong, R.mipmap.chazhao};

        final int[] imageIdPre = {R.mipmap.cheliang_pre, R.mipmap.jiankong_pre,
                R.mipmap.chazhao_pre, R.mipmap.jtd_pre, R.mipmap.geren_pre, R.mipmap.cha_out_pre, R.mipmap.ic_launcher_round, R.mipmap.jiankong_pre, R.mipmap.chazhao_pre};
        viewPage.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPage);
        for (int i = 0; i < imageId.length; i++) {
            if (i == 0)
                tabLayout.getTabAt(i).setIcon(imageIdPre[i]);
            else
                tabLayout.getTabAt(i).setIcon(imageId[i]);

        }
        tabLayout.setScrollPosition(3, 0, false);
        tabLayout.addOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                tab.setIcon(imageIdPre[tab.getPosition()]);
                if (tab.getPosition() <= 6)
                    more.setVisibility(View.VISIBLE);
                else
                    more.setVisibility(View.GONE);
            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {
                tab.setIcon(imageId[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {

            }
        });
    }


    @OnClick(R.id.more)
    public void onViewClicked() {
        tabLayout.getTabAt(7).select();
        more.setVisibility(View.GONE);
    }
}
