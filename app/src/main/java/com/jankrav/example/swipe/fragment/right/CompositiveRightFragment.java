package com.jankrav.example.swipe.fragment.right;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.jankrav.example.swipe.R;
import com.jankrav.example.swipe.event.PageChangedEvent;
import com.jankrav.example.swipe.view.VerticalPager;
import com.squareup.otto.Subscribe;


/**
 * A simple {@link Fragment} subclass.
 */
public class CompositiveRightFragment extends Fragment {
    /**
     * Start page index. 0 - top right page, 1 - central right page, 2 - bottom right page.
     */
    private static final int CENTRAL_PAGE_INDEX = 1;

    private VerticalPager mVerticalPager;

    public CompositiveRightFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_right_compositive, container, false);
        findViews(fragmentView);
        return fragmentView;
    }

    void findViews(View fragmentView){
        mVerticalPager = (VerticalPager) fragmentView.findViewById(R.id.fragment_right_vertical_pager);
        snapPageWhenLayoutIsReady(mVerticalPager, CENTRAL_PAGE_INDEX);
    }

    private void snapPageWhenLayoutIsReady(final View pageView, final int page) {
        pageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {
                mVerticalPager.snapToPage(page, VerticalPager.PAGE_SNAP_DURATION_INSTANT);

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN)
                    // recommended removeOnGlobalLayoutListener method is available since API 16 only
                    pageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                else
                    removeGlobalOnLayoutListenerForJellyBean(pageView);
            }

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            private void removeGlobalOnLayoutListenerForJellyBean(final View pageView) {
                pageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    @Subscribe
    public void onLocationChanged(PageChangedEvent event) {
        mVerticalPager.setPagingEnabled(event.hasVerticalNeighbors());
    }

}
