package com.example.moein.taskmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AddTaskActivity extends AppCompatActivity {


    public static Intent newIntent(Context context){
        Intent intent = new Intent(context,AddTaskActivity.class);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        FragmentManager fragmentManager = getSupportFragmentManager();

//        if (fragmentManager.findFragmentById(R.id.add_task_fragment_container) == null){
//
//        }

        fragmentManager.beginTransaction()
                .add(R.id.add_task_fragment_container,AddTaskFragment.newInstance())
                .commit();
    }
}
