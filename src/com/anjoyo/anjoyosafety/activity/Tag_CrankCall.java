package com.anjoyo.anjoyosafety.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosatety.activity.R;

public class Tag_CrankCall extends MyBaseActivity implements OnClickListener{

	TextView tvshow;
	TextView tagbtn;
	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.cranksmsandcall_blacklist);
		
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		tvshow = (TextView) findViewById(R.id.cranksmsandcall_sms_showtv);
		tvshow.setText("ɧ�ŵ绰");
		tagbtn = (TextView) findViewById(R.id.addblacklist);
		tagbtn.setText("������İ������");
		tagbtn.setOnClickListener(this);
	}

	@Override
	protected void controll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		Toast.makeText(this, "���", 0).show();
		Intent intent = new Intent(this,Tag_SubCrankCall.class);
		startActivity(intent);
	}

}
