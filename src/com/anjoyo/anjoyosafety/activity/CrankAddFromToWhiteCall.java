package com.anjoyo.anjoyosafety.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosatety.activity.R;

public class CrankAddFromToWhiteCall extends MyBaseActivity implements OnClickListener{

	TextView tv;
	Button bt;
	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.cranksms_addfromsms);
	}

	@Override
	protected void findViewById() {
		tv = (TextView) findViewById(R.id.cranksms_addfromsms_tv1);
		tv.setText("从通话记录中添加");
		bt =(Button) findViewById(R.id.cranksms_addfromsms_return);
	bt.setOnClickListener(this);
	}

	@Override
	protected void controll() {
		

	}

	@Override
	public void onClick(View v) {
	switch (v.getId()) {
	case R.id.cranksms_addfromsms_return:
		finish();
		break;

	
	}
		
	}



}
