package com.dongdxy.android.test.misc;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class StaticReferenceActivity extends Activity {

	private TextView text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		text = (TextView) findViewById(R.id.text);
		text.setText("holding a static obj");
		TestClassData.getInstance();
	}

}
