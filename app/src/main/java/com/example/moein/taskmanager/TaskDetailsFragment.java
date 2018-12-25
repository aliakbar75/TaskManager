package com.example.moein.taskmanager;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.moein.taskmanager.models.Task;
import com.example.moein.taskmanager.models.TaskLab;

import java.text.SimpleDateFormat;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskDetailsFragment extends Fragment {

    private static final String ARG_TASK_ID = "task_id";
    private Task mTask;

    private TextView mTitleTextView;
    private TextView mDescriptionTextView;
    private TextView mDateTextView;
    private TextView mTimeTextView;

    private Button mEditButton;
    private Button mDeleteButton;
    private Button mDoneButton;

    public static TaskDetailsFragment newInstance(UUID taskId) {
        
        Bundle args = new Bundle();
        args.putSerializable(ARG_TASK_ID,taskId);
        TaskDetailsFragment fragment = new TaskDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public TaskDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID taskId = (UUID) getArguments().getSerializable(ARG_TASK_ID);
        mTask = TaskLab.getInstance().getTask(taskId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_details, container, false);
        mTitleTextView = view.findViewById(R.id.task_title_details);
        mDescriptionTextView = view.findViewById(R.id.task_description_details);
        mDateTextView = view.findViewById(R.id.task_date_details);
        mTimeTextView = view.findViewById(R.id.task_time_details);
        mEditButton = view.findViewById(R.id.edit_task_button);
        mDeleteButton = view.findViewById(R.id.delete_task_button);
        mDoneButton = view.findViewById(R.id.done_task_button);



        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = EditTaskActivity.newIntent(getActivity(),mTask.getId());
                startActivity(intent);
            }
        });

        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskLab.getInstance().deleteTask(mTask);
                getActivity().finish();
            }
        });

        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskLab.getInstance().taskDone(mTask);
                getActivity().finish();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        mTitleTextView.setText(mTask.getTitle());
        mDescriptionTextView.setText(mTask.getDescriptions());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm");

        try {
            String formattedDate = simpleDateFormat.format(mTask.getDate());
            String formattedTime = simpleTimeFormat.format(mTask.getTime());
            mDateTextView.setText("Date :  " + formattedDate);
            mTimeTextView.setText("Time :  " + formattedTime);
        }catch (Exception e){

        }
    }
}
