package com.example.dz1;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    public static final Integer MIN =1;
    public static final Integer MAX =100;

    private static DataSource sInstance;
    private final List<MyData> mData; //создаем лист даты

    public DataSource() {  //в конструкторе его создаем
        mData = new ArrayList<>();
        for (int i=MIN; i<=MAX; i++) {
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

