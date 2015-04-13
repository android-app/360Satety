package com.anjoyo.anjoyosafety.activity;

import com.anjoyo.anjoyosatety.activity.R;

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

@SuppressWarnings("deprecation")
public class Clear_Garbage extends TabActivity implements OnClickListener,OnTabChangeListener{

	private TabHost tabHost;
	private TabWidget tabWidget;
    private Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.clear_garbage);
		btn_back=(Button) findViewById(R.id.clear_garbage_back);
		btn_back.setOnClickListener(this);
		tabHost = getTabHost();
		tabWidget = getTabWidget();
		LayoutInflater inflater = LayoutInflater.from(this);
		View v1 = inflater.inflate(R.layout.garbage_widget_item1, null);
		View v2 = inflater.inflate(R.layout.garbage_widget_item2, null);
		View v3 = inflater.inflate(R.layout.garbage_widget_item3, null);	
		
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(v1)
				.setContent(new Intent(this,Clear_GarbageContent.class)));
		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(v2)
				.setContent(new Intent(this,Clear_GarbageContent.class)));
		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator(v3)
				.setContent(new Intent(this,Clear_GarbageContent.class)));

		tabWidget.setOrientation(LinearLayout.HORIZONTAL);
		tabHost.setCurrentTab(0);
		tabHost.setup();
    	
    }
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.clear_garbage_back:
			finish();
			break;

		}
		
	}
	@Override
	public void onTabChanged(String tabId) {
		// TODO Auto-generated method stub
		
	}
	
}
