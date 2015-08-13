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
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tru.clover.api.client.error.*;
import com.tru.clover.api.client.error.Error;
import com.tru.clover.api.common.WrappedList;
import com.tru.clover.api.inventory.Tag;
import com.tru.clover.api.inventory.service.GetTags;
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
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Andrew on 4/30/2015.
 */

public class OrdersInProgressFragment extends Fragment {

    private List<Order> progressOrdersList = new ArrayList<>();

    private LinearLayout horizLinearLayout;
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


        View scrollView = scrollAndClearBtnLinLay.findViewById(R.id.scroll_view);

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

    private void updateOrdersView() {

        buttonList.clear();
        countdownTvList.clear();


        //TODO: always call five order view for testing, this should change to an arbitrary number of views...

//        if (twoRows) {
//            updateTenOrdersView();
//        } else {
            updateFiveOrdersView();
//        }
    }


    private void updateTenOrdersView(){

//        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
//                screenWidthDp/5,
//                ViewGroup.LayoutParams.MATCH_PARENT);
//
//        horizLinearLayout.removeAllViews();
//
//        int listsize = progressOrdersList.size();
//        int pages;
//
//
//        if(listsize%10==0){
//            pages = listsize/10;
//        }
//        else{
//            pages = listsize/10+1;
//        }
//
//
//        int frames = pages*5;
//        int pagenumber =0;
//
//        for(int f = 1;f<=frames;f++) {
//
//            LinearLayout linearLayout = new LinearLayout(getActivity());
//            linearLayout.setLayoutParams(linearLayoutParams);
//
//            horizLinearLayout.addView(linearLayout);
//            mLayoutInflater.inflate(R.layout.order_item_layout2, linearLayout);
//
//            RelativeLayout relativeLayoutTop = (RelativeLayout) linearLayout.findViewById(R.id.order_item_top);
//            RelativeLayout relativeLayoutBottom = (RelativeLayout) linearLayout.findViewById(R.id.order_item_bottom);
//
//            int topId;
//            if(f%5==0){
//                topId = pagenumber*10+5;
//            }else {
//                topId = pagenumber * 10 + f % 5;
//            }
//
//            int bottomId = topId+5;
//
//            if(topId<=listsize) {
//
//                relativeLayoutTop.setVisibility(View.VISIBLE);
//
//                Order topOrder = progressOrdersList.get(topId - 1);
//
//
//                String label = "";
//
//                if(topOrder.getOrderType()!=null&&showOrderType) {
//                    label = topOrder.getOrderType().getLabel();
//                }
//
//                String origin = "";
//
//                if(topOrder.getDevice().getId()!=null&&showOrigin){
//                    origin = orderMonitorData.getDeviceNamefromId(topOrder.getDevice().getId());
//                }
//
//                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
//
//                int titleBgColor1 = getResources().getColor(R.color.background);
//                if(topOrder.getOrderType()!=null) {
//                    String titleColor1 = sp.getString(topOrder.getOrderType().getLabel(), getString(R.string.backgroundcode));
//                    titleBgColor1 = Color.parseColor(titleColor1);
//                }
//
//                int bodyBgColor1 = getResources().getColor(R.color.white);
//                if(topOrder.getDevice()!=null){
//                    String bodyColor1 = sp.getString(topOrder.getDevice().getId(),getString(R.string.white));
//                    bodyBgColor1 = Color.parseColor(bodyColor1);
//                }
//
//                String detailString = "";
//
//                List<LineItem> lineItemList= topOrder.getLineItems();
//
//                if(lineItemList!=null) {
//                    for (LineItem li : lineItemList) {
//
//                        if(orderMonitorData.showLineItem(li.getName())) {
//                            detailString = detailString + String.valueOf(Character.toChars(9654))+ li.getName() + "\r\n";
//                            List<Modification> modList = li.getModifications();
//
//                            //check for modifications to line item
//                            if (modList != null) {
//
//                                Collections.sort(modList, new Comparator<Modification>() {
//                                    @Override
//                                    public int compare(Modification m1, Modification m2) {
//                                        int res = m1.getName().compareTo(m2.getName());
//                                        return res;
//                                    }
//                                });
//
//                                for (Modification mo : modList) {
//                                    detailString = detailString + " -" + mo.getName() + "\r\n";
//                                }
//                            }
//                            //check for custom modification
//                            if (li.getNote() != null) {
//                                detailString = detailString + " -" + li.getNote() + "\r\n";
//                            }
//                        }
//                    }
//                }
//
//                TextView orderTitleText = (TextView) relativeLayoutTop.findViewById(R.id.order_id_text);
//                TextView orderDetailText = (TextView) relativeLayoutTop.findViewById(R.id.order_detail_text);
//                TextView countdownText = (TextView) relativeLayoutTop.findViewById(R.id.countdown_text);
//                countdownText.setTag(topOrder.getCreatedTime());
//
//                if(showTimer){
//                    countdownText.setVisibility(View.VISIBLE);
//                    countdownTvList.add(countdownText);
//                }else{
//                    countdownText.setVisibility(View.GONE);
//                }
//
//                Button topDoneButton = (Button) relativeLayoutTop.findViewById(R.id.done_button);
//                orderTitleText.setTextSize(fontSize);
//                orderTitleText.setBackgroundColor(titleBgColor1);
//                orderDetailText.setTextSize(fontSize);
//                orderDetailText.setBackgroundColor(bodyBgColor1);
//                topDoneButton.setBackgroundColor(titleBgColor1);
//                countdownText.setTextSize(fontSize);
//                topDoneButton.setTextSize(fontSize);
//
//                orderDetailText.setMovementMethod(new ScrollingMovementMethod());
//
//                DateTime orderCreated = new DateTime(topOrder.getCreatedTime());
//                String timeCreatedString = DateTimeFormat.forPattern("hh:mm:ss a").print(orderCreated);
//
//                if(showOrderType) {
//                    timeCreatedString = timeCreatedString + "\r\n" + label;
//                }
//
//                if(showOrigin){
//                    timeCreatedString = timeCreatedString + "\r\n" + origin;
//                }
//
//                orderTitleText.setText(timeCreatedString);
//                orderDetailText.setText(detailString);
//
//                //add a index as a tag to reference the order in the click listener
//                topDoneButton.setTag(topId);
//
//                buttonList.add(topDoneButton);
//
//                topDoneButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        RelativeLayout parentView = (RelativeLayout) v.getParent();
//
//                        LayoutTransition lt = new LayoutTransition();
//                        lt.enableTransitionType(LayoutTransition.CHANGING);
//                        lt.addTransitionListener(new LayoutTransition.TransitionListener() {
//
//                            @Override
//                            public void startTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
//
//                            }
//
//                            @Override
//                            public void endTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
//
//                            }
//                        });
//
//                        Order doneOrder = progressOrdersList.get((Integer) v.getTag() - 1);
//
//                        //progressOrdersList.remove((Integer) v.getTag() - 1);
//
//                        orderMonitorData.markDone(doneOrder.getId(),doneOrder);
//                        parentView.setLayoutTransition(lt);
//                        v.setVisibility(View.INVISIBLE);
//
//                    }
//                });
//
//                if (bottomId <=listsize) {
//                    relativeLayoutBottom.setVisibility(View.VISIBLE);
//                    //minus one index for zero-based list
//                    Order bottomOrder = progressOrdersList.get(bottomId-1);
//                    String detailString2 = "";
//
//                    String label2 = "";
//
//                    if(bottomOrder.getOrderType()!=null&&showOrderType) {
//                        label2 = bottomOrder.getOrderType().getLabel();
//                    }
//
//                    String origin2 = "";
//
//                    if(bottomOrder.getDevice().getId()!=null&&showOrigin){
//                        origin2 = orderMonitorData.getDeviceNamefromId(bottomOrder.getDevice().getId());
//                    }
//
//                    int titleBgColor2 = getResources().getColor(R.color.background);
//                    if(bottomOrder.getOrderType()!=null) {
//                        String titleColor2 = sp.getString(bottomOrder.getOrderType().getLabel(), getString(R.string.backgroundcode));
//                        titleBgColor2 = Color.parseColor(titleColor2);
//                    }
//
//                    int bodyBgColor2 = getResources().getColor(R.color.white);
//                    if(bottomOrder.getDevice()!=null){
//                        String bodyColor2 = sp.getString(bottomOrder.getDevice().getId(),getString(R.string.white));
//                        bodyBgColor2 = Color.parseColor(bodyColor2);
//                    }
//
//                    TextView orderTitleText2 = (TextView) relativeLayoutBottom.findViewById(R.id.order_id_text2);
//                    TextView orderDetailText2 = (TextView) relativeLayoutBottom.findViewById(R.id.order_detail_text2);
//                    TextView countdownText2 = (TextView) relativeLayoutBottom.findViewById(R.id.countdown_text2);
//                    countdownText2.setTag(bottomOrder.getCreatedTime());
//
//                    if(showTimer){
//                        countdownText2.setVisibility(View.VISIBLE);
//                        countdownTvList.add(countdownText2);
//                    }else{
//                        countdownText2.setVisibility(View.GONE);
//                    }
//
//
//                    Button bottomDoneButton = (Button) relativeLayoutBottom.findViewById(R.id.done_button2);
//                    bottomDoneButton.setTextSize(fontSize);
//
//                    orderTitleText2.setTextSize(fontSize);
//                    orderTitleText2.setBackgroundColor(titleBgColor2);
//                    orderDetailText2.setTextSize(fontSize);
//                    orderDetailText2.setBackgroundColor(bodyBgColor2);
//                    bottomDoneButton.setBackgroundColor(titleBgColor2);
//                    countdownText2.setTextSize(fontSize);
//
//                    orderDetailText2.setMovementMethod(new ScrollingMovementMethod());
//
//                    DateTime orderCreated2 = new DateTime(bottomOrder.getCreatedTime());
//                    String timeCreatedString2 = DateTimeFormat.forPattern("hh:mm:ss a").print(orderCreated2);
//
//                    if(showOrderType) {
//                        timeCreatedString2 = timeCreatedString2 + "\r\n" + label2;
//                    }
//
//                    if(showOrigin){
//                        timeCreatedString2 = timeCreatedString2 + "\r\n" + origin2;
//                    }
//
//                    orderTitleText2.setText(timeCreatedString2);
//
//                    List<LineItem> lineItemList2 = bottomOrder.getLineItems();
//
//                    if(lineItemList2!=null) {
//                        for (LineItem li2 : lineItemList2) {
//
//                            if(orderMonitorData.showLineItem(li2.getName())) {
//
//                                detailString2 = detailString2 + String.valueOf(Character.toChars(9654)) + li2.getName() + "\r\n";
//
//                                List<Modification> modList2 = li2.getModifications();
//
//                                if (modList2 != null) {
//
//                                    Collections.sort(modList2, new Comparator<Modification>() {
//                                        @Override
//                                        public int compare(Modification m1, Modification m2) {
//                                            int res = m1.getName().compareTo(m2.getName());
//                                            return res;
//                                        }
//                                    });
//
//                                    for (Modification mo2 : modList2) {
//                                        detailString2 = detailString2 + " -" + mo2.getName() + "\r\n";
//                                    }
//                                }
//                                if (li2.getNote() != null) {
//                                    detailString2 = detailString2 + " -" + li2.getNote() + "\r\n";
//                                }
//                            }
//                        }
//                    }
//
//                    orderDetailText2.setText(detailString2);
//
//                    bottomDoneButton.setTag(bottomId);
//
//                    buttonList.add(bottomDoneButton);
//
//                    bottomDoneButton.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                            RelativeLayout parentView = (RelativeLayout) v.getParent();
//
//                            LayoutTransition lt = new LayoutTransition();
//                            lt.enableTransitionType(LayoutTransition.CHANGE_DISAPPEARING);
//                            lt.addTransitionListener(new LayoutTransition.TransitionListener() {
//
//                                @Override
//                                public void startTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
//
//                                }
//
//                                @Override
//                                public void endTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
//
//                                }
//                            });
//
//                            //remove the order from the list (subtract one to reference the zero-based list)
//
//                            Order doneOrder = progressOrdersList.get((Integer) v.getTag() - 1);
//
//                            orderMonitorData.markDone(doneOrder.getId(),doneOrder);
//                            parentView.setLayoutTransition(lt);
//                            v.setVisibility(View.INVISIBLE);
//                        }
//                    });
//                }
//            }
//
//            if(f%5==0){pagenumber++;}
//        }
    }

    private void updateFiveOrdersView(){

        final RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(
                screenWidthDp/5,
                ViewGroup.LayoutParams.MATCH_PARENT);

        horizLinearLayout.removeAllViews();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());

        for(int i =1;i<=progressOrdersList.size();i++){

            final Order thisOrder = progressOrdersList.get(i - 1);

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
            TextView countdownText = (TextView) relativeLayout.findViewById(R.id.countdown_text);

            countdownText.setTag(thisOrder.getCreatedTime());

            if(showTimer){
                countdownText.setVisibility(View.VISIBLE);
                countdownTvList.add(countdownText);
            }else{
                countdownText.setVisibility(View.GONE);
            }

            Button doneButton = (Button) relativeLayout.findViewById(R.id.done_button);
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

            //TODO: pass font size to adapter or read from shared prefs?
            final List<LineItem> displayLiList = sortItemsIntoBins(filteredLiList);

            //TODO: don't call sortitems into bins in the constructor, make a new list
            LineItemListAdapter lineItemListAdapter = new LineItemListAdapter(getActivity(),displayLiList,fontSize);

            lineItemLv.setAdapter(lineItemListAdapter);

            //TODO: get last visible position and compare with length of lineitem list
            //TODO: if everything is visible do nothing
            //TODO: if not, add a listview to the linear layout to the right of this layout and add
            //TODO: add an onpredrawlistener to the line item listview and check for overflow there, recursive function or a limit on the number of overflow views...


            final ViewTreeObserver vto = lineItemLv.getViewTreeObserver();
            vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    int runnableLastPos = lineItemLv.getLastVisiblePosition();
                    Log.v("runnable last", String.valueOf(runnableLastPos));
                    Log.v("size of displayLilist", String.valueOf(displayLiList.size()));

                    if (runnableLastPos < displayLiList.size() - 1) {
                        Log.v("a new listview", "should be added");
                        RelativeLayout overFlowRl = new RelativeLayout(getActivity());
                        overFlowRl.setLayoutParams(relativeLayoutParams);
                        horizLinearLayout.addView(overFlowRl, horizLinearLayout.indexOfChild(lineItemLv)+1);

                        Log.v(String.valueOf(horizLinearLayout.indexOfChild(lineItemLv)),"line item lv");


                        mLayoutInflater.inflate(R.layout.overflow_relative_layout, overFlowRl);
                        ListView overFlowLv = (ListView) overFlowRl.findViewById(R.id.overflow_list_view);
                        List<LineItem> overFlowLiList = displayLiList.subList(runnableLastPos, displayLiList.size());
                        LineItemListAdapter overFlowAdapter = new LineItemListAdapter(getActivity(), overFlowLiList, fontSize);
                        overFlowLv.setAdapter(overFlowAdapter);
                    }

                    vto.removeOnPreDrawListener(this);
                    return true;
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

            doneButton.setTag(i);

            buttonList.add(doneButton);

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


                    Order doneOrder = progressOrdersList.get((Integer) v.getTag() - 1);

                    orderMonitorData.markDone(doneOrder.getId(), doneOrder);

                    parentView.setLayoutTransition(lt);
                    v.setVisibility(View.INVISIBLE);
                }
            });

        }

    }

    private List<LineItem> sortItemsIntoBins(List<LineItem> lineItemList){

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

        for(String key:binTreeMap.keySet()){
            LineItem tagLineItem = new LineItem();
            tagLineItem.setId(getString(R.string.tag_line_item));
            tagLineItem.setName(key);
            displayList.add(tagLineItem);
            List<LineItem> correspBinList = binTreeMap.get(key);
            for(LineItem lineItem:correspBinList){
                displayList.add(lineItem);
            }
        }

        return displayList;
    }

}
