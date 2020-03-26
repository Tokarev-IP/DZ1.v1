package com.example.dz1;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    private static DataSource sInstance;
    private final List<MyData> mData; //создаем лист даты

    public DataSource() {  //в конструкторе его создаем

        mData = new ArrayList<>();
        for (int i=1; i<101; i++) {
            String string1 = Integer.toString(i);
            mData.add(new MyData(string1));
        }

    }


    public List<MyData> getData() {  //метод возврата даты
        return mData;
    }

    public synchronized static DataSource getInstance(){
        if (sInstance == null){
            sInstance = new DataSource();
        }
        return sInstance;
    }


    public static class MyData{

        public MyData(String title) {
            mTitle = title;
        }

        String mTitle;

    }


}

