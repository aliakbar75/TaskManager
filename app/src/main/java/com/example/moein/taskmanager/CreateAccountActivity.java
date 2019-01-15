package com.example.moein.taskmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class CreateAccountActivity extends AppCompatActivity {

    private static final String EXTRA_USER_ID = "com.example.moein.taskmanager.user_id";
    private UUID mUserId;

    public static Intent newIntent(Context context,UUID userId){
        Intent intent = new Intent(context,CreateAccountActivity.class);
        intent.putExtra(EXTRA_USER_ID,userId);
        return intent;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        UUID userId = (UUID) getIntent().getSerializableExtra(EXTRA_USER_ID);

        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragmentManager.findFragmentById(R.id.create_account_fragment_container)== null){
            fragmentManager.beginTransaction()
                    .add(R.id.create_account_fragment_container,CreateAccountFragment.newInstance(userId))
                    .commit();
        }
    }
}
