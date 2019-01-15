package com.example.moein.taskmanager;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moein.taskmanager.models.User;
import com.example.moein.taskmanager.models.UserLab;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private EditText mUserNameEditText;
    private EditText mPasswordEditText;
    private Button mCreateButton;
    private Button mLoginButton;
    private Button mSkipButton;

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mUserNameEditText = view.findViewById(R.id.user_name_login);
        mPasswordEditText = view.findViewById(R.id.password_login);
        mCreateButton = view.findViewById(R.id.create_account_button);
        mLoginButton = view.findViewById(R.id.login_button);
        mSkipButton = view.findViewById(R.id.skip_button);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.login_toolbar_title);

        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                Intent intent = CreateAccountActivity.newIntent(getActivity(),user.getId());
                startActivity(intent);
            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = mUserNameEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();

                User user = UserLab.getInstance(getActivity()).getUser(userName);
                if (user != null){
                    if (password.equals(user.getPassword())){
                        Intent intent = TasksActivity.newIntent(getActivity(),user.getId());
                        startActivity(intent);
                        getActivity().finish();
                    }
                }else{
                    Toast.makeText(getActivity(), R.string.ivalid_data_login, Toast.LENGTH_SHORT).show();
                }

            }
        });

        mSkipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                Intent intent = TasksActivity.newIntent(getActivity(),user.getId());
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }

}
