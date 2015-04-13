package com.anjoyo.anjoyosafety.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosatety.activity.R;

public class Tag_CrankAd extends MyBaseActivity implements OnClickListener{

	TextView tvshow;
	TextView tagbtn;
	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.cranksmsandcall_blacklist);
	}

	@Override
	protected void findViewById() {
		tvshow = (TextView) findViewById(R.id.cranksmsandcall_sms_showtv);
		tvshow.setText("广告推销");
		tagbtn = (TextView) findViewById(R.id.addblacklist);
		tagbtn.setText("标记最近陌生号码");
		tagbtn.setOnClickListener(this);	
	}

	@Override
	protected void controll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this,Tag_SubCrankAd.class);
		startActivity(intent);
		
	}

	
}
