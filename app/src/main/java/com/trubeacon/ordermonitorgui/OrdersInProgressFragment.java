package com.trubeacon.ordermonitorgui;

import android.animation.LayoutTransition;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.text.method.ScrollingMovementMethod;
import android.transition.Transition;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tru.clover.api.client.error.*;
import com.tru.clover.api.merchant.Device;
import com.tru.clover.api.merchant.Devices;
import com.tru.clover.api.merchant.service.GetDevices;
import com.tru.clover.api.order.LineItem;
import com.tru.clover.api.order.Modification;
import com.tru.clover.api.order.Order;

import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrew on 4/30/2015.
 */

public class OrdersInProgressFragment extends Fragment {

    private List<Order> progressOrdersList = new ArrayList<>();

    private LinearLayout horizLinearLayout;
    private LayoutInflater mLayoutInflater;

    private static String DISPLAY_KEY = "display preference";
    private static String ORDER_TYPE_KEY = "order type preference";
    private static String FONT_SIZE_KEY = "font size preference";

    private static String actionBarTitle = "Orders In Progress";

    private Handler periodicUpdateHandler = new Handler();

    private static int refreshRateMs = 5000;

    private boolean twoRows;
    private boolean showOrderType;
    private boolean showOrigin;
    private float fontSize;

    private Integer screenWidthDp;
    private OrderMonitorData orderMonitorData = OrderMonitorData.getOrderMonitorData();


    private Runnable periodicUpdateRunnable = new Runnable() {

        @Override
        public void run() {
            orderMonitorData.refreshOrders();
        }
    };

    private BroadcastReceiver ordersBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            progressOrdersList = orderMonitorData.getProgressOrdersList();
            updateOrdersView();
            periodicUpdateHandler.postDelayed(periodicUpdateRunnable,refreshRateMs);

        }
    };

    @Override
    public void onResume() {
        super.onResume();
        periodicUpdateHandler.postDelayed(periodicUpdateRunnable, 0);
        OrderMonitorBroadcaster.registerReceiver(ordersBroadcastReceiver, OrderMonitorData.BroadcastEvent.REFRESH_ORDERS);
    }

    @Override
    public void onPause() {
        super.onPause();
        periodicUpdateHandler.removeCallbacks(periodicUpdateRunnable);
        OrderMonitorBroadcaster.unregisterReceiver(ordersBroadcastReceiver);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());

        String mId = sharedPref.getString(getString(R.string.merchant_id_key),"");
        String token = sharedPref.getString(getString(R.string.saved_token_key),"");

        if(mId!=null&&token!=null) {
            CloverService.getService().getDevices(mId,token, new GetDevices.GetDevicesCallback(){
                @Override
                public void onGetDevices(Devices devices) {
                    ArrayList<Device> deviceList = new ArrayList<Device>(devices.getDevices());
                    for(Device dev:deviceList){
                        if(dev.getName()!=null) {
                            orderMonitorData.addDevicetoHash(dev.getUuid(), dev.getName());
                        }else{
                            orderMonitorData.addDevicetoHash(dev.getUuid(), dev.getModel());
                        }
                    }
                }

                @Override
                public void onFailGetDevices(com.tru.clover.api.client.error.Error error) {
                    Log.v("getdevices failed",error.getMessage());

                }
            });
        }


        View scrollView = inflater.inflate(R.layout.fragment_orders_in_progress, container, false);

        mLayoutInflater = inflater;

        horizLinearLayout = (LinearLayout) scrollView.findViewById(R.id.horiz_lin_layout);


        //find screen width to set width of relative layout
        DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
        screenWidthDp = (int) (displayMetrics.widthPixels / displayMetrics.density);

        //get display preferences

        String ordersToDisplay = sharedPref.getString(DISPLAY_KEY, "");

        if (ordersToDisplay.equals(getString(R.string.five))) {
            twoRows = false;
        } else {
            twoRows = true;
        }

        String fontSizeString = sharedPref.getString(FONT_SIZE_KEY, "");

        fontSize = Integer.parseInt(fontSizeString);

        showOrderType = sharedPref.getBoolean(getString(R.string.display_order_type_pref),true);
        showOrigin = sharedPref.getBoolean(getString(R.string.display_device_pref),true);

        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(actionBarTitle);
        actionBar.setDisplayHomeAsUpEnabled(false);


        return scrollView;
    }

    private void updateOrdersView() {

        if (twoRows) {
            updateTenOrdersView();
        } else {
            updateFiveOrdersView();
        }
    }


    private void updateTenOrdersView(){

        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
                screenWidthDp/5,
                ViewGroup.LayoutParams.MATCH_PARENT);

        horizLinearLayout.removeAllViews();

        int listsize = progressOrdersList.size();
        int pages;

        if(listsize%10==0){
            pages = listsize/10;
        }
        else{
            pages = listsize/10+1;
        }


        int frames = pages*5;
        int pagenumber =0;

        for(int f = 1;f<=frames;f++) {

            LinearLayout linearLayout = new LinearLayout(getActivity());
            linearLayout.setLayoutParams(linearLayoutParams);

            horizLinearLayout.addView(linearLayout);
            mLayoutInflater.inflate(R.layout.order_item_layout2, linearLayout);

            RelativeLayout relativeLayoutTop = (RelativeLayout) linearLayout.findViewById(R.id.order_item_top);
            RelativeLayout relativeLayoutBottom = (RelativeLayout) linearLayout.findViewById(R.id.order_item_bottom);

            int topId;
            if(f%5==0){
                topId = pagenumber*10+5;
            }else {
                topId = pagenumber * 10 + f % 5;
            }

            int bottomId = topId+5;

            if(topId<=listsize) {

                relativeLayoutTop.setVisibility(View.VISIBLE);

                Order topOrder = progressOrdersList.get(topId - 1);


                String label = "";

                if(topOrder.getOrderType()!=null&&showOrderType) {
                    label = topOrder.getOrderType().getLabel();
                }

                String origin = "";

                if(topOrder.getDevice().getId()!=null&&showOrigin){
                    origin = orderMonitorData.getDeviceNamefromId(topOrder.getDevice().getId());
                }

                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());

                int titleBgColor1 = getResources().getColor(R.color.background);
                if(topOrder.getOrderType()!=null) {
                    String titleColor1 = sp.getString(topOrder.getOrderType().getLabel(), getString(R.string.backgroundcode));
                    titleBgColor1 = Color.parseColor(titleColor1);
                }

                int bodyBgColor1 = getResources().getColor(R.color.white);
                if(topOrder.getDevice()!=null){
                    String bodyColor1 = sp.getString(topOrder.getDevice().getId(),getString(R.string.white));
                    bodyBgColor1 = Color.parseColor(bodyColor1);
                }

                String detailString = "";

                List<LineItem> lineItemList= topOrder.getLineItems();

                if(lineItemList!=null) {
                    for (LineItem li : lineItemList) {
                        detailString = detailString + li.getName() + "\r\n";
                        List<Modification> modList = li.getModifications();
                        //check for modifications to line item
                        if(modList!=null){
                            for(Modification mo:modList){
                                detailString = detailString + " -" + mo.getName()+"\r\n";
                            }
                        }
                        //check for custom modification
                        if(li.getNote()!=null) {
                            detailString = detailString + " -" + li.getNote()+"\r\n";
                        }
                    }
                }

                TextView orderTitleText = (TextView) relativeLayoutTop.findViewById(R.id.order_id_text);
                TextView orderDetailText = (TextView) relativeLayoutTop.findViewById(R.id.order_detail_text);
                Button topDoneButton = (Button) relativeLayoutTop.findViewById(R.id.done_button);
                orderTitleText.setTextSize(fontSize);
                orderTitleText.setBackgroundColor(titleBgColor1);
                orderDetailText.setTextSize(fontSize);
                orderDetailText.setBackgroundColor(bodyBgColor1);
                topDoneButton.setBackgroundColor(titleBgColor1);

                orderDetailText.setMovementMethod(new ScrollingMovementMethod());

                DateTime orderCreated = new DateTime(topOrder.getCreatedTime());
                String timeCreatedString = DateTimeFormat.forPattern("hh:mm:ss a").print(orderCreated);

                //TODO:check again here for showing order and origin
                if(showOrderType) {
                    timeCreatedString = timeCreatedString + "\r\n" + label;
                }

                if(showOrigin){
                    timeCreatedString = timeCreatedString + "\r\n" + origin;
                }

                orderTitleText.setText(timeCreatedString);
                orderDetailText.setText(detailString);

                //add a index as a tag to reference the order in the click listener
                topDoneButton.setTag(topId);

                topDoneButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        RelativeLayout parentView = (RelativeLayout) v.getParent();

                        LayoutTransition lt = new LayoutTransition();
                        lt.enableTransitionType(LayoutTransition.CHANGING);
                        lt.addTransitionListener(new LayoutTransition.TransitionListener() {

                            @Override
                            public void startTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {

                            }

                            @Override
                            public void endTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {

                            }
                        });

                        Order doneOrder = progressOrdersList.get((Integer) v.getTag() - 1);

                        //progressOrdersList.remove((Integer) v.getTag() - 1);

                        orderMonitorData.markDone(doneOrder.getId(),doneOrder);
                        parentView.setLayoutTransition(lt);
                        v.setVisibility(View.INVISIBLE);

                    }
                });

                if (bottomId <=listsize) {
                    relativeLayoutBottom.setVisibility(View.VISIBLE);
                    //minus one index for zero-based list
                    Order bottomOrder = progressOrdersList.get(bottomId-1);
                    String detailString2 = "";

                    String label2 = "";

                    if(bottomOrder.getOrderType()!=null&&showOrderType) {
                        label2 = bottomOrder.getOrderType().getLabel();
                    }

                    String origin2 = "";

                    if(bottomOrder.getDevice().getId()!=null&&showOrigin){
                        origin2 = orderMonitorData.getDeviceNamefromId(bottomOrder.getDevice().getId());
                    }

                    int titleBgColor2 = getResources().getColor(R.color.background);
                    if(bottomOrder.getOrderType()!=null) {
                        String titleColor2 = sp.getString(bottomOrder.getOrderType().getLabel(), getString(R.string.backgroundcode));
                        titleBgColor2 = Color.parseColor(titleColor2);
                    }

                    int bodyBgColor2 = getResources().getColor(R.color.white);
                    if(bottomOrder.getDevice()!=null){
                        String bodyColor2 = sp.getString(bottomOrder.getDevice().getId(),getString(R.string.white));
                        bodyBgColor2 = Color.parseColor(bodyColor2);
                    }

                    TextView orderTitleText2 = (TextView) relativeLayoutBottom.findViewById(R.id.order_id_text2);
                    TextView orderDetailText2 = (TextView) relativeLayoutBottom.findViewById(R.id.order_detail_text2);
                    Button bottomDoneButton = (Button) relativeLayoutBottom.findViewById(R.id.done_button2);

                    orderTitleText2.setTextSize(fontSize);
                    orderTitleText2.setBackgroundColor(titleBgColor2);
                    orderDetailText2.setTextSize(fontSize);
                    orderDetailText2.setBackgroundColor(bodyBgColor2);
                    bottomDoneButton.setBackgroundColor(titleBgColor2);

                    orderDetailText2.setMovementMethod(new ScrollingMovementMethod());

                    DateTime orderCreated2 = new DateTime(bottomOrder.getCreatedTime());
                    String timeCreatedString2 = DateTimeFormat.forPattern("hh:mm:ss a").print(orderCreated2);

                    if(showOrderType) {
                        timeCreatedString2 = timeCreatedString2 + "\r\n" + label2;
                    }

                    if(showOrigin){
                        timeCreatedString2 = timeCreatedString2 + "\r\n" + origin2;
                    }

                    orderTitleText2.setText(timeCreatedString2);

                    List<LineItem> lineItemList2 = bottomOrder.getLineItems();

                    if(lineItemList2!=null) {
                        for (LineItem li2 : lineItemList2) {
                            detailString2 = detailString2 + li2.getName() + "\r\n";
                            if(li2.getModifications()!=null){
                                for(Modification mo2:li2.getModifications()){
                                    detailString2 = detailString2 + " -" + mo2.getName() + "\r\n";
                                }
                            }
                            if(li2.getNote()!=null){
                                detailString2 = detailString2 + " -" + li2.getNote() + "\r\n";
                            }
                        }
                    }

                    orderDetailText2.setText(detailString2);

                    bottomDoneButton.setTag(bottomId);

                    bottomDoneButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            RelativeLayout parentView = (RelativeLayout) v.getParent();

                            LayoutTransition lt = new LayoutTransition();
                            lt.enableTransitionType(LayoutTransition.CHANGE_DISAPPEARING);
                            lt.addTransitionListener(new LayoutTransition.TransitionListener() {

                                @Override
                                public void startTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {

                                }

                                @Override
                                public void endTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {

                                }
                            });

                            //remove the order from the list (subtract one to reference the zero-based list)

                            Order doneOrder = progressOrdersList.get((Integer) v.getTag() - 1);

                            orderMonitorData.markDone(doneOrder.getId(),doneOrder);
                            parentView.setLayoutTransition(lt);
                            v.setVisibility(View.INVISIBLE);
                        }
                    });
                }
            }

            if(f%5==0){pagenumber++;}
        }
    }

    private void updateFiveOrdersView(){

        RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(
                screenWidthDp/5,
                ViewGroup.LayoutParams.MATCH_PARENT);

        horizLinearLayout.removeAllViews();

        for(int i =1;i<=progressOrdersList.size();i++){

            Order thisOrder = progressOrdersList.get(i - 1);

            String label = "";

            if(thisOrder.getOrderType()!=null&&showOrderType) {
                label = thisOrder.getOrderType().getLabel();
            }

            String origin = "";

            if(thisOrder.getDevice().getId()!=null&&showOrigin){
                origin = orderMonitorData.getDeviceNamefromId(thisOrder.getDevice().getId());
            }

            RelativeLayout relativeLayout= new RelativeLayout(getActivity());
            relativeLayout.setLayoutParams(relativeLayoutParams);
            horizLinearLayout.addView(relativeLayout);
            mLayoutInflater.inflate(R.layout.order_item_layout1, relativeLayout);

            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());

            int titleBgColor = getResources().getColor(R.color.background);
            if(thisOrder.getOrderType()!=null) {
                String titleColor = sp.getString(thisOrder.getOrderType().getLabel(), getString(R.string.backgroundcode));
                titleBgColor = Color.parseColor(titleColor);
            }

            int bodyBgColor = getResources().getColor(R.color.white);
            if(thisOrder.getDevice()!=null){
                String bodyColor = sp.getString(thisOrder.getDevice().getId(),getString(R.string.white));
                bodyBgColor = Color.parseColor(bodyColor);
            }

            TextView orderTitleText = (TextView) relativeLayout.findViewById(R.id.order_id_text);
            TextView orderDetailText = (TextView) relativeLayout.findViewById(R.id.order_detail_text);
            Button doneButton = (Button) relativeLayout.findViewById(R.id.done_button);

            orderTitleText.setTextSize(fontSize);
            orderTitleText.setBackgroundColor(titleBgColor);
            orderDetailText.setTextSize(fontSize);
            orderDetailText.setBackgroundColor(bodyBgColor);
            doneButton.setBackgroundColor(titleBgColor);

            orderDetailText.setMovementMethod(new ScrollingMovementMethod());

            DateTime orderCreated = new DateTime(thisOrder.getCreatedTime());
            String timeCreatedString = DateTimeFormat.forPattern("hh:mm:ss a").print(orderCreated);

            if(showOrderType) {
                timeCreatedString = timeCreatedString + "\r\n" + label;
            }

            if(showOrigin){
                timeCreatedString = timeCreatedString + "\r\n" + origin;
            }

            orderTitleText.setText(timeCreatedString);

            List<LineItem> lineItemList = thisOrder.getLineItems();

            String detailString = "";

            if(lineItemList!=null) {
                for (LineItem li : lineItemList) {
                    detailString = detailString + li.getName() + "\r\n";
                    List<Modification> modList = li.getModifications();
                    if(modList!=null){
                        for(Modification mo:modList){
                            detailString = detailString + " -" + mo.getName()+"\r\n";
                        }
                    }
                    if(li.getNote()!=null){
                        detailString = detailString + " -" + li.getNote() + "\r\n";
                    }
                }
            }

            orderDetailText.setText(detailString);

            doneButton.setTag(i);

            doneButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    RelativeLayout parentView = (RelativeLayout) v.getParent();

                    LayoutTransition lt = new LayoutTransition();
                    lt.enableTransitionType(LayoutTransition.CHANGE_DISAPPEARING);
                    lt.addTransitionListener(new LayoutTransition.TransitionListener() {

                        @Override
                        public void startTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {

                        }

                        @Override
                        public void endTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {

                        }
                    });

                    //remove the order from the list (subtract one to reference the zero-based list)

                    Order doneOrder = progressOrdersList.get((Integer) v.getTag() - 1);

                    orderMonitorData.markDone(doneOrder.getId(),doneOrder);

                    parentView.setLayoutTransition(lt);
                    v.setVisibility(View.INVISIBLE);
                }
            });

        }

    }

}
