package com.sld.yl.testproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sld.yl.testproject.R;
import com.sld.yl.testproject.utils.SpacesItemDecoration;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by luojianping on 2018/7/26
 * Describe 作者很懒什么都没有写
 * Package name com.sld.yl.testproject.adapter
 */
public class ReclycleAdapter extends CommonAdapter<List<String>> {

    private final Context mContext;

    public ReclycleAdapter(Context context, int layoutId, List<List<String>> datas) {
        super(context, layoutId, datas);
        mContext = context;
    }

    @Override
    protected void convert(ViewHolder holder, List<String> strings, int position) {
        RecyclerView recyclerView = holder.getView(R.id.chid_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 6));
        recyclerView.addItemDecoration(new SpacesItemDecoration(1, 1));
        recyclerView.setAdapter(new CommonAdapter<String>(mContext, R.layout.item_child_layout, strings) {

            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.tv, s);
            }
        });
    }
}
