package com.example.moein.taskmanager;


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

import com.example.moein.taskmanager.models.User;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginAlertFragment extends DialogFragment {

    private static final String ARG_USER_ID = "user_id";

    private TextView mTextView;
    private Button mCreateButton;
    private Button mCancelButton;
    private UUID mUserId;

    public static LoginAlertFragment newInstance(UUID userId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER_ID,userId);
        LoginAlertFragment fragment = new LoginAlertFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public LoginAlertFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserId = (UUID) getArguments().getSerializable(ARG_USER_ID);
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
        mCreateButton = view.findViewById(R.id.create_account_button);
        mCancelButton = view.findViewById(R.id.cancel_button);

        mTextView.setText(R.string.login_alert);
        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = CreateAccountActivity.newIntent(getActivity(),mUserId);
                startActivity(intent);
                dismiss();
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                getActivity().finish();
            }
        });
        return view;
    }

}
