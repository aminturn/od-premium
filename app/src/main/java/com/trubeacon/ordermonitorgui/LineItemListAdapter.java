package com.trubeacon.ordermonitorgui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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

    public LineItemListAdapter(Context context,List<LineItem> lineItemList){
        this.lineItemList = lineItemList;
        mContext = context;
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
        if(lineItemList.get(position).getId().equals(mContext.getString(R.string.tag_line_item))){
            return false;
        }else{
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


        if(!thisLineItem.getId().equals(mContext.getString(R.string.tag_line_item))) {

            //line item is an actual line item, not a separator

            if (thisLineItem.getUserData() != null && thisLineItem.getUserData().equals(mContext.getString(R.string.checked))) {
                itemDoneIv.setVisibility(View.VISIBLE);
            } else {
                itemDoneIv.setVisibility(View.INVISIBLE);
            }

            String lineItemDetail = thisLineItem.getName();

            List<Modification> modList = thisLineItem.getModifications();
            if (modList != null) {

                Collections.sort(modList, new Comparator<Modification>() {
                    @Override
                    public int compare(Modification m1, Modification m2) {
                        return m1.getName().compareTo(m2.getName());
                    }
                });

                for (Modification mo : modList) {
                    lineItemDetail = lineItemDetail + "\r\n" + "  -" + mo.getName();
                }
            }

            if (thisLineItem.getBinName() != null) {
                Log.v("bin name", thisLineItem.getBinName());
            } else {
                Log.v("bin name", " is null");
            }

            thisLineItem.getUserData();

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
