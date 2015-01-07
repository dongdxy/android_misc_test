package com.dongdxy.android.test.misc;

import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.SeekBar;

public class PageCenterActivity extends PageActivity {

	SeekBar mSeekBar;

	@Override
	protected void setcontentView() {
		setContentView(R.layout.pager_center);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mSeekBar = (SeekBar) findViewById(R.id.seekBar);
		mSeekBar.setMax(mViewPager.getAdapter().getCount() - 1);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int index) {
				mSeekBar.setProgress(index);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
	}
}
