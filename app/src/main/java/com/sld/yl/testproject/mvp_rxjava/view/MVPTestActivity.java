package com.sld.yl.testproject.mvp_rxjava.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sld.yl.testproject.R;
import com.sld.yl.testproject.mvp_rxjava.ActivityUtils;
import com.sld.yl.testproject.mvp_rxjava.Injection;
import com.sld.yl.testproject.mvp_rxjava.presenter.MyPresenter;

public class MVPTestActivity extends AppCompatActivity {

    private MyPresenter mMyPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvptest);
        MVPFragment mvpFragment = MVPFragment.newInstance("", "");


        ActivityUtils.addFragmentToActivity(
                getSupportFragmentManager(), mvpFragment, R.id.fragment_layout);

        // Create the presenter
        mMyPresenter = new MyPresenter(
                /* Injection.provideTasksRepository(getApplicationContext()),*/
                mvpFragment,
                Injection.provideSchedulerProvider());

     /*   // Load previously saved state, if available.
        if (savedInstanceState != null) {
            TasksFilterType currentFiltering =
                    (TasksFilterType) savedInstanceState.getSerializable(CURRENT_FILTERING_KEY);
            mTasksPresenter.setFiltering(currentFiltering);
        }*/
    }
}
