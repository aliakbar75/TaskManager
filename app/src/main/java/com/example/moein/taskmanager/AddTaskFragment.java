package com.example.moein.taskmanager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.moein.taskmanager.models.Task;
import com.example.moein.taskmanager.models.TaskLab;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTaskFragment extends Fragment {


    private EditText mTitleEditText;
    private EditText mDescriptionEditText;
    private EditText mDateEditText;
    private EditText mTimeEditText;

    private Button mSaveButton;
    private Button mCancelButton;

    public static AddTaskFragment newInstance() {
        
        Bundle args = new Bundle();
        
        AddTaskFragment fragment = new AddTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public AddTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        mTitleEditText = view.findViewById(R.id.task_title);
        mDescriptionEditText = view.findViewById(R.id.task_description);
        mDateEditText = view.findViewById(R.id.task_date);
        mTimeEditText = view.findViewById(R.id.task_time);
        mSaveButton = view.findViewById(R.id.task_save_button);
        mCancelButton = view.findViewById(R.id.task_cancel_button);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = mTitleEditText.getText().toString();
                String description = mDescriptionEditText.getText().toString();
                String stringDate = mDateEditText.getText().toString();
                String stringTime = mTimeEditText.getText().toString();

                int[] taskColors = getResources().getIntArray(R.array.task_light_colors);
                int color = taskColors[new Random().nextInt(taskColors.length)];
                Date date = null;
                Date time = null;
                try {
                    date = new SimpleDateFormat("yyyy/MM/dd").parse(stringDate);
                    time = new SimpleDateFormat("HH:mm a").parse(stringTime);
                }catch (Exception e){
                }

                TaskLab.getInstance().addTask(title,description,date,time,color);


                getActivity().finish();
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        return view;
    }

}
