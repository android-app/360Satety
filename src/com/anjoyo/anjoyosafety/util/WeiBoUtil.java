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
		textObject.text ="#360�ֻ���ʿ����������#���Ǳ�֪ͨ�����ɧ�ţ����Ķ������й©��˽������360�ֻ���ʿ�������ض����棬�ܾ�ɧ�š�������˽����Ҳ���԰ɣ�";
		return textObject;
	}
}
