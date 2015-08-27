package com.trubeacon.ordermonitorgui;

import android.animation.LayoutTransition;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tru.clover.api.client.error.*;
import com.tru.clover.api.client.error.Error;
import com.tru.clover.api.common.WrappedList;
import com.tru.clover.api.inventory.Modifier;
import com.tru.clover.api.inventory.Tag;
import com.tru.clover.api.inventory.service.GetTags;
import com.tru.clover.api.merchant.Device;
import com.tru.clover.api.merchant.Devices;
import com.tru.clover.api.merchant.service.GetDevices;
import com.tru.clover.api.order.LineItem;
import com.tru.clover.api.order.Modification;
import com.tru.clover.api.order.Order;

import org.apache.commons.lang3.builder.HashCodeBuilder;
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
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * Created by Andrew on 4/30/2015.
 */

public class OrdersInProgressFragment extends Fragment {

    private List<Order> progressOrdersList = new ArrayList<>();
    private HashMap<String,Order> currentOrderHashMap = new HashMap<String,Order>();

    private LinearLayout horizLinearLayout;
    private HorizontalScrollView scrollView;
    private LayoutInflater mLayoutInflater;
    private List<Button> buttonList = new ArrayList<>();

    private static String actionBarTitle = "Orders In Progress";

    private Handler periodicUpdateHandler = new Handler();

    private Handler countdownHandler = new Handler();

    private static int refreshRateMs = 5000;

    private boolean twoRows;
    private boolean showOrderType;
    private boolean showOrigin;
    private float fontSize;
    private boolean showTimer;
    private TreeMap<String,List<LineItem>> binTreeMap = new TreeMap<String,List<LineItem>>();

    private DateTime startPoll = new DateTime();
    private DateTime lastpolled = DateTime.now().minusMinutes(360);

    private Integer screenWidthDp;
    private OrderMonitorData orderMonitorData = OrderMonitorData.getOrderMonitorData();

    private List<TextView> countdownTvList = new ArrayList<>();


    private Runnable updateCountdown = new Runnable() {
        @Override
        public void run() {
            long orderCreatedTime;

            for(TextView tv:countdownTvList){
                orderCreatedTime = (long) tv.getTag();
                DateTime orderCreated = new DateTime(orderCreatedTime);
                int seconds = Seconds.secondsBetween(orderCreated,DateTime.now()).getSeconds();
                int minutes = seconds/60;
                int sec = seconds%60;
                String secondsString = null;
                if(sec<10){
                    secondsString = "0" + String.valueOf(sec);
                }else{
                    secondsString = String.valueOf(sec);
                }
                tv.setText(String.valueOf(minutes)+":"+secondsString);
            }
            countdownHandler.postDelayed(updateCountdown,100);
        }
    };


    private Runnable periodicUpdateRunnable = new Runnable() {

        @Override
        public void run() {
            startPoll = DateTime.now();
            Log.v("now", String.valueOf(startPoll.getMillis()));
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
        countdownHandler.postDelayed(updateCountdown, 0);
        OrderMonitorBroadcaster.registerReceiver(ordersBroadcastReceiver, OrderMonitorData.BroadcastEvent.REFRESH_ORDERS);
    }

    @Override
    public void onPause() {
        super.onPause();
        periodicUpdateHandler.removeCallbacks(periodicUpdateRunnable);
        countdownHandler.removeCallbacks(updateCountdown);
        OrderMonitorBroadcaster.unregisterReceiver(ordersBroadcastReceiver);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.progress_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if(itemId == R.id.clear_all){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(getString(R.string.clear_all_alert))
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            for (Button b : buttonList) {
                                b.setVisibility(View.INVISIBLE);
                            }
                            currentOrderHashMap.clear();
                            horizLinearLayout.removeAllViews();
                            orderMonitorData.markAllOrdersDone();
                            dialogInterface.dismiss();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //do nothing
                            dialogInterface.cancel();
                        }
                    });
            builder.create().show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        lastpolled = DateTime.now().minusMinutes(360);

        currentOrderHashMap.clear();

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());

        String mId = sharedPref.getString(getString(R.string.merchant_id_key), "");
        String token = sharedPref.getString(getString(R.string.saved_token_key), "");

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

            CloverService.getService().getTags(mId, token, new GetTags.GetTagsCallback() {
                @Override
                public void onGetTags(WrappedList<Tag> wrappedList) {
                    orderMonitorData.setTagList(wrappedList);
                }

                @Override
                public void onFailGetTags(Error error) {
                    Log.v("failed to get tags",error.getMessage());
                }
            });
        }

        View scrollAndClearBtnLinLay = inflater.inflate(R.layout.fragment_orders_in_progress, container, false);

        scrollView = (HorizontalScrollView) scrollAndClearBtnLinLay.findViewById(R.id.scroll_view);

        mLayoutInflater = inflater;

        horizLinearLayout = (LinearLayout) scrollView.findViewById(R.id.horiz_lin_layout);

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        screenWidthDp = dm.widthPixels;

        //get display preferences

        String ordersToDisplay = sharedPref.getString(getString(R.string.display_pref), "5");

        if (ordersToDisplay.equals(getString(R.string.five))) {
            twoRows = false;
        } else {
            twoRows = true;
        }

        String fontSizeString = sharedPref.getString(getString(R.string.font_size_pref), "30");

        fontSize = Integer.parseInt(fontSizeString);

        showOrderType = sharedPref.getBoolean(getString(R.string.display_order_type_pref), true);
        showOrigin = sharedPref.getBoolean(getString(R.string.display_device_pref),true);
        showTimer = sharedPref.getBoolean(getString(R.string.order_timer_pref),true);

        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(actionBarTitle);
        actionBar.setDisplayHomeAsUpEnabled(false);

        return scrollAndClearBtnLinLay;
    }


    private void addViewToHorizLinearLayout(int i,String orderId){

        //set the order tag to the id
        final LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
                screenWidthDp/5,
                ViewGroup.LayoutParams.MATCH_PARENT);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());


        final Order thisOrder = currentOrderHashMap.get(orderId);

        String label = "";

        if(thisOrder.getOrderType()!=null&&showOrderType) {
            label = thisOrder.getOrderType().getLabel();
        }

        String origin = "";

        if(thisOrder.getDevice().getId()!=null&&showOrigin){
            origin = orderMonitorData.getDeviceNamefromId(thisOrder.getDevice().getId());
        }

        final LinearLayout overFlowLinearLayout= new LinearLayout(getActivity());
        overFlowLinearLayout.setLayoutParams(linearLayoutParams);

        //add at position in progress orders list
        horizLinearLayout.addView(overFlowLinearLayout, i);

        mLayoutInflater.inflate(R.layout.order_item_layout1, overFlowLinearLayout,true);
        overFlowLinearLayout.setTag(thisOrder.getId());

        final RelativeLayout relativeLayout = (RelativeLayout) overFlowLinearLayout.findViewById(R.id.order_relative_layout);

        int titleBgColor = getResources().getColor(R.color.background);
        if(thisOrder.getOrderType()!=null) {
            String titleColor = sp.getString(thisOrder.getOrderType().getLabel(), getString(R.string.backgroundcode));
            titleBgColor = Color.parseColor(titleColor);
        }

        //TODO: apply this to the listview?
        int bodyBgColor = getResources().getColor(R.color.white);
        if(thisOrder.getDevice()!=null){
            String bodyColor = sp.getString(thisOrder.getDevice().getId(),getString(R.string.white));
            bodyBgColor = Color.parseColor(bodyColor);
        }

        TextView orderTitleText = (TextView) relativeLayout.findViewById(R.id.order_id_text);
        final ListView lineItemLv = (ListView) relativeLayout.findViewById(R.id.line_item_list);
        final TextView countdownText = (TextView) relativeLayout.findViewById(R.id.countdown_text);

        countdownText.setTag(thisOrder.getCreatedTime());

        if(showTimer){
            countdownText.setVisibility(View.VISIBLE);
            countdownTvList.add(countdownText);
        }else{
            countdownText.setVisibility(View.GONE);
        }

        final Button doneButton = (Button) relativeLayout.findViewById(R.id.done_button);
        doneButton.setTextSize(fontSize);

        orderTitleText.setTextSize(fontSize);
        orderTitleText.setBackgroundColor(titleBgColor);
        doneButton.setBackgroundColor(titleBgColor);
        countdownText.setTextSize(fontSize);

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
        List<LineItem> filteredLiList = new ArrayList<>();


        if(lineItemList!=null) {
            for (LineItem li : lineItemList) {
                if(orderMonitorData.showLineItem(li.getName())) {
                    filteredLiList.add(li);
                }
            }
        }

        final List<LineItem> displayLiList = sortItemsIntoBins(filteredLiList,thisOrder);

        LineItemListAdapter lineItemListAdapter = new LineItemListAdapter(getActivity(),displayLiList,fontSize);

        lineItemLv.setAdapter(lineItemListAdapter);

        final ViewTreeObserver vto = lineItemLv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int runnableLastPos = lineItemLv.getLastVisiblePosition();
                View lastLv = lineItemLv.getChildAt(runnableLastPos);

                int lvTop = lineItemLv.getTop();
                int lvBottom = lineItemLv.getBottom();
                int lastLvBottom = lvTop+lastLv.getTop()+lastLv.getHeight();
                float lastLvHeight = lastLv.getHeight();


                float percentOffScreen = (lastLvBottom-lvBottom)/lastLvHeight;
                Log.v("percent off",String.valueOf(percentOffScreen));

                if(percentOffScreen>0.15){
                    runnableLastPos = runnableLastPos-1;
                }

                //TODO: remove overflowed line items from list adapter

                if (runnableLastPos < displayLiList.size() - 1) {

                    ViewGroup parent = (ViewGroup) relativeLayout.getParent();
                    parent.removeView(relativeLayout);

                    overFlowLinearLayout.addView(relativeLayout, 0);

                    LinearLayout.LayoutParams newParams = (LinearLayout.LayoutParams) overFlowLinearLayout.getLayoutParams();
                    newParams.width = screenWidthDp*2/5;

                    overFlowLinearLayout.setLayoutParams(newParams);

                    LinearLayout.LayoutParams linLayParams = (LinearLayout.LayoutParams) relativeLayout.getLayoutParams();
                    linLayParams.width = screenWidthDp/5;

                    RelativeLayout overFlowRl = new RelativeLayout(getActivity());

                    relativeLayout.setLayoutParams(linLayParams);
                    overFlowRl.setLayoutParams(linLayParams);

                    mLayoutInflater.inflate(R.layout.overflow_relative_layout, overFlowRl);
                    overFlowLinearLayout.addView(overFlowRl,1);

                    ListView overFlowLv = (ListView) overFlowRl.findViewById(R.id.overflow_list_view);

                    List<LineItem> overFlowLiList = displayLiList.subList(runnableLastPos+1, displayLiList.size());
                    LineItemListAdapter overFlowAdapter = new LineItemListAdapter(getActivity(), overFlowLiList, fontSize);
                    overFlowLv.setAdapter(overFlowAdapter);

                    overFlowLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            //unregister the receiver so that refreshed orders aren't shown before they can be updated
                            OrderMonitorBroadcaster.unregisterReceiver(ordersBroadcastReceiver);
                            periodicUpdateHandler.removeCallbacks(periodicUpdateRunnable);

                            ImageView checkImage = (ImageView) view.findViewById(R.id.item_checked_image);
                            LineItem clickedLineItem = (LineItem) view.getTag();

                            if (clickedLineItem.getUserData() != null && clickedLineItem.getUserData().equals(getString(R.string.checked))) {
                                clickedLineItem.setUserData("");
                                checkImage.setVisibility(View.INVISIBLE);
                                orderMonitorData.updateLineItem(thisOrder.getId(), clickedLineItem.getId(), clickedLineItem);
                            } else {
                                clickedLineItem.setUserData(getString(R.string.checked));
                                checkImage.setVisibility(View.VISIBLE);
                                orderMonitorData.updateLineItem(thisOrder.getId(), clickedLineItem.getId(), clickedLineItem);
                            }

                            OrderMonitorBroadcaster.registerReceiver(ordersBroadcastReceiver, OrderMonitorData.BroadcastEvent.REFRESH_ORDERS);
                            periodicUpdateHandler.post(periodicUpdateRunnable);

                        }

                    });

                    addOverFlowViews(overFlowLv,overFlowRl,overFlowLinearLayout,overFlowLiList,1,thisOrder);
                }
                vto.removeOnGlobalLayoutListener(this);
            }
        });


        lineItemLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //unregister the receiver so that refreshed orders aren't shown before they can be updated
                OrderMonitorBroadcaster.unregisterReceiver(ordersBroadcastReceiver);
                periodicUpdateHandler.removeCallbacks(periodicUpdateRunnable);

                ImageView checkImage = (ImageView) view.findViewById(R.id.item_checked_image);
                LineItem clickedLineItem = (LineItem) view.getTag();

                if (clickedLineItem.getUserData()!=null&&clickedLineItem.getUserData().equals(getString(R.string.checked))) {
                    clickedLineItem.setUserData("");
                    checkImage.setVisibility(View.INVISIBLE);
                    orderMonitorData.updateLineItem(thisOrder.getId(),clickedLineItem.getId(),clickedLineItem);
                } else {
                    clickedLineItem.setUserData(getString(R.string.checked));
                    checkImage.setVisibility(View.VISIBLE);
                    orderMonitorData.updateLineItem(thisOrder.getId(),clickedLineItem.getId(),clickedLineItem);
                }

                OrderMonitorBroadcaster.registerReceiver(ordersBroadcastReceiver,OrderMonitorData.BroadcastEvent.REFRESH_ORDERS);
                periodicUpdateHandler.post(periodicUpdateRunnable);

            }

        });

        doneButton.setTag(thisOrder.getId());

        buttonList.add(doneButton);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RelativeLayout parentView = (RelativeLayout) v.getParent();

                orderMonitorData.markDone((String) v.getTag(), thisOrder);

                //TODO: delete view and remove from orderidlist, maybe wait for callback, it may come in again and a view will be added before its marked done...

                horizLinearLayout.removeView(overFlowLinearLayout);

                currentOrderHashMap.remove(thisOrder.getId());

                buttonList.remove(doneButton);
                countdownTvList.remove(countdownText);
            }
        });

    }

    private void addOverFlowViews(final ListView listView, final RelativeLayout relativeLayout,final LinearLayout overFlowLinearLayout,final List<LineItem> lineItemList,final int pos, final Order order){

        final ViewTreeObserver vto = listView.getViewTreeObserver();

        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                        int runnableLastPos = listView.getLastVisiblePosition();

                View lastLv = listView.getChildAt(runnableLastPos);

                int lvBottom = listView.getBottom();
                int lastLvBottom = lastLv.getBottom();
                float lastLvHeight = lastLv.getHeight();

                float percentOffScreen = (lastLvBottom-lvBottom)/lastLvHeight;
                if(percentOffScreen>0.10){
                    runnableLastPos = runnableLastPos-1;
                }

                        if (runnableLastPos < lineItemList.size() - 1) {

                            ViewGroup parent = (ViewGroup) relativeLayout.getParent();
                            parent.removeView(relativeLayout);

                            overFlowLinearLayout.addView(relativeLayout, pos);

                            LinearLayout.LayoutParams newParams = (LinearLayout.LayoutParams) overFlowLinearLayout.getLayoutParams();
                            newParams.width = screenWidthDp*(pos+2)/5;

                            overFlowLinearLayout.setLayoutParams(newParams);

                            LinearLayout.LayoutParams linLayParams = (LinearLayout.LayoutParams) relativeLayout.getLayoutParams();
                            linLayParams.width = screenWidthDp/5;

                            RelativeLayout overFlowRl = new RelativeLayout(getActivity());

                            relativeLayout.setLayoutParams(linLayParams);
                            overFlowRl.setLayoutParams(linLayParams);

                            mLayoutInflater.inflate(R.layout.overflow_relative_layout, overFlowRl);
                            overFlowLinearLayout.addView(overFlowRl,pos+1);


                            ListView overFlowLv = (ListView) overFlowRl.findViewById(R.id.overflow_list_view);

                            List<LineItem> overFlowLiList = lineItemList.subList(runnableLastPos+1, lineItemList.size());
                            LineItemListAdapter overFlowAdapter = new LineItemListAdapter(getActivity(), overFlowLiList, fontSize);
                            overFlowLv.setAdapter(overFlowAdapter);

                            addOverFlowViews(overFlowLv,overFlowRl,overFlowLinearLayout,overFlowLiList,pos+1,order);

                        }
                        vto.removeOnGlobalLayoutListener(this);
                    }
                });


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    //TODO: hide or show check mark, don't wait for the update
                    //unregister the receiver so that refreshed orders aren't shown before they can be updated
                    OrderMonitorBroadcaster.unregisterReceiver(ordersBroadcastReceiver);
                    periodicUpdateHandler.removeCallbacks(periodicUpdateRunnable);

                    ImageView checkImage = (ImageView) view.findViewById(R.id.item_checked_image);
                    LineItem clickedLineItem = (LineItem) view.getTag();

                    if (clickedLineItem.getUserData() != null && clickedLineItem.getUserData().equals(getString(R.string.checked))) {
                        clickedLineItem.setUserData("");
                        checkImage.setVisibility(View.INVISIBLE);
                        orderMonitorData.updateLineItem(order.getId(), clickedLineItem.getId(), clickedLineItem);
                    } else {
                        clickedLineItem.setUserData(getString(R.string.checked));
                        checkImage.setVisibility(View.VISIBLE);
                        orderMonitorData.updateLineItem(order.getId(), clickedLineItem.getId(), clickedLineItem);
                    }

                    OrderMonitorBroadcaster.registerReceiver(ordersBroadcastReceiver, OrderMonitorData.BroadcastEvent.REFRESH_ORDERS);
                    periodicUpdateHandler.post(periodicUpdateRunnable);

                }
            });
    }



    private void removeViewFromHorizLinearLayout(String orderId){
        LinearLayout linearLayout = (LinearLayout) horizLinearLayout.findViewWithTag(orderId);
        horizLinearLayout.removeView(linearLayout);
    }

    private int indexToDrawView(String orderId){

        List<Order> orderList = new ArrayList<>();

        for(String key:currentOrderHashMap.keySet()){
            orderList.add(currentOrderHashMap.get(key));
        }

        Collections.sort(orderList, new Comparator<Order>() {
            @Override
            public int compare(Order order1, Order order2) {
                if(order1.getCreatedTime()>order2.getCreatedTime()){
                    return 1;
                }else if(order1.getCreatedTime()<order2.getCreatedTime()){
                    return-1;
                }else{
                    return 0;
                }
            }
        });

        for(int i=0;i<orderList.size();i++){
            Order order = orderList.get(i);
            if(order.getId().equals(orderId)){
                return i;
            }
        }

        return -1;
    }

    private boolean orderModified(Order localOrder, Order pulledOrder){


        List<LineItem> localLineItems = localOrder.getLineItems();
        List<LineItem> pulledLineItems = pulledOrder.getLineItems();

        if(localLineItems.size()!=pulledLineItems.size()){
            return true;
        }

        for(int i =0;i<localLineItems.size();i++){
            if(!localLineItems.get(i).getName().equals(pulledLineItems.get(i).getName())){
                return true;
            }else {

                LineItem localLineItem = localLineItems.get(i);
                LineItem pulledLineItem = pulledLineItems.get(i);

                //if one is null, but the other isn't the order has been modified
                if(localLineItem.getModifications()==null^pulledLineItem.getModifications()==null){
                    Log.v("exclusive OR","one modlist is null");
                    return true;
                }

                //at this point, if one is null, they both are, so only need to null check one
                if(localLineItem.getModifications()!=null) {
                    if (localLineItem.getModifications().size() != pulledLineItem.getModifications().size()) {
                        Log.v("different size","of mod list");
                        return true;
                    } else {
                        for (int k = 0; k < localLineItem.getModifications().size(); k++) {
                            if (!localLineItem.getModifications().get(k).getName().equals(pulledLineItem.getModifications().get(k).getName())) {
                                Log.v("modifiers","aren't equal");
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }


    private void updateOrdersView() {



        Order order;

        for(int i = 0;i<progressOrdersList.size();i++){

            order = progressOrdersList.get(i);
            //if the order is new, add a view for it at the appropriate location and add its id to the list
            if(!currentOrderHashMap.containsKey(order.getId())){
                //addViewToHorizLinearLayout(i);
                currentOrderHashMap.put(order.getId(),order);
                int index = indexToDrawView(order.getId());
                addViewToHorizLinearLayout(index,order.getId());
            }else{
                //view should have been added, check to see if it needs to be updated
                if(orderModified(currentOrderHashMap.get(order.getId()),order)){
                    removeViewFromHorizLinearLayout(order.getId());
                    int index = indexToDrawView(order.getId());
                    currentOrderHashMap.put(order.getId(),order);
                    //TODO:get scroll position
                    int scrollPos = scrollView.getScrollX();
                    Log.v(String.valueOf(scrollPos),"scroll pos");
                    addViewToHorizLinearLayout(index, order.getId());
                    //TODO: reset scroll position, maybe check if its in bounds?

                    //TODO: wait to do this until after the screen has been drawn
                    scrollView.setScrollX(scrollPos+100);
                }
            }
        }

        Set<String> keySet = currentOrderHashMap.keySet();

        for(String key:keySet){
            //check if an order was deleted from the station
            if(!progressOrdersList.contains(currentOrderHashMap.get(key))){
                currentOrderHashMap.remove(key);
                removeViewFromHorizLinearLayout(key);
            }
        }
    }

    private List<LineItem> sortItemsIntoBins(List<LineItem> lineItemList, Order order){

        binTreeMap.clear();

        for(LineItem li:lineItemList){
            if(li.getBinName()==null){
                if(!binTreeMap.containsKey("null")) {
                    //no null bin created
                    List<LineItem> nullBinList = new ArrayList<>();
                    nullBinList.add(li);
                    binTreeMap.put("null",nullBinList);
                }else{
                    //null bin created
                    List<LineItem> nullBinList = binTreeMap.get("null");
                    nullBinList.add(li);
                    binTreeMap.put("null", nullBinList);
                }
            }else{
                if(!binTreeMap.containsKey(li.getBinName())){
                    //this item's bin is new
                    List<LineItem> binList = new ArrayList<>();
                    binList.add(li);
                    binTreeMap.put(li.getBinName(),binList);
                }else{
                    //there is already a key in the treemap and an associated list for this bin
                    List<LineItem> binList = binTreeMap.get(li.getBinName());
                    binList.add(li);
                    binTreeMap.put(li.getBinName(),binList);
                }
            }
        }

        List<LineItem> displayList = new ArrayList<>();


        //TODO: if there isn't a bin with "", but there is more than a null bin, add a line item at the beginning with the table name

        for(String key:binTreeMap.keySet()){
            LineItem tagLineItem = new LineItem();
            tagLineItem.setId(getString(R.string.tag_line_item));
            tagLineItem.setName(key);
            if(!tagLineItem.getName().equals("null")){
                displayList.add(tagLineItem);
            }
            if(tagLineItem.getName().equals("")){
                if(order.getTitle()!=null){
                    tagLineItem.setName(order.getTitle());
                }
            }

            List<LineItem> correspBinList = binTreeMap.get(key);
            for(LineItem lineItem:correspBinList){
                displayList.add(lineItem);
                List<Modification> modList = lineItem.getModifications();
                //TODO: sort modifiers alphabetically
                if(modList!=null) {
                    for (Modification mo : modList) {
                        LineItem modLineItem = new LineItem();
                        modLineItem.setName("  -" + mo.getName());
                        modLineItem.setId(getString(R.string.modifier));
                        displayList.add(modLineItem);
                    }
                }
            }
        }

        return displayList;
    }

}
