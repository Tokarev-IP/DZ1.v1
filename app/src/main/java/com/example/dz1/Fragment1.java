package com.example.dz1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class Fragment1 extends BaseFragment {

    public static final Integer NUMBER =101;
    public static final String MY_EXTRA ="my_extra";
    private static final String STATE = "state";
    private MyDataAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflatedView = inflater.inflate(R.layout.fragment_1, container, false);
        RecyclerView recyclerView = inflatedView.findViewById(R.id.list);                                 //находим ресайкл

        mAdapter = new MyDataAdapter(DataSource.getInstance().getData());


        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), getResources().getInteger(R.integer.col_clount)));           //говорим, какой лэйаут менеджер использовать
         
                
        recyclerView.setAdapter(mAdapter);                                                              //устанавливаем адаптер

        Button addButton =  inflatedView.findViewById(R.id.add_button);

        addButton.setOnClickListener(v -> {
            int index = DataSource.getInstance().getData().size();
            String index_string = Integer.toString(index+1);
            DataSource.getInstance().getData().add(new DataSource.MyData(index_string));
            mAdapter.notifyItemInserted(index);
        });

        if (savedInstanceState != null) {
            String string_cash = savedInstanceState.getString(STATE);
            int data = DataSource.getInstance().getData().size();
            int int_cash = Integer.parseInt(string_cash);

            if (int_cash!=data){
                for (int i=NUMBER; i<=int_cash; i++) {
                    String string_i = Integer.toString(i);
                    DataSource.getInstance().getData().add(new DataSource.MyData(string_i));
                }
            }
        }

        return inflatedView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String cash = Integer.toString(DataSource.getInstance().getData().size());
        outState.putString(STATE, cash);
    }


    class MyDataAdapter extends RecyclerView.Adapter<MyViewHolder> {  //создаем класс адаптера (типизирован ViewHolder'ом( его дженерик))

        List<DataSource.MyData> mData;  //поле у адаптера, которую мы будем записывать в конструкторе
        final int TYPE_FIRST = 0;
        final  int TYPE_SECOND = 1;

        public MyDataAdapter(List<DataSource.MyData> data) {  //конструктор, куда будут передаваться наши данные
            mData = data;
        }


        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {  //создаем наш ViewHolder
            Log.d("ListActivity", "onCreateViewHolder");
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

            View v = LayoutInflater
                    .from(parent.getContext())
                    .inflate(layoutId, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(v);   //вызываем конструктор ViewHolder'a
            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Log.d("ListActivity", "onBindViewHolder  " + position);
            holder.mTitle.setText(mData.get(position).mTitle);  //ставим текст в title (данные из адаптера установить в View)
        }  //mTitle - данные



        @Override
        public int getItemCount() {
            return mData.size();
        }

        @Override
        public int getItemViewType(int position) {
            return (position % 2 == 0) ? TYPE_FIRST : TYPE_SECOND;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTitle;

        public MyViewHolder(@NonNull View itemView) {  //ищем View, которые используем
            super(itemView);

            mTitle = itemView.findViewById(R.id.title);

            mTitle.setOnClickListener(view -> {

                int pos = getAdapterPosition();


               Fragment fragment2 = new Fragment2();

                Bundle bundle = new Bundle();
                bundle.putInt(MY_EXTRA, pos);
                fragment2.setArguments(bundle);

                if (getFragmentManager()!=null) {
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, fragment2);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }

            });
        }
    }
    }




