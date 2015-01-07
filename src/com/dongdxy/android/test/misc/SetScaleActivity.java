package com.dongdxy.android.test.misc;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.TextView;

public class SetScaleActivity extends Activity {

	private TextView text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		text = (TextView) findViewById(R.id.text);
		final Button btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				btn.setPivotX(0f);
				btn.setScaleX(btn.getScaleX() < 1.1f ? 2f : 0.5f);
				text.setText(btn.getLeft() + " " + btn.getRight());
			}
		});
		btn.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View arg0, int arg1, KeyEvent arg2) {
				Log.e("xxx", "btn onKey " + arg2);
				return false;
			}
		});

		Log.e("xxx", "getCallingActivity: " + getCallingActivity());
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.d("xxx", "CpuInfoActivity onKeyDown " + event);
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onPause() {
		Log.d("xxx", "onPause");
		super.onPause();
	}

	@Override
	protected void onStop() {
		Log.d("xxx", "onStop");
		super.onStop();
	}
}
