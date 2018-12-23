package com.example.moein.taskmanager;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TasksActivity extends AppCompatActivity {

    TabLayout mTabLayout;
    ViewPager mTaskViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        mTabLayout = findViewById(R.id.tab_layout);
        mTaskViewPager = findViewById(R.id.task_view_pager);
        mTabLayout.setupWithViewPager(mTaskViewPager);

        mTaskViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return null;
            }

            @Override
            public int getCount() {
                return 0;
            }
        });
    }
}
