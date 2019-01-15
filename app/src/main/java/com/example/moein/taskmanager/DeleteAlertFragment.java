package com.example.moein.taskmanager;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.moein.taskmanager.models.Task;
import com.example.moein.taskmanager.models.TaskLab;

import java.util.Date;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteAlertFragment extends DialogFragment {

    private static final String ARG_TASK_ID = "task_id";
    public static final String EXTRA_DELETED = "deleted";
    private TextView mTextView;
    private Button mDeleteButton;
    private Button mCancelButton;
    private Task mTask;

    public static DeleteAlertFragment newInstance() {

        Bundle args = new Bundle();
        DeleteAlertFragment fragment = new DeleteAlertFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public DeleteAlertFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow()
                .setLayout(WindowManager.LayoutParams.MATCH_PARENT, 500);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_alert, container, false);

        mTextView = view.findViewById(R.id.alert);
        mDeleteButton = view.findViewById(R.id.create_account_button);
        mCancelButton = view.findViewById(R.id.cancel_button);

        mTextView.setText(R.string.delete_text_alert);
        mDeleteButton.setText(R.string.delete_button_text);
        mCancelButton.setText(R.string.cancel_delete_button_text);
        mDeleteButton.setBackgroundColor(getResources().getColor(R.color.delete_button));
        mCancelButton.setBackgroundColor(getResources().getColor(R.color.cancel_button));

        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResult();
                dismiss();
            }
        });


        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        return view;
    }

    private void sendResult() {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DELETED, true);
        getTargetFragment().
                onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
    }

}
