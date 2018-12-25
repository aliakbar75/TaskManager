package com.example.moein.taskmanager;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TasksActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mTaskViewPager;
    private FloatingActionButton mFloatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        mTabLayout = findViewById(R.id.tab_layout);
        mTaskViewPager = findViewById(R.id.task_view_pager);
        mFloatingActionButton = findViewById(R.id.floating_action_button);

        mTabLayout.setupWithViewPager(mTaskViewPager);

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = AddTaskActivity.newIntent(TasksActivity.this);
                startActivity(intent);
            }
        });

        mTaskViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                if(i==0)
                    return TasksListFragment.newInstance(0);
                if(i==1)
                    return TasksListFragment.newInstance(1);
                if(i==2)
                    return TasksListFragment.newInstance(2);

                return null;
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                if(position==0)
                    return "All";
                if (position==1)
                    return "Done";
                if (position==2)
                    return "Undone";

                return null;
            }
        });
    }
}
