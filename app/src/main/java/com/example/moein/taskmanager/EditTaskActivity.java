package com.example.moein.taskmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class EditTaskActivity extends AppCompatActivity {

    public static final String EXTRA_TASK_ID = "com.example.moein.taskmanager.task_id";

    public static Intent newIntent(Context context, UUID taskId){
        Intent intent = new Intent(context,EditTaskActivity.class);
        intent.putExtra(EXTRA_TASK_ID,taskId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        UUID taskId = (UUID) getIntent().getSerializableExtra(EXTRA_TASK_ID);
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragmentManager.findFragmentById(R.id.edit_task_fragment_container)== null){
            fragmentManager.beginTransaction()
                    .add(R.id.edit_task_fragment_container,EditTaskFragment.newInstance(taskId))
                    .commit();
        }
    }
}
