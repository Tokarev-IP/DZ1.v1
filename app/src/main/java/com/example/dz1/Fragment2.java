package com.example.dz1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentTransaction;


public class Fragment2 extends BaseFragment {

    public static final String MY_EXTRA ="my_extra";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflatedView = inflater.inflate(R.layout.fragment_2, container, false);

        TextView mTextView1 = inflatedView.findViewById(R.id.numberText1);
        TextView mTextView2 = inflatedView.findViewById(R.id.numberText2);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            int myInt = bundle.getInt(MY_EXTRA);
            String stringPos = Integer.toString(myInt + 1);
            if (myInt % 2 == 0) {
                mTextView2.setText(stringPos);
            }
            else mTextView1.setText(stringPos);
        }

        Button goButton =  inflatedView.findViewById(R.id.go_back_button);
        goButton.setOnClickListener(v ->{

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new Fragment1());
            transaction.addToBackStack(null);
            transaction.commit();
                });

        return inflatedView;
    }
}






