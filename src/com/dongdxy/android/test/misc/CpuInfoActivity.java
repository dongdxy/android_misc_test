package com.dongdxy.android.test.misc;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

public class CpuInfoActivity extends Activity {
	
	private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        String str = "Build.CPU_ABI:\t"+Build.CPU_ABI+"\nBuild.CPU_ABI2:\t"+Build.CPU_ABI2;
        text.setText(str);
    }

}
