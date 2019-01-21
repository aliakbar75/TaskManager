package com.example.moein.taskmanager;


import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moein.taskmanager.models.Task;
import com.example.moein.taskmanager.models.TaskLab;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class TasksListFragment extends Fragment {

    private static final int ALL_TASKS = 0;
    private static final int DONE_TASKS = 1;
    private static final int UNDONE_tASKS = 2;

    private static final String TAB_TYPE = "tab_type";
    private static final String ARG_USER_ID = "user_id";
    private static final int REQ_DELETE = 2;
    private static final String DIALOG_DELETE_ALERT = "delete_alert";
    private RecyclerView mTasksRecyclerView;
    private TasksAdapter mTasksAdapter;
    private TextView mEmptyTextView;
    private ImageView mEmptyImageView;
    private List<Task> mTasks;

    public static TasksListFragment newInstance(int tabType,Long userId) {
        
        Bundle args = new Bundle();
        args.putInt(TAB_TYPE,tabType);
        args.putSerializable(ARG_USER_ID,userId);
        TasksListFragment fragment = new TasksListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public TasksListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Long userId = (Long) getArguments().getSerializable(ARG_USER_ID);

        setHasOptionsMenu(true);

        int tabType = getArguments().getInt(TAB_TYPE);
        TaskLab taskLab = TaskLab.getInstance(getActivity());
        switch (tabType){
            case ALL_TASKS:
                mTasks = taskLab.getTasks(userId,ALL_TASKS);
                break;
            case DONE_TASKS:
                mTasks = taskLab.getTasks(userId,DONE_TASKS);
                break;
            case UNDONE_tASKS:
                mTasks = taskLab.getTasks(userId,UNDONE_tASKS);
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_tasks_list, container, false);
        findViews(view);
        setAdapter();
        return view;
    }

    private void findViews(View view) {
        mTasksRecyclerView = view.findViewById(R.id.tasks_recycler_view);
        mEmptyTextView = view.findViewById(R.id.empty_text);
        mEmptyImageView = view.findViewById(R.id.empty_image);
        mTasksRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_tasks_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DeleteAlertFragment deleteAlertFragment = DeleteAlertFragment.newInstance();
        deleteAlertFragment.setTargetFragment(TasksListFragment.this,REQ_DELETE);
        deleteAlertFragment.show(getFragmentManager(),DIALOG_DELETE_ALERT);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d("hhhhhhh", "onResume");
        setAdapter();
        if (mTasks.size()==0){
            mEmptyTextView.setVisibility(View.VISIBLE);
            mEmptyImageView.setVisibility(View.VISIBLE);
        }else {
            mEmptyTextView.setVisibility(View.GONE);
            mEmptyImageView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK)
            return;

        if (requestCode == REQ_DELETE) {
            boolean isDeleted = data.getBooleanExtra(DeleteAlertFragment.EXTRA_DELETED,false);
            if (isDeleted){
                for (Task task : mTasks){
                    TaskLab.getInstance(getActivity()).deleteTask(task);
                }
                ((TasksActivity) getActivity()).onResume();
            }
        }
    }

    private void setAdapter() {
        if (mTasksAdapter == null){
            mTasksAdapter = new TasksAdapter(mTasks);
            mTasksRecyclerView.setAdapter(mTasksAdapter);
        }else {
            mTasksAdapter.setTasks(mTasks);
            mTasksAdapter.notifyDataSetChanged();
        }
    }


    private class TasksHolder extends RecyclerView.ViewHolder{

        private static final String DIALOG_TASK_DETAILS = "dialog_task_details";

        private Task mTask;
        private TextView mTitleTextView;
        private TextView mTaskFirstLetterTextView;
        private ConstraintLayout mItemListLayout;

        public TasksHolder(@NonNull View itemView) {
            super(itemView);

            findViews(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TaskDetailsFragment taskDetailsFragment = TaskDetailsFragment.newInstance(mTask.getMId());
                    taskDetailsFragment.show(getFragmentManager(),DIALOG_TASK_DETAILS);
//                    Intent intent = TaskDetailsActivity.newIntent(getActivity(),mTask.getId());
//                    startActivity(intent);
                }
            });
        }

        private void findViews(@NonNull View itemView) {
            mTitleTextView = itemView.findViewById(R.id.list_item_task_title);
            mTaskFirstLetterTextView = itemView.findViewById(R.id.task_first_letter);
            mItemListLayout = itemView.findViewById(R.id.item_list_root_layout);
        }

        public void bind(Task task){
            mTask = task;
            mTitleTextView.setText(task.getMTitle());
            mTaskFirstLetterTextView.setText(mTitleTextView.getText().toString().substring(0,1));
            mItemListLayout.setBackgroundColor(task.getMColor());
            GradientDrawable background = (GradientDrawable) mTaskFirstLetterTextView.getBackground();
            background.setColor(task.getMIconColor());
        }
    }

    private class TasksAdapter extends RecyclerView.Adapter<TasksHolder>{

        private List<Task> mTasks;

        public TasksAdapter(List<Task> tasks) {
            mTasks = tasks;
        }

        public void setTasks(List<Task> tasks){
            mTasks = tasks;
        }
        @NonNull
        @Override
        public TasksHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.item_list_task, viewGroup, false);
            TasksHolder tasksHolder = new TasksHolder(view);
            return tasksHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull TasksHolder tasksHolder, int i) {
            Task task = mTasks.get(i);
            tasksHolder.bind(task);
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }
    }
}
