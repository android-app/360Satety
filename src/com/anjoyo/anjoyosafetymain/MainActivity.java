package com.anjoyo.anjoyosafetymain;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anjoyo.anjoyosafety.activity.Battery;
import com.anjoyo.anjoyosafety.activity.CrankSmsAndCall;
import com.anjoyo.anjoyosafety.adapter.MainGridViewAdapter1;
import com.anjoyo.anjoyosafety.adapter.MainGridViewAdapter2;
import com.anjoyo.anjoyosafety.adapter.MyViewPage;
import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosafety.custom.DragGrid1;
import com.anjoyo.anjoyosafety.custom.DragGrid2;
import com.anjoyo.anjoyosatety.activity.R;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.SlidingMenu.OnCloseListener;

public class MainActivity extends MyBaseActivity implements
		OnItemClickListener, OnClickListener, OnPageChangeListener {
	private ViewPager viewpager;
	MyViewPage viewPagerAdt;
	private View view1;
	private View view2;
	private List<View> listvp;
	private DragGrid1 gridview1;
	private DragGrid2 gridview2;
	private MainGridViewAdapter1 gridviewAdt1;
	private MainGridViewAdapter2 gridviewAdt2;
	private TextView tv;
	private ImageView act_main_option, act_main_pagenum;
	private SlidingMenu slidingMenu;
	private View act_main_zhedang;
	private boolean isFrist = true;

	@Override
	protected void setContentView() {
		setContentView(R.layout.main);
	}

	@Override
	protected void findViewById() {
		viewpager = (ViewPager) findViewById(R.id.act_main_viewpager);
		act_main_option = (ImageView) findViewById(R.id.act_main_option);
		act_main_pagenum = (ImageView) findViewById(R.id.act_main_pagenum);
		act_main_pagenum.setOnClickListener(this);
		act_main_zhedang = (View) findViewById(R.id.act_main_zhedang);
	}

	@Override
	protected void controll() {
		view1 = LayoutInflater.from(this).inflate(R.layout.first_main_gridview,
				null);
		view2 = LayoutInflater.from(this).inflate(
				R.layout.second_main_gridview, null);
		gridview1 = (DragGrid1) view1.findViewById(R.id.first_main_gridview);
		gridview2 = (DragGrid2) view2.findViewById(R.id.second_main_gridview);
		listvp = new ArrayList<View>();

		gridviewAdt1 = new MainGridViewAdapter1(this);
		gridviewAdt2 = new MainGridViewAdapter2(this);
		gridview1.setAdapter(gridviewAdt1);
		gridview2.setAdapter(gridviewAdt2);

		listvp.add(view1);
		listvp.add(view2);
		viewPagerAdt = new MyViewPage(listvp);
		viewpager.setAdapter(viewPagerAdt);

		// 为GridView设置上item点击事件
		gridview1.setOnItemClickListener(this);
		gridview2.setOnItemClickListener(this);
		initSlidingMenu();
		act_main_option.setOnClickListener(this);
		viewpager.setOnPageChangeListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		tv = (TextView) arg1.findViewById(R.id.main_gridview_item_name);
		String text = tv.getText().toString();
		final Intent intent = new Intent();
		if (text.equals("手机清理")) {
			intent.setClass(MainActivity.this,
					com.anjoyo.anjoyosafety.activity.ClearAct.class);
			startActivity(intent);
		} else if (text.equals("流量监控")) {
			intent.setClass(MainActivity.this,
					com.anjoyo.anjoyosafety.activity.FlowMainAct.class);
			startActivity(intent);
		} else if (text.equals("骚扰拦截")) {
			intent.setClass(MainActivity.this, CrankSmsAndCall.class);
			startActivity(intent);
		} else if (text.equals("手机体检")) {
			Toast.makeText(this, "手机体检", 0).show();
		} else if (text.equals("隐私行为监控")) {
			Toast.makeText(this, "隐私行为监控", 0).show();
		} else if (text.equals("通讯录备份")) {
			Toast.makeText(this, "通讯录备份", 0).show();
		} else if (text.equals("手机防盗")) {
			Toast.makeText(this, "手机防盗", 0).show();
		} else if (text.equals("安全市场")) {
			Toast.makeText(this, "安全市场", 0).show();
		} else if (text.equals("应用工具")) {
			Toast.makeText(this, "应用工具", 0).show();
		} else if (text.equals("隐私空间")) {
			intent.setClass(
					this,
					com.anjoyo.anjoyosafety.activity.PersonalSpaceSettingPwdActivity.class);
			startActivity(intent);
		} else if (text.equals("手机杀毒")) {
			Toast.makeText(this, "手机杀毒", 0).show();
		} else if (text.equals("节电管理")) {
			intent.setClass(MainActivity.this, Battery.class);
			startActivity(intent);
		} else if (text.equals("手机管家")) {
			Toast.makeText(this, "手机管家", 0).show();
		} else if (text.equals("安全二维码")) {
			intent.setClass(MainActivity.this,
					com.anjoyo.anjoyosafety.activity.CodeMainActivity.class);
			startActivity(intent);
		} else if (text.equals("恶意广告拦截")) {
			intent.setClass(MainActivity.this,
					com.anjoyo.anjoyosafety.activity.FindActivity.class);
			startActivity(intent);
		}

	}

	// 初始化SlidingMenu
	private void initSlidingMenu() {
		slidingMenu = new SlidingMenu(this);
		slidingMenu.setMode(SlidingMenu.RIGHT);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		slidingMenu.setBehindWidth(120);
		slidingMenu.setFadeDegree(0.35f);
		slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		slidingMenu.setMenu(R.layout.main_slidingmenu);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		// 点击弹出slidingmune菜单
		case R.id.act_main_option:
			slidingMenu.showMenu();
			act_main_zhedang.setVisibility(View.VISIBLE);
			act_main_option.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.main_menu_negative_normal));

			slidingMenu.setOnCloseListener(new OnCloseListener() {
				@Override
				public void onClose() {
					act_main_zhedang.setVisibility(View.GONE);
					// TODO Auto-generated method stub
					act_main_option.setBackgroundDrawable(getResources()
							.getDrawable(R.drawable.main_menu_normal));

				}
			});

			break;
		case R.id.act_main_pagenum:
			if (viewpager.getCurrentItem() == 0) {
				viewpager.setCurrentItem(1);
			} else {
				viewpager.setCurrentItem(0);
			}
			break;
		}
	}

	private void setpage() {
		Animation animation = AnimationUtils
				.loadAnimation(this, R.anim.page_in);
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
				int i = viewpager.getCurrentItem();
				if (i == 0) {
					act_main_pagenum
							.setBackgroundResource(R.drawable.main_page_one);
				} else {
					act_main_pagenum
							.setBackgroundResource(R.drawable.main_page_two);
				}
				Animation animation2 = AnimationUtils.loadAnimation(
						MainActivity.this, R.anim.page_to);
				act_main_pagenum.startAnimation(animation2);

			}
		});
		act_main_pagenum.startAnimation(animation);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		setpage();

	}

}