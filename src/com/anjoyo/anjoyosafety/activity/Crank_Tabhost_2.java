package com.anjoyo.anjoyosafety.activity;

import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosafety.dbhelper.SmsDbHelper;
import com.anjoyo.anjoyosatety.activity.R;

public class Crank_Tabhost_2 extends MyBaseActivity{

	@Override
	protected void setContentView() {
		setContentView(R.layout.cranksmsandcall_frament_tab2);
		
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void controll() {
		// TODO Auto-generated method stub
		SmsDbHelper sms = new SmsDbHelper(this);
		sms.getWritableDatabase();
	}
	

}
