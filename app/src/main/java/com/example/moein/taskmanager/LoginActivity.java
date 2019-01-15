package com.example.moein.taskmanager;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragmentManager.findFragmentById(R.id.login_fragment_container)== null){
            fragmentManager.beginTransaction()
                    .add(R.id.login_fragment_container,LoginFragment.newInstance())
                    .commit();
        }
    }
}
