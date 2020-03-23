package com.example.dz1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.CollationElementIterator;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    private MyDataAdapter mAdapter;
    private TextView textNumb;
    List<DataSource.MyData> mData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.list);
        mAdapter = new MyDataAdapter(DataSource.getInstance().getData());
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(mAdapter);

        textNumb = findViewById(R.id.numbText);
        findViewById(R.id.button1).setOnClickListener(view -> toggleState());
      //  findViewById(R.id.numbText).setOnClickListener(view -> insert());


    }


    private void toggleState() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment top = getSupportFragmentManager().findFragmentById(R.id.fragment_top);
        Fragment bottom = getSupportFragmentManager().findFragmentById(R.id.fragment_bottom);

        if (bottom != null && bottom.isAdded()) {
            transaction.remove(bottom);
        }
        else {transaction.add(R.id.fragment_bottom, new Fragment2());
        }
        transaction.addToBackStack(null);
        transaction.commit();
    }


    class MyDataAdapter extends RecyclerView.Adapter<MyViewHolder> {

        List<DataSource.MyData> mData;
       final int TYPE_FIRST = 0;
       final  int TYPE_SECOND = 1;

        private void insert() {
            String item = "101";
           int insertIndex = 101;
            mData.add(insertIndex, new DataSource.MyData(item) );
            notifyItemInserted(insertIndex);


        }


        public MyDataAdapter(List<DataSource.MyData> data) {
            mData = data;
        }


        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            int layoutId;
            switch (viewType) {
                case TYPE_FIRST:
                    layoutId = R.layout.list_item;
                    break;
                default:
                case TYPE_SECOND:
                    layoutId = R.layout.list_item2;
                    break;
            }

            View v = LayoutInflater.
                    from(parent.getContext()).
                    inflate(layoutId, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(v);
            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.mTitle.setText(mData.get(position).mTitle);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        @Override
        public int getItemViewType(int position) {
            return (position % 2 == 0) ? TYPE_FIRST : TYPE_SECOND;
        }


        private void insert(int position,  DataSource.MyData data) {

            String item = "Pig";
            int insertIndex = 2;
            mData.add(insertIndex, new DataSource.MyData(item) );
            mAdapter.notifyItemInserted(insertIndex);

            String  k ="101";
            mData.add(101, new DataSource.MyData(k)  );
            mAdapter.notifyItemInserted(position);

            mData.clear();
            mAdapter.notifyDataSetChanged();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mTitle = itemView.findViewById(R.id.title);


            mTitle.setOnClickListener(view -> {


                int pos = getAdapterPosition();
                String stringPos = Integer.toString(pos + 1);
                textNumb.setText(stringPos);

                Fragment fragm2 = getSupportFragmentManager().findFragmentById(R.id.fragment_bottom);
                fragm2 = new Fragment2();

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.fragment_bottom, fragm2);
                transaction.addToBackStack(null);
                transaction.commit();


                if (fragm2 != null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("key", pos);
                    fragm2.setArguments(bundle);
                }

            });
        }


    }


}









