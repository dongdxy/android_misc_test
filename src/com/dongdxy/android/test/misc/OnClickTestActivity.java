package com.dongdxy.android.test.misc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class OnClickTestActivity extends Activity {

	private TextView text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		text = (TextView) findViewById(R.id.text);
		Button btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String str = "on click ...\n\n";
				for (StackTraceElement ste : Thread.currentThread()
						.getStackTrace()) {
					str = str + ste.toString() + "\n";
				}
				text.setText(str);
			}
		});
	}

}
