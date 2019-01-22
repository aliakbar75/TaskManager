package com.example.moein.taskmanager;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchDialogFragment extends DialogFragment {

    public static final String EXTRA_SEARCH = "searched";
    private EditText mSearchEditText;
    private Button mSearchButton;
    private Button mCancelButton;


    public static SearchDialogFragment newInstance() {

        Bundle args = new Bundle();

        SearchDialogFragment fragment = new SearchDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public SearchDialogFragment() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.fragment_search_dialog, container, false);
        mSearchEditText = view.findViewById(R.id.search_edit_text);
        mSearchButton = view.findViewById(R.id.search_button);
        mCancelButton = view.findViewById(R.id.cancel_button);

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = mSearchEditText.getText().toString();
                sendResult(searchText);
                dismiss();
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }

    private void sendResult(String searchText) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_SEARCH, searchText);
        getTargetFragment().
                onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
    }

}
