package com.example.moein.taskmanager;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class DateTimePickerFragment extends DialogFragment {

    private TabLayout mTabLayout;

    public static DateTimePickerFragment newInstance() {
        
        Bundle args = new Bundle();
        
        DateTimePickerFragment fragment = new DateTimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public DateTimePickerFragment() {
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
        View view = inflater.inflate(R.layout.fragment_date_time_picker, container, false);

        mTabLayout = view.findViewById(R.id.time_picker_tab_layout);

        mTabLayout.addTab(mTabLayout.newTab().setText("DatePicker"));
        mTabLayout.addTab(mTabLayout.newTab().setText("TimePicker"));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(mTabLayout.getSelectedTabPosition() == 0){
//                    FragmentManager fragmentManager = getFragmentManager();
//                    if(fragmentManager.findFragmentById(R.id.date_picker_container)== null){
//                        fragmentManager.beginTransaction()
//                                .add(R.id.date_picker_container,DatePickerFragment.newInstance())
//                                .commit();
//                    }
                }else if(mTabLayout.getSelectedTabPosition() == 1){

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

}
