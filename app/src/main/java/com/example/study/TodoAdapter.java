package com.example.study;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder>{

    private ArrayList<ToDo> tasks;

    ItemClicked activity;
    //Context context;

    public interface ItemClicked{
        void onItemClicked(int index);
    }

    public TodoAdapter(Context context,ArrayList<ToDo> list){
       Log.d("tasklist",String.valueOf(list.size()));
        this.tasks=list;
        this.activity = (ItemClicked) context;
        //this.context = context;
        //Log.d("activity",activity.getClass().toString());
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView taskview;
        ImageButton del;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskview = (TextView) itemView.findViewById(R.id.tvTask1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.onItemClicked(tasks.indexOf((ToDo)view.getTag()));
                }
            });
        }
    }
    @NonNull
    @Override
    public TodoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout,viewGroup,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder,final int i) {

        viewHolder.itemView.setTag(tasks.get(i));
        viewHolder.taskview.setText(tasks.get(i).getTask());



        //viewHolder.tvTask.setText("hello");
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
