package com.example.dxytest;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {
	
	private static List<Class<?>> entries = new ArrayList<Class<?>>();
	static{
		entries.add(CpuInfoActivity.class);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<Class<?>>(this, android.R.layout.activity_list_item, entries));
	}
    
}
