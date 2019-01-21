package com.example.moein.taskmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class AddTaskActivity extends AppCompatActivity {

    private static final String EXTRA_USER_ID = "com.example.moein.taskmanager.user_id";

    public static Intent newIntent(Context context, Long userId){
        Intent intent = new Intent(context,AddTaskActivity.class);
        intent.putExtra(EXTRA_USER_ID,userId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Long userId = (Long) getIntent().getSerializableExtra(EXTRA_USER_ID);

        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragmentManager.findFragmentById(R.id.add_task_fragment_container)== null){
            fragmentManager.beginTransaction()
                    .add(R.id.add_task_fragment_container,AddTaskFragment.newInstance(userId))
                    .commit();
        }
    }
}
