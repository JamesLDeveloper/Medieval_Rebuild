package com.example.medievalrebuild;

import android.app.Application;

public class MyApplication extends Application {
    private static MainActivity mainActivityInstance;

    public static void setMainActivityInstance(MainActivity activity) {
        mainActivityInstance = activity;
    }

    public static MainActivity getMainActivityInstance() {
        return mainActivityInstance;
    }

    public static void clearMainActivityInstance() {
        mainActivityInstance = null;
    }
}
