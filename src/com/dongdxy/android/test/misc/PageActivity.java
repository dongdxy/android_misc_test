package com.dongdxy.android.test.misc;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.DecelerateInterpolator;

public class PageActivity extends FragmentActivity {

	ViewPager mViewPager;
	MyFragmentPagerAdapter mFragmentPagerAdapter;

	static String[] mUrls = {
			"http://www.duokan.com/reader/www/app.html?id=ac20db61a9b9475eadb2025efebb7867",
			"http://oneice.lofter.com/post/280ba_348075d", "http://huaban.com/pins/268979854/",
			"http://www.dili360.com/article/p5445c87061e9413.htm",
			"http://www.modernweekly.com/news/content.aspx?artID=39586" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setcontentView();
		initViews();
	}

	protected void setcontentView() {
		setContentView(R.layout.pager);
	}

	private void initViews() {
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setPageTransformer(false, new FadePageTransformer());
		mViewPager.setOffscreenPageLimit(3);
		setScrollerTime(1500);
		setupPageDataFromFiles();
	}

	private void setupPageData() {
		mFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(mFragmentPagerAdapter);
	}

	private void setupPageDataFromFiles() {
		RawAdapter adapter = new RawAdapter(getSupportFragmentManager());

		File f = new File("/data/duokancache/ezbookData/nationalGeo/2k");
		File file[] = f.listFiles();
		Log.d("xxx", "Size: " + file.length);
		for (int i = 0; i < file.length; i++) {
			Log.d("xxx", "FileName:" + file[i].getName());
			adapter.add(file[i]);
		}

		mViewPager.setAdapter(adapter);
	}

	private static class RawAdapter extends FragmentStatePagerAdapter {
		ArrayList<File> mRawList = new ArrayList<File>();

		public RawAdapter(FragmentManager fm) {
			super(fm);
		}

		public void add(File file) {
			mRawList.add(file);
		}

		@Override
		public Fragment getItem(int index) {

			return new FullPageFragment(mRawList.get(index));
		}

		@Override
		public int getCount() {
			return mRawList.size();
		}
	}

	static class MyFragmentPagerAdapter extends FragmentPagerAdapter {
		public MyFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int index) {

			return new WebPageFragment(mUrls[index]);
		}

		@Override
		public int getCount() {
			return 5;
		}
	}

	public void setScrollerTime(int scrollerTime) {
		try {
			FixedSpeedScroller scroller = null;
			Field mScroller;
			mScroller = ViewPager.class.getDeclaredField("mScroller");
			mScroller.setAccessible(true);
			scroller = new FixedSpeedScroller(mViewPager.getContext(), new DecelerateInterpolator());
			scroller.setTime(scrollerTime);
			mScroller.set(mViewPager, scroller);
		} catch (Exception e) {
		}
	}

	private static class FadePageTransformer implements ViewPager.PageTransformer {
		public void transformPage(View view, float position) {
			view.setTranslationX(view.getWidth() * -position);

			if (position <= -1.0F || position >= 1.0F) {
				view.setAlpha(0.0F);
			} else if (position == 0.0F) {
				view.setAlpha(1.0F);
			} else {
				// position is between -1.0F & 0.0F OR 0.0F & 1.0F
				view.setAlpha(1.0F - Math.abs(position));
			}
		}
	}

}
