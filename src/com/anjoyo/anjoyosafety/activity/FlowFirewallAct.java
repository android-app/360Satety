package com.anjoyo.anjoyosafety.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.TrafficStats;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.anjoyo.anjoyosafety.adapter.FlowFirewallListAdapter;
import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosatety.activity.R;

public class FlowFirewallAct extends MyBaseActivity implements OnClickListener{

	private FlowFirewallListAdapter adapter;
	private ListView listView;
	Button data,smallData,smallWifi;
	TextView wifi;
	LinearLayout ll_smallData,ll_smallWifi;
	@Override
	protected void setContentView() {
		setContentView(R.layout.flow_firewall);
		
	}
	@Override
	protected void findViewById() {
		listView=(ListView) findViewById(R.id.flow_firewall_listview);
		data=(Button) findViewById(R.id.flow_firewall_bigdata);
		wifi=(TextView) findViewById(R.id.flow_firewall_bigwifi);
		smallData=(Button) findViewById(R.id.flow_firewall_smalldata);
		smallWifi=(Button) findViewById(R.id.flow_firewall_smallwifi);
		ll_smallData=(LinearLayout) findViewById(R.id.flow_firewall_ll_smalldata);
		ll_smallWifi=(LinearLayout) findViewById(R.id.flow_firewall_ll_smallwifi);
		
	}
	@Override
	protected void controll() {
		getApp();
		wifi.setOnClickListener(this);
		
	}
	public void getApp() {

		final PackageManager pm = FlowFirewallAct.this.getPackageManager();
		List<ResolveInfo>  trafficlists = new ArrayList<ResolveInfo>();
		Intent intent = new Intent();
		intent.setAction("android.intent.action.MAIN");
		intent.addCategory("android.intent.category.LAUNCHER");
		final List<ResolveInfo> lists = pm.queryIntentActivities(intent, 0);
		for(ResolveInfo info : lists){
			if(TrafficStats.getUidRxBytes(info.activityInfo.applicationInfo.uid) != -1 
					&& TrafficStats.getUidTxBytes(info.activityInfo.applicationInfo.uid) != -1){

				trafficlists.add(info);
			}else{

			}
		}	
		adapter = new FlowFirewallListAdapter(FlowFirewallAct.this,trafficlists);
		listView.setAdapter(adapter);
	}
	@Override
	public void onClick(View v) {
//		boolean b=true;
//		switch (v.getId()) {
//		case R.id.flow_firewall_bigdata:
//			if(b=true){
//				b=false;
//				data.setBackgroundResource(R.drawable.firewall_btn_normal);
//				
//			}else if(b=false){
//				b=true;
//				data.setBackgroundResource(R.drawable.firewall_btn_pressed);				
//			}
//			
//			break;
//		}
		
	}


}
