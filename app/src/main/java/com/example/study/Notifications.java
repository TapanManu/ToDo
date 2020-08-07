package com.example.study;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.example.study.MainActivity;

public class Notifications extends Activity {
    public void addNotification(String title,String text){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(Notifications.this)
                                                    .setContentTitle(title)
                                                    .setContentText(text);

        Intent notification = new Intent(Notifications.this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(Notifications.this);
        stackBuilder.addParentStack(MainActivity.class);

        stackBuilder.addNextIntent(notification);

        //TaskStackBuilder stackBuilder;
        PendingIntent resultpendingintent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultpendingintent);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        /* notificationID allows you to update the notification later on. */
        mNotificationManager.notify(0,builder.build());

    }
}
