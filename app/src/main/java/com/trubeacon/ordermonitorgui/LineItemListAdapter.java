package com.trubeacon.ordermonitorgui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.tru.clover.api.order.LineItem;
import com.tru.clover.api.order.Modification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Andrew on 8/3/15.
 */
public class LineItemListAdapter extends BaseAdapter {

    private List<LineItem> lineItemList = new ArrayList<>();
    private Context mContext;
    private float fontSize;
    private OrderMonitorData orderMonitorData = OrderMonitorData.getOrderMonitorData();
    private int bodyColor;

    public LineItemListAdapter(Context context,List<LineItem> lineItemList,float fontSize, int bodyColor){
        this.lineItemList = lineItemList;
        mContext = context;
        this.fontSize=fontSize;
        this.bodyColor = bodyColor;
    }

    public void updateAdapter(List<LineItem> lineItemList){
        this.lineItemList = lineItemList;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return lineItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return lineItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean isEnabled(int position) {

        if(position<lineItemList.size()) {

            if (lineItemList.get(position).getId().equals(mContext.getString(R.string.tag_line_item)) || lineItemList.get(position).getId().equals(mContext.getString(R.string.modifier))) {
                return false;
            } else {
                return true;
            }
        }else{
            Log.v("lineitemlistadapter", "index out of bounds");
            return true;
        }

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LineItem thisLineItem = lineItemList.get(i);


        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.line_item_view,viewGroup,false);
        }

        TextView lineItemDetailTv = (TextView) view.findViewById(R.id.line_item_detail);
        ImageView itemDoneIv = (ImageView) view.findViewById(R.id.item_checked_image);

        lineItemDetailTv.setTextSize(fontSize);

        View marginView = view.findViewById(R.id.margin_view);


        if(thisLineItem.getId().equals(mContext.getString(R.string.tag_line_item))||thisLineItem.getId().equals(mContext.getString(R.string.modifier))){
            //no margins on modifiers and tables separators
            marginView.setVisibility(View.GONE);
        }else{
            //top margins on line items except for first in list or if they come after a tables seperator
            if(i==0){
                marginView.setVisibility(View.GONE);
            }else if(lineItemList.get(i-1).getId().equals(mContext.getString(R.string.tag_line_item))){
                marginView.setVisibility(View.GONE);
            }else{
                marginView.setVisibility(View.VISIBLE);
            }
        }


        if(!thisLineItem.getId().equals(mContext.getString(R.string.tag_line_item))) {

            //line item is an actual line item, not a separator
            view.setBackgroundColor(bodyColor);

            int lineItemColor = orderMonitorData.lineItemColor(thisLineItem.getName());

            lineItemDetailTv.setTextColor(lineItemColor);

            //if null or ordered, image is invisible
            if (thisLineItem.getUserData() == null || thisLineItem.getUserData().equals(mContext.getString(R.string.ordered))) {
                itemDoneIv.setVisibility(View.INVISIBLE);
            //if ready, image visible with single check
            } else if(thisLineItem.getUserData().equals(mContext.getString(R.string.ready))){
                itemDoneIv.setVisibility(View.VISIBLE);
                itemDoneIv.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_done_black_24dp));
            //if served, image visible with double check
            }else{
                itemDoneIv.setVisibility(View.VISIBLE);
                itemDoneIv.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_done_all_black_24dp));
            }

            String lineItemDetail = thisLineItem.getName();


            //TODO: change how custom modifier is shown
            if (thisLineItem.getNote() != null) {
                lineItemDetail = lineItemDetail + "\r\n" + "  -" + thisLineItem.getNote();
            }

            lineItemDetailTv.setText(lineItemDetail);

            view.setTag(thisLineItem);

        }else{
            //line item is a separator
            lineItemDetailTv.setText(thisLineItem.getName());
            view.setBackgroundColor(mContext.getResources().getColor(R.color.background));
            lineItemDetailTv.setTextColor(mContext.getResources().getColor(R.color.white));
            itemDoneIv.setVisibility(View.INVISIBLE);
        }

        return view;
    }
}
