package com.example.moein.taskmanager;


import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.moein.taskmanager.models.Task;
import com.example.moein.taskmanager.models.TaskLab;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchDialogFragment extends DialogFragment {

    private static final String ARG_USER_ID = "userId";
    public static final String EXTRA_SEARCH = "searched";
    private EditText mTitleSearchEditText;
    private EditText mDescriptionSearchEditText;
    private Button mSearchButton;
    private Button mCancelButton;
    private RecyclerView mSearchRecyclerView;
    private TasksAdapter mTasksAdapter;

    private List<Task> mTasks;


    public static SearchDialogFragment newInstance(Long userId) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_USER_ID, userId);
        SearchDialogFragment fragment = new SearchDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public SearchDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow()
                .setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_dialog, container, false);
        mTitleSearchEditText = view.findViewById(R.id.search_title_edit_text);
        mDescriptionSearchEditText = view.findViewById(R.id.search_description_edit_text);
        mSearchButton = view.findViewById(R.id.search_button);
        mCancelButton = view.findViewById(R.id.cancel_button);
        mSearchRecyclerView = view.findViewById(R.id.search_recycler_view);
        mSearchRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final Long userId = (Long) getArguments().getSerializable(ARG_USER_ID);


        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchTitleText = mTitleSearchEditText.getText().toString();
                String searchDescriptionText = mDescriptionSearchEditText.getText().toString();

                mTasks = TaskLab.getInstance(getActivity()).search(userId,searchTitleText,searchDescriptionText);

                if (mTasksAdapter == null){
                    mTasksAdapter = new TasksAdapter(mTasks);
                    mSearchRecyclerView.setAdapter(mTasksAdapter);
                }else {
                    mTasksAdapter.setTasks(mTasks);
                    mTasksAdapter.notifyDataSetChanged();
                }

//                sendResult(searchText);
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

//    private void sendResult(String searchText) {
//        Intent intent = new Intent();
//        intent.putExtra(EXTRA_SEARCH, searchText);
//        getTargetFragment().
//                onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
//    }

}
