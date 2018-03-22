package com.jankrav.example.swipe.fragment.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jankrav.example.swipe.R;

/**
 * Fragment to manage the bottom page of the 5 pages application navigation (top, center, bottom, left, right).
 */
public class BottomFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom, container, false);
    }
}
