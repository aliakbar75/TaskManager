package com.example.moein.taskmanager;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moein.taskmanager.models.User;
import com.example.moein.taskmanager.models.UserLab;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateAccountFragment extends Fragment {

    private static final String ARG_USER_ID = "user_id";
    private EditText mUserNameEditText;
    private EditText mPasswordEditText;
    private Button mCreateButton;
    private Button mCancelButton;

    private Long mUserId;

    public static CreateAccountFragment newInstance(Long userId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER_ID,userId);
        CreateAccountFragment fragment = new CreateAccountFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public CreateAccountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserId = (Long) getArguments().getSerializable(ARG_USER_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_account, container, false);
        mUserNameEditText = view.findViewById(R.id.user_name_login);
        mPasswordEditText = view.findViewById(R.id.password_login);
        mCreateButton = view.findViewById(R.id.create_account_button);
        mCancelButton = view.findViewById(R.id.cancel_button);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.create_account_toolbar_title);

        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = mUserNameEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                User user0 = UserLab.getInstance(getActivity()).getUser(userName);
                if (userName.length() > 12 || userName.length() < 4){
                    Toast.makeText(getActivity(), R.string.invalid_username, Toast.LENGTH_SHORT).show();
                }else if (password.length() > 12 || password.length() < 4) {
                    Toast.makeText(getActivity(), R.string.invalid_password, Toast.LENGTH_SHORT).show();
                }else if (user0 != null && userName.equals(user0.getMUserName())){
                    Toast.makeText(getActivity(), R.string.user_name_exist, Toast.LENGTH_SHORT).show();
                }else {
                    User user =new User(mUserId,userName,password);
                    UserLab.getInstance(getActivity()).updateUser(user);
                    getActivity().finish();
                }

            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return view;
    }

}
