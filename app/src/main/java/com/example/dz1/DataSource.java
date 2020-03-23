package com.example.dz1;


import java.util.ArrayList;
import java.util.List;

public class DataSource {


    private static DataSource sInstance;
    private final List<MyData> mData;

    public DataSource() {
        mData = new ArrayList<>();



         int add = 101;
        for (int i=1; i<add; i++) {
            String string1 = Integer.toString(i);
            mData.add(new MyData(string1));
        }
    }


    public List<MyData> getData() {
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
