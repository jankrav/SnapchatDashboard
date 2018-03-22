package com.jankrav.example.swipe.fragment.container;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jankrav.example.swipe.R;
import com.jankrav.example.swipe.adapter.FragmentsClassesPagerAdapter;
import com.jankrav.example.swipe.event.EventManager;
import com.jankrav.example.swipe.event.PageChangedEvent;
import com.jankrav.example.swipe.fragment.view.CentralFragment;
import com.jankrav.example.swipe.fragment.view.LeftFragment;
import com.jankrav.example.swipe.fragment.view.RightFragment;

import java.util.ArrayList;

/**
 * Fragment to manage the horizontal pages (left, central, right) of the 5 pages application navigation (top, center,
 * bottom, left, right).
 */
public class CentralCompositeFragment extends Fragment {
    private EventManager eventManager = EventManager.getInstance();
    private ViewPager mHorizontalPager;
    private int mCentralPageIndex = 0;
    private OnPageChangeListener mPagerChangeListener = new OnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            PageChangedEvent event = new PageChangedEvent(mCentralPageIndex == position);
            eventManager.post(event);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_composite_central, container, false);
        findViews(fragmentView);
        return fragmentView;
    }

    private void findViews(View fragmentView) {
        mHorizontalPager = fragmentView.findViewById(R.id.fragment_composite_central_pager);
        initViews();
    }

    private void initViews() {
        populateHozizontalPager();
        mHorizontalPager.setCurrentItem(mCentralPageIndex);
        mHorizontalPager.addOnPageChangeListener(mPagerChangeListener);
    }

    private void populateHozizontalPager() {
        ArrayList<Class<? extends Fragment>> pages = new ArrayList<Class<? extends Fragment>>();
        pages.add(LeftFragment.class);
        pages.add(CentralFragment.class);
        pages.add(RightFragment.class);
        mCentralPageIndex = pages.indexOf(CentralFragment.class);
        mHorizontalPager.setAdapter(new FragmentsClassesPagerAdapter(getChildFragmentManager(), getActivity(), pages));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHorizontalPager.removeOnPageChangeListener(mPagerChangeListener);
    }
}
