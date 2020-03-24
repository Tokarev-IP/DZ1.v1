package com.example.dz1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Fragment2 extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflatedView = inflater.inflate(R.layout.fragment_2, container, false);
      //  mButton = (Button) inflatedView.findViewById(R.id.setNumber);
        TextView mTextView = inflatedView.findViewById(R.id.numberText);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            int myInt = bundle.getInt("key");
            String stringPos = Integer.toString(myInt + 1);
            mTextView.setText(stringPos);
        }
        return inflatedView;
    }
}








     /* class Fragment21 extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView mTextView;

        mTextView = findViewById(R.id.numberText);

        Intent intent = getIntent();
        int extraInt = intent.getIntExtra(MainActivity.MY_EXTRA, -1);
        String stringPos = Integer.toString(extraInt + 1);
        mTextView.setText(stringPos);
    }
}
      */

/*
    @Override
    public void onActivityDataListener(String string) {


        Button mButton;
        TextView mTextView;

        View inflatedView = inflater.inflate(R.layout.fragment_2, container, false);
        mButton = (Button) inflatedView.findViewById(R.id.setNumber);
        mTextView = (TextView) inflatedView.findViewById(R.id.numberText);
        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mTextView.setText("парапарап");
            }
        });
        return inflatedView;
    }

    }

*/






