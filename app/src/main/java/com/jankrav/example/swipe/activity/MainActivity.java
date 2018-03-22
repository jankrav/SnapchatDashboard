package com.jankrav.example.swipe.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

import com.jankrav.example.swipe.R;
import com.jankrav.example.swipe.event.EventManager;
import com.jankrav.example.swipe.event.PageChangedEvent;
import com.jankrav.example.swipe.view.VerticalPager;
import com.jankrav.example.swipe.view.VerticalPagerListener;

/**
 * Manages start screen of the application.
 */
public class MainActivity extends FragmentActivity implements VerticalPagerActivity,
        VerticalPagerListener {
    /**
     * Start page index. 0 - top page, 1 - central page, 2 - bottom page.
     */
    private static final int CENTRAL_PAGE_INDEX = 1;

    public VerticalPager mVerticalPager;
    private EventManager eventManager = EventManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }

    private void findViews() {
        mVerticalPager = findViewById(R.id.activity_main_vertical_pager);
        initViews();
    }

    private void initViews() {
        snapPageWhenLayoutIsReady(mVerticalPager, CENTRAL_PAGE_INDEX);
    }

    private void snapPageWhenLayoutIsReady(final View pageView, final int page) {
        /*
         * VerticalPager is not fully initialized at the moment, so we want to snap to the central page only when it
		 * layout and measure all its pages.
		 */
        pageView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mVerticalPager.snapToPage(page, VerticalPager.PAGE_SNAP_DURATION_INSTANT);
                pageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        eventManager.register(this);
    }

    @Override
    protected void onPause() {
        eventManager.unregister();
        super.onPause();
    }

    @Override
    public void onLocationChanged(PageChangedEvent event) {
        mVerticalPager.setPagingEnabled(event.hasVerticalNeighbors());
    }

    @Override
    public void snapToPage(int page) {
        mVerticalPager.snapToPage(page);
    }
}
