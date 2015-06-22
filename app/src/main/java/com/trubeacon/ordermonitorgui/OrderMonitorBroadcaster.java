package com.trubeacon.ordermonitorgui;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import com.tru.clover.api.client.param.Param;

/**
 * Created by Andrew on 5/7/15.
 */

public class OrderMonitorBroadcaster {

    public static void sendBroadcast(Enum<?> event, Param... params) {
        Intent broadcastIntent = new Intent(event.toString());
        for (Param param : params) {
            String key = param.getKey();
            Object value = param.getValue();
            if (value != null && value instanceof String) { // TODO: possibly other types worth adding
                broadcastIntent.putExtra(key, (String) value);
            }
        }
        LocalBroadcastManager.getInstance(OrderMonitorGUI.getAppContext()).sendBroadcast(broadcastIntent);
    }

    public static void registerReceiver(BroadcastReceiver broadcastReceiver, Enum<?> storeBroadcastEvent) {
        LocalBroadcastManager.getInstance(OrderMonitorGUI.getAppContext()).registerReceiver(broadcastReceiver, new IntentFilter(storeBroadcastEvent.toString()));
    }

    public static void unregisterReceiver(BroadcastReceiver broadcastReceiver) {
        LocalBroadcastManager.getInstance(OrderMonitorGUI.getAppContext()).unregisterReceiver(broadcastReceiver);
    }

}


