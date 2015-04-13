package com.anjoyo.anjoyosafety.activity;

import android.app.ActionBar.LayoutParams;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosatety.activity.R;

public class Crank_Tabhost_3 extends MyBaseActivity implements OnClickListener {

	TextView tv;
	ImageView addimg;
	ImageView cometag;
	RelativeLayout r1;
	RelativeLayout r2;
	RelativeLayout r3;
	RelativeLayout r4;
	RelativeLayout r5;

	@Override
	protected void setContentView() {
		setContentView(R.layout.cranksmsandcall_frament_tab3);

	}

	@Override
	protected void findViewById() {
		cometag = (ImageView) findViewById(R.id.crank_show_imageview);
		cometag.setOnClickListener(this);
		addimg = (ImageView) findViewById(R.id.crank_addimg);
		r1 = (RelativeLayout) findViewById(R.id.tag_crankcall);
		r1.setOnClickListener(this);
		r2 = (RelativeLayout) findViewById(R.id.tag_crankad);
		r2.setOnClickListener(this);
		r3 = (RelativeLayout) findViewById(R.id.tag_crankhouse);
		r3.setOnClickListener(this);
		r4 = (RelativeLayout) findViewById(R.id.tag_crankeat);
		r4.setOnClickListener(this);
		r5 = (RelativeLayout) findViewById(R.id.tag_cranke_add);
		r5.setOnClickListener(this);
		tv = (TextView) findViewById(R.id.crank_addtext);
	}

	@Override
	protected void controll() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.crank_show_imageview:
			Intent intent = new Intent(Crank_Tabhost_3.this,
					CrankCall_Tag.class);
			startActivity(intent);
			break;
		case R.id.tag_crankcall:
			Intent intent1 = new Intent(Crank_Tabhost_3.this,
					Tag_CrankCall.class);
			startActivity(intent1);
			break;
		case R.id.tag_crankad:
			Intent intent2 = new Intent(Crank_Tabhost_3.this, Tag_CrankAd.class);
			startActivity(intent2);
			break;
		case R.id.tag_crankhouse:
			Intent intent3 = new Intent(Crank_Tabhost_3.this,
					Tag_CrankHouse.class);
			startActivity(intent3);
			break;
		case R.id.tag_crankeat:
			Intent intent4 = new Intent(Crank_Tabhost_3.this,
					Tag_CrankEat.class);
			startActivity(intent4);
			break;
		case R.id.tag_cranke_add:
			if (tv.getText().equals("添加新标记")) {
				getwhereto();
				addimg.setVisibility(ImageView.GONE);
				
			} else {
				Toast.makeText(this, "没实现", 0).show();
			}
			break;
		}

	}

	void getwhereto() {
		final Dialog dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.show();
		Window window = dialog.getWindow();
		window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		window.setContentView(R.layout.tag_addshow);
		Button smsadd = (Button) window.findViewById(R.id.tag_addno);
		Button calladd = (Button) window.findViewById(R.id.tag_addoff);
		final EditText edittext = (EditText) window
				.findViewById(R.id.tag_editshow);
		smsadd.setOnClickListener(new OnClickListener() {
			// 创建新列表
			@Override
			public void onClick(View v) {
				if (!edittext.getText().equals("")) {
					tv.setText(edittext.getText());
				}
				dialog.dismiss();
			}
		});
		// 不进行操作
		calladd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});

	}

}