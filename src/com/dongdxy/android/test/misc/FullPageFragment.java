package com.dongdxy.android.test.misc;

import java.io.File;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FullPageFragment extends Fragment {

	private File mFile;
	private ImageView mImageView;
	private TextView mBottomText;
	static boolean testFlag = true;

	public FullPageFragment(File file) {
		mFile = file;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.full_page, container, false);
		mImageView = (ImageView) view.findViewById(R.id.fullImage);
		mBottomText = (TextView) view.findViewById(R.id.bottomText);
		testFlag = !testFlag;
		mBottomText.setText(Html.fromHtml(testFlag ? getString(R.string.nice_html_1)
				: getString(R.string.nice_html_2)));
		Picasso.with(getActivity()).setIndicatorsEnabled(true);
		Picasso.with(getActivity()).load(mFile).fit().into(mImageView);
		return view;
	}

	public static long getUsedMemorySize() {
		long freeSize = 0L;
		long totalSize = 0L;
		long usedSize = -1L;
		try {
			Runtime info = Runtime.getRuntime();
			freeSize = info.freeMemory();
			totalSize = info.totalMemory();
			usedSize = totalSize - freeSize;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usedSize;

	}

}
