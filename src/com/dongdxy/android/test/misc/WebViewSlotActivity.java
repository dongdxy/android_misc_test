package com.dongdxy.android.test.misc;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewSlotActivity extends Activity {

	WebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview_main);
		mWebView = (WebView) findViewById(R.id.webview);
		setupWebView();
		loadUrl();
	}

	private void setupWebView() {
		mWebView.setWebViewClient(new WebViewClient());
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.setInitialScale(170);
		mWebView.setWebChromeClient(new WebChromeClient());
	}

	private void loadUrl() {
		mWebView.loadUrl("file:///android_asset/jQuery-SlotMachine-master/test.html");
	}

}
