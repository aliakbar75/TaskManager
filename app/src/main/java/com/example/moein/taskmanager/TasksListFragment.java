package com.example.moein.taskmanager;


import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moein.taskmanager.models.Task;
import com.example.moein.taskmanager.models.TaskLab;

import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class TasksListFragment extends Fragment {

    private static final String TAB_TYPE = "tab_type";
    private RecyclerView mTasksRecyclerView;
    private List<Task> mTasks;

    public static TasksListFragment newInstance(int tabType) {
        
        Bundle args = new Bundle();
        args.putInt(TAB_TYPE,tabType);
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

//        int tabType = getArguments().getInt(TAB_TYPE);
//        TaskLab taskLab = TaskLab.getInstance();
//        switch (tabType){
//            case 0:
//                mTasks = taskLab.getAllTasks();
//                break;
//            case 1:
//                mTasks = taskLab.getDoneTasks();
//                break;
//            case 2:
//                mTasks = taskLab.getUndoneTasks();
//                break;
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_tasks_list, container, false);
        mTasksRecyclerView = view.findViewById(R.id.tasks_recycler_view);
        mTasksRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        TasksAdapter tasksAdapter = new TasksAdapter(mTasks);
//        mTasksRecyclerView.setAdapter(tasksAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        int tabType = getArguments().getInt(TAB_TYPE);
        TaskLab taskLab = TaskLab.getInstance();
        switch (tabType){
            case 0:
                mTasks = taskLab.getAllTasks();
                break;
            case 1:
                mTasks = taskLab.getDoneTasks();
                break;
            case 2:
                mTasks = taskLab.getUndoneTasks();
                break;
        }
        TasksAdapter tasksAdapter = new TasksAdapter(mTasks);
        mTasksRecyclerView.setAdapter(tasksAdapter);
    }


    private class TasksHolder extends RecyclerView.ViewHolder{

        private Task mTask;
        private TextView mTitleTextView;
        private TextView mTaskFirstLetterTextView;
        private ConstraintLayout mItemListLayout;
        public TasksHolder(@NonNull View itemView) {
            super(itemView);
            mTitleTextView = itemView.findViewById(R.id.list_item_task_title);
            mTaskFirstLetterTextView = itemView.findViewById(R.id.task_first_letter);
            mItemListLayout = itemView.findViewById(R.id.item_list_root_layout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = TaskDetailsActivity.newIntent(getActivity(),mTask.getId());
                    startActivity(intent);
                }
            });
        }
        public void bind(Task task){
            mTask = task;
            mTitleTextView.setText(task.getTitle());
            mTaskFirstLetterTextView.setText(mTitleTextView.getText().toString().substring(0,1));
            mItemListLayout.setBackgroundColor(task.getColor());
            GradientDrawable background = (GradientDrawable) mTaskFirstLetterTextView.getBackground();
            background.setColor(task.getIconColor());
        }
    }

    private class TasksAdapter extends RecyclerView.Adapter<TasksHolder>{

        private List<Task> mTasks;

        public TasksAdapter(List<Task> tasks) {
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
