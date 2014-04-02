package com.example.dxytest;

import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class CpuInfoActivity extends Activity {
	
	private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        String str = "Build.CPU_ABI:"+Build.CPU_ABI+" Build.CPU_ABI2:"+Build.CPU_ABI2;
        text.setText(str);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
