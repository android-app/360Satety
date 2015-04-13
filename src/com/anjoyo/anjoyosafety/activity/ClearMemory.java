package com.anjoyo.anjoyosafety.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anjoyo.anjoyosafety.adapter.ClearMemoryAdapter;
import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosafety.bean.ProcessInfo;
import com.anjoyo.anjoyosafety.util.TrafficDataUtil;
import com.anjoyo.anjoyosatety.activity.R;

/**
 * 
 * @author dingzhixiang
 * @2013-11-11下午2:33:11 清理内存的Activity
 */
public class ClearMemory extends MyBaseActivity implements OnItemClickListener,OnClickListener {
	private TextView avaiMemSize_tv, backsf_tv;
	private ActivityManager atyManager = null;
	private List<ProcessInfo> processInfoList = null;
	private ListView listView;
	private PackageManager pm;
	private ClearMemoryAdapter adapter;
	private Button btn_clea,btn_return,btn_timing;
	private Animation animation;
	private ImageView loding_iv;
	private RelativeLayout rl_loading;
	private CheckBox cb;
	private ActivityManager am;
	private List<RunningAppProcessInfo> runningTasks;
	private ProcessInfo info;
    private int count=0;
	@Override
	protected void controll() {
		// 获取ActivityManager服务的对象
		atyManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		String avaiMemory = getSystemAvailableMemorySize();
		avaiMemSize_tv.setText(avaiMemory);
		processInfoList = new ArrayList<ProcessInfo>();
		backsf_tv = (TextView) findViewById(R.id.clear_memory_num);
		new Thread(new MyThread()).start();
		listView.setOnItemClickListener(this);
		btn_return.setOnClickListener(this);
		btn_timing.setOnClickListener(this);
	}

	class MyThread implements Runnable {
		@Override
		public void run() {
			handler.sendEmptyMessage(1);
			getRunningAppProcessInfo(ClearMemory.this);
			handler.sendEmptyMessage(2);
			handler.sendEmptyMessage(3);
			btn_clea.setOnClickListener(ClearMemory.this);
		}

	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				showAnim();

				break;
			case 3:
				adapter = new ClearMemoryAdapter(ClearMemory.this,
						processInfoList);
				listView.setAdapter(adapter);
				backsf_tv.setText(String.valueOf(processInfoList.size()));
				break;
			case 2:
				stopAnim();
				break;
			
			}
		};
	};

	@Override
	protected void findViewById() {
		avaiMemSize_tv = (TextView) findViewById(R.id.clear_memory_availsize);
		listView = (ListView) findViewById(R.id.clear_memory_listview);
		btn_clea = (Button) findViewById(R.id.clear_memory_btn_clear);
		loding_iv = (ImageView) findViewById(R.id.clear_memory_loding);
		rl_loading = (RelativeLayout) findViewById(R.id.clear_memory_rl_loading);
		btn_return=(Button) findViewById(R.id.clear_memory_back);
		btn_timing=(Button) findViewById(R.id.clear_memory_timing);
	}

	@Override
	protected void setContentView() {
		setContentView(R.layout.clear_memory);
	}

	private String getSystemAvailableMemorySize() {
		ActivityManager.MemoryInfo outInfo = new ActivityManager.MemoryInfo();
		atyManager.getMemoryInfo(outInfo);
		String availMem = Formatter.formatFileSize(ClearMemory.this,
				outInfo.availMem);
		return availMem;
	}

	public void getRunningAppProcessInfo(Context context) {
		try {
			pm = this.getPackageManager();// 获得包管理器
			am = (ActivityManager) context
					.getSystemService(Context.ACTIVITY_SERVICE); // 获得ActivityManager对象
			runningTasks = am.getRunningAppProcesses(); // 获得所有正在进行的程序存放在一个list中
			for (RunningAppProcessInfo infos : runningTasks) {
				PackageInfo pInfo = new PackageInfo(context);// 获得PackageInfo对象
				String processName = infos.processName;
				if ("system".equals(processName)
						|| "com.mobilesafe".equals(processName)
						|| "com.android.phone".equals(processName)
						|| "android.process.acore".equals(processName)
						|| "android.process.media".equals(processName)) {
					continue;
				}
				info = new ProcessInfo(ClearMemory.this, infos);
				info.setAppIcon(pInfo.getInfo(processName).loadIcon(pm));
				info.setAppLabel(pInfo.getInfo(processName).loadLabel(pm)
						.toString());
				info.setPkgname(processName.toString());
				info.Checked=true;
				final android.os.Debug.MemoryInfo[] memoryInfo = am
						.getProcessMemoryInfo(new int[] { infos.pid });
				long memory = memoryInfo[0].getTotalPrivateDirty();
				info.setMemSize(memory);
				processInfoList.add(info);
			}
		} catch (Exception ex) {
		}
	}

	class PackageInfo {
		private List<ApplicationInfo> appList;

		public PackageInfo(Context context) {
			PackageManager pm = context.getApplicationContext()
					.getPackageManager();
			appList = pm
					.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
		}

		public ApplicationInfo getInfo(String name) {
			if (name == null) {
				return null;
			}
			for (ApplicationInfo appInfo : appList) {
				if (name.equals(appInfo.processName)) {
					return appInfo;
				}
			}
			return null;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position,
			long arg3) {
		ProcessInfo info=processInfoList.get(position);
		cb = (CheckBox) view.findViewById(R.id.clear_memory_cb);
		if (info.isChecked()) {
			cb.setChecked(true);
			info.setChecked(false);
			count++;
		} else {
			if(count>0){
				count--;
			}
			cb.setChecked(false);
			info.setChecked(true);			
		}
		
		if(count>0){
			
			btn_clea.setText("一键清理("+count+""+")");			
		}else{
			btn_clea.setText("一键清理");
			
		}
		
	}

	private void clearMore() {
		int total = 0;
		int memorySize = 0;
		for (ProcessInfo infos : processInfoList) {
			if (infos.isChecked()) {
				ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
				am.killBackgroundProcesses(infos.getPkgname());
				total++;
				memorySize += infos.getMemSize();
				processInfoList.remove(infos);
				break;
			}
		}
		Toast.makeText(
				this,
				"清理了" + total + "个进程," + "释放了"
						+ TrafficDataUtil.getMemoryData(memorySize) + "内存",
				Toast.LENGTH_SHORT).show();
		adapter=new ClearMemoryAdapter(this, processInfoList);
		listView.setAdapter(adapter);
		btn_clea.setText("一键清理");
	}

	private void showAnim() {
		animation = AnimationUtils.loadAnimation(this, R.anim.train_xuanzhuan);
		loding_iv.startAnimation(animation);
	}

	private void stopAnim() {
		loding_iv.clearAnimation();
		rl_loading.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.clear_memory_back:
			finish();
			break;
		case R.id.clear_memory_btn_clear:
			if (count > 0) {
				clearMore();
			} else {
				Toast.makeText(ClearMemory.this, "请选择需要清理的软件。",
						Toast.LENGTH_SHORT).show();
			}		
			break;
		case R.id.clear_memory_timing:
			Intent intent = new Intent(this, Clear_Timing.class);
			startActivity(intent);
			break;
		}
	}
}
