package com.trubeacon.ordermonitorgui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class OrderMonitorPreferences extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static String actionBarTitle = "Order Monitor Settings";
    private static String CLOVER_PREF_KEY = "clover_pref_key";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);

        Preference cloverPref = findPreference(CLOVER_PREF_KEY);
        cloverPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                getActivity().getFragmentManager().beginTransaction().add(R.id.container, new MyWebViewFragment()).addToBackStack("").commit();

                return false;
            }
        });

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //set up the actionbar with the fragment title and enable the home button
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(actionBarTitle);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());

        Preference displayPref = findPreference(getString(R.string.display_pref));
        displayPref.setSummary(sp.getString(getString(R.string.display_pref),""));

        Preference fontPref = findPreference(getString(R.string.font_size_pref));
        fontPref.setSummary(sp.getString(getString(R.string.font_size_pref),""));

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId==android.R.id.home){
            getActivity().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

        if (s.equals(getString(R.string.display_pref))) {
            Preference displayPref = findPreference(s);
            // Set summary to be the user-description for the selected value
            displayPref.setSummary(sharedPreferences.getString(s, ""));
        }
        else if(s.equals(getString(R.string.font_size_pref))) {
            Preference fontPref = findPreference(s);
            // Set summary to be the user-description for the selected value
            fontPref.setSummary(sharedPreferences.getString(s, ""));
        }
        else if(s.equals(getString(R.string.order_type_pref))){
            Preference orderPref = findPreference(s);
            orderPref.setSummary(sharedPreferences.getString(s,""));
        }

    }
}
