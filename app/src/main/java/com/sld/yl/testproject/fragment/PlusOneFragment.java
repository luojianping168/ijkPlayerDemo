package com.sld.yl.testproject.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.plus.PlusOneButton;
import com.sld.yl.testproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class PlusOneFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // The request code must be 0 or greater.
    private static final int PLUS_ONE_REQUEST_CODE = 0;
    // The URL to +1.  Must be a valid URL.
    private final String PLUS_ONE_URL = "http://developer.android.com";
    @BindView(R.id.videoView)
    VideoView videoView;
    Unbinder unbinder;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private PlusOneButton mPlusOneButton;
    private long mClickTime;


    public PlusOneFragment() {
        // Required empty public constructor
    }


    public static PlusOneFragment newInstance(String param1, String param2) {
        PlusOneFragment fragment = new PlusOneFragment();
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
        View view = inflater.inflate(R.layout.fragment_plus_one, container, false);
        view.findViewById(R.id.click_tv).setOnClickListener(this);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MediaController controller = new MediaController(getContext());
        videoView.setMediaController(controller);
        controller.setMediaPlayer(videoView);

    }

    @Override
    public void onClick(View view) {
        long timeMillis = System.currentTimeMillis();
        if (timeMillis - mClickTime < 500) {
            ((TextView) view).setText("你双击成功了,宋梁逗比");
            Toast.makeText(getActivity(), "你双击成功了,宋梁逗比", Toast.LENGTH_SHORT).show();
        } else {
            ((TextView) view).setText("你单击了");
            mClickTime = timeMillis;
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
