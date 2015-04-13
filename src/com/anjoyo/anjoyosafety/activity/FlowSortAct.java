package com.anjoyo.anjoyosafety.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.TrafficStats;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.anjoyo.anjoyosafety.adapter.FlowSortListAdapter;
import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosafety.bean.TrafficInfo;
import com.anjoyo.anjoyosatety.activity.R;

public class FlowSortAct extends MyBaseActivity {

	private ListView listView;
	private FlowSortListAdapter adapter;
	private LinearLayout noUse_;
	private LinearLayout useList;
	private Timer timer;
	private TimerTask timerTask;
	private List<TrafficInfo> trafficInfo_list;
   
	@Override
	protected void controll() {
		trafficInfo_list=new ArrayList<TrafficInfo>();
		adapter=new FlowSortListAdapter(this, trafficInfo_list);
		initTraficInfo();
		listView.setAdapter(adapter);
	}

	@Override
	protected void setContentView() {
		setContentView(R.layout.flow_sort);
	}

	@Override
	protected void findViewById() {
		listView = (ListView) findViewById(R.id.flow_sort_listview1);
		noUse_ = (LinearLayout) findViewById(R.id.flow_sort_nouseflow);
		useList = (LinearLayout) findViewById(R.id.flow_sort_listview);
	}

	public void initTraficInfo() {
		trafficInfo_list.clear();
		// 获得一个包管理器
		final PackageManager pm = FlowSortAct.this.getPackageManager();
		// action为android.intent.action.MAIN表示这个Activity是应用的入口
		Intent intent = new Intent();
		intent.setAction("android.intent.action.MAIN");
		intent.addCategory("android.intent.category.LAUNCHER");

		final List<ResolveInfo> resolveInfo = pm.queryIntentActivities(intent,
				0);
		for (ResolveInfo info : resolveInfo) {
			String name = info.loadLabel(pm).toString();
			Drawable icon = info.loadIcon(pm);
			String packageName = info.activityInfo.packageName;
			int uid = 0;
			try {
				PackageInfo packageInfo = pm.getPackageInfo(packageName, 0);
				uid = packageInfo.applicationInfo.uid;
				long received = TrafficStats.getUidRxBytes(uid);
				long transmitted = TrafficStats.getUidTxBytes(uid);
				if (received == -1 && transmitted == -1 ) {
					noUse_.setVisibility(View.GONE);
					useList.setVisibility(View.VISIBLE);
				}else{
					
					noUse_.setVisibility(View.VISIBLE);
					useList.setVisibility(View.GONE);
					TrafficInfo trafficInfo = new TrafficInfo();  
					trafficInfo.setName(name);  
					trafficInfo.setIcon(icon);  
					trafficInfo.setUid(uid);  
					trafficInfo_list.add(trafficInfo);
				}
			} catch (NameNotFoundException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	protected void onStart() {
		timer = new Timer();
		timerTask = new TimerTask() {
			@Override
			public void run() {
				Message msg = Message.obtain();
				handler.sendMessage(msg);
			}
		};
		timer.schedule(timerTask, 1000, 3000);
		super.onStart();
	}

	@Override
	protected void onStop() {
		if (timer != null) {
			timer.cancel();
			timer = null;
			timerTask = null;
		}
		super.onStop();
	}

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			adapter.notifyDataSetChanged();
		}
	};
}
