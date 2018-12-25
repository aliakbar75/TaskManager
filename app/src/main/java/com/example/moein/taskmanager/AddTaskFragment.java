package com.example.moein.taskmanager;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
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


    private EditText mTitleEditText;
    private EditText mDescriptionEditText;
    private TextView mDateTextView;
    private TextView mTimeTextView;

    private Button mSaveButton;
    private Button mCancelButton;

    private Spinner mColorSpinner;

    private TimePickerDialog mTimePickerDialog;
    private DatePickerDialog mDatePickerDialog;

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

        final Calendar calendar = Calendar.getInstance();

        mTitleEditText = view.findViewById(R.id.task_title);
        mDescriptionEditText = view.findViewById(R.id.task_description);
        mDateTextView = view.findViewById(R.id.task_date);
        mTimeTextView = view.findViewById(R.id.task_time);
        mSaveButton = view.findViewById(R.id.task_save_button);
        mCancelButton = view.findViewById(R.id.task_cancel_button);
        mColorSpinner = view.findViewById(R.id.color_spinner);

        final int[] color = new int[1];
        mColorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        mColorSpinner.setBackgroundColor(getResources().getColor(R.color.light_yellow));
                        color[0] = getResources().getColor(R.color.light_yellow);
                        break;
                    case 1:
                        mColorSpinner.setBackgroundColor(getResources().getColor(R.color.light_Blue));
                        color[0] = getResources().getColor(R.color.light_Blue);
                        break;
                    case 2:
                        mColorSpinner.setBackgroundColor(getResources().getColor(R.color.light_red));
                        color[0] = getResources().getColor(R.color.light_red);
                        break;
                    case 3:
                        mColorSpinner.setBackgroundColor(getResources().getColor(R.color.light_green));
                        color[0] = getResources().getColor(R.color.light_green);
                        break;
                    case 4:
                        mColorSpinner.setBackgroundColor(getResources().getColor(R.color.light_purple));
                        color[0] = getResources().getColor(R.color.light_purple);
                        break;
                    case 5:
                        mColorSpinner.setBackgroundColor(getResources().getColor(R.color.light_orange));
                        color[0] = getResources().getColor(R.color.light_orange);
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
                String stringDate = mDateTextView.getText().toString();
                String stringTime = mTimeTextView.getText().toString();

//                int[] taskColors = getResources().getIntArray(R.array.task_light_colors);
//                int color = taskColors[new Random().nextInt(taskColors.length)];
                Date date = null;
                Date time = null;
                try {
                    date = new SimpleDateFormat("yyyy/MM/dd").parse(stringDate);
                    time = new SimpleDateFormat("HH:mm").parse(stringTime);
                }catch (Exception e){
                }

                if(title.length() != 0){
                    TaskLab.getInstance().addTask(title,description,date,time,color[0]);
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

}
