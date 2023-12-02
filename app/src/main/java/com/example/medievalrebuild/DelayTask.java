//package com.example.medievalrebuild;
//
//import android.os.AsyncTask;
//import android.util.Log;
//import android.os.SystemClock;
//
//public class DelayTask extends AsyncTask<Integer, Void, Void> {
//
//    @Override
//    protected Void doInBackground(Integer... params) {
//        int time = params[0];
//        long endTime = System.currentTimeMillis() + time;
//
//        while (System.currentTimeMillis() < endTime && !isCancelled()) {
//            SystemClock.sleep(10);
//        }
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(Void aVoid) {
//        super.onPostExecute(aVoid);
//        // Task completed
//    }
//
//    @Override
//    protected void onCancelled(){
//        super.onCancelled();
//        Log.d("DelayTask", "Task cancelled");
//    }
//
//}
