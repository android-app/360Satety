package com.anjoyo.anjoyosafety.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import com.anjoyo.anjoyosafety.adapter.FindAdapter;
import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosafety.contants.AppInfo;
import com.anjoyo.anjoyosafety.contants.FindAppInfo;
import com.anjoyo.anjoyosafety.util.AdManagerUtil;
import com.anjoyo.anjoyosatety.activity.R;

import dalvik.system.DexFile;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.Gallery;
import android.widget.ProgressBar;
import android.widget.TextView;

//用来查找系统软件图标并显示的界面
public class FindActivity extends MyBaseActivity {
	private TextView tv_hintTextView;
	private boolean isRun = true;
	public static ArrayList<FindAppInfo> appList;
	Gallery gallery;
	 ProgressBar pBar ;

	public void getInfo() {
		appList = new ArrayList<FindAppInfo>();

		List<PackageInfo> packages = getPackageManager()
				.getInstalledPackages(0);

		for (int i = 0,count=packages.size(); i < count; i++) {
			PackageInfo packageInfo = packages.get(i);
			// 判断是否为自己安装的软件非系统软件
			if ((packageInfo.applicationInfo.flags & packageInfo.applicationInfo.FLAG_SYSTEM) <= 0) {
				FindAppInfo tmpInfo = new FindAppInfo();
				tmpInfo.appIcon = packageInfo.applicationInfo
						.loadIcon(getPackageManager());
				// System.out.println(tmpInfo.appName);
				appList.add(tmpInfo);
			}
		}
	}

	@Override
	protected void controll() {
		getInfo();
		gallery = (Gallery) findViewById(R.id.gallery1);
		showNum();
		// 创建用于描述图像数据的ImageAdapter对象
		FindAdapter adapter = new FindAdapter(this, appList);
		// 设置Gallery组件的Adapter对象
		gallery.setAdapter(adapter);
		
	}
	 Handler handler = new Handler() {
			@Override
			public void handleMessage(android.os.Message msg) {
				int what = msg.what;
				pBar.setProgress(what + 1);
				gallery.setSelection(what);
				tv_hintTextView.setText((what + 1) + "/" + appList.size());
			}
		};
	protected void showNum() {
		if (appList != null) {
			gallery.setPadding(10, 10, 10, 10);
			pBar.setMax(appList.size());
			new Thread(new Runnable() {
				private int progress = 0;
				@Override
				public void run() {
					while (isRun) {
						int count=appList.size();
						while (progress < count) {
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							handler.sendEmptyMessage(progress++);
							if(progress==count){
								Intent intent = new Intent(FindActivity.this,
										InterceptActivity.class);
								startActivity(intent);
								isRun=false;
								finish();
							}
						}
					}
				}
			}).start();
		}
	}

	@Override
	protected void findViewById() {
		tv_hintTextView = (TextView) findViewById(R.id.tv_hint);
		  pBar = (ProgressBar) findViewById(R.id.pb_gallery);
	}

	@Override
	protected void setContentView() {
		setContentView(R.layout.ad_find);
	}
}
