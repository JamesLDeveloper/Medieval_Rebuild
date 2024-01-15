package com.example.medievalrebuild;

public class MainActivityHolder {

    private static MainActivity mainActivity;

    public static void setMainActivity(MainActivity activity){
        mainActivity = activity;
    }

    public static MainActivity getMainActivity(){
        return mainActivity;
    }


}
