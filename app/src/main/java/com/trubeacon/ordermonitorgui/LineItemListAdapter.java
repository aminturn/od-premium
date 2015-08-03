package com.trubeacon.ordermonitorgui;

import android.content.Context;
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
    public View getView(int i, View view, ViewGroup viewGroup) {


        //TODO: find line item view
        //TODO: fill textview with li name and modifier names (sort these alphabetically)
        //TODO: set background and indicator according the note on the line item (i.e. ordered, prepping, prepped, served)

        //TODO: binnames for tables app?

        LineItem thisLineItem = lineItemList.get(i);


        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.line_item_view,viewGroup,false);
        }

        TextView lineItemDetailTv = (TextView) view.findViewById(R.id.line_item_detail);
        ImageView itemDoneIv = (ImageView) view.findViewById(R.id.item_checked_image);

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
                lineItemDetail = lineItemDetail + " -" + mo.getName() + "\r\n";
            }
        }

        //TODO: which field to use for marking the item done?, the note is used for custom modifiers..

        thisLineItem.getUserData();


        if (li.getNote() != null) {
            detailString = detailString + " -" + li.getNote() + "\r\n";
        }


        return null;
    }
}
