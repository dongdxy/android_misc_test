package com.dongdxy.android.test.misc;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class CrashHandleActivity extends Activity {

	private TextView text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		text = (TextView) findViewById(R.id.text);
		text = null;
		text.setText("test");
	}

	public static class CrashHandler implements UncaughtExceptionHandler {

		private static CrashHandler sInstance = new CrashHandler();

		private UncaughtExceptionHandler mDefaultCrashHandler;

		private Context mContext;

		private CrashHandler() {
			init(MyApplication.getInstance());
		}

		public static CrashHandler init() {
			if (sInstance == null) {
				new CrashHandler();
			}
			return sInstance;
		}

		public void init(Context context) {
			mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
			Thread.setDefaultUncaughtExceptionHandler(this);
			mContext = context.getApplicationContext();
		}

		@Override
		public void uncaughtException(Thread thread, Throwable ex) {
			// 导出异常信息到SD卡中
			uploadLog(ex);

			// 打印出当前调用栈信息
			ex.printStackTrace();

			Toast.makeText(MyApplication.getInstance(), "oops", Toast.LENGTH_SHORT).show();
			android.os.Process.killProcess(android.os.Process.myPid());

		}

		private void uploadLog(Throwable ex) {
			long current = System.currentTimeMillis();
			String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(current));

			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(time);
			stringBuffer.append("\n");
			dumpPhoneInfo(stringBuffer);
			stringBuffer.append("\n");

			StringWriter stackTrace = new StringWriter();
			ex.printStackTrace(new PrintWriter(stackTrace));
			stringBuffer.append(stackTrace.toString());

			String log = stringBuffer.toString();

		}

		private void dumpPhoneInfo(StringBuffer stringBuffer) {
			PackageManager pm = mContext.getPackageManager();
			PackageInfo pi;
			try {
				pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
				stringBuffer.append("App Version: ");
				stringBuffer.append(pi.versionName);
				stringBuffer.append('_');
				stringBuffer.append(pi.versionCode);
			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			stringBuffer.append("OS Version: ");
			stringBuffer.append(Build.VERSION.RELEASE);
			stringBuffer.append("_");
			stringBuffer.append(Build.VERSION.SDK_INT);

			stringBuffer.append("Vendor: ");
			stringBuffer.append(Build.MANUFACTURER);

			stringBuffer.append("Model: ");
			stringBuffer.append(Build.MODEL);

			stringBuffer.append("CPU ABI: ");
			stringBuffer.append(Build.CPU_ABI);
		}

	}

}
