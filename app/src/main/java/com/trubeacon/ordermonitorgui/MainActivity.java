package com.trubeacon.ordermonitorgui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private KeyUpCallback keyUpCallback;


    public interface KeyUpCallback{
        void keyUp(int keyCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        if(savedInstanceState==null) {
            getFragmentManager().beginTransaction().add(R.id.container, new OrdersInProgressFragment()).commit();
            getFragmentManager().executePendingTransactions();
        }

        Fragment topFragment = getFragmentManager().findFragmentById(R.id.container);

        if(topFragment instanceof OrdersInProgressFragment){
            keyUpCallback = (KeyUpCallback) topFragment;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            getFragmentManager().beginTransaction().replace(R.id.container, new OrderMonitorPreferences()).addToBackStack("").commit();
            return true;
        }
        else if (id==R.id.action_orders_in_progress){
            OrdersInProgressFragment ordersInProgressFragment = new OrdersInProgressFragment();
            keyUpCallback = ordersInProgressFragment;
            getFragmentManager().beginTransaction().replace(R.id.container,ordersInProgressFragment).addToBackStack("").commit();
            return true;
        }
        else if(id == R.id.action_done_orders){
            getFragmentManager().beginTransaction().replace(R.id.container,new DoneOrdersFragment()).addToBackStack("").commit();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
            FragmentManager fm = getFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
            } else {
                super.onBackPressed();
            }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        Fragment topFragment = getFragmentManager().findFragmentById(R.id.container);
        if(topFragment instanceof OrdersInProgressFragment){
            keyUpCallback.keyUp(keyCode);
        }
        return true;
    }
}
