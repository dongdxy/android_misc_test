package com.dongdxy.android.test.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebviewJsSecurityActivity extends Activity {

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
		WebSettings webSettings = mWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		mWebView.addJavascriptInterface(this, "testJsInterface");
		mWebView.setWebViewClient(new WebViewClient());
		mWebView.setWebChromeClient(new WebChromeClient());
	}

	private void loadUrl() {
//		mWebView.loadUrl("file:///android_asset/test.html");
		mWebView.loadUrl("http://security.tencent.com/lucky/check_tools.html");
	}

	public void test() {
		Log.e("xxx", "testJsInterface->test()");
	}

	@JavascriptInterface
	public void test2() {
		Log.e("xxx", "testJsInterface->test2() no annotation");

		exec("cd /data/data/com.example.test");
		exec("pwd");
		exec("ls /data/data/com.example.test");
		exec("ls /data/data/com.alipay.scansdk.demo");
	}

	private void exec(String cmd) {
		Log.e("xxx", "testJsInterface->exec() : " + cmd);
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			while ((line = in.readLine()) != null) {
				Log.e("xxx", "testJsInterface->exec() return: " + line);
			}
		} catch (IOException e) {
			Log.e("xxx", "testJsInterface->exec() exception" + e.toString());
			e.printStackTrace();
		}
	}
}
