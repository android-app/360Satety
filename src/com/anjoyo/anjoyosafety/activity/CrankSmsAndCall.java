package com.anjoyo.anjoyosafety.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;

import com.anjoyo.anjoyosatety.activity.R;

@SuppressWarnings("deprecation")
public class CrankSmsAndCall extends TabActivity implements OnTabChangeListener,OnClickListener {

	TabHost tabhost;
	TabWidget tabWidget;
	TextView tv;
	Button ruturn;
	Button setbtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cranksmsandcall_main);
		tabhost = getTabHost();
		tabWidget = getTabWidget();
		tv = (TextView) findViewById(R.id.cranksmsandcall_main_showtv);
		ruturn=(Button) findViewById(R.id.crank_main_return);
		ruturn.setOnClickListener(this);
		setbtn=(Button) findViewById(R.id.crank_main_setbtn);
		setbtn.setOnClickListener(this);
		LayoutInflater factory = LayoutInflater.from(this);
		View v1 = factory.inflate(R.layout.cranksmsandcall_tabhost1, null);
		View v2 = factory.inflate(R.layout.cranksmsandcall_tabhost2, null);
		View v3 = factory.inflate(R.layout.cranksmsandcall_tabhost3, null);
		TabSpec tabspec1 = tabhost.newTabSpec("1").setIndicator(v1)
				.setContent(new Intent(this, Crank_Tabhost_1.class));
		TabSpec tabspec2 = tabhost.newTabSpec("2").setIndicator(v2)
				.setContent(new Intent(this, Crank_Tabhost_2.class));
		TabSpec tabspec3 = tabhost.newTabSpec("3").setIndicator(v3)
				.setContent(new Intent(this, Crank_Tabhost_3.class));
		tabhost.addTab(tabspec1);
		tabhost.addTab(tabspec2);
		tabhost.addTab(tabspec3);
		tabhost.setOnTabChangedListener(this);
	}
    //tabhost改变监听
	@Override
	public void onTabChanged(String tabId) {
		for (int i = 0; i < tabWidget.getChildCount(); i++) {
			if (tabhost.getCurrentTab() == 0) {
				tv.setText("垃圾短息");
			} else if (tabhost.getCurrentTab() == 1) {
				tv.setText("骚扰电话");
			} else if (tabhost.getCurrentTab() == 2) {
				tv.setText("标记管理");
			}
		}

	}

	//主界面单击事件
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.crank_main_return:
			finish();
			break;
		case R.id.crank_main_setbtn:
			//跳转到设置界面
			Intent intent  = new Intent(this,CrankSmsAndCallSet.class);
			startActivity(intent);
			
			break;
		}
		
	}

}
