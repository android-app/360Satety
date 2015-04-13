package com.anjoyo.anjoyosafety.activity;

import java.util.ArrayList;
import java.util.List;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.anjoyo.anjoyosafety.adapter.InterceptJuBaoAdapter;
import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosafety.contants.JuBaoInfo;
import com.anjoyo.anjoyosafety.util.WeiBoUtil;
import com.anjoyo.anjoyosatety.activity.R;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
//广告举报界面
public class InterceptJuBaoActivity extends MyBaseActivity implements OnClickListener,OnItemClickListener{
	private Button ad_jubao_fanhui,ad_jubao_bt,ad_jubao_dialog_share,ad_jubao_dialog_fanhui;
	private ListView ad_jubao_lv;
	private InterceptJuBaoAdapter adapter;
	private ArrayList<JuBaoInfo> appList;
	ImageView ad_jubao_xuanzhuan;
	JuBaoInfo baoInfo;
	View view;
	int count=0;
	private static String appKey = "1201533495";
	SsoHandler mSsoHandler;
	@Override
	protected void controll() {
		startAnim();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				getInfo();
				handler.sendEmptyMessageDelayed(1, 500);
			}
		}).start();
		
	}

	@Override
	protected void findViewById() {
		ad_jubao_fanhui=(Button) findViewById(R.id.ad_jubao_fanhui);
		ad_jubao_xuanzhuan=(ImageView) findViewById(R.id.ad_jubao_xuanzhuan);
		ad_jubao_bt=(Button) findViewById(R.id.ad_jubao_bt);
		ad_jubao_lv=(ListView) findViewById(R.id.ad_jubao_lv);
		ad_jubao_lv.setOnItemClickListener(this);
		ad_jubao_fanhui.setOnClickListener(this);
		ad_jubao_bt.setOnClickListener(this);
	}

	@Override
	protected void setContentView() {
		setContentView(R.layout.ad_jubao);
		
	}
	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			msg.what=1;
			//getInfo();
			stopAnim();
			ad_jubao_xuanzhuan.setVisibility(View.GONE);
			ad_jubao_lv.setVisibility(View.VISIBLE);
			adapter=new InterceptJuBaoAdapter(InterceptJuBaoActivity.this, appList);
			ad_jubao_lv.setAdapter(adapter);
		};
	};
	private void startAnim() {
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.train_xuanzhuan);
		ad_jubao_xuanzhuan.startAnimation(anim);
	}

	private void stopAnim() {
		ad_jubao_xuanzhuan.clearAnimation();
		ad_jubao_xuanzhuan.setVisibility(View.GONE);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ad_jubao_fanhui:
			finish();
			break;
		case R.id.ad_jubao_bt:
			if (count>0) {
				showDialog();
				for (int i = 0; i < appList.size(); i++) {
					if (!appList.get(i).isChecked()) {
					//	ad_jubao_list_item_checked.setBackgroundResource(R.drawable.block_msg_reported);
						appList.get(i).setIcon(getResources().getDrawable(R.drawable.block_msg_reported));
						appList.get(i).setJuBao(true);
						adapter.notifyDataSetChanged();
					}	
				}

				
				ad_jubao_bt.setText("请选择要举报的软件");
				count=0;
			}else {
				Toast.makeText(this, "请选择要举报的软件", 0).show();

			}
			break;
		}
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		baoInfo=appList.get(arg2);
		if (baoInfo.isChecked()) {
			count++;
			if (!baoInfo.isJuBao) {
				baoInfo.setIcon(getResources().getDrawable(R.drawable.checkbox_checked));
				adapter.notifyDataSetChanged();
				ad_jubao_bt.setText("请选择要举报的软件"+"("+count+")");
				baoInfo.setChecked(false);
			}
		}else {
			if (count>0) {
				count--;
			}
			if (!baoInfo.isJuBao) {
				baoInfo.setIcon(getResources().getDrawable(R.drawable.checkbox_disable_checked));
				adapter.notifyDataSetChanged();
				if (count==0) {
					ad_jubao_bt.setText("请选择要举报的软件");
				}else {
					ad_jubao_bt.setText("请选择要举报的软件"+"("+count+")");
				}
				baoInfo.setChecked(true);
				
			}
		}
	}
	//获取手机内所有应用程序的应用图标，应用名称
	public void getInfo() {
		appList = new ArrayList<JuBaoInfo>();
		List<PackageInfo> packages = getPackageManager()
		.getInstalledPackages(0);
		for (int i = 0; i < packages.size(); i++) {
			PackageInfo packageInfo = packages.get(i);
			baoInfo = new JuBaoInfo();
			baoInfo.appName = packageInfo.applicationInfo.loadLabel(
					getPackageManager()).toString();
			baoInfo.appIcon = packageInfo.applicationInfo
			.loadIcon(getPackageManager());
			baoInfo.Checked=true;
			baoInfo.isJuBao=false;
			baoInfo.Icon=getResources().getDrawable(R.drawable.checkbox_disable_checked);
			appList.add(baoInfo);
		}
	}

	public void showDialog(){
		final Dialog dialog = new Dialog(this,R.style.dialog);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.show();
		Window window = dialog.getWindow();
		window.setContentView(R.layout.ad_jubao_dialog);
		ad_jubao_dialog_share=(Button)window.findViewById(R.id.ad_jubao_dialog_share);
		ad_jubao_dialog_fanhui=(Button)window.findViewById(R.id.ad_jubao_dialog_fanhui);
		ad_jubao_dialog_share.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				WeiBoUtil.shareText(InterceptJuBaoActivity.this,appKey);
				dialog.dismiss();
			}
		});
		ad_jubao_dialog_fanhui.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// SSO 授权回调
		// 重要：发起 SSO 登陆的 Activity 必须重写 onActivityResult
		if (mSsoHandler != null) {
			mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
		}
	}



}
