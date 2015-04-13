package com.anjoyo.anjoyosafety.util;

import android.app.Activity;

import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;

public class WeiBoUtil {
	public static String AppKey;
	public static void shareText(Activity activity,String AppKey) {
		IWeiboShareAPI shareAPI=WeiboShareSDK.createWeiboAPI(activity, AppKey,true);
		shareAPI.registerApp();
		WeiboMultiMessage message=new WeiboMultiMessage();
		message.textObject=getTextObj();
		SendMultiMessageToWeiboRequest request=new SendMultiMessageToWeiboRequest();
		request.transaction=String.valueOf(System.currentTimeMillis());
		request.multiMessage=message;
		shareAPI.sendRequest(request);
	}
	public static TextObject getTextObj() {
		TextObject textObject = new TextObject();
		textObject.text ="#360手机卫士恶意广告拦截#总是被通知栏广告骚扰？担心恶意广告会泄漏隐私？我用360手机卫士轻松拦截恶意广告，拒绝骚扰、保护隐私！你也试试吧！";
		return textObject;
	}
}
