package com.anjoyo.anjoyosafety.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosafety.contants.AppInfo;
import com.anjoyo.anjoyosatety.activity.R;

public class InterceptDetailActivity extends MyBaseActivity implements OnClickListener{
	private TextView name,ad_datail_show_ad_show,ad_detail_num,ad_datail_show_ad_count;
	private ImageView icon,ad_detail_showad;
	private AppInfo appInfo;
	private Button ad_detail_fanhui1,ad_detail_fanhui2,ad_detail_fankui,ad_fankui_tijiao,ad_fankui_quxiao,
	ad_datail_show_ad_off,Uninstall;
	private ToggleButton wifibToggleButton,g2g3ToggleButton;
	private EditText ad_fankui_et;
	private RelativeLayout ad_detail_rl8,ad_fankui_rl2,ad_fankui_rl3,ad_fankui_rl4,ad_fankui_rl5,
	ad_fankui_rl6;
	private CheckBox cb1,cb2,cb3,cb4,cb5;
	private int arg2,count ;
	private String adname;
	private boolean ischilk1=true,ischilk2=true,ischilk3=true,ischilk4=true,ischilk5=true;
	public static boolean isUninstall=false;
	@Override
	protected void controll() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		 arg2 = intent.getExtras().getInt("arg2");
		 count = intent.getExtras().getInt("count");
		appInfo=InterceptActivity.appList.get(arg2);
		adname=intent.getExtras().getString("adname");
		name.setText(appInfo.getAppName());
		icon.setImageDrawable(appInfo.getAppIcon());
		ad_detail_num.setText("含有"+count+"款广告插件");
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		name = (TextView) findViewById(R.id.ad_detail_name);
		Uninstall = (Button) findViewById(R.id.ad_detail_Uninstall);
		ad_detail_num = (TextView) findViewById(R.id.ad_detail_num);
		icon = (ImageView) findViewById(R.id.ad_detail_icon);
		icon = (ImageView) findViewById(R.id.ad_detail_icon);
		ad_detail_rl8 = (RelativeLayout) findViewById(R.id.ad_detail_rl8);
		ad_detail_rl8.setOnClickListener(this);
		ad_detail_showad = (ImageView) findViewById(R.id.ad_detail_show_ad);
		wifibToggleButton = (ToggleButton) findViewById(R.id.ad_detail_tb_wifi);
		g2g3ToggleButton = (ToggleButton) findViewById(R.id.ad_detail_tb_2g3g);
		ad_detail_fankui=(Button) findViewById(R.id.ad_detail_fankui);
		ad_detail_fankui.setOnClickListener(this);
		ad_detail_fanhui1 = (Button) findViewById(R.id.ad_detail_fanhui1);
		ad_detail_fanhui2 = (Button) findViewById(R.id.ad_detail_fanhui2);
		ad_detail_fanhui1.setOnClickListener(this);
		ad_detail_fanhui2.setOnClickListener(this);
		Uninstall.setOnClickListener(this);
	}

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ad_detail);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ad_detail_fanhui1:
			finish();
			break;
		case R.id.ad_detail_fanhui2:
			finish();
			break;
		case R.id.ad_detail_tb_wifi:

			break;
		case R.id.ad_detail_tb_2g3g:

			break;
		case R.id.ad_detail_Uninstall:
			 Uri uri = Uri.parse("package:" + appInfo.getPackageName());
			         Intent intent = new Intent(Intent.ACTION_DELETE, uri);
			        startActivity(intent);
			        isUninstall=true;
			break;
		case R.id.ad_detail_fankui:
			showDialogFanKui();
			break;
		case R.id.ad_detail_rl8:
			showDialogName();
			break;
		}
	}
	public void showDialogFanKui(){
		final Dialog dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.show();
		Window window = dialog.getWindow();

		window.setContentView(R.layout.ad_fankui);	
		ad_fankui_et=(EditText) window.findViewById(R.id.ad_fankui_et);
		ad_fankui_et.setFocusable(false);

		ad_fankui_rl2=(RelativeLayout)window.findViewById(R.id.ad_fankui_rl2);
		ad_fankui_rl3=(RelativeLayout)window.findViewById(R.id.ad_fankui_rl3);
		ad_fankui_rl4=(RelativeLayout)window.findViewById(R.id.ad_fankui_rl4);
		ad_fankui_rl5=(RelativeLayout)window.findViewById(R.id.ad_fankui_rl5);
		ad_fankui_rl6=(RelativeLayout)window.findViewById(R.id.ad_fankui_rl6);
		cb1=(CheckBox)window.findViewById(R.id.ad_fankui_cb1);
		cb2=(CheckBox)window.findViewById(R.id.ad_fankui_cb2);
		cb3=(CheckBox)window.findViewById(R.id.ad_fankui_cb3);
		cb4=(CheckBox)window.findViewById(R.id.ad_fankui_cb4);
		cb5=(CheckBox)window.findViewById(R.id.ad_fankui_cb5);
		ad_fankui_tijiao=(Button)window.findViewById(R.id.ad_fankui_tijiao);
		ad_fankui_quxiao=(Button)window.findViewById(R.id.ad_fankui_quxiao);
		ad_fankui_rl2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (ischilk1) {
					cb1.setChecked(true);
					ischilk1=false;
				}else {
					cb1.setChecked(false);
					ischilk1=true;
				}
			}
		});
		ad_fankui_rl3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (ischilk2) {
					cb2.setChecked(true);
					ischilk2=false;
				}else {
					cb2.setChecked(false);
					ischilk2=true;
				}
			}
		});
		ad_fankui_rl4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (ischilk3) {
					cb3.setChecked(true);
					ischilk3=false;
				}else {
					cb3.setChecked(false);
					ischilk3=true;
				}
			}
		});
		ad_fankui_rl5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (ischilk4) {
					cb4.setChecked(true);
					ischilk4=false;
				}else {
					cb4.setChecked(false);
					ischilk4=true;
				}
			}
		});
		ad_fankui_rl6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (ischilk5) {
					cb5.setChecked(true);
					ischilk5=false;
				}else {
					cb5.setChecked(false);
					ischilk5=true;
				}
			}
		});
		ad_fankui_et.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {
				ad_fankui_et.setFocusableInTouchMode(true);
				showSoftKeyboard(ad_fankui_et,InterceptDetailActivity.this);
			}
		});
		ad_fankui_tijiao.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {

				dialog.dismiss();

				//关闭输入法软键盘
				closeKeyboard(ad_fankui_et,InterceptDetailActivity.this);
			}
		});
		ad_fankui_quxiao.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {

				dialog.dismiss();
				closeKeyboard(ad_fankui_et,InterceptDetailActivity.this);

			}
		});

	}
	public void showDialogName(){
		final Dialog dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.show();
		Window window = dialog.getWindow();
		window.setContentView(R.layout.ad_detail_show_ad);	
		ad_datail_show_ad_off=(Button)window.findViewById(R.id.ad_datail_show_ad_off);
		ad_datail_show_ad_show=(TextView)window.findViewById(R.id.ad_datail_show_ad_show);
		ad_datail_show_ad_count=(TextView)window.findViewById(R.id.ad_datail_show_ad_count);
		ad_datail_show_ad_show.setText(adname);
		ad_datail_show_ad_count.setText(count+"款");
		ad_datail_show_ad_off.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				
			}
		});
	}
	//弹出软件盘
	public static void showSoftKeyboard(EditText et,Context context){
		InputMethodManager imm=(InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(et,InputMethodManager.RESULT_SHOWN);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);
	}

	//隐藏软件盘
	public static void closeKeyboard(EditText et,Context context) {  
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);  
		imm.hideSoftInputFromWindow(et.getWindowToken(), 0);  
	} 

}
