package com.anjoyo.anjoyosafety.activity;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anjoyo.anjoyosafety.base.MyBaseActivity;
import com.anjoyo.anjoyosatety.activity.R;

public class FlowSetting extends MyBaseActivity implements OnClickListener{

	private RelativeLayout rl_allflow;
	private Button setallflow_ok,setallflow_no;
	TextView tv_allFlow;
	EditText et_allFlow;
	@Override
	protected void controll() {
		rl_allflow.setOnClickListener(this);
	}

	@Override
	protected void findViewById() {
		rl_allflow=(RelativeLayout) findViewById(R.id.flow_setting_rl_allf);
		
		tv_allFlow=(TextView) findViewById(R.id.flow_setting_tv_allflow);
		
	}

	@Override
	protected void setContentView() {
		setContentView(R.layout.flow_setting);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.flow_setting_rl_allf:
			final Dialog dialog = new Dialog(this);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			//dialog.setContentView(R.layout.flow_setting_allflow_dialog);
			dialog.show();
			Window window = dialog.getWindow();
	        // 设置窗口的内容页面,flow_setting_allflow_dialog文件中定义view内容
	        window.setContentView(R.layout.flow_setting_allflow_dialog);	
	        et_allFlow=(EditText) window.findViewById(R.id.flow_setting_et_allflow);
	        //弹出输入法软键盘
	        showSoftKeyboard(et_allFlow,FlowSetting.this);
	        et_allFlow.setFocusable(true);

	        setallflow_ok=(Button)window.findViewById(R.id.flow_setting_allflow_ok);
			setallflow_no=(Button)window.findViewById(R.id.flow_setting_allflow_no);
	        setallflow_ok.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					tv_allFlow.setText(et_allFlow.getText());	
					dialog.dismiss();
					
					//关闭输入法软键盘
					closeKeyboard(et_allFlow,FlowSetting.this);
				}
			});
	        setallflow_no.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					closeKeyboard(et_allFlow,FlowSetting.this);
					
				}
			});
			break;
		}
		
	}

	public static void showSoftKeyboard(EditText et,Context context){
		InputMethodManager imm=(InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(et,InputMethodManager.RESULT_SHOWN);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);
	}

	
	public static void closeKeyboard(EditText et,Context context) {  
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);  
		imm.hideSoftInputFromWindow(et.getWindowToken(), 0);  
	} 
}
