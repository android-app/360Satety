package com.anjoyo.anjoyosafety.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.anjoyo.anjoyosafety.adapter.InterceptJianCeAdapter1;
import com.anjoyo.anjoyosafety.adapter.InterceptJianCeAdapter2;
import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosatety.activity.R;

//广告监测界面
public class InterceptJianCeActivity extends MyBaseActivity implements OnClickListener{
	private Button ad_jiance_fanhui,ad_jiance_bt1,ad_jiance_bt2;
	private InterceptJianCeAdapter1 adapter1;
	private InterceptJianCeAdapter2 adapter2;
	private ImageView  imageView1,imageView2;
	private ListView listView1,listView2;
	private RelativeLayout rl2,rl3;
	private TextView textView1,textView2;
	private boolean isClick1=true;
	private boolean isClick2=true;
	@Override
	protected void controll() {
		adapter1=new InterceptJianCeAdapter1(this, InterceptActivity.appList);
		adapter2=new InterceptJianCeAdapter2(this, InterceptActivity.appList);
		listView1.setAdapter(adapter1);
		listView2.setAdapter(adapter2);
		ad_jiance_bt1.setText("0");
		ad_jiance_bt2.setText(InterceptActivity.appList.size()+"");
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		ad_jiance_fanhui=(Button) findViewById(R.id.ad_jiance_fanhui);
		ad_jiance_bt1=(Button) findViewById(R.id.ad_jiance_bt1);
		ad_jiance_bt2=(Button) findViewById(R.id.ad_jiance_bt2);
		imageView1=(ImageView) findViewById(R.id.ad_jiance_iv1);
		imageView2=(ImageView) findViewById(R.id.ad_jiance_iv2);
		listView1=(ListView) findViewById(R.id.ad_jiance_lv1);
		listView2=(ListView) findViewById(R.id.ad_jiance_lv2);
		rl2=(RelativeLayout) findViewById(R.id.ad_jiance_rl2);
		rl3=(RelativeLayout) findViewById(R.id.ad_jiance_rl3);
		textView1=(TextView) findViewById(R.id.ad_jiance_message1);
		textView2=(TextView) findViewById(R.id.ad_jiance_message2);
		rl2.setOnClickListener(this);
		rl3.setOnClickListener(this);
		ad_jiance_fanhui.setOnClickListener(this);
		
		
	}

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ad_jiance);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ad_jiance_fanhui:
			finish();
			break;
		case R.id.ad_jiance_rl2:
			if (isClick1) {
				imageView1.setBackgroundDrawable(getResources().getDrawable(R.drawable.arrow_bottom_transparent));
				textView1.setVisibility(View.VISIBLE);
				isClick1=false;
			}else {
				imageView1.setBackgroundDrawable(getResources().getDrawable(R.drawable.arrow_up));
				textView1.setVisibility(View.GONE);
				isClick1=true;
			}
			break;
		case R.id.ad_jiance_rl3:
			if (isClick2) {
				imageView2.setBackgroundDrawable(getResources().getDrawable(R.drawable.arrow_bottom_transparent));
				listView2.setVisibility(View.VISIBLE);
				isClick2=false;
			}else {
				imageView2.setBackgroundDrawable(getResources().getDrawable(R.drawable.arrow_up));
				listView2.setVisibility(View.GONE);
				isClick2=true;
			}
			break;


		}
	}



}
