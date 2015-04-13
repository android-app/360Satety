package com.anjoyo.anjoyosafety.contants;

import java.util.ArrayList;
import java.util.List;

import com.anjoyo.anjoyosafety.bean.ViewPagerBean1;
import com.anjoyo.anjoyosafety.bean.ViewPagerBean2;
import com.anjoyo.anjoyosatety.activity.R;

public class MainFinal {
public static List<ViewPagerBean1> list1;
public static List<ViewPagerBean2> list2;
	// 定义整型数组 即图片源 main1
	public static int[] mImageIds1 = { R.drawable.disk_sysclear_alert,// 手机清理
			R.drawable.disk_phone_block,// 骚扰拦截
			R.drawable.disk_phone_exam_default,// 手机体检
			R.drawable.disk_privacy_protection,// 隐私空间
			R.drawable.disk_anti_scan,// 手机杀毒
			R.drawable.disk_power_manager,// 节电管理
			R.drawable.disk_program_manager,// 手机管家
			R.drawable.disk_active_defense,// 隐私行为监控

	};
	// 定义整型数组 即图片源 main2
	public static int[] mImageIds2 = { R.drawable.disk_flow_traffic,// 流量监控
			R.drawable.disk_secure_bak,// 通讯录备份
			R.drawable.disk_phone_anti_open,// 手机防盗
			R.drawable.disk_app_market,// 安全市场
			R.drawable.disk_advertise_block,// 恶意广告拦截
			R.drawable.qr_code,// 安全二维码
			R.drawable.disk_use_tools,// 应用工具
	};
	// 主界面1GridViewItem名
	public static String[] itemName1 = { "手机清理", "骚扰拦截", "手机体检", "隐私空间",
			"手机杀毒", "节电管理", "手机管家", "隐私行为监控", };
	// 主界面2GridViewItem名
	public static String[] itemName2 = { "流量监控", "通讯录备份", "手机防盗", "安全市场",
			"恶意广告拦截", "安全二维码", "应用工具" };



	public static List<ViewPagerBean1> getbeans1(){
		list1=new ArrayList<ViewPagerBean1>();
		for (int i = 0; i < itemName1.length; i++) {
			ViewPagerBean1 bean=new ViewPagerBean1();
			bean.setName(itemName1[i]);
			bean.setId(mImageIds1[i]);
			list1.add(bean);
		}
		return list1;
		
	}
	public static List<ViewPagerBean2> getbeans2(){
		list2=new ArrayList<ViewPagerBean2>();
		for (int i = 0; i < itemName2.length; i++) {
			ViewPagerBean2 bean=new ViewPagerBean2();
			bean.setName(itemName2[i]);
			bean.setId(mImageIds2[i]);
			list2.add(bean);
		}
		return list2;
		
	}

}
