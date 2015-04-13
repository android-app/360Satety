package com.anjoyo.anjoyosafety.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosatety.activity.R;

public class Battery extends MyBaseActivity implements OnClickListener {

	BroadcastReceiver broadcast;
	IntentFilter intentfilter;
	Button bt;
	FrameLayout anim;
	int level;
	int scale;
	ImageView fristnum;
	ImageView secondnum;
	ImageView thirdnum;
	ImageView fourthnum;

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.battery);
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		bt = (Button) findViewById(R.id.battery_return);
		bt.setOnClickListener(this);
		anim = (FrameLayout) findViewById(R.id.battery_anim);
		fristnum = (ImageView) findViewById(R.id.battery_fristnum);
		secondnum = (ImageView) findViewById(R.id.battery_secondnum);
		thirdnum = (ImageView) findViewById(R.id.battery_thirdnum);
		fourthnum = (ImageView) findViewById(R.id.battery_fourthnum);
	}

	@Override
	protected void controll() {
		getbatterylevel();
	}

	// 得到当前电量
	public void getbatterylevel() {
		broadcast = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
					level = intent.getIntExtra("level", 0);
					scale = intent.getIntExtra("scale", 100);
					// 动画
					setbatteryanmi();

				}
			}
		};
		registerReceiver(broadcast, new IntentFilter(
				Intent.ACTION_BATTERY_CHANGED));
	}

	// 电池动画
	void setbatteryanmi() {
		float f = (float) ((level * 100) / scale) / 100;
		Animation animation = new ScaleAnimation(1.0f, 1.0f, 1.0f, f, 0, 210);
		animation.setDuration(1000);
		animation.setFillAfter(true);
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
				settime();

			}
		});
		anim.startAnimation(animation);

	}

	@Override
	public void onClick(View v) {
		finish();

	}

	void settime() {
		// 预算估计 每分钟耗电0.09电量算出还能用的时间
		float minutetime = (float) (level / 0.10);
		// 估算能用的小时
		float hourtime = (float) (minutetime / 60);
		String Stringhour = hourtime + "";
		String[] array = new String[10];
		array = Stringhour.split("\\.");
		String xuanze = array[0];
		// 判断拆分的第一个数组内元素个数是不是1，是着在前面加上0
		if (xuanze.length() == 1) {
			String xuanze2 = new String();
			xuanze2 = "0" + xuanze;
			xuanze = xuanze2;
		}
		// 得到要设置的1个数
		String frist = String.valueOf(xuanze.charAt(0));

		// 得到要设置的2个数
		String second = String.valueOf(xuanze.charAt(1));

		// 将小数点后面的部分前加上0.
		String qq = new String("0." + array[1]);

		int qwe = (int) (Float.valueOf(qq) * 60);
		String arrat1 = String.valueOf(qwe);
		//判断是否只有一个数，是一个数在前面加上一个"0"
		if (arrat1.length() == 1) {
			String xuanze3 = new String();
			xuanze3 = "0" + xuanze;
			arrat1 = xuanze3;
		}
		// 得到要设置的3个数
		String thrid = String.valueOf(arrat1.charAt(0));

		// 得到要设置的4个数
		String fourth = String.valueOf(arrat1.charAt(1));

		// 将四个数放在数组里面
		String[] stringarr = new String[4];
		stringarr[0] = frist;
		stringarr[1] = second;
		stringarr[2] = thrid;
		stringarr[3] = fourth;
		// 设置图片
		for (int i = 0; i < stringarr.length; i++) {
			if (stringarr[i].equals("0")) {

				if (i == 0) {
					fristnum.setBackgroundResource(R.drawable.number_0);
				} else if (i == 1) {
					secondnum.setBackgroundResource(R.drawable.number_0);
				} else if (i == 2) {
					thirdnum.setBackgroundResource(R.drawable.number_0);
				} else {
					fourthnum.setBackgroundResource(R.drawable.number_0);
				}

			} else if (stringarr[i].equals("1")) {

				if (i == 0) {
					fristnum.setBackgroundResource(R.drawable.number_1);
				} else if (i == 1) {
					secondnum.setBackgroundResource(R.drawable.number_1);
				} else if (i == 2) {
					thirdnum.setBackgroundResource(R.drawable.number_1);
				} else {
					fourthnum.setBackgroundResource(R.drawable.number_1);
				}

			} else if (stringarr[i].equals("2")) {

				if (i == 0) {
					fristnum.setBackgroundResource(R.drawable.number_2);
				} else if (i == 1) {
					secondnum.setBackgroundResource(R.drawable.number_2);
				} else if (i == 2) {
					thirdnum.setBackgroundResource(R.drawable.number_2);
				} else {
					fourthnum.setBackgroundResource(R.drawable.number_2);
				}

			} else if (stringarr[i].equals("3")) {

				if (i == 0) {
					fristnum.setBackgroundResource(R.drawable.number_3);
				} else if (i == 1) {
					secondnum.setBackgroundResource(R.drawable.number_3);
				} else if (i == 2) {
					thirdnum.setBackgroundResource(R.drawable.number_3);
				} else {
					fourthnum.setBackgroundResource(R.drawable.number_3);
				}

			} else if (stringarr[i].equals("4")) {

				if (i == 0) {
					fristnum.setBackgroundResource(R.drawable.number_4);
				} else if (i == 1) {
					secondnum.setBackgroundResource(R.drawable.number_4);
				} else if (i == 2) {
					thirdnum.setBackgroundResource(R.drawable.number_4);
				} else {
					fourthnum.setBackgroundResource(R.drawable.number_4);
				}

			} else if (stringarr[i].equals("5")) {

				if (i == 0) {
					fristnum.setBackgroundResource(R.drawable.number_5);
				} else if (i == 1) {
					secondnum.setBackgroundResource(R.drawable.number_5);
				} else if (i == 2) {
					thirdnum.setBackgroundResource(R.drawable.number_5);
				} else {
					fourthnum.setBackgroundResource(R.drawable.number_5);
				}

			} else if (stringarr[i].equals("6")) {

				if (i == 0) {
					fristnum.setBackgroundResource(R.drawable.number_6);
				} else if (i == 1) {
					secondnum.setBackgroundResource(R.drawable.number_6);
				} else if (i == 2) {
					thirdnum.setBackgroundResource(R.drawable.number_6);
				} else {
					fourthnum.setBackgroundResource(R.drawable.number_6);
				}

			} else if (stringarr[i].equals("7")) {
				if (i == 0) {
					fristnum.setBackgroundResource(R.drawable.number_7);
				} else if (i == 1) {
					secondnum.setBackgroundResource(R.drawable.number_7);
				} else if (i == 2) {
					thirdnum.setBackgroundResource(R.drawable.number_7);
				} else {
					fourthnum.setBackgroundResource(R.drawable.number_7);
				}

			} else if (stringarr[i].equals("8")) {
				if (i == 0) {
					fristnum.setBackgroundResource(R.drawable.number_8);
				} else if (i == 1) {
					secondnum.setBackgroundResource(R.drawable.number_8);
				} else if (i == 2) {
					thirdnum.setBackgroundResource(R.drawable.number_8);
				} else {
					fourthnum.setBackgroundResource(R.drawable.number_8);
				}

			} else if (stringarr[i].equals("9")) {
				if (i == 0) {
					fristnum.setBackgroundResource(R.drawable.number_9);
				} else if (i == 1) {
					secondnum.setBackgroundResource(R.drawable.number_9);
				} else if (i == 2) {
					thirdnum.setBackgroundResource(R.drawable.number_9);
				} else {
					fourthnum.setBackgroundResource(R.drawable.number_9);
				}

			}

		}
	}

	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(broadcast);
	}
}
