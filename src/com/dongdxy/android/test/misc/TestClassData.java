package com.dongdxy.android.test.misc;

public class TestClassData {
	public int[] data;

	private static TestClassData sInstance;

	private TestClassData() {
		data = new int[1024 * 1024];
	}

	public static TestClassData getInstance() {
		if (sInstance == null) {
			sInstance = new TestClassData();
		}

		return sInstance;
	}
}