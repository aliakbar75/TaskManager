package com.example.moein.taskmanager;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.moein.taskmanager.models.Task;
import com.example.moein.taskmanager.models.TaskLab;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskDetailsFragment extends DialogFragment {

    private static final String ARG_TASK_ID = "task_id";
    private static final String DIALOG_TASK_EDIT = "dialog_task_edit";
    private static final String DIALOG_DELETE_ALERT = "delete_alert";
    private static final int REQ_DELETE = 1;
    private static final int REQ_EDIT = 2;
    private Task mTask;

    private TextView mTitleTextView;
    private TextView mDescriptionTextView;
    private TextView mDateTextView;
    private TextView mTimeTextView;

    private Button mEditButton;
    private Button mDeleteButton;
    private Button mDoneButton;
    private Button mShareButton;

    private ConstraintLayout mConstraintLayout;

    public static TaskDetailsFragment newInstance(Long taskId) {
        
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
    public void onStart() {
        super.onStart();
        getDialog().getWindow()
                .setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Long taskId = (Long) getArguments().getSerializable(ARG_TASK_ID);
        mTask = TaskLab.getInstance(getActivity()).getTask(taskId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_details, container, false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.task_details_toolbar_title);

        findViews(view);
        buttonsListeners();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void buttonsListeners() {
        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditTaskFragment editTaskFragment = EditTaskFragment.newInstance(mTask.getMId());
//                editTaskFragment.setTargetFragment(TaskDetailsFragment.this,REQ_EDIT);
                editTaskFragment.show(getFragmentManager(),DIALOG_TASK_EDIT);
                dismiss();

//                Intent intent = EditTaskActivity.newIntent(getActivity(),mTask.getId());
//                startActivity(intent);
            }
        });

        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteAlertFragment deleteAlertFragment = DeleteAlertFragment.newInstance();
                deleteAlertFragment.setTargetFragment(TaskDetailsFragment.this,REQ_DELETE);
                deleteAlertFragment.show(getFragmentManager(),DIALOG_DELETE_ALERT);
//                deleteDialog();
            }
        });

        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTask.setMDone(true);
                TaskLab.getInstance(getActivity()).updateTask(mTask);
                dismiss();
                ((TasksActivity) getActivity()).onResume();
            }
        });

        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String dateString = "  ";
                String timeString = "  ";
                try {
                    dateString = new SimpleDateFormat("yyyy/MM/dd").format(mTask.getMDate());
                    timeString = new SimpleDateFormat("HH:mm").format(mTask.getMTime());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String doneString = mTask.getMDone() ? getString(R.string.task_done) : getString(R.string.task_undone);

                String shareText = getString(R.string.share_text, mTask.getMTitle(), mTask.getMDescriptions(), dateString, timeString,doneString);

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_title_subject));

                startActivity(Intent.createChooser(shareIntent, getString(R.string.share_title)));
            }
        });
    }


    private void findViews(View view) {
        mTitleTextView = view.findViewById(R.id.task_title_details);
        mDescriptionTextView = view.findViewById(R.id.task_description_details);
        mDateTextView = view.findViewById(R.id.task_date_details);
        mTimeTextView = view.findViewById(R.id.task_time_details);
        mEditButton = view.findViewById(R.id.edit_task_button);
        mDeleteButton = view.findViewById(R.id.delete_task_button);
        mDoneButton = view.findViewById(R.id.done_task_button);
        mConstraintLayout = view.findViewById(R.id.task_detail_fragment);
        mShareButton = view.findViewById(R.id.share_task_button);
    }

    private void updateUI() {
        mTitleTextView.setText(mTask.getMTitle());
        mDescriptionTextView.setText(mTask.getMDescriptions());
        mConstraintLayout.setBackgroundColor(mTask.getMColor());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm");

        try {
            String formattedDate = simpleDateFormat.format(mTask.getMDate());
            String formattedTime = simpleTimeFormat.format(mTask.getMTime());
            mDateTextView.setText("Date :  " + formattedDate);
            mTimeTextView.setText("Time :  " + formattedTime);
        }catch (Exception e){
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
                TaskLab.getInstance(getActivity()).deleteTask(mTask);
                dismiss();
                ((TasksActivity) getActivity()).onResume();
            }
        }

    }

    //    private void deleteDialog() {
//        AlertDialog.Builder deleteDialog = new AlertDialog.Builder(getActivity());
//        deleteDialog.setMessage(R.string.delete_text_alert);
//        deleteDialog.setPositiveButton(R.string.delete_button_text, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                TaskLab.getInstance(getActivity()).deleteTask(mTask);
//                dismiss();
//                ((TasksActivity) getActivity()).onResume();
//            }
//        });
//
//        deleteDialog.setNegativeButton(R.string.cancel_delete_button_text, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//            }
//        });
//        AlertDialog dialog = deleteDialog.create();
//        dialog.show();
//        dialog.getWindow().setBackgroundDrawableResource(R.color.light_red);
//    }
}
