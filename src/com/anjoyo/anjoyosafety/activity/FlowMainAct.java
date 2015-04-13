package com.anjoyo.anjoyosafety.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;
import android.widget.TextView;
import com.anjoyo.anjoyosatety.activity.R;

@SuppressWarnings("deprecation")
public class FlowMainAct extends TabActivity implements OnTabChangeListener,OnClickListener {
	private TabHost tabHost;
	private TabWidget tabWidget;
	private TextView tv_title;
    private Button btn_back,setting;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flow_main);

		tv_title = (TextView) findViewById(R.id.flow_main_title);
		btn_back=(Button) findViewById(R.id.flow_main_back);
		tabHost = getTabHost();
		tabWidget = getTabWidget();
		LayoutInflater inflater = LayoutInflater.from(this);
		View v1 = inflater.inflate(R.layout.flow_widget_item, null);
		View v2 = inflater.inflate(R.layout.flow_widget_item2, null);
		View v3 = inflater.inflate(R.layout.flow_widget_item3, null);
		setting=(Button) findViewById(R.id.flow_main_set);
		setting.setOnClickListener(this);
		
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(v1)
				.setContent(new Intent(this,FlowMonitoring.class)));
		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(v2)
				.setContent(new Intent(this,FlowFirewallAct.class)));
		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator(v3)
				.setContent(new Intent(this,FlowSortAct.class)));

		tabWidget.setOrientation(LinearLayout.HORIZONTAL);
		tabHost.setCurrentTab(0);
		tabHost.setup();

		tabHost.setOnTabChangedListener(this);
		btn_back.setOnClickListener(this);
	}

	@Override
	public void onTabChanged(String tabId) {
		for (int i = 0; i < tabWidget.getChildCount(); i++) {
			if (tabHost.getCurrentTab() == 0) {
				tv_title.setText("流量监控");
			} else if (tabHost.getCurrentTab() == 1) {
				tv_title.setText("联网防火墙");
			} else if (tabHost.getCurrentTab() == 2) {
				tv_title.setText("本月流量排行");
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.flow_main_back:
			finish();
			break;
		case R.id.flow_main_set:
			Intent intent=new Intent(this,FlowSetting.class);
			startActivity(intent);
		}
		
	}

}
