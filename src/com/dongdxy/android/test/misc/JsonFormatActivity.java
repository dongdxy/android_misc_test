package com.dongdxy.android.test.misc;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class JsonFormatActivity extends Activity {

	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (TextView) findViewById(R.id.text);
		StringBuffer sb = new StringBuffer();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("k1", "val1");
		map.put("k2", "val2");
		map.put("k3", "val3");
		
		String map2str = map.toString();
		sb.append("basic map2str").append("\n").append(map2str).append("\n");
		
		String map2json = new JSONObject(map).toString();
		sb.append("\n basic map2json").append("\n").append(map2json).append("\n");
		
		Map<String, Object> objectMap = new HashMap<String, Object>();
		objectMap.put("mapObject", map);
		objectMap.put("map2str", map2str);
		objectMap.put("map2json", map2json);
		objectMap.put("nullValue", null);
		
		String objectMap2str = objectMap.toString();
		sb.append("\n objectMap2str").append("\n").append(objectMap2str).append("\n");
		
		String objectMap2json = new JSONObject(objectMap).toString();
		sb.append("\n objectMap2json").append("\n").append(objectMap2json).append("\n");
		
		map.clear();
		
		textView.setText(sb.toString());
	}

}
