package com.dongdxy.android.test.misc;

import android.app.Application;

import com.dongdxy.android.test.misc.CrashHandleActivity.CrashHandler;

public class MyApplication extends Application {
	private static MyApplication sInstance;

	@Override
	public void onCreate() {
		super.onCreate();
		sInstance = this;
		CrashHandler.init();
	}

	public static MyApplication getInstance() {
		return sInstance;
	}
}
