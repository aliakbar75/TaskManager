package com.example.moein.taskmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class TaskDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_TASK_ID = "com.example.moein.taskmanager.task_id";

    public static Intent newIntent(Context context, Long taskId){
        Intent intent = new Intent(context,TaskDetailsActivity.class);
        intent.putExtra(EXTRA_TASK_ID,taskId);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Long taskId = (Long) getIntent().getSerializableExtra(EXTRA_TASK_ID);
        if (fragmentManager.findFragmentById(R.id.task_details_fragment_container) == null){
            fragmentManager.beginTransaction()
                    .add(R.id.task_details_fragment_container,TaskDetailsFragment.newInstance(taskId))
                    .commit();
        }
    }
}
