package com.jankrav.example.swipe.fragment.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jankrav.example.swipe.R;
import com.jankrav.example.swipe.activity.MainActivity;
import com.jankrav.example.swipe.activity.VerticalPagerActivity;

/**
 * Fragment to manage the central page of the 5 pages application navigation (top, center, bottom, left, right).
 */
public class CentralFragment extends Fragment {
    Button btnProfile, btnARMessaging, btnAugs, btnDiscover;

    ViewPager pager;
    VerticalPagerActivity mActivity;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_central, container, false);

        pager = (ViewPager) container;
        mActivity = (VerticalPagerActivity) getActivity();

        btnARMessaging = fragmentView.findViewById(R.id.btnARMessaging);
        btnARMessaging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pager.setCurrentItem(0);
            }
        });

        btnAugs = fragmentView.findViewById(R.id.btnAugs);
        btnAugs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pager.setCurrentItem(2);
            }
        });

        btnProfile = fragmentView.findViewById(R.id.btnProfile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.snapToPage(0);
            }
        });

        btnDiscover = fragmentView.findViewById(R.id.btnDiscover);
        btnDiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.snapToPage(2);
            }
        });

        return fragmentView;
    }

}
