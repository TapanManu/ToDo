package com.example.study;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "CHANNEL_ID";
    private static final String TAG = "main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        TextView tv = findViewById(R.id.tv);
        ConstraintLayout layout = findViewById(R.id.cons);
        Notifications n = new Notifications();
        switch(item.getItemId()){
            case R.id.first:
                tv.setText("first");
                layout.setBackgroundColor(Color.GREEN);
                addNotification("BACKGROUND CHANGED","New Color is GREEN",0);
                break;
            case R.id.second:
                tv.setText("second");
                layout.setBackgroundColor(Color.BLUE);
                addNotification("BACKGROUND CHANGED","New Color is BLUE",1);
                break;
            case R.id.third:
                tv.setText("third");
                layout.setBackgroundColor(Color.RED);
                addNotification("BACKGROUND CHANGED","New Color is RED",2);
                break;
            default:
                tv.setText("Just for study!!");
                layout.setBackgroundColor(Color.WHITE);
        }
        return true;
    }
    public void addNotification(String title,String text,int notificationid){
        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent notification = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);

        stackBuilder.addNextIntent(notification);

        //TaskStackBuilder stackBuilder;
        PendingIntent resultpendingintent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultpendingintent);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        /* notificationID allows you to update the notification later on. */
        mNotificationManager.notify(notificationid,builder.build());

    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}