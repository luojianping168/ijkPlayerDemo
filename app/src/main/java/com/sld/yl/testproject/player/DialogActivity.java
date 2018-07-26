package com.sld.yl.testproject.player;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.sld.yl.testproject.R;
import com.sld.yl.testproject.player.ui.PlayerDialog;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogActivity extends AppCompatActivity {

    @BindView(R.id.dialog_btn)
    Button dialogBtn;
    @BindView(R.id.fragment_layout)
    FrameLayout fragmentLayout;
    private PlayerDialog mDialogFragment;
    private boolean isOpen = true;

    private PlayerFragment mFragment;
    private int mA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.dialog_btn)
    public void onViewClicked() {
       /* mDialogFragment = PlayerDialog.newInstance("", "");
        mDialogFragment.show(getSupportFragmentManager(),"PlayerDialog");*/
        // startActivity(new Intent(this,URLPlayerActivity.class));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragmentLayout.getVisibility() == View.GONE) {
            fragmentLayout.setVisibility(View.VISIBLE);
            mA++;
            if (mA % 4 == 1)
                mFragment = PlayerFragment.newInstance("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4", "");
            else if(mA % 4 == 2)
                mFragment = PlayerFragment.newInstance("https://res.exexm.com/cw_145225549855002", "");
            else if(mA % 4 == 3)
                mFragment = PlayerFragment.newInstance("rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov", "");
            else
                mFragment = PlayerFragment.newInstance("rtmp://live.hkstv.hk.lxdns.com/live/hks", "");
            transaction.replace(R.id.fragment_layout, mFragment);
            transaction.commit();
            if (isOpen) {

                isOpen = false;
            }
        } else {
            fragmentLayout.setVisibility(View.GONE);
            transaction.remove(mFragment);
            mFragment.onDestroy();
            transaction = null;
            mFragment = null;
        }
    }

    @Override
    public void onBackPressed() {
        if (mDialogFragment != null && mDialogFragment.onBackPressed()) {
            return;
        }
        finish();
    }
}
