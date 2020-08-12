package com.example.study;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;

public class ToDoApplicationClass extends Application {

    public static ArrayList<ToDo> tasks;
    @Override
    public void onCreate() {
        super.onCreate();
        tasks = new ArrayList<ToDo>();
        tasks.add(new ToDo("Coding"));
        tasks.add(new ToDo("Interview Preparation"));
        tasks.add(new ToDo("Android development"));
        tasks.add(new ToDo("ML/DL"));

    }
    public static int search(String value){
        int i=0;
        if(value==null){
            return -1;
        }
        for(i=0;i<tasks.size();i++){
            Log.d("value",tasks.get(i).getTask());
            if(tasks.get(i).getTask().equals(value))
                return i;

        }
        return -1;
    }
}
