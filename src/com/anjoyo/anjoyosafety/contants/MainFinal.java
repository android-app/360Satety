package com.anjoyo.anjoyosafety.contants;

import java.util.ArrayList;
import java.util.List;

import com.anjoyo.anjoyosafety.bean.ViewPagerBean1;
import com.anjoyo.anjoyosafety.bean.ViewPagerBean2;
import com.anjoyo.anjoyosatety.activity.R;

public class MainFinal {
public static List<ViewPagerBean1> list1;
public static List<ViewPagerBean2> list2;
	// ������������ ��ͼƬԴ main1
	public static int[] mImageIds1 = { R.drawable.disk_sysclear_alert,// �ֻ�����
			R.drawable.disk_phone_block,// ɧ������
			R.drawable.disk_phone_exam_default,// �ֻ����
			R.drawable.disk_privacy_protection,// ��˽�ռ�
			R.drawable.disk_anti_scan,// �ֻ�ɱ��
			R.drawable.disk_power_manager,// �ڵ����
			R.drawable.disk_program_manager,// �ֻ��ܼ�
			R.drawable.disk_active_defense,// ��˽��Ϊ���

	};
	// ������������ ��ͼƬԴ main2
	public static int[] mImageIds2 = { R.drawable.disk_flow_traffic,// �������
			R.drawable.disk_secure_bak,// ͨѶ¼����
			R.drawable.disk_phone_anti_open,// �ֻ�����
			R.drawable.disk_app_market,// ��ȫ�г�
			R.drawable.disk_advertise_block,// ����������
			R.drawable.qr_code,// ��ȫ��ά��
			R.drawable.disk_use_tools,// Ӧ�ù���
	};
	// ������1GridViewItem��
	public static String[] itemName1 = { "�ֻ�����", "ɧ������", "�ֻ����", "��˽�ռ�",
			"�ֻ�ɱ��", "�ڵ����", "�ֻ��ܼ�", "��˽��Ϊ���", };
	// ������2GridViewItem��
	public static String[] itemName2 = { "�������", "ͨѶ¼����", "�ֻ�����", "��ȫ�г�",
			"����������", "��ȫ��ά��", "Ӧ�ù���" };



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
