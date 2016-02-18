package com.trubeacon.ordermonitorgui;

import android.app.Application;
import android.content.Context;


/**
 * Created by Andrew on 5/1/2015.
 */
public class OrderMonitorGUI extends Application {

    private static Context context;


    public void onCreate() {

        super.onCreate();
        OrderMonitorGUI.context = getApplicationContext();
    }

    public static Context getAppContext(){
        return context;
    }


    public static Context context() {
        return OrderMonitorGUI.context;
    }


}
