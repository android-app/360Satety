package com.anjoyo.anjoyosafety.activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.widget.ListView;
import android.widget.TextView;

import com.anjoyo.anjoyosafety.adapter.ClearAutostartAdapter;
import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosafety.bean.AutostartSoftWareInfo;
import com.anjoyo.anjoyosatety.activity.R;

/**
 * 
 * @author dingzhixiang
 *@2013-11-9下午3:18:33
 */
public class Clear_AutostartAct extends MyBaseActivity {

	private ListView listView;
	private TextView tv_autostart_num;
	private ClearAutostartAdapter adapter;
	private List<AutostartSoftWareInfo> autostartInfo;
	static final String BOOT_START_PERMISSION = "android.permission.RECEIVE_BOOT_COMPLETED";

	@Override
	protected void controll() {
		getautostartInfo();
		adapter = new ClearAutostartAdapter(this, autostartInfo);
		tv_autostart_num.setText(String.valueOf(autostartInfo.size()));
		listView.setAdapter(adapter);
		
	}

	@Override
	protected void findViewById() {
		tv_autostart_num = (TextView) findViewById(R.id.clear_autostart_num);
		listView = (ListView) findViewById(R.id.clear_autostart_listview);
	}

	@Override
	protected void setContentView() {
		setContentView(R.layout.clear_autostart);

	}

	private void getautostartInfo() {
		List<ApplicationInfo> packages = this.getPackageManager()
				.getInstalledApplications(0);
		autostartInfo = new ArrayList<AutostartSoftWareInfo>(packages.size());
		Iterator<ApplicationInfo> appInfoIterator = packages.iterator();

		while (appInfoIterator.hasNext()) {
			
			ApplicationInfo app = (ApplicationInfo) appInfoIterator.next();
			// 查找安装的package是否有开机启动权限
			if (PackageManager.PERMISSION_GRANTED == getPackageManager()
					.checkPermission(BOOT_START_PERMISSION, app.packageName)) {
				AutostartSoftWareInfo info=new AutostartSoftWareInfo();
				info.setApp_name(getPackageManager().getApplicationLabel(app).toString());
				info.setApp_Icon(getPackageManager().getApplicationIcon(app));
				autostartInfo.add(info);
				
			}
		}
	}

}
