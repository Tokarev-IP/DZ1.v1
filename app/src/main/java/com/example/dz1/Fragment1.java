package com.example.dz1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class Fragment1 extends BaseFragment {

    private MyDataAdapter mAdapter;
    List<DataSource.MyData> mData;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflatedView = inflater.inflate(R.layout.fragment_1, container, false);
        RecyclerView recyclerView = inflatedView.findViewById(R.id.list);                                 //находим ресайкл

        mAdapter = new MyDataAdapter(DataSource.getInstance().getData());                         //создаем адаптер
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));         //говорим, какой лэйаут менеджер использовать
        recyclerView.setAdapter(mAdapter);                                                        //устанавливаем адаптер

        return inflatedView;
    }

    class MyDataAdapter extends RecyclerView.Adapter<MyViewHolder> {  //создаем класс адаптера (типизирован ViewHolder'ом( его дженерик))

        List<DataSource.MyData> mData;  //поле у адаптера, которую мы будем записывать в конструкторе
        final int TYPE_FIRST = 0;
        final  int TYPE_SECOND = 1;

        /*
        private void insert() {
            String item = "101";
            int insertIndex = 101;
            mData.add(insertIndex, new DataSource.MyData(item) );
            notifyItemInserted(insertIndex);
        }

         */


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

/*
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
 */
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTitle;

        public MyViewHolder(@NonNull View itemView) {  //ищем View, которые используем
            super(itemView);

            mTitle = itemView.findViewById(R.id.title);

            mTitle.setOnClickListener(view -> {

                int pos = getAdapterPosition();

                Bundle bundle = new Bundle();
                bundle.putInt("key", pos);
                new Fragment2().setArguments(bundle);

                if (getFragmentManager()!=null) {
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, new Fragment2());
                    transaction.addToBackStack(null);
                    transaction.commit();
                }

            });
        }

    }

    }




