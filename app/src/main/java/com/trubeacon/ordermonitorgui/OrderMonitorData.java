package com.trubeacon.ordermonitorgui;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.tru.clover.api.client.error.*;
import com.tru.clover.api.client.filter.Filter;
import com.tru.clover.api.common.WrappedList;
import com.tru.clover.api.order.Order;
import com.tru.clover.api.order.service.GetOrders;
import com.tru.clover.api.order.service.UpdateOrder;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class OrderMonitorData {

    private static OrderMonitorData orderMonitorData;
    private static List<Order> progressOrdersList = new ArrayList<>();
    private static List<Order> doneOrdersList = new ArrayList<>();
    private static Context mContext;

    //75314KYGAMC4P

//Scooters merchant credentials
    private static String mId = "75314KYGAMC4P";
    private static String token = "02da5d87-8240-2a5d-38d0-f6bfa4e8627d";

//TruBeacon, Inc merchant credentials
    //private static String mId = "N0H6K4ZCTSW1P";
    //private static String token = "77b77258-4de3-cf35-2c4f-d856ba598cec";

    //private static String mId = "";
    //private static String token = "";

    private static String ORDER_DONE_KEY = "#trubeacon_ordermonitor_done";

    public static OrderMonitorData getOrderMonitorData(){
        if(orderMonitorData==null){
            orderMonitorData = new OrderMonitorData();
            mContext = OrderMonitorGUI.getAppContext();
        }
        return orderMonitorData;
    }
    public List<Order> getDoneOrdersList(){
        return doneOrdersList;
    }

    public static List<Order> getProgressOrdersList(){
        return new ArrayList<>(progressOrdersList);
    }


    public void refreshOrders(){

        DateTime start = DateTime.now().minusMinutes(30);
        DateTime stop = DateTime.now();

        //get merchant id and token from shared preferences
//        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(OrderMonitorGUI.getAppContext());
//        mId = sp.getString(mContext.getString(R.string.merchant_id_key),mContext.getString(R.string.default_merchant));
//        token = sp.getString(mContext.getString(R.string.saved_token_key),mContext.getString(R.string.default_token));

        Log.v("merchant Id",mId);
        Log.v("token",token);


        if(mId.equals(mContext.getString(R.string.default_merchant))||token.equals(mContext.getString(R.string.default_token))){
            Toast.makeText(OrderMonitorGUI.getAppContext(), "Please connect to your Clover account from the Settings menu", Toast.LENGTH_LONG).show();
        }else {

            // fetch all orders from the last 6 months
            CloverService.getService().getOrders(mId, token, new GetOrders.GetOrdersCallback() {

                        @Override
                        public void onGetOrders(WrappedList<Order> orders) {

                            Log.v("orders fetched", String.valueOf(orders.size()));

                            List<Order> allOrders = new ArrayList<Order>(orders);
                            updateProgressOrdersList(allOrders);

                            //show oldest orders first, should come in chronological order with most recent order first, so reverse the list
                            Collections.reverse(progressOrdersList);
                            Collections.reverse(doneOrdersList);

                            OrderMonitorBroadcaster.sendBroadcast(BroadcastEvent.REFRESH_ORDERS);

                        }

                        @Override
                        public void onFailGetOrders(com.tru.clover.api.client.error.Error error) {
                            Log.e("Failed to fetch orders", error.getMessage());
                        }

                    },
                    Filter.filter("clientCreatedTime", Filter.Comparator.GREATER_THAN, start.getMillis()),
                    Filter.filter("clientCreatedTime", Filter.Comparator.LESS_THAN, stop.getMillis()));
        }
    }

    //TODO: add a try catch here...not having the permissions caused the app to crash
    public void markDone(String orderId,Order updateOrder){

        updateOrder.setNote(ORDER_DONE_KEY);

        CloverService.getService().updateOrder(mId, token, orderId, updateOrder, new UpdateOrder.UpdateOrderCallback() {
            @Override
            public void onUpdateOrder(Order order) {
                Log.v("order updated", "order updated");
            }

            @Override
            public void onFailUpdateOrder(com.tru.clover.api.client.error.Error error) {
                Log.v("failed to update order", error.getMessage());
            }
        });
    }

    public void markUnDone(String orderId,Order updateOrder){

        updateOrder.setNote("");

        CloverService.getService().updateOrder(mId, token, orderId, updateOrder, new UpdateOrder.UpdateOrderCallback() {
            @Override
            public void onUpdateOrder(Order order) {
                Log.v("order updated","order updated");
            }

            @Override
            public void onFailUpdateOrder(com.tru.clover.api.client.error.Error error) {
                Log.v("failed to update order", error.getMessage());
            }
        });
    }

    private void updateProgressOrdersList(List<Order> allOrders){

        if(allOrders!=null) {

            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(OrderMonitorGUI.getAppContext());

            boolean showDineIn = sp.getBoolean(mContext.getString(R.string.dine_in_preference), true);
            boolean showToGo = sp.getBoolean(mContext.getString(R.string.to_go_preference), true);
            boolean showCatering = sp.getBoolean(mContext.getString(R.string.catering_preference), true);
            boolean showPhoneIn = sp.getBoolean(mContext.getString(R.string.phone_in_preference), true);

            List<Order> filteredList = new ArrayList<>();

            for (int i = 0; i < allOrders.size(); i++) {

                if(allOrders.get(i).getOrderType()!=null) {

                    boolean isDineIn = allOrders.get(i).getOrderType().getLabel().equals(mContext.getString(R.string.dine_in));
                    boolean isToGo = allOrders.get(i).getOrderType().getLabel().equals(mContext.getString(R.string.to_go));
                    boolean isCatering = allOrders.get(i).getOrderType().getLabel().equals(mContext.getString(R.string.catering));
                    boolean isPhoneIn = allOrders.get(i).getOrderType().getLabel().equals(mContext.getString(R.string.phone_in));

                    if (showDineIn) {
                        if (isDineIn) {
                            filteredList.add(allOrders.get(i));
                        }
                    }
                    if (showToGo) {
                        if (isToGo) {
                            filteredList.add(allOrders.get(i));
                        }
                    }
                    if (showCatering) {
                        if (isCatering) {
                            filteredList.add(allOrders.get(i));
                        }
                    }
                    if (showPhoneIn) {
                        if (isPhoneIn) {
                            filteredList.add(allOrders.get(i));
                        }
                    }
                    if(!isDineIn&&!isToGo&&!isCatering&&!isPhoneIn){
                        filteredList.add(allOrders.get(i));
                    }
                }
                else {
                    filteredList.add(allOrders.get(i));
                }
            }

            progressOrdersList.clear();
            doneOrdersList.clear();

            for (Order o : filteredList) {
                if (o.getNote() != null) {
                    if (o.getNote().equals(ORDER_DONE_KEY)) {
                        doneOrdersList.add(o);
                    } else {
                        progressOrdersList.add(o);
                    }
                } else {
                    progressOrdersList.add(o);
                }
            }
        }
    }


    public enum BroadcastEvent{

        REFRESH_ORDERS;

        public static final String REFRESH_ORDERS_VALUE = "REFRESH_ORDERS";

    }

}
