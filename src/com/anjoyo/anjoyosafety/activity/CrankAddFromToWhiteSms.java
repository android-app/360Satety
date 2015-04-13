package com.anjoyo.anjoyosafety.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosatety.activity.R;

public class CrankAddFromToWhiteSms extends MyBaseActivity implements
		OnClickListener {

	Button bt;

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.cranksms_addfromsms);
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		bt = (Button) findViewById(R.id.cranksms_addfromsms_return);
		bt.setOnClickListener(this);
	}

	@Override
	protected void controll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.cranksms_addfromsms_return:
			finish();
			break;

		default:
			break;
		}
	}

}
