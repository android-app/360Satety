package com.anjoyo.anjoyosafety.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.net.TrafficStats;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosafety.util.TrafficDataUtil;
import com.anjoyo.anjoyosatety.activity.R;

public class FlowMonitoring extends MyBaseActivity implements OnClickListener {

	private Button btn_correct, setallflow_ok, setallflow_no;
	private TextView thismonth_used, residue, residue_percent;// 本月已用流量，本月剩余流量,本月剩余流量百分比
	private ProgressBar progressBar;

	protected void controll() {
		btn_correct.setOnClickListener(this);
		// 2g/3g总接收数据大小
		long received = TrafficStats.getMobileRxBytes();
		// 2g/3g总上传数据大小
		long transmitted = TrafficStats.getMobileRxBytes();
		long total = received + transmitted;
		Log.i("s", String.valueOf(total));
		// 通过工具类的方法将流量转换成字符串，加上单位
		String totalStr = TrafficDataUtil.getTrafficData(total);
		thismonth_used.setText(totalStr);
		// 本月总共流量，字节为单位
		long thisMonthAllFlow = 100 * (1024 * 1024);
		residue.setText(TrafficDataUtil
				.getTrafficData(thisMonthAllFlow - total));

		long progress = getProgress(total, thisMonthAllFlow - total);
		progressBar.setProgress((int) progress);

		double s = (float) (thisMonthAllFlow - total)
				/ (float) thisMonthAllFlow;
		String str = String.valueOf((s * 100));
		String[] strarray = str.split("\\.");
		residue_percent.setText(strarray[0] + "%");
	}

	private long getProgress(long usedFlow, long allFlow) {
		long a = (usedFlow);
		long b = (allFlow);
		long c = a / b * 100;
		return c;
	}

	@Override
	protected void findViewById() {
		btn_correct = (Button) findViewById(R.id.flow_correct);
		thismonth_used = (TextView) findViewById(R.id.flow_thismonth_usedflow);
		residue = (TextView) findViewById(R.id.flow_thismmonth_residue);
		progressBar = (ProgressBar) findViewById(R.id.flow_progress);
		residue_percent = (TextView) findViewById(R.id.flow_monitoring_residue);
	}

	@Override
	protected void setContentView() {
		setContentView(R.layout.flow_monitoring);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.flow_correct:
			final Dialog dialog = new Dialog(this);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.show();
			Window window = dialog.getWindow();
			// 设置窗口的内容页面,flow_setting_allflow_dialog文件中定义view内容
			window.setContentView(R.layout.flow_monitoring_dialog);
			setallflow_ok = (Button) window
					.findViewById(R.id.flow_monitoring_dialog_ok);
			setallflow_no = (Button) window
					.findViewById(R.id.flow_monitoring_dialog_no);
			final EditText thismonth_used_et = (EditText) window
					.findViewById(R.id.flow_monitoring_dialog_et);
			setallflow_ok.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					thismonth_used.setText(thismonth_used_et.getText()
							.toString() + "M");
					long thisMonthAllFlow = 100 * (1024 * 1024);
					long used = Long.valueOf(thismonth_used_et.getText()
							.toString()) * 1024 * 1024;
					residue.setText(TrafficDataUtil
							.getTrafficData(thisMonthAllFlow - used));

					double s = (float) (thisMonthAllFlow - used)
							/ (float) thisMonthAllFlow;
					String str = String.valueOf((s * 100));
					String[] strarray = str.split("\\.");
					residue_percent.setText(strarray[0] + "%");

					dialog.dismiss();
				}
			});
			setallflow_no.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();

				}
			});
			break;
		}

	}

	
}
