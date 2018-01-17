package com.lottery.jilinkuai3.jpush;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.lottery.jilinkuai3.activity.WebMainActivity;
import com.lottery.shishicaikaijiang.R;
import com.lottery.library.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

 /*       *自定义接收器
        *
        *如果不定义这个 Receiver，则：
        *1)默认用户会打开主界面
        *2)接收不到自定义消息*/


public class JpushReceiver extends BroadcastReceiver {
    private static final String TAG = "JPush";
    private static final int ID_NOTICE = 0x001;
    private Context mContext;//
//265347331bf8417dbad0e74e419384


    @Override
    public void onReceive(Context context, Intent intent) {


        ////////////////////////////////////////////////
        mContext = context;
        String type = null;
        String jsonDatas = null;
        Bundle bundle = intent.getExtras();
        jsonDatas = bundle.getString(JPushInterface.EXTRA_EXTRA);

        String dataJson = null;

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            LogUtils.e("[JpushReceiver] 接收Registration Id : " + regId);//1104a89792936ff9edc
            //send the Registration Id to your server...
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            LogUtils.e("[JpushReceiver] type type : " + type);

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {


        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            LogUtils.e("[JpushReceiver] 用户点击打开了通知");
            Intent intentMain = new Intent(context, WebMainActivity.class);
            intentMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentMain);

        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            LogUtils.e("[JpushReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

            processCustomMessage();


        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            LogUtils.e("[JpushReceiver]" + intent.getAction() + " connected state change to " + connected);
        } else {
            LogUtils.e("[JpushReceiver] Unhandled intent - " + intent.getAction());
        }


    }


    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (bundle.getString(JPushInterface.EXTRA_EXTRA).isEmpty()) {
                    LogUtils.e("This message has no Extra data");
                    continue;
                }

                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next().toString();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    LogUtils.e("Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }


    private void processCustomMessage() {

    }


    private void receivingNotification(Context context, Bundle bundle) {
        NotificationManager manager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        // 使用notification
        // 使用广播或者通知进行内容的显示
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                context);
        builder.setContentText(message).setSmallIcon(R.mipmap.ic_launcher).setContentTitle(JPushInterface.EXTRA_TITLE);
        builder.setDefaults(Notification.DEFAULT_SOUND);
        manager.notify(1, builder.build());
    }

    private void notice(Context context, Intent intent) {

        builderNotification(context, intent);
    }

    private void builderNotificationWithIcon(Context context, Intent intent,
                                             Bitmap bitmap) {
        PendingIntent contentIntent = PendingIntent.getActivity(context, ID_NOTICE, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher).setLargeIcon(bitmap)
                .setContentTitle("您有一条未读消息")
//                .setContentText(Html.fromHtml(content))
//                .setWhen(System.currentTimeMillis()).setTicker(content)
                .setAutoCancel(true).setContentIntent(contentIntent);
        Notification notification = builder.getNotification();
        notification.defaults = Notification.DEFAULT_SOUND;
        nm.notify(ID_NOTICE, notification);
    }

    private void builderNotification(Context context, Intent intent) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        builderNotificationWithIcon(context, intent, bitmap);
    }


}
