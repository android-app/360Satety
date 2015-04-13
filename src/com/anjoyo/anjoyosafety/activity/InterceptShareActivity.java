package com.anjoyo.anjoyosafety.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosatety.activity.R;
//我要分享界面
public class InterceptShareActivity extends MyBaseActivity implements OnClickListener{
private Button ad_share_fanhui;
	@Override
	protected void controll() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		ad_share_fanhui=(Button) findViewById(R.id.ad_share_fanhui);
		ad_share_fanhui.setOnClickListener(this);
	}

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ad_share);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ad_share_fanhui:
			finish();
			break;

		default:
			break;
		}
	}

}
