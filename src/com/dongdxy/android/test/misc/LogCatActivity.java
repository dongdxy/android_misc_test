package com.dongdxy.android.test.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class LogCatActivity extends Activity {

	private TextView text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		text = (TextView) findViewById(R.id.text);
		text.setText("listen logcat...");
		// new CatchLogThread().start();
		startRecordingLogs();
	}

	@Override
	protected void onDestroy() {
		isTesting = false;
		super.onDestroy();
	}

	@Override
	protected void onStop() {
		text.setText(stopRecordingLogs());
		super.onStop();
	}

	private boolean isTesting = true;
	String line;

	class CatchLogThread extends Thread {
		@Override
		public void run() {
			Process mLogcatProc = null;
			BufferedReader reader = null;
			while (isTesting) {
				try {
					// 获取logcat日志信息
					mLogcatProc = Runtime.getRuntime().exec(new String[] { "logcat", "" });
					reader = new BufferedReader(new InputStreamReader(mLogcatProc.getInputStream()));
					while ((line = reader.readLine()) != null) {
						handler.post(new Runnable() {

							@Override
							public void run() {
								text.setText(text.getText() + "\n" + line);
							}
						});
						if (line.indexOf("android.intent.category.HOME") > 0) {
							// isTesting = false;
							handler.sendMessage(handler.obtainMessage());
							// Runtime.getRuntime().exec("logcat -c");// 删除日志
							// break;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	};

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Log.i("xxx", "Home key press");
			Toast.makeText(getApplicationContext(), "Home key press", Toast.LENGTH_SHORT).show();
		};
	};

	ReadThread thread;

	public void startRecordingLogs() {
		if (thread == null || !thread.isAlive()) {
			thread = new ReadThread();
			thread.start();
		}
	}

	public String stopRecordingLogs() {
		String results = thread.stopLogging();
		return results;
	}

	private class ReadThread extends Thread {

		String results;
		Process process;
		Object lockObject = new Object();

		public void run() {
			synchronized (lockObject) {
				try {
					process = Runtime.getRuntime().exec("logcat -v time");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				BufferedReader reader = new BufferedReader(new InputStreamReader(
						process.getInputStream()));

				String line;
				final StringBuilder log = new StringBuilder();
				String separator = System.getProperty("line.separator");

				try {
					while ((line = reader.readLine()) != null) {
						log.append(line);
						log.append(separator);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				results = log.toString();
			}

		}

		public String stopLogging() {
			process.destroy();
			synchronized (lockObject) {
				return results;
			}
		}
	}
}
