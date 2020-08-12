package com.example.study;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;



import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TodoAdapter.ItemClicked {
    TextView tvTask,tvTask1;
    EditText etTask;
    Button addTask;
    ListFrag listFrag;
    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTask = findViewById(R.id.tvTask);
        tvTask1 = findViewById((R.id.tvTask1));
        etTask = findViewById(R.id.etTask);
        addTask = findViewById(R.id.btnaddtask);
        fragmentManager = this.getSupportFragmentManager();
        listFrag = (ListFrag) fragmentManager.findFragmentById(R.id.listfrag);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etTask.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "please enter task", Toast.LENGTH_SHORT).show();
                    //tvTask.setText(null);
                }
                else {
                    ToDoApplicationClass.tasks.add(new ToDo(etTask.getText().toString().trim()));
                    new Notifications(MainActivity.this,"Success","tasks added successfully",1);
                    tvTask.setText(null);
                    listFrag.notifyDataChanged();
                }
            }
        });
        onItemClicked(0);
    }

    @Override
    public void onItemClicked(int index) {
        Log.d("size",String.valueOf(ToDoApplicationClass.tasks.size()));
        tvTask.setText(ToDoApplicationClass.tasks.get(index).getTask());
    }
}

