package com.anjoyo.anjoyosafety.util;

import java.text.DecimalFormat;

public class TrafficDataUtil {

	public static String  getTrafficData(long total){
		
		String dataText = "0";
		DecimalFormat df = new DecimalFormat("###.00");
		if(total == -1){
			return dataText;
		}else if(total < 10240) {
			dataText = "<10K";
			
		}else if(total < 1024 * 1024){
			dataText = df.format(total / 1024f) + "KB";
		}else if(total < 1024*1024*1024){
			dataText = df.format(total / 1024f /1024f) + "MB";
		}else if(total < 1024*1024*1024*1024){
			dataText = df.format(total / 1024f /1024f/1024f) + "GB";
		}
		return dataText;
	}
	public static String  getMemoryData(long total){
		
		String dataText = "0";
		DecimalFormat df = new DecimalFormat("###.00");
		if(total == -1){
			return dataText;
		}else if(total < 1024) {
			dataText = "<1MB";
			
		}else if(total < 1024 * 1024){
			dataText = df.format(total / 1024f) + "MB";
		}
		return dataText;
	}
}
