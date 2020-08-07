package com.example.study;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ToDo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        final EditText et = findViewById(R.id.et);
        TextView task = findViewById(R.id.tvtask);
        Button add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = et.getText().toString();
                Intent result = new Intent();
                result.putExtra("result",message);
                setResult(Activity.RESULT_OK,result);
                finish();
            }
        });
    }
}