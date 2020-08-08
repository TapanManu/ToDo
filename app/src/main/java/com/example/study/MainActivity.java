package com.example.study;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;

import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static androidx.constraintlayout.widget.ConstraintSet.PARENT_ID;

public class MainActivity extends AppCompatActivity {
    TextView tv,prev;
    Button b;
    public static ArrayList<String> tasks = new ArrayList<String>();
    ConstraintLayout layout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //tasks.add("hello");
        tv = findViewById(R.id.tv);
        b = findViewById(R.id.button);
        layout = findViewById(R.id.cons);
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
        int position=0;
        Log.d("tasks size", String.valueOf(tasks.size()));
        //Log.d("value",tasks.get(0));
        if(tasks.size()==0){
            tv.setText("Your Tasks appear here");
        }
        else{
            Log.d("value",tasks.get(0));
            tv.setText("");
            prev=tv;
            for(String t:tasks){
                //tv.append(t+"\n");   //appends the content within same text view
                prev = addView(10,t);
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){


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
    private TextView addView(int position,String t){

        ConstraintSet set = new ConstraintSet();
        TextView textView = new TextView(getApplicationContext());
        int textViewId = 100 + position;
        //previousTextViewId = textViewId;
        textView.setId(textViewId);
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(100, 140);
        layoutParams.setMargins(100,100,0,0);
        textView.setLayoutParams(layoutParams);
        if (Build.VERSION.SDK_INT < 23)
        {
            textView.setTextAppearance(getApplicationContext(), R.style.AppTheme);
        }
        else
        {
            textView.setTextAppearance(R.style.AppTheme);
        }
        //textView.setBackgroundColor(backgroundColor);
        textView.setText(t);
        textView.setGravity(Gravity.CENTER);
//markerLayout is the ConstraintLayout
        layout.addView(textView);
        set.clone(layout);
        //set.addToVerticalChain(textView.getId(),previousTextViewId,PARENT_ID);
        //set.connect(textView.getId(), ConstraintSet.TOP,
          //      layout.getId(), ConstraintSet.TOP,18);
        set.connect(textView.getId(), ConstraintSet.BOTTOM,
                prev.getId(), ConstraintSet.TOP,100);
       // set.connect(textView.getId(), ConstraintSet.TOP,
       //         prev.getId(), ConstraintSet.TOP,60);
        set.applyTo(layout);
        return textView;
    }
}