package com.luckynineapps.indoleasing.config.message;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.luckynineapps.indoleasing.R;

import java.util.Random;

/**
 * Created by Hakim on 18/07/2019.
 */
public class MessangingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        sendNotification("1", "Indo Leasing", "Test Notif");
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
    }

    private void sendNotification(String notifId, String title, String message){

        int requestCode = (int) (Math.random() * 1000);
        String channelId = "Message";
        Intent intent = null;

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Notification.Builder notificationBuilder = new Notification.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setAutoCancel(true)
                .setSound(alarmSound)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);

        if(intent != null){
            // Create the TaskStackBuilder and add the intent, which inflates the back stack
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addNextIntentWithParentStack(intent);

            notificationBuilder.setContentIntent(stackBuilder.getPendingIntent(requestCode, PendingIntent.FLAG_UPDATE_CURRENT));
        }


        if (!TextUtils.isEmpty(message)) {
            String[] bodyTexts = message.split("\n");
            if (bodyTexts.length <= 1) {
                notificationBuilder.setContentText(message);
            } else {
                Notification.InboxStyle style = new Notification.InboxStyle();

                for (String bodyText : bodyTexts) {
                    style.addLine(bodyText);
                }

                notificationBuilder.setStyle(style);
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(channelId,
                    "Indo Leasing",
                    NotificationManager.IMPORTANCE_DEFAULT);
            mChannel.setDescription(message);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.WHITE);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[] {100, 300, 200, 300});

            notificationManager.createNotificationChannel(mChannel);

            notificationBuilder.setChannelId(channelId);
        }

        Random r = new Random();
        int i = r.nextInt(80 - 65) + 65;
        notificationManager.notify(i, notificationBuilder.build());
    }
}
