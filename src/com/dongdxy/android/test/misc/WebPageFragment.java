package com.dongdxy.android.test.misc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebPageFragment extends Fragment {

	WebView mWebView;
	private String mUrl;
	
	public WebPageFragment(String url) {
		mUrl = url;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.webview_main, container, false);
		mWebView = (WebView) view.findViewById(R.id.webview);
		setupWebView();
		loadUrl();

		return view;
	}

	private void setupWebView() {
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.setInitialScale(170);
		mWebView.setWebChromeClient(new WebChromeClient());
		mWebView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
				return true;
			}
		});
	}

	private void loadUrl() {
		mWebView.loadUrl(mUrl);
	}

}
