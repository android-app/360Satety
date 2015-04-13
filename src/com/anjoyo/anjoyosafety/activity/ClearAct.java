package com.anjoyo.anjoyosafety.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosafety.util.WeiBoUtil;
import com.anjoyo.anjoyosafetymain.MainActivity;
import com.anjoyo.anjoyosatety.activity.R;

public class ClearAct extends MyBaseActivity implements OnClickListener {
	public static final String APP_KEY = "1201533495";
	private RelativeLayout rl_memory, rl_autostart, rl_garbage, rl_privacy,
			rl_packge;

	private Button btn_timing,btn_return,btn_clear,clear_sucess,share;
	private ImageView smallman,garbage1,garbage2,iv_return;
	private Animation animation;
	private LinearLayout clear_main_tv,clear_main_tv2;
	private RelativeLayout rl_sucesss,rl_clearing;
	
	@Override
	protected void controll() {
		rl_memory.setOnClickListener(this);
		rl_autostart.setOnClickListener(this);
		rl_garbage.setOnClickListener(this);
		rl_privacy.setOnClickListener(this);
		rl_packge.setOnClickListener(this);
		btn_timing.setOnClickListener(this);
		btn_return.setOnClickListener(this);
		btn_clear.setOnClickListener(this);
		iv_return.setOnClickListener(this);
		clear_sucess.setOnClickListener(this);
		share.setOnClickListener(this);
	}

	@Override
	protected void findViewById() {
		rl_memory = (RelativeLayout) findViewById(R.id.clear_main_ll_memory);
		rl_autostart = (RelativeLayout) findViewById(R.id.clear_main_ll_autostart);
		rl_garbage = (RelativeLayout) findViewById(R.id.clear_main_ll_garbage);
		rl_privacy=(RelativeLayout) findViewById(R.id.clear_main_ll_privacy);
		rl_packge=(RelativeLayout) findViewById(R.id.clear_main_ll_package);
		btn_timing=(Button) findViewById(R.id.clear_main_timing);
		btn_return=(Button) findViewById(R.id.clear_main_back);
		btn_clear=(Button) findViewById(R.id.clear_main_bigclear);
		smallman=(ImageView) findViewById(R.id.clear_main_clearman);
		garbage1=(ImageView) findViewById(R.id.clear_main_garbage1);
		garbage2=(ImageView) findViewById(R.id.clear_main_garbage2);
		clear_main_tv=(LinearLayout) findViewById(R.id.clear_main_tv);
		clear_main_tv2=(LinearLayout) findViewById(R.id.clear_main_tv2);
		rl_sucesss=(RelativeLayout) findViewById(R.id.clear_success);
		clear_sucess=(Button) findViewById(R.id.clear_success_btn_success);
		share=(Button) findViewById(R.id.clear_success_btn_share);
		rl_clearing=(RelativeLayout) findViewById(R.id.clear_main_clearing);
		iv_return=(ImageView) findViewById(R.id.clear_main_return2);
	}

	@Override
	protected void setContentView() {
		setContentView(R.layout.clear_main);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.clear_main_ll_memory:
			Intent intent = new Intent(this, ClearMemory.class);
			startActivity(intent);
			break;
		case R.id.clear_main_ll_autostart:
			Intent intent2 = new Intent(this, Clear_AutostartAct.class);
			startActivity(intent2);
			break;
		case R.id.clear_main_ll_garbage:
			Intent intent3 = new Intent(this, Clear_Garbage.class);
			startActivity(intent3);
			break;
		case R.id.clear_main_ll_privacy:
			Intent intent4 = new Intent(this, Clear_Privacy.class);
			startActivity(intent4);
			break;
		case R.id.clear_main_ll_package:
			Intent intent5 = new Intent(this, Clear_Packge.class);
			startActivity(intent5);
			break;
		case R.id.clear_main_timing:
			Intent intent6 = new Intent(this, Clear_Timing.class);
			startActivity(intent6);
			break;
		case R.id.clear_main_back:
			finish();
			break;
		case R.id.clear_main_bigclear:
			clear_main_tv.setVisibility(View.GONE);		
			btn_clear.setVisibility(View.GONE);
			rl_clearing.setVisibility(View.VISIBLE);
			garbageShowAnim();
			showAnim();
			break;
		case R.id.clear_main_return2:
			finish();			
			break;
		case R.id.clear_success_btn_success:
			finish();
			break;
		case R.id.clear_success_btn_share:
			WeiBoUtil.shareText(this, APP_KEY);
			break;
		}

	}

	private void showAnim(){
		animation=AnimationUtils.loadAnimation(this, R.anim.clear);
		smallman.startAnimation(animation);
		animation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				smallman.setImageResource(R.drawable.float_window_anzai_clean_finish);
				clear_main_tv2.setVisibility(View.VISIBLE);
				rl_sucesss.setVisibility(View.VISIBLE);
				rl_clearing.setVisibility(View.GONE);
				garbage1.setVisibility(View.GONE);	
				garbage2.setVisibility(View.GONE);
				
			}
		});
		
		
	}
	private void garbageShowAnim(){
		animation=AnimationUtils.loadAnimation(this, R.anim.garbage_anim);
		garbage2.startAnimation(animation);
		garbage1.startAnimation(animation);
	}
	
}
