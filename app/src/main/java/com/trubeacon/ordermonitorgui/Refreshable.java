package com.trubeacon.ordermonitorgui;

/**
 * Created by Andrew on 6/24/15.
 */
public interface Refreshable {

    public void refresh();
    public void onPreRefresh();
    public void onRefresh();

}
