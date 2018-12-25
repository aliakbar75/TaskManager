package com.example.moein.taskmanager;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.moein.taskmanager.models.TaskLab;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTaskFragment extends Fragment{

    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";

    private ConstraintLayout mConstraintLayout;
    private EditText mTitleEditText;
    private EditText mDescriptionEditText;
    private TextView mDateTextView;
    private TextView mTimeTextView;

    private Button mSaveButton;
    private Button mCancelButton;

    private Spinner mColorSpinner;

    private TimePickerDialog mTimePickerDialog;
    private DatePickerDialog mDatePickerDialog;

    String stringDate;
    String stringTime;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            stringDate = savedInstanceState.getString(KEY_DATE);
            stringTime = savedInstanceState.getString(KEY_TIME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);

        final Calendar calendar = Calendar.getInstance();

        mTitleEditText = view.findViewById(R.id.task_title);
        mDescriptionEditText = view.findViewById(R.id.task_description);
        mDateTextView = view.findViewById(R.id.task_date);
        mTimeTextView = view.findViewById(R.id.task_time);
        mSaveButton = view.findViewById(R.id.task_save_button);
        mCancelButton = view.findViewById(R.id.task_cancel_button);
        mColorSpinner = view.findViewById(R.id.color_spinner);
        mConstraintLayout = view.findViewById(R.id.add_task_fragment);

        mDateTextView.setText(stringDate);
        mTimeTextView.setText(stringTime);

        final int[] color = new int[1];
        final int[] iconColor = new int[1];
        mColorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        mColorSpinner.setBackgroundColor(getResources().getColor(R.color.dark_green));
                        mConstraintLayout.setBackgroundColor(getResources().getColor(R.color.light_green));
                        color[0] = getResources().getColor(R.color.light_green);
                        iconColor[0] = getResources().getColor(R.color.dark_green);
                        break;
                    case 1:
                        mColorSpinner.setBackgroundColor(getResources().getColor(R.color.dark_Blue));
                        mConstraintLayout.setBackgroundColor(getResources().getColor(R.color.light_Blue));
                        color[0] = getResources().getColor(R.color.light_Blue);
                        iconColor[0] = getResources().getColor(R.color.dark_Blue);
                        break;
                    case 2:
                        mColorSpinner.setBackgroundColor(getResources().getColor(R.color.dark_red));
                        mConstraintLayout.setBackgroundColor(getResources().getColor(R.color.light_red));
                        color[0] = getResources().getColor(R.color.light_red);
                        iconColor[0] = getResources().getColor(R.color.dark_red);
                        break;
                    case 3:
                        mColorSpinner.setBackgroundColor(getResources().getColor(R.color.dark_yellow));
                        mConstraintLayout.setBackgroundColor(getResources().getColor(R.color.light_yellow));
                        color[0] = getResources().getColor(R.color.light_yellow);
                        iconColor[0] = getResources().getColor(R.color.dark_yellow);
                        break;
                    case 4:
                        mColorSpinner.setBackgroundColor(getResources().getColor(R.color.dark_purple));
                        mConstraintLayout.setBackgroundColor(getResources().getColor(R.color.light_purple));
                        color[0] = getResources().getColor(R.color.light_purple);
                        iconColor[0] = getResources().getColor(R.color.dark_purple);
                        break;
                    case 5:
                        mColorSpinner.setBackgroundColor(getResources().getColor(R.color.dark_orange));
                        mConstraintLayout.setBackgroundColor(getResources().getColor(R.color.light_orange));
                        color[0] = getResources().getColor(R.color.light_orange);
                        iconColor[0] = getResources().getColor(R.color.dark_orange);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                mDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        mDateTextView.setText(i+"/"+(i1+1)+"/"+i2);
                        stringDate = mDateTextView.getText().toString();
                    }
                },year,month,day);
                mDatePickerDialog.show();
            }
        });

        mTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = calendar.get(Calendar.HOUR);
                int minute = calendar.get(Calendar.MINUTE);
                mTimePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        mTimeTextView.setText(i +":"+i1);
                        stringTime = mTimeTextView.getText().toString();
                    }
                },hour,minute,false);
                mTimePickerDialog.show();
            }
        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = mTitleEditText.getText().toString();
                String description = mDescriptionEditText.getText().toString();

                Date date = null;
                Date time = null;
                try {
                    date = new SimpleDateFormat("yyyy/MM/dd").parse(stringDate);
                    time = new SimpleDateFormat("HH:mm").parse(stringTime);
                }catch (Exception e){
                }

                if(title.length() != 0){
                    TaskLab.getInstance().addTask(title,description,date,time,color[0],iconColor[0]);
                }

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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_DATE,stringDate);
        outState.putString(KEY_TIME,stringTime);
    }
}
