package com.dongdxy.android.test.misc;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity {

	private static List<Class<?>> entries = new ArrayList<Class<?>>();
	static {
		entries.add(WebViewSlotActivity.class);
		entries.add(CrashHandleActivity.class);
		entries.add(SetScaleActivity.class);
		entries.add(PageActivity.class);
		entries.add(PageCenterActivity.class);
		entries.add(LogCatActivity.class);
		entries.add(CpuInfoActivity.class);
		entries.add(JsonFormatActivity.class);
		entries.add(MoveTaskToBackActivity.class);
		entries.add(WebviewJsSecurityActivity.class);
		entries.add(StaticReferenceActivity.class);
		entries.add(OnClickTestActivity.class);
		entries.add(GridViewActivity.class);
		entries.add(FullScreenDialogActivity.class);
		entries.add(WebViewActivity.class);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new EntryAdapter(this));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent i = new Intent(this, entries.get(position));
		startActivity(i);
	}

	private class EntryAdapter extends ArrayAdapter<Class<?>> {

		public EntryAdapter(Context context) {
			super(context, android.R.layout.simple_list_item_1, entries);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView text1 = (TextView) super.getView(position, convertView, parent);
			text1.setText(getItem(position).getSimpleName().replace("Activity", ""));
			return text1;
		}

	}
}
