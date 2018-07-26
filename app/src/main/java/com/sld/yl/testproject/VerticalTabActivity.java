package com.sld.yl.testproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.sld.yl.testproject.fragment.ViewPageFragment;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class VerticalTabActivity extends AppCompatActivity {

    @BindView(R.id.vertical_TabLayout)
    VerticalTabLayout verticalTabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_tab);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

    }

    public class MyAdapter extends FragmentPagerAdapter implements TabAdapter {

        private final ArrayList<String> titles;

        public MyAdapter(FragmentManager fm) {
            super(fm);
            titles = new ArrayList<>();
            Collections.addAll(titles, "Android", "IOS", "Web", "JAVA", "C++",
                    ".NET", "JavaScript", "Swift", "PHP", "Python", "C#", "Groovy", "SQL", "Ruby");
        }

        @Override
        public Fragment getItem(int i) {
            return ViewPageFragment.newInstance(titles.get(i), "");
        }

        @Override
        public int getCount() {
            return titles.size();
        }

        @Override
        public ITabView.TabBadge getBadge(int position) {
            return null;
        }

        @Override
        public ITabView.TabIcon getIcon(int position) {
            return null;
        }

        @Override
        public ITabView.TabTitle getTitle(int position) {
            return new TabView.TabTitle.Builder()
                    .setContent(titles.get(position))
                    .setTextColor(Color.BLUE, Color.YELLOW)
                    .build();
        }

        @Override
        public int getBackground(int position) {
            return 0;
        }
    }
}
