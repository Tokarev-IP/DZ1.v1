package com.example.dz1;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    private static final String STATE = "text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (savedInstanceState == null) {
            transaction.add(R.id.fragment_container, new Fragment1());
            transaction.commit();
        }

        if (savedInstanceState != null) {
            String string_cash = savedInstanceState.getString(STATE);
            int data = DataSource.getInstance().getData().size();
            int int_cash = Integer.parseInt(string_cash);

            if (int_cash!=data){
            for (int i=1; i<=int_cash-100; i++) {
                String string_i = Integer.toString(i + 100);
                DataSource.getInstance().getData().add(new DataSource.MyData(string_i));
            }
            }
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String cash = Integer.toString(DataSource.getInstance().getData().size());
        outState.putString(STATE, cash);
    }

}






