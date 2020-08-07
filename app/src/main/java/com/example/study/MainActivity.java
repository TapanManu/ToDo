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
    Notifications n;

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
        switch(item.getItemId()){
            case R.id.first:
                tv.setText("first");
                layout.setBackgroundColor(Color.GREEN);
                new Notifications(this,"BACKGROUND CHANGED","New Color is GREEN",0);
                break;
            case R.id.second:
                tv.setText("second");
                layout.setBackgroundColor(Color.BLUE);
                new Notifications(this,"BACKGROUND CHANGED","New Color is BLUE",1);
                break;
            case R.id.third:
                tv.setText("third");
                layout.setBackgroundColor(Color.RED);
                new Notifications(this,"BACKGROUND CHANGED","New Color is RED",2);
                break;
            default:
                tv.setText("Just for study!!");
                layout.setBackgroundColor(Color.WHITE);
        }
        return true;
    }

}