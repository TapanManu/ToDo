package com.example.study;

import android.app.Application;

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
}
