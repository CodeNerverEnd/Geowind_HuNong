package com.geowind.hunong.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import com.geowind.hunong.application.HunongApplication;
import com.geowind.hunong.dao.impl.ExpertReplyDaoImpl;
import com.geowind.hunong.dao.impl.SystemMsgDaoImpl;
import com.geowind.hunong.dao.impl.TaskDaoImpl;
import com.geowind.hunong.entity.ExpertReply;
import com.geowind.hunong.entity.SystemMsg;
import com.geowind.hunong.entity.Task;
import com.geowind.hunong.global.activitys.MainActivity;
import com.geowind.hunong.global.activitys.MsgDetailsActivity;
import com.geowind.hunong.json.ExpertReplyJson;
import com.geowind.hunong.json.SystemMsgJson;
import com.geowind.hunong.json.TaskJson;
import com.geowind.hunong.utils.JpushUtil;
import com.geowind.hunong.utils.NotificationVibrator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * 
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
	private static final String TAG = "JPush";

	@Override
	public void onReceive(Context context, Intent intent) {
		//初始化数据库辅助类
        Bundle bundle = intent.getExtras();

		Log.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
            //send the Registration Id to your server...

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
        	Log.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
        	processCustomMessage(context, bundle);
        
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知");
            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
			if(bundle!=null)
			{
				InsertDataToDb(context,bundle);
			}
        	
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户点击打开了通知");
            
        	//打开自定义的Activity
        	Intent i = new Intent(context, MsgDetailsActivity.class);
			i.putExtras(bundle);
			i.putExtra("msgType",bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE));
			i.putExtra("json",bundle.getString(JPushInterface.EXTRA_EXTRA));
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		//	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
			context.startActivity(i);
        	
        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..
			System.out.println( "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));

        } else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
        	boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
        	Log.w(TAG, "[MyReceiver]" + intent.getAction() +" connected state change to "+connected);
        } else {
        	Log.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
        }
	}

	private void InsertDataToDb(Context context,Bundle bundle) {
		String bundleString=bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
		if(bundleString!=null){
			String jsonString=bundle.getString(JPushInterface.EXTRA_EXTRA);
			System.out.println(jsonString);



			try {
				JSONObject extraJson = new JSONObject(jsonString);
				Object jsonExtra = extraJson.get("jsonExtra");
				switch (bundleString){

					case "任务提醒":
							HunongApplication.NEW_TASK_COUNT++;
							TaskDaoImpl taskDao=new TaskDaoImpl(context);
							Task task=TaskJson.parseJsonObject(jsonExtra.toString());
							String pic=task.getPic();
							String s=pic.replace("\\","");
							task.setPic(s);
//							System.out.println("pic========"+s);
//						    System.out.println(extraJson.toString());
							taskDao.insert(task);
						break;
					case  "专家回复":
						HunongApplication.NEW_EXPERT_REPLY_COUNT++;
						ExpertReplyDaoImpl expertReplyDao=new ExpertReplyDaoImpl(context);
						ExpertReply expertReply=ExpertReplyJson.parseJsonObject(jsonExtra.toString());
						System.out.println(expertReply.toString());
						expertReplyDao.insert(expertReply);
						break;
					case   "系统消息":
						HunongApplication.NEW_SYSTEM_MSG_COUNT++;
						SystemMsgDaoImpl systemMsgDao=new SystemMsgDaoImpl(context);
						SystemMsg systemMsg= SystemMsgJson.parseJsonObject(jsonExtra.toString());
						systemMsgDao.insert(systemMsg);
						break;

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}




		}

	}

	// 打印所有的 intent extra 数据
	private static String printBundle(Bundle bundle) {
		StringBuilder sb = new StringBuilder();
		for (String key : bundle.keySet()) {
			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
				sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
			}else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
				sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
			} else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
				if (bundle.getString(JPushInterface.EXTRA_EXTRA).isEmpty()) {
					Log.i(TAG, "This message has no Extra data");
					continue;
				}

				try {
					JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
					Iterator<String> it =  json.keys();

					while (it.hasNext()) {
						String myKey = it.next().toString();
						sb.append("\nkey:" + key + ", value: [" +
								myKey + " - " +json.optString(myKey) + "]");
					}
				} catch (JSONException e) {
					Log.e(TAG, "Get message extra JSON error!");
				}

			} else {
				sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
			}
		}
		return sb.toString();
	}
	
	//send msg to MainActivity
	private void processCustomMessage(Context context, Bundle bundle) {
		if (MainActivity.isForeground) {
			String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
			String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
			Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
			msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
			if (!JpushUtil.isEmpty(extras)) {
				try {
					JSONObject extraJson = new JSONObject(extras);
					if (null != extraJson && extraJson.length() > 0) {
						msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
					}
				} catch (JSONException e) {

				}

			}
			context.sendBroadcast(msgIntent);
		}
	}
}
