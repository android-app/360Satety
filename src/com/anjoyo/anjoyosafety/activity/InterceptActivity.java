package com.anjoyo.anjoyosafety.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.anjoyo.anjoyosafety.adapter.InterceptAdapter;
import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosafety.contants.AppInfo;
import com.anjoyo.anjoyosafety.util.AdManagerUtil;
import com.anjoyo.anjoyosafety.util.PopMenu;
import com.anjoyo.anjoyosafety.util.WeiBoUtil;
import com.anjoyo.anjoyosatety.activity.R;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

import dalvik.system.DexFile;
//广告拦截主界面
public class InterceptActivity extends MyBaseActivity implements
		OnClickListener ,OnItemClickListener{
	private ImageView iView,ad_main_xuanzhuan;
	PopMenu popMenu;
	private View popView;
	private PopupWindow pop;
	private LinearLayout ll2;
	private Button ad_main_fanhui;
	private ListView lView;
	public static InterceptAdapter adapter;
	public static ArrayList<AppInfo> appList;
	public static String adname;
	private Button num;  
	AppInfo tmpInfo;
	private static String appKey = "1201533495";
	SsoHandler mySsoHandler;
	@Override
	protected void controll() {
		startAnim();
		initpop();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				getInfo();
				handler.sendEmptyMessageDelayed(1, 500);
			}
		}).start();
		
		
		lView.setOnItemClickListener(this);
	}

	@Override
	protected void findViewById() {
		iView = (ImageView) findViewById(R.id.ad_main_caidan);
		ad_main_xuanzhuan = (ImageView) findViewById(R.id.ad_main_xuanzhuan);
		ll2 = (LinearLayout) findViewById(R.id.ad_main_ll2);
		ad_main_fanhui = (Button) findViewById(R.id.ad_main_fanhui);
		lView = (ListView) findViewById(R.id.ad_main_lv);
		num=(Button) findViewById(R.id.ad_main_num);
		ll2.setOnClickListener(this);
		iView.setOnClickListener(this);
		ad_main_fanhui.setOnClickListener(this);
	}

	@Override
	protected void setContentView() {
		setContentView(R.layout.ad_main);
	}
	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			msg.what=1;
			ad_main_xuanzhuan.setVisibility(View.GONE);
			stopAnim();
			lView.setVisibility(View.VISIBLE);
			adapter = new InterceptAdapter(InterceptActivity.this, appList);
			lView.setAdapter(adapter);
			num.setText(appList.size()+"");
		};
	};
	private void startAnim() {
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.train_xuanzhuan);
		ad_main_xuanzhuan.startAnimation(anim);
	}

	private void stopAnim() {
		ad_main_xuanzhuan.clearAnimation();
		ad_main_xuanzhuan.setVisibility(View.GONE);
	}
//popwindons初始化和内容按钮的点击事件
	private void initpop() {
		popView = LayoutInflater.from(this).inflate(R.layout.popmenu, null);
		pop = PopMenu.getPopupWindow(popView);
		pop.setFocusable(true);
		Bitmap bitmap = null;
		pop.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmap));
		pop.setAnimationStyle(R.style.AnimationPreview);
		TextView tv1 = (TextView) popView.findViewById(R.id.PopMenu_bt1);
		TextView tv2 = (TextView) popView.findViewById(R.id.PopMenu_bt2);
		TextView tv3 = (TextView) popView.findViewById(R.id.PopMenu_bt3);
		tv1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(InterceptActivity.this,
						InterceptSetActivity.class);
				startActivity(intent);
				pop.dismiss();
			}
		});
		tv2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(InterceptActivity.this,
						InterceptJuBaoActivity.class);
				startActivity(intent);
				pop.dismiss();
			}
		});
		tv3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				pop.dismiss();
				WeiBoUtil.shareText(InterceptActivity.this,appKey);
			}
		});

	}
	@Override
	protected void onActivityResult(int requestCode1, int resultCode1, Intent data1) {
		super.onActivityResult(requestCode1, resultCode1, data1);
		// SSO 授权回调
		// 重要：发起 SSO 登陆的 Activity 必须重写 onActivityResult
		if (mySsoHandler != null) {
			mySsoHandler.authorizeCallBack(requestCode1, resultCode1, data1);
		}
	}
	
@Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	if (InterceptDetailActivity.isUninstall) {
		getInfo();
		adapter = new InterceptAdapter(this, appList);
		lView.setAdapter(adapter);
		num.setText(appList.size()+"");
		adapter.notifyDataSetChanged();
	}
}

	//获取手机内所有应用程序的应用图标，应用名称，包名等
	public void getInfo() {
		appList = new ArrayList<AppInfo>();

		List<PackageInfo> packages = getPackageManager()
				.getInstalledPackages(0);

		for (int i = 0; i < packages.size(); i++) {
			PackageInfo packageInfo = packages.get(i);
			//判断是否为自己安装的软件非系统软件
			if ((packageInfo.applicationInfo.flags & packageInfo.applicationInfo.FLAG_SYSTEM) <= 0) {
				String packageName =packageInfo.packageName;
				 tmpInfo = new AppInfo();
				StringBuffer buffer=new StringBuffer();
				 int count=0;  
				try {
					String path = getPackageManager()
					.getApplicationInfo(packageName, 0).sourceDir;// 获得某个程序的APK路径
					DexFile dexFile = new DexFile(path);
					Enumeration<String> enu = dexFile.entries();
					while (enu.hasMoreElements()) {
						String pm = enu.nextElement();// 获取某个应用的所有包名
						for (int j = 0; j < AdManagerUtil.adPackage.size(); j++) {
							if (pm.equals(AdManagerUtil.adPackage.get(j))) {
								buffer.append(AdManagerUtil.adName.get(j)).append("\n");
								count ++;
								tmpInfo.adName=buffer.toString();
								tmpInfo.count=count;
							}
						}
					}
				} catch (NameNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (!buffer.toString().equals("")) {
					tmpInfo.appName = packageInfo.applicationInfo.loadLabel(
							getPackageManager()).toString();
					
					tmpInfo.packageName = packageInfo.packageName;
					
					tmpInfo.versionName = packageInfo.versionName;
					
					tmpInfo.versionCode = packageInfo.versionCode;
					
					tmpInfo.appIcon = packageInfo.applicationInfo
					.loadIcon(getPackageManager());
					appList.add(tmpInfo);
					
				}
				
			}

		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ad_main_caidan:
			if (pop.isShowing()) {
				pop.dismiss();
			} else {
				pop.showAtLocation(v, Gravity.RIGHT | Gravity.TOP, 20, 90);
			}
			break;
		case R.id.ad_main_ll2:
			Intent intent = new Intent(this, InterceptJianCeActivity.class);
			startActivity(intent);
			break;
		case R.id.ad_main_fanhui:
			finish();
			break;

		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		// getAdApp(arg2);
		
		AppInfo info=appList.get(arg2);
		Intent intent=new Intent(this,InterceptDetailActivity.class);
		intent.putExtra("arg2",arg2);
		intent.putExtra("adname",info.getAdName());
		intent.putExtra("count",info.getCount());
		startActivity(intent);
	}
}
