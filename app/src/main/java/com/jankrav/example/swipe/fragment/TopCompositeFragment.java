package com.jankrav.example.swipe.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.jankrav.example.swipe.R;
import com.jankrav.example.swipe.adapter.FragmentsClassesPagerAdapter;
import com.jankrav.example.swipe.event.EventBus;
import com.jankrav.example.swipe.event.PageChangedEvent;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopCompositeFragment extends Fragment {

    public ViewPager mHorizontalPager;
    private int mCentralPageIndex = 0;
    private ViewPager.OnPageChangeListener mPagerChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            EventBus.getInstance().post(new PageChangedEvent(mCentralPageIndex == position));
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_top_composite, container, false);
        findViews(fragmentView);
        return fragmentView;
    }

    private void findViews(View fragmentView) {
        mHorizontalPager = (ViewPager) fragmentView.findViewById(R.id.fragment_composite_central_pager);
        initViews();
    }

    private void initViews() {
        populateHozizontalPager();
        mHorizontalPager.setCurrentItem(mCentralPageIndex);
        mHorizontalPager.setOnPageChangeListener(mPagerChangeListener);
    }

    private void populateHozizontalPager() {
        ArrayList<Class<? extends Fragment>> pages = new ArrayList<Class<? extends Fragment>>();
        pages.add(LeftFragment.class);
        pages.add(TopFragment.class);
        pages.add(RightFragment.class);
        mCentralPageIndex = pages.indexOf(CentralFragment.class);
        mHorizontalPager.setAdapter(new FragmentsClassesPagerAdapter(getChildFragmentManager(), getActivity(), pages));
    }
}
