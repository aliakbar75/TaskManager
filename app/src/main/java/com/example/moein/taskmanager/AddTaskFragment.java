package com.example.moein.taskmanager;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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

import com.example.moein.taskmanager.models.Task;
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

    private String stringDate;
    private String stringTime;

    private int color;
    private int iconColor;

    private int[] lightColors = new int[6];
    private int[] darkColors = new int[6];

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

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.add_task_toolbar_title);

        findViews(view);

        mDateTextView.setText(stringDate);
        mTimeTextView.setText(stringTime);

        configureColors();
        dateTimeDialog();
        buttonsListeners();

        return view;
    }

    private void findViews(View view) {
        mTitleEditText = view.findViewById(R.id.task_title);
        mDescriptionEditText = view.findViewById(R.id.task_description);
        mDateTextView = view.findViewById(R.id.task_date);
        mTimeTextView = view.findViewById(R.id.task_time);
        mSaveButton = view.findViewById(R.id.task_save_button);
        mCancelButton = view.findViewById(R.id.task_cancel_button);
        mColorSpinner = view.findViewById(R.id.color_spinner);
        mConstraintLayout = view.findViewById(R.id.add_task_fragment);
    }

    private void configureColors() {
        makeLightColors();
        makeDarkColors();

        mColorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setColors(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void buttonsListeners() {
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeTask();
                getActivity().finish();
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

    private void makeTask() {
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
            Task task = new Task(title);
            task.setDescriptions(description);
            task.setDate(date);
            task.setTime(time);
            task.setColor(color);
            task.setIconColor(iconColor);
            TaskLab.getInstance().addTask(task);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_DATE,stringDate);
        outState.putString(KEY_TIME,stringTime);
    }

    private void dateTimeDialog(){

        final Calendar calendar = Calendar.getInstance();

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
    }

    private void setColors (int i){
        mConstraintLayout.setBackgroundColor(lightColors[i]);
        mColorSpinner.setBackgroundColor(darkColors[i]);
        color = lightColors[i];
        iconColor = darkColors[i];
    }

    private void makeLightColors(){
        lightColors[0]=getResources().getColor(R.color.light_green);
        lightColors[1]=getResources().getColor(R.color.light_Blue);
        lightColors[2]=getResources().getColor(R.color.light_red);
        lightColors[3]=getResources().getColor(R.color.light_yellow);
        lightColors[4]=getResources().getColor(R.color.light_purple);
        lightColors[5]=getResources().getColor(R.color.light_orange);
    }

    private void makeDarkColors(){
        darkColors[0]=getResources().getColor(R.color.dark_green);
        darkColors[1]=getResources().getColor(R.color.dark_Blue);
        darkColors[2]=getResources().getColor(R.color.dark_red);
        darkColors[3]=getResources().getColor(R.color.dark_yellow);
        darkColors[4]=getResources().getColor(R.color.dark_purple);
        darkColors[5]=getResources().getColor(R.color.dark_orange);
    }
}
