package com.sld.yl.testproject.mvp_rxjava.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sld.yl.testproject.R;
import com.sld.yl.testproject.mvp_rxjava.contractor.TasksContract;

import static com.google.common.base.Preconditions.checkNotNull;

public class MVPFragment extends Fragment implements TasksContract.View {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private TasksContract.Presenter mPresenter;


    public MVPFragment() {

    }


    public static MVPFragment newInstance(String param1, String param2) {
        MVPFragment fragment = new MVPFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mv, container, false);

        view.findViewById(R.id.click_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.showDialog();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }


    @Override
    public void setPresenter(TasksContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);//获取到p对象了 功能逻辑就调用p的接口
    }

    @Override
    public void showDiaglog() {
        new AlertDialog.Builder(getActivity()).setTitle("mvp测试").setMessage("回调成功了").show();
    }
}
