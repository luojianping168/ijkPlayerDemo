package com.sld.yl.testproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.sld.yl.testproject.adapter.ReclycleAdapter;
import com.sld.yl.testproject.utils.DataBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowExcelActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.recyeler)
    RecyclerView recyeler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_excel);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        DataBean data = (DataBean) getIntent().getSerializableExtra("DATA");
        Log.e("kongle ", "initView: " + data.toString());
        title.setText(data.data.get(0).get(0));
        data.data.remove(0);
        recyeler.setLayoutManager(new LinearLayoutManager(this));
        recyeler.setAdapter(new ReclycleAdapter(this, R.layout.item_panter_layout, data.data));

    }
}
