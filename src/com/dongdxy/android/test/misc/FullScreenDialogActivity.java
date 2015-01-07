package com.dongdxy.android.test.misc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class FullScreenDialogActivity extends Activity {

	private Button mButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBackgroundTranparent();
		setContentView(R.layout.activity_main);
		mButton = (Button) findViewById(R.id.btn);
		mButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(
						FullScreenDialogActivity.this, R.style.AppTheme));
				LayoutInflater inflater = (LayoutInflater) FullScreenDialogActivity.this
						.getSystemService(LAYOUT_INFLATER_SERVICE);
				View layout = inflater.inflate(R.layout.dialog_content,
						(ViewGroup) findViewById(R.id.root));
				builder.setView(layout);
				builder.setPositiveButton("ok..", new OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						arg0.dismiss();
					}
				});
				builder.setNegativeButton("cancel...", new OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						arg0.dismiss();
					}
				});
				builder.create().show();
			}
		});
		mButton.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View arg0) {
				Intent i = new Intent(getApplicationContext(), CpuInfoActivity.class);
				startActivityForResult(i, 0);
				return true;
			}
		});
		
		View smallTest = findViewById(R.id.btn_small_test);
		smallTest.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent("com.dxy.intent.action.SMALL_TEST");
				startActivity(i);
			}
		});
	}
	
	private void setBackgroundTranparent() {
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.dimAmount = 0.6f;
		getWindow().setAttributes(lp);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
	}

}
