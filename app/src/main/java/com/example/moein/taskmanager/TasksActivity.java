package com.example.moein.taskmanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import com.example.moein.taskmanager.models.Task;
import com.example.moein.taskmanager.models.TaskLab;
import com.example.moein.taskmanager.models.User;
import com.example.moein.taskmanager.models.UserLab;

import java.util.UUID;

public class TasksActivity extends AppCompatActivity implements AddTaskFragment.Callbacks,EditTaskFragment.Callbacks,TaskDetailsFragment.Callbacks{

    private static final String EXTRA_USER_ID = "com.example.moein.taskmanager.user_id";
    private static final String DIALOG_ADD_TASK = "add_task";
    private static final String DIALOG_LOGIN_ALERT = "login_alert";
    private static final int ALL_TASKS = 0;
    private static final int DONE_TASKS = 1;
    private static final int UNDONE_tASKS = 2;

    private TabLayout mTabLayout;
    private ViewPager mTaskViewPager;
    private FloatingActionButton mFloatingActionButton;

    private Long mUserId;

    public static Intent newIntent(Context context,Long userId){
        Intent intent = new Intent(context,TasksActivity.class);
        intent.putExtra(EXTRA_USER_ID,userId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        mUserId = (Long) getIntent().getSerializableExtra(EXTRA_USER_ID);

        findViews();
        mTabLayout.setupWithViewPager(mTaskViewPager);
        floatingActionButtonListener();
        setViewPagerAdapter(mUserId);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setViewPagerAdapter(mUserId);
    }

    @Override
    public void onBackPressed() {
        String userName = UserLab.getInstance(this).getUser(mUserId).getMUserName();
        if (userName == null){
            LoginAlertFragment loginAlertFragment = LoginAlertFragment.newInstance(mUserId);
            loginAlertFragment.show(getSupportFragmentManager(),DIALOG_LOGIN_ALERT);
        }else {
            finish();
        }

    }

    private void findViews() {
        mTabLayout = findViewById(R.id.tab_layout);
        mTaskViewPager = findViewById(R.id.task_view_pager);
        mFloatingActionButton = findViewById(R.id.floating_action_button);
    }

    private void floatingActionButtonListener() {
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AddTaskFragment addTaskFragment = AddTaskFragment.newInstance(mUserId);
                addTaskFragment.show(getSupportFragmentManager(),DIALOG_ADD_TASK);
//                Intent intent = AddTaskActivity.newIntent(TasksActivity.this,userId);
//                startActivity(intent);
            }
        });
    }

    private void setViewPagerAdapter(final Long userId) {
        mTaskViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                if(i==ALL_TASKS)
                    return TasksListFragment.newInstance(ALL_TASKS,userId);
                if(i==DONE_TASKS)
                    return TasksListFragment.newInstance(DONE_TASKS,userId);
                if(i==UNDONE_tASKS)
                    return TasksListFragment.newInstance(UNDONE_tASKS,userId);

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
                    return getString(R.string.all_tasks);
                if (position==1)
                    return getString(R.string.done_tasks);
                if (position==2)
                    return getString(R.string.undone_tasks);

                return null;
            }
        });
    }

    @Override
    public void updateUI() {
        onResume();
    }
}
