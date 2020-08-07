package com.example.study;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Button b;
    public static ArrayList<String> tasks = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //tasks.add("hello");
        tv = findViewById(R.id.tv);
        b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ToDo.class);
                startActivityForResult(intent,10);
            }
        });

    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d("tasks size", String.valueOf(tasks.size()));
        dispTask();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK){
            Log.d("success","Result received");
            assert data != null;
            String result = data.getStringExtra("result");
            assert result != null;
            //Log.d("result",result);
            tasks.add(result);
            //Log.d("value",tasks.get(0));
        }
    }


    public void dispTask(){
        Log.d("tasks size", String.valueOf(tasks.size()));
        //Log.d("value",tasks.get(0));
        if(tasks.size()==0){
            tv.setText("Your Tasks appear here");
        }
        else{
            Log.d("value",tasks.get(0));
            for(String t:tasks){
                tv.setText(t+"\n");
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){

        ConstraintLayout layout = findViewById(R.id.cons);
        switch(item.getItemId()){
            case R.id.first:
                layout.setBackgroundColor(Color.GREEN);
                new Notifications(this,"BACKGROUND CHANGED","New Color is GREEN",0);
                break;
            case R.id.second:
                layout.setBackgroundColor(Color.BLUE);
                new Notifications(this,"BACKGROUND CHANGED","New Color is BLUE",1);
                break;
            case R.id.third:
                layout.setBackgroundColor(Color.RED);
                new Notifications(this,"BACKGROUND CHANGED","New Color is RED",2);
                break;
            default:

                layout.setBackgroundColor(Color.WHITE);
        }
        return true;
    }

}