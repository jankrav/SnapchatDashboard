package com.jankrav.example.swipe.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jankrav.example.swipe.R;

/**
 * Fragment to manage the right page of the 5 pages application navigation (top, center, bottom, left, right).
 */
public class RightFragment extends Fragment {

	// -----------------------------------------------------------------------
	//
	// Methods
	//
	// -----------------------------------------------------------------------
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View fragmentView = inflater.inflate(R.layout.fragment_right, container, false);
		return fragmentView;
	}

}
