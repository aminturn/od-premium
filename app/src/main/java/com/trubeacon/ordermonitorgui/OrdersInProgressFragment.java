package com.trubeacon.ordermonitorgui;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.trubeacon.cloverandroidapi.client.error.Error;
import com.trubeacon.cloverandroidapi.common.WrappedList;
import com.trubeacon.cloverandroidapi.inventory.Tag;
import com.trubeacon.cloverandroidapi.inventory.service.GetTags;
import com.trubeacon.cloverandroidapi.merchant.Device;
import com.trubeacon.cloverandroidapi.merchant.Devices;
import com.trubeacon.cloverandroidapi.merchant.service.GetDevices;
import com.trubeacon.cloverandroidapi.order.LineItem;
import com.trubeacon.cloverandroidapi.order.Modification;
import com.trubeacon.cloverandroidapi.order.Order;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

//TODO: add a number to the order header indicating which key bumps the order

public class OrdersInProgressFragment extends Fragment implements MainActivity.KeyUpCallback {

    private List<Order> progressOrdersList = new ArrayList<>();
    private HashMap<String,Order> currentOrderHashMap = new HashMap<String,Order>();

    private LinearLayout horizLinearLayout;
    private HorizontalScrollView scrollView;
    private LayoutInflater mLayoutInflater;
    private ActionBar actionBar;
    private List<Button> buttonList = new ArrayList<>();

    private Handler periodicUpdateHandler = new Handler();
    private Handler countdownHandler = new Handler();
    private static int refreshRateMs = 5000;

    private boolean overFlow;
    private boolean showOrderType;
    private boolean showOrigin;
    private float fontSize;
    private boolean showTimer;

    private boolean showOrderedItems;
    private boolean showReadyItems;
    private boolean showServedItems;

    private int denominatorForWidth;
    private TreeMap<String,List<LineItem>> binTreeMap = new TreeMap<String,List<LineItem>>();

    private Integer screenWidthDp;
    private OrderMonitorData orderMonitorData = OrderMonitorData.getOrderMonitorData();

    private List<TextView> countdownTvList = new ArrayList<>();

    private Order lastOrder;
    private boolean isOrderDoneReceiverRegistered = false;

    //**********************************************************************************************
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
                String secondsString;
                if(sec<10){
                    secondsString = "0" + String.valueOf(sec);
                }else{
                    secondsString = String.valueOf(sec);
                }
                tv.setText(String.valueOf(minutes)+":"+secondsString);
            }
            countdownHandler.postDelayed(updateCountdown, 100);
        }
    };

    //**********************************************************************************************
    private Runnable periodicUpdateRunnable = new Runnable() {
        @Override
        public void run() {
            Log.v("periodic update","runnable");
            orderMonitorData.refreshOrders();
        }
    };

    //**********************************************************************************************
    private BroadcastReceiver ordersBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v("orders","broadcast receiver");
            progressOrdersList = orderMonitorData.getProgressOrdersList();
            updateOrdersView();
            updateOrderCount(progressOrdersList.size());
            periodicUpdateHandler.postDelayed(periodicUpdateRunnable, refreshRateMs);
        }
    };

    //**********************************************************************************************
    private BroadcastReceiver lineItemBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v("line item","broadcast receiver");
            OrderMonitorBroadcaster.unregisterReceiver(this);
            OrderMonitorBroadcaster.registerReceiver(ordersBroadcastReceiver, OrderMonitorData.BroadcastEvent.REFRESH_ORDERS);
            periodicUpdateHandler.postDelayed(periodicUpdateRunnable,refreshRateMs);
        }
    };

    //**********************************************************************************************
    private BroadcastReceiver orderMarkedDoneReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v("orderdone","receiver");
            OrderMonitorBroadcaster.unregisterReceiver(this);
            isOrderDoneReceiverRegistered = false;
            OrderMonitorBroadcaster.registerReceiver(ordersBroadcastReceiver, OrderMonitorData.BroadcastEvent.REFRESH_ORDERS);
            periodicUpdateHandler.postDelayed(periodicUpdateRunnable, refreshRateMs);
        }
    };

    //**********************************************************************************************
    @Override
    public void onResume() {
        super.onResume();
        OrderMonitorBroadcaster.registerReceiver(ordersBroadcastReceiver, OrderMonitorData.BroadcastEvent.REFRESH_ORDERS);
        periodicUpdateHandler.post(periodicUpdateRunnable);
        countdownHandler.post(updateCountdown);
    }

    //**********************************************************************************************
    @Override
    public void onPause() {
        super.onPause();
        periodicUpdateHandler.removeCallbacks(periodicUpdateRunnable);
        countdownHandler.removeCallbacks(updateCountdown);
        OrderMonitorBroadcaster.unregisterReceiver(ordersBroadcastReceiver);
        OrderMonitorBroadcaster.unregisterReceiver(orderMarkedDoneReceiver);
    }

    //**********************************************************************************************
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.progress_menu, menu);
    }

    //**********************************************************************************************
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

    //**********************************************************************************************
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    //**********************************************************************************************
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.v("oncreateView","oncreateview");

        currentOrderHashMap.clear();

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());

        String mId = sharedPref.getString(getString(R.string.merchant_id_key), "");
        String token = sharedPref.getString(getString(R.string.saved_token_key), "");
        overFlow = sharedPref.getBoolean(getString(R.string.overflow_pref_key),true);

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
                public void onFailGetDevices(com.trubeacon.cloverandroidapi.client.error.Error error) {
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

        String fontSizeString = sharedPref.getString(getString(R.string.font_size_pref), "30");

        fontSize = Integer.parseInt(fontSizeString);

        String ordersOnScreen = sharedPref.getString(getString(R.string.order_width_key),"4");

        denominatorForWidth = Integer.parseInt(ordersOnScreen);

        showOrderType = sharedPref.getBoolean(getString(R.string.display_order_type_pref), false);
        showOrigin = sharedPref.getBoolean(getString(R.string.display_device_pref),false);
        showTimer = sharedPref.getBoolean(getString(R.string.order_timer_pref),true);

        Set<String> defset = new HashSet<>(Arrays.asList(getResources().getStringArray(R.array.item_state_vals)));
        Set<String> orderStatesSet = sharedPref.getStringSet(getString(R.string.item_states_key),defset);

        showOrderedItems = orderStatesSet.contains(getString(R.string.ordered));
        showReadyItems = orderStatesSet.contains(getString(R.string.ready));
        showServedItems = orderStatesSet.contains(getString(R.string.served));

        actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);

        return scrollAndClearBtnLinLay;
    }

    //**********************************************************************************************
    private void updateOrderCount(int count){
        String titleStr = "";

        if(count==0){
            titleStr = "NO ORDERS IN PROGRESS";
        }
        else if(count==1){
            titleStr = String.valueOf(count) + " ORDER IN PROGRESS";
        }
        else{
            titleStr = String.valueOf(count) + " ORDERS IN PROGRESS";

        }
        actionBar.setTitle(titleStr);
    }

    //**********************************************************************************************
    private void addViewToHorizLinearLayout(int i,String orderId){

        final LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
                screenWidthDp/denominatorForWidth,
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

        Log.v("client created time", String.valueOf(thisOrder.getClientCreatedTime()));
        Log.v("order created time", String.valueOf(thisOrder.getCreatedTime()));

        final LinearLayout overFlowLinearLayout= new LinearLayout(getActivity());
        overFlowLinearLayout.setLayoutParams(linearLayoutParams);

        //add at position in progress orders list
        horizLinearLayout.addView(overFlowLinearLayout, i);

        mLayoutInflater.inflate(R.layout.order_item_layout1, overFlowLinearLayout, true);
        overFlowLinearLayout.setTag(thisOrder.getId());

        final RelativeLayout relativeLayout = (RelativeLayout) overFlowLinearLayout.findViewById(R.id.order_relative_layout);

        int titleBgColor = getResources().getColor(R.color.background);
        if(thisOrder.getOrderType()!=null) {
            String titleColor = sp.getString(thisOrder.getOrderType().getLabel(), getString(R.string.backgroundcode));
            titleBgColor = Color.parseColor(titleColor);
        }

        final int bodyBgColor;

        if(thisOrder.getDevice()!=null){
            String bodyColor = sp.getString(thisOrder.getDevice().getId(),getString(R.string.white));
            bodyBgColor = Color.parseColor(bodyColor);
        }else{
            bodyBgColor = getResources().getColor(R.color.white);
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

        boolean showTimeTicket = sp.getBoolean(getString(R.string.show_time_stamp_and_ticket_number),true);

        String timeCreatedString = "";

        String orderTitle = "";

        if(thisOrder.getTitle()!=null){
            orderTitle = thisOrder.getTitle();
        }

        if(showTimeTicket){
            if(StringUtils.isNumeric(orderTitle)) {
                timeCreatedString = "#" + orderTitle + "   " + DateTimeFormat.forPattern("hh:mm a").print(orderCreated);
            }else{
                timeCreatedString = DateTimeFormat.forPattern("hh:mm a").print(orderCreated);
            }
        }

        if(showOrderType) {
            if(showTimeTicket){
                timeCreatedString = timeCreatedString + "\r\n";
            }
            timeCreatedString = timeCreatedString + label;
        }

        if(showOrigin){
            if(showOrderType||showTimeTicket){
                timeCreatedString = timeCreatedString + "\r\n";
            }
            timeCreatedString = timeCreatedString + origin;
        }

        if(!timeCreatedString.equals("")) {
            orderTitleText.setText(timeCreatedString);
        }else{
            orderTitleText.setVisibility(View.GONE);
        }


        final List<LineItem> lineItemList = thisOrder.getLineItems();
        List<LineItem> filteredLiList = new ArrayList<>();


        if(lineItemList!=null) {
            for (LineItem li : lineItemList) {
                if(orderMonitorData.showLineItem(li.getName(),li)) {
                    filteredLiList.add(li);
                }
            }
        }

        final List<LineItem> displayLiList = sortItemsIntoBins(filteredLiList,thisOrder);

        final LineItemListAdapter lineItemListAdapter = new LineItemListAdapter(getActivity(),displayLiList,fontSize,bodyBgColor);

        lineItemLv.setAdapter(lineItemListAdapter);

        if(overFlow) {

            final ViewTreeObserver vto = lineItemLv.getViewTreeObserver();
            vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    int runnableLastPos = lineItemLv.getLastVisiblePosition();
                    View lastLv = lineItemLv.getChildAt(runnableLastPos);

                    int lvTop = lineItemLv.getTop();
                    int lvBottom = lineItemLv.getBottom();
                    int lastLvBottom = lvTop + lastLv.getTop() + lastLv.getHeight();
                    float lastLvHeight = lastLv.getHeight();


                    float percentOffScreen = (lastLvBottom - lvBottom) / lastLvHeight;

                    if (percentOffScreen > 0.15) {
                        runnableLastPos = runnableLastPos - 1;
                    }

                    if (runnableLastPos < displayLiList.size() - 1) {

                        lineItemListAdapter.updateAdapter(displayLiList.subList(0, runnableLastPos + 1));

                        ViewGroup parent = (ViewGroup) relativeLayout.getParent();
                        parent.removeView(relativeLayout);

                        overFlowLinearLayout.addView(relativeLayout, 0);


                        LinearLayout.LayoutParams newParams = (LinearLayout.LayoutParams) overFlowLinearLayout.getLayoutParams();
                        newParams.width = screenWidthDp * 2 / denominatorForWidth;

                        overFlowLinearLayout.setLayoutParams(newParams);

                        LinearLayout.LayoutParams linLayParams = (LinearLayout.LayoutParams) relativeLayout.getLayoutParams();
                        linLayParams.width = screenWidthDp / denominatorForWidth;

                        RelativeLayout overFlowRl = new RelativeLayout(getActivity());

                        relativeLayout.setLayoutParams(linLayParams);
                        overFlowRl.setLayoutParams(linLayParams);

                        mLayoutInflater.inflate(R.layout.overflow_relative_layout, overFlowRl);
                        overFlowLinearLayout.addView(overFlowRl, 1);

                        final ListView overFlowLv = (ListView) overFlowRl.findViewById(R.id.overflow_list_view);

                        final List<LineItem> overFlowLiList = displayLiList.subList(runnableLastPos + 1, displayLiList.size());
                        LineItemListAdapter overFlowAdapter = new LineItemListAdapter(getActivity(), overFlowLiList, fontSize, bodyBgColor);
                        overFlowLv.setAdapter(overFlowAdapter);

                        overFlowLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                //unregister the receiver so that refreshed orders aren't shown before they can be updated
                                OrderMonitorBroadcaster.unregisterReceiver(ordersBroadcastReceiver);
                                periodicUpdateHandler.removeCallbacks(periodicUpdateRunnable);

                                OrderMonitorBroadcaster.registerReceiver(lineItemBroadcastReceiver, OrderMonitorData.BroadcastEvent.LINE_ITEM_UPDATE);

                                ImageView checkImage = (ImageView) view.findViewById(R.id.item_checked_image);
                                LineItem clickedLineItem = (LineItem) view.getTag();

                                //if ordered or null, mark ready
                                if (clickedLineItem.getUserData() == null || clickedLineItem.getUserData().equals(getString(R.string.ordered))) {
                                    clickedLineItem.setUserData(getString(R.string.ready));
                                    checkImage.setVisibility(View.VISIBLE);
                                    checkImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_black_24dp));
                                    orderMonitorData.updateLineItem(thisOrder.getId(), clickedLineItem.getId(), clickedLineItem);

                                    //if ready, mark served
                                } else if (clickedLineItem.getUserData().equals(getString(R.string.ready))) {
                                    clickedLineItem.setUserData(getString(R.string.served));
                                    checkImage.setVisibility(View.VISIBLE);
                                    checkImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_all_black_24dp));
                                    orderMonitorData.updateLineItem(thisOrder.getId(), clickedLineItem.getId(), clickedLineItem);
                                } else {
                                    //if served. mark ordered
                                    clickedLineItem.setUserData(getString(R.string.ordered));
                                    checkImage.setVisibility(View.INVISIBLE);
                                    orderMonitorData.updateLineItem(thisOrder.getId(), clickedLineItem.getId(), clickedLineItem);
                                }

                                //if any of these are false, an item may need to be removed form the view, so it needs to be redrawn
                                if (!(showOrderedItems && showReadyItems && showServedItems)) {
                                    int whereIsOrder = horizLinearLayout.indexOfChild(overFlowLinearLayout);
                                    removeViewFromHorizLinearLayout(thisOrder.getId());
                                    if (hasLineItems(thisOrder)) {
                                        addViewToHorizLinearLayout(whereIsOrder, thisOrder.getId());
                                    } else {
                                        currentOrderHashMap.remove(thisOrder.getId());
                                        updateOrderCount(currentOrderHashMap.size());
                                        buttonList.remove(doneButton);
                                        countdownTvList.remove(countdownText);
                                        updateButtonKeys();
                                    }
                                }
                            }

                        });

                        addOverFlowViews(overFlowLv, overFlowRl, overFlowLinearLayout, overFlowLiList, 1, thisOrder, bodyBgColor);
                    }
                    vto.removeOnGlobalLayoutListener(this);
                }
            });
        }


        lineItemLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //unregister the receiver so that refreshed orders aren't shown before they can be updated
                OrderMonitorBroadcaster.unregisterReceiver(ordersBroadcastReceiver);
                periodicUpdateHandler.removeCallbacks(periodicUpdateRunnable);
                OrderMonitorBroadcaster.registerReceiver(lineItemBroadcastReceiver, OrderMonitorData.BroadcastEvent.LINE_ITEM_UPDATE);

                ImageView checkImage = (ImageView) view.findViewById(R.id.item_checked_image);
                LineItem clickedLineItem = (LineItem) view.getTag();

                //if ordered or null, mark ready
                if (clickedLineItem.getUserData()==null||clickedLineItem.getUserData().equals(getString(R.string.ordered))) {
                    clickedLineItem.setUserData(getString(R.string.ready));
                    checkImage.setVisibility(View.VISIBLE);
                    checkImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_black_24dp));
                    orderMonitorData.updateLineItem(thisOrder.getId(),clickedLineItem.getId(),clickedLineItem);

                //if item ready, mark served
                } else if(clickedLineItem.getUserData().equals(getString(R.string.ready))) {
                    clickedLineItem.setUserData(getString(R.string.served));
                    checkImage.setVisibility(View.VISIBLE);
                    checkImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_all_black_24dp));
                    orderMonitorData.updateLineItem(thisOrder.getId(), clickedLineItem.getId(), clickedLineItem);
                //if served, mark ordered
                }else{
                    clickedLineItem.setUserData(getString(R.string.ordered));
                    checkImage.setVisibility(View.INVISIBLE);
                    orderMonitorData.updateLineItem(thisOrder.getId(),clickedLineItem.getId(),clickedLineItem);
                }


                if(!(showOrderedItems&&showReadyItems&&showServedItems)) {
                    int whereIsOrder = horizLinearLayout.indexOfChild(overFlowLinearLayout);
                    removeViewFromHorizLinearLayout(thisOrder.getId());

                    if(hasLineItems(thisOrder)) {
                        addViewToHorizLinearLayout(whereIsOrder, thisOrder.getId());
                    }else{
                        currentOrderHashMap.remove(thisOrder.getId());
                        updateOrderCount(currentOrderHashMap.size());
                        buttonList.remove(doneButton);
                        countdownTvList.remove(countdownText);
                        updateButtonKeys();
                    }
                }
            }
        });

        doneButton.setTag(thisOrder.getId());

        buttonList.add(doneButton);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OrderMonitorBroadcaster.unregisterReceiver(ordersBroadcastReceiver);
                periodicUpdateHandler.removeCallbacks(periodicUpdateRunnable);

                if(!isOrderDoneReceiverRegistered) {
                    OrderMonitorBroadcaster.registerReceiver(orderMarkedDoneReceiver, OrderMonitorData.BroadcastEvent.ORDER_DONE);
                    isOrderDoneReceiverRegistered=true;
                }

                orderMonitorData.markDone((String) v.getTag(), thisOrder);

                lastOrder = thisOrder;

                horizLinearLayout.removeView(overFlowLinearLayout);

                currentOrderHashMap.remove(thisOrder.getId());
                updateOrderCount(currentOrderHashMap.size());
                buttonList.remove(doneButton);
                countdownTvList.remove(countdownText);
                updateButtonKeys();
            }
        });

    }

    //**********************************************************************************************
    private void addOverFlowViews(final ListView listView, final RelativeLayout relativeLayout,final LinearLayout overFlowLinearLayout,final List<LineItem> lineItemList,final int pos, final Order order, final int bodyBgColor){

        final ViewTreeObserver vto = listView.getViewTreeObserver();

        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                int runnableLastPos = listView.getLastVisiblePosition();

                View lastLv = listView.getChildAt(runnableLastPos);

                int lvTop = listView.getTop();
                int lvBottom = listView.getBottom();
                int lastLvBottom = lvTop + lastLv.getTop() + lastLv.getHeight();
                float lastLvHeight = lastLv.getHeight();

                float percentOffScreen = (lastLvBottom - lvBottom) / lastLvHeight;

                if (percentOffScreen > 0.10) {
                    runnableLastPos = runnableLastPos - 1;
                }

                if (runnableLastPos < lineItemList.size() - 1) {

                    LineItemListAdapter listAdapter = (LineItemListAdapter) listView.getAdapter();
                    listAdapter.updateAdapter(lineItemList.subList(0, runnableLastPos + 1));

                    ViewGroup parent = (ViewGroup) relativeLayout.getParent();
                    parent.removeView(relativeLayout);

                    overFlowLinearLayout.addView(relativeLayout, pos);

                    LinearLayout.LayoutParams newParams = (LinearLayout.LayoutParams) overFlowLinearLayout.getLayoutParams();
                    newParams.width = screenWidthDp * (pos + 2) / denominatorForWidth;

                    overFlowLinearLayout.setLayoutParams(newParams);

                    LinearLayout.LayoutParams linLayParams = (LinearLayout.LayoutParams) relativeLayout.getLayoutParams();
                    linLayParams.width = screenWidthDp / denominatorForWidth;

                    RelativeLayout overFlowRl = new RelativeLayout(getActivity());

                    relativeLayout.setLayoutParams(linLayParams);
                    overFlowRl.setLayoutParams(linLayParams);

                    mLayoutInflater.inflate(R.layout.overflow_relative_layout, overFlowRl);
                    overFlowLinearLayout.addView(overFlowRl, pos + 1);

                    ListView overFlowLv = (ListView) overFlowRl.findViewById(R.id.overflow_list_view);

                    final List<LineItem> overFlowLiList = lineItemList.subList(runnableLastPos + 1, lineItemList.size());
                    LineItemListAdapter overFlowAdapter = new LineItemListAdapter(getActivity(), overFlowLiList, fontSize, bodyBgColor);
                    overFlowLv.setAdapter(overFlowAdapter);

                    overFlowLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            //unregister the receiver so that refreshed orders aren't shown before they can be updated
                            OrderMonitorBroadcaster.unregisterReceiver(ordersBroadcastReceiver);
                            periodicUpdateHandler.removeCallbacks(periodicUpdateRunnable);
                            OrderMonitorBroadcaster.registerReceiver(lineItemBroadcastReceiver, OrderMonitorData.BroadcastEvent.LINE_ITEM_UPDATE);

                            ImageView checkImage = (ImageView) view.findViewById(R.id.item_checked_image);
                            LineItem clickedLineItem = (LineItem) view.getTag();

                            //if state null or ordered, mark ready
                            if (clickedLineItem.getUserData() == null || clickedLineItem.getUserData().equals(getString(R.string.ordered))) {
                                clickedLineItem.setUserData(getString(R.string.ready));
                                checkImage.setVisibility(View.VISIBLE);
                                checkImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_black_24dp));
                                orderMonitorData.updateLineItem(order.getId(), clickedLineItem.getId(), clickedLineItem);
                                //if item ready, mark served
                            } else if (clickedLineItem.getUserData().equals(getString(R.string.ready))) {
                                clickedLineItem.setUserData(getString(R.string.served));
                                checkImage.setVisibility(View.VISIBLE);
                                checkImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_all_black_24dp));
                                orderMonitorData.updateLineItem(order.getId(), clickedLineItem.getId(), clickedLineItem);
                            }
                            //if item served, mark ordered
                            else {
                                clickedLineItem.setUserData(getString(R.string.ordered));
                                checkImage.setVisibility(View.INVISIBLE);
                                orderMonitorData.updateLineItem(order.getId(), clickedLineItem.getId(), clickedLineItem);
                            }

                            if (!(showOrderedItems && showReadyItems && showServedItems)) {
                                int whereIsOrder = horizLinearLayout.indexOfChild(overFlowLinearLayout);
                                removeViewFromHorizLinearLayout(order.getId());

                                if (hasLineItems(order)) {
                                    addViewToHorizLinearLayout(whereIsOrder, order.getId());
                                } else {
                                    currentOrderHashMap.remove(order.getId());
                                    updateOrderCount(currentOrderHashMap.size());
                                    updateButtonKeys();
                                }
                            }
                        }
                    });

                    addOverFlowViews(overFlowLv, overFlowRl, overFlowLinearLayout, overFlowLiList, pos + 1, order, bodyBgColor);

                }
                vto.removeOnGlobalLayoutListener(this);
            }
        });

    }


    //**********************************************************************************************
    private void removeViewFromHorizLinearLayout(String orderId){
        LinearLayout linearLayout = (LinearLayout) horizLinearLayout.findViewWithTag(orderId);
        horizLinearLayout.removeView(linearLayout);
    }

    //**********************************************************************************************
    private int indexToDrawView(String orderId){

        List<Order> orderList = new ArrayList<>(currentOrderHashMap.values());

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

    //**********************************************************************************************
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

                if(localLineItem.getUserData()==null^pulledLineItem.getUserData()==null){
                    return true;
                }

                if(localLineItem.getUserData()!=null) {
                    if (!localLineItem.getUserData().equals(pulledLineItem.getUserData())) {
                        return true;
                    }
                }

                //if one is null, but the other isn't the order has been modified
                if(localLineItem.getModifications()==null^pulledLineItem.getModifications()==null){
                    return true;
                }

                Set<String> localModifierNames = new HashSet<String>();
                Set<String> pulledModifierNames = new HashSet<String>();

                //at this point, if one is null, they both are, so only need to null check one
                if(localLineItem.getModifications()!=null) {
                    if (localLineItem.getModifications().size() != pulledLineItem.getModifications().size()) {
                        return true;
                    } else {
                        for (int k = 0; k < localLineItem.getModifications().size(); k++) {
                            localModifierNames.add(localLineItem.getModifications().get(k).getName());
                            pulledModifierNames.add(pulledLineItem.getModifications().get(k).getName());
                            }

                        if(!localModifierNames.equals(pulledModifierNames)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    //**********************************************************************************************
    private void updateButtonKeys(){
        //add indicators to buttons for keypad bumping
        Log.v("update","button keys");
        List<Button> cleanUpButtonList = new ArrayList<>();

        for(Button button: buttonList){
            String orderId = (String) button.getTag();
            int index = indexToDrawView(orderId);
            if(index==-1){
                //there is no longer an order associated with this button
                cleanUpButtonList.add(button);
            }else {
                if (index < denominatorForWidth) {
                    button.setText("DONE (" + String.valueOf(index + 1) + ")");
                } else {
                    button.setText("DONE");
                }
            }
        }

        for(Button button:cleanUpButtonList){
            Log.v("buttonList", "cleanup");
            buttonList.remove(button);
        }
    }

    //**********************************************************************************************
    private void updateOrdersView() {

        Order order;

        for(int i = 0;i<progressOrdersList.size();i++){

            order = progressOrdersList.get(i);
            //if the order is new, add a view for it at the appropriate location and add its id to the list
            if(!currentOrderHashMap.containsKey(order.getId())){
                //addViewToHorizLinearLayout(i);
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
                boolean playNotifcation = sp.getBoolean(getString(R.string.play_sound),true);

                if(playNotifcation){
                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    Ringtone r = RingtoneManager.getRingtone(getActivity(),notification);
                    r.play();
                }

                currentOrderHashMap.put(order.getId(),order);
                int index = indexToDrawView(order.getId());
                addViewToHorizLinearLayout(index,order.getId());
                updateButtonKeys();

            }else{
                //view should have been added, check to see if it needs to be updated
                if(orderModified(currentOrderHashMap.get(order.getId()),order)){
                    removeViewFromHorizLinearLayout(order.getId());
                    int index = indexToDrawView(order.getId());
                    currentOrderHashMap.put(order.getId(),order);
                    //TODO: get scroll position
                    addViewToHorizLinearLayout(index, order.getId());
                    updateButtonKeys();
                    //TODO: reset scroll position, maybe check if its in bounds?
                    //TODO: wait to do this until after the screen has been drawn
                }
            }
        }

        Set<String> keySet = new HashSet<>(currentOrderHashMap.keySet());

        for(String key:keySet){
            //check if an order was deleted from the station
            if(!progressOrdersList.contains(currentOrderHashMap.get(key))){
                Log.v("remove", "order from hashmap");
                currentOrderHashMap.remove(key);
                removeViewFromHorizLinearLayout(key);
                updateButtonKeys();
            }
        }
    }

    //**********************************************************************************************
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

        if(!binTreeMap.containsKey("null")&&!binTreeMap.containsKey("")){
            LineItem tablesLineItem = new LineItem();
            tablesLineItem.setName(order.getTitle());
            tablesLineItem.setId(getString(R.string.tag_line_item));
            displayList.add(tablesLineItem);
        }

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
            Collections.sort(correspBinList, new Comparator<LineItem>() {
                @Override
                public int compare(LineItem li1, LineItem li2) {
                    if(li1.getCreatedTime()<li2.getCreatedTime()){
                        return -1;
                    }
                    return 1;
                }
            });

            for(LineItem lineItem:correspBinList){
                displayList.add(lineItem);
                List<Modification> modList = lineItem.getModifications();

                if(modList!=null) {
                    Collections.sort(modList, new Comparator<Modification>(){
                        @Override
                        public int compare(Modification m1, Modification m2){
                            int res = m1.getName().compareTo(m2.getName());
                            return res;
                        }
                    });

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

    //**********************************************************************************************
    private boolean hasLineItems(Order order){

        boolean hasLineItems=false;

        if(order.getLineItems()!=null){
            for (LineItem li : order.getLineItems()) {
                if (orderMonitorData.showLineItem(li.getName(), li)) {
                    hasLineItems = true;
                }
            }
        }
        return hasLineItems;
    }


    //**********************************************************************************************
    private Order getOrderFromIndex(int index){

        //assume zero-based indexing
        List<Order> sortedOrders = new ArrayList<>(currentOrderHashMap.values());

        Collections.sort(sortedOrders, new Comparator<Order>() {
            @Override
            public int compare(Order order1, Order order2) {
                if (order1.getCreatedTime() > order2.getCreatedTime()) {
                    return 1;
                } else if (order1.getCreatedTime() < order2.getCreatedTime()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });


        return sortedOrders.get(index);
    }


    //**********************************************************************************************
    @Override
    public void keyUp(int keyCode) {

        int orderIndex;

        Log.v("key", String.valueOf(keyCode));

        switch(keyCode){
            case KeyEvent.KEYCODE_NUMPAD_1:
            case KeyEvent.KEYCODE_1:
                orderIndex = 0;
                break;
            case KeyEvent.KEYCODE_NUMPAD_2:
            case KeyEvent.KEYCODE_2:
                orderIndex = 1;
                break;
            case KeyEvent.KEYCODE_NUMPAD_3:
            case KeyEvent.KEYCODE_3:
                if(denominatorForWidth>2){
                    orderIndex = 2;
                }else{
                    orderIndex = -1;
                }
                break;
            case KeyEvent.KEYCODE_NUMPAD_4:
            case KeyEvent.KEYCODE_4:
                if(denominatorForWidth>3){
                    orderIndex = 3;
                }else{
                    orderIndex=-1;
                }
                break;
            case KeyEvent.KEYCODE_NUMPAD_5:
            case KeyEvent.KEYCODE_5:
                if(denominatorForWidth>4){
                    orderIndex=4;
                }else{
                    orderIndex=-1;
                }
                break;
            case KeyEvent.KEYCODE_NUMPAD_6:
            case KeyEvent.KEYCODE_6:
                if(denominatorForWidth>5){
                    orderIndex=5;
                }else{
                    orderIndex=-1;
                }
                break;
            case KeyEvent.KEYCODE_DEL:
                orderIndex = 11;
                break;
            default:
                orderIndex = -1;
                break;
        }//end of switch

        Log.v("orderindex",String.valueOf(orderIndex));
        Log.v("hashmapsize",String.valueOf(currentOrderHashMap.size()));

        if(orderIndex!=-1){
            if(orderIndex!=11){
                //bump an order
                if(orderIndex<currentOrderHashMap.size()) {
                    //bump an order
                    Order bumpedOrder = getOrderFromIndex(orderIndex);
                    bumpOrder(bumpedOrder);
                    lastOrder = bumpedOrder;
                }
            }else{
                //undo last order
                if(lastOrder!=null){
                    orderMonitorData.markUnDone(lastOrder.getId(),lastOrder);
                    currentOrderHashMap.put(lastOrder.getId(),lastOrder);
                    int index = indexToDrawView(lastOrder.getId());
                    addViewToHorizLinearLayout(index,lastOrder.getId());
                    updateButtonKeys();
                    lastOrder = null;
                }
            }
        }
    }

    //**********************************************************************************************
    private void bumpOrder(Order bumpedOrder){

        OrderMonitorBroadcaster.unregisterReceiver(ordersBroadcastReceiver);
        periodicUpdateHandler.removeCallbacks(periodicUpdateRunnable);

        if(!isOrderDoneReceiverRegistered) {
            OrderMonitorBroadcaster.registerReceiver(orderMarkedDoneReceiver, OrderMonitorData.BroadcastEvent.ORDER_DONE);
            isOrderDoneReceiverRegistered=true;
        }

        orderMonitorData.markDone(bumpedOrder.getId(), bumpedOrder);
        currentOrderHashMap.remove(bumpedOrder.getId());
        updateOrderCount(currentOrderHashMap.size());

        LinearLayout linearLayout = (LinearLayout) horizLinearLayout.findViewWithTag(bumpedOrder.getId());
        Button doneButton = (Button) linearLayout.findViewById(R.id.done_button);
        TextView countdownText = (TextView) linearLayout.findViewById(R.id.countdown_text);

        buttonList.remove(doneButton);
        countdownTvList.remove(countdownText);
        horizLinearLayout.removeView(linearLayout);
        updateButtonKeys();

    }
}
