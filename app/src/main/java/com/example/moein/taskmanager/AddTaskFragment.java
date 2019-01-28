package com.example.moein.taskmanager;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.moein.taskmanager.models.Task;
import com.example.moein.taskmanager.models.TaskLab;
import com.example.moein.taskmanager.utils.PictureUtils;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTaskFragment extends DialogFragment {


    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";
    private static final String ARG_USER_ID = "userId";
    private static final String DATE_PICKER = "date_picker";
    private static final int REQ_TAKE_PHOTOS = 0;
    private static final int REQ_PICK_IMAGE = 1 ;

    private ConstraintLayout mConstraintLayout;
    private EditText mTitleEditText;
    private EditText mDescriptionEditText;
    private TextView mDateTextView;
    private TextView mTimeTextView;

    private Button mSaveButton;
    private Button mCancelButton;
    private Button mTakePictureButton;
    private Button mChoosePictureButton;

    private ImageView mTaskImage;

    private Spinner mColorSpinner;

    private TimePickerDialog mTimePickerDialog;
    private DatePickerDialog mDatePickerDialog;

    private Long mUserId;

    private Date mDate;

    private Task mTask;

    private File mPhotoFile;

    private String stringDate;
    private String stringTime;

    private int color;
    private int iconColor;

    private int[] lightColors = new int[6];
    private int[] darkColors = new int[6];

    private Callbacks mCallbacks;

    public interface Callbacks{
        void updateUI();
    }

    public static AddTaskFragment newInstance(Long userId) {
        
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER_ID, userId);
        AddTaskFragment fragment = new AddTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public AddTaskFragment() {
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

        mTask = new Task();
        TaskLab.getInstance(getActivity()).addTask(mTask);
        mUserId = (Long) getArguments().getSerializable(ARG_USER_ID);
        mPhotoFile = TaskLab.getInstance(getActivity()).getPhotoFile(mTask);
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

        updatePhotoView();
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
        mTakePictureButton = view.findViewById(R.id.take_picture_button);
        mChoosePictureButton = view.findViewById(R.id.choose_picture_button);
        mTaskImage = view.findViewById(R.id.task_image_view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Callbacks)
            mCallbacks = (Callbacks) context;
        else
            throw new RuntimeException("Callbacks not impl");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onResume() {
        super.onResume();
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
                dismiss();
                mCallbacks.updateUI();
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskLab.getInstance(getActivity()).deleteTask(mTask);
                dismiss();
            }
        });

        mTakePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                Uri uri = getPhotoFileUri();
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

                PackageManager packageManager = getActivity().getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(
                        captureIntent,
                        PackageManager.MATCH_DEFAULT_ONLY);

                for (ResolveInfo activity : activities) {
                    getActivity().grantUriPermission(
                            activity.activityInfo.packageName,
                            uri,
                            Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }

                startActivityForResult(captureIntent, REQ_TAKE_PHOTOS);
            }
        });

        mChoosePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQ_PICK_IMAGE);
            }
        });

    }

    private Uri getPhotoFileUri() {
        return FileProvider.getUriForFile(getActivity(),
                "com.example.moein.taskmanager.fileprovider",
                mPhotoFile);
    }

//    private File getPhotoFile() {
//        UUID uuid = UUID.randomUUID();
//        File filesDir = getActivity().getApplicationContext().getFilesDir();
//        File photoFile = new File(filesDir, uuid.toString());
//
//        return photoFile;
//    }

    private void makeTask() {
        String title = mTitleEditText.getText().toString();
        String description = mDescriptionEditText.getText().toString();

        Date date = null;
        Date time = null;
        try {
            date = new SimpleDateFormat("yyyy/MM/dd").parse(stringDate);
            time = new SimpleDateFormat("HH:mm").parse(stringTime);
        }catch (Exception e){
            e.printStackTrace();
        }

        if(title.length() != 0){
            mTask.setMTitle(title);
            mTask.setMUserId(mUserId);
            mTask.setMDescriptions(description);
            mTask.setMDate(mDate);
            mTask.setMTime(time);
            mTask.setMColor(color);
            mTask.setMIconColor(iconColor);
            TaskLab.getInstance(getActivity()).updateTask(mTask);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_DATE,stringDate);
        outState.putString(KEY_TIME,stringTime);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_TAKE_PHOTOS) {
            Uri uri = getPhotoFileUri();
            getActivity().revokeUriPermission(uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            updatePhotoView();
        }else if (requestCode == REQ_PICK_IMAGE){
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                Bitmap bitmap = PictureUtils.getScaledBitmap(imageStream);
                mTaskImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void updatePhotoView() {
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mTaskImage.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(
                    mPhotoFile.getPath(),
                    getActivity());

            mTaskImage.setImageBitmap(bitmap);
        }
    }

    private void dateTimeDialog(){

        final Calendar calendar = Calendar.getInstance();

//        mDateTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DateTimePickerFragment dateTimePickerFragment = DateTimePickerFragment.newInstance();
//                dateTimePickerFragment.show(getFragmentManager(),DATE_PICKER);
//            }
//        });


        mDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                mDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        mDate = new GregorianCalendar(i,i1,i2).getTime();
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
