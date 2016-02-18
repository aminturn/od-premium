package com.trubeacon.cloverandroidapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.util.HashMap;
import java.util.Map;


public class BundleUtils {

	public static Map<String, String> bundleToMap(Bundle bundle) {
		Map<String, String> map = new HashMap<String, String>();
		if (bundle != null) {
			for (String key : bundle.keySet()) {
				map.put(key, bundle.getString(key));
			}
		}
		return map;
	}

	public static Bundle mapToBundle(Map<String, String> map) {
		Bundle bundle = new Bundle();
		if (map != null) {
			for (String key : map.keySet()) {
				bundle.putString(key, map.get(key));
			}
		}
		return bundle;
	}

	public static String parseStringExtra(String key, FragmentActivity activity) {
		String value = null;
		Intent intent = activity.getIntent();
		if (intent != null) {
			Bundle extras = intent.getExtras();
			if (extras != null) {
				value = extras.getString(key);
			}
		}
		return value;
	}

	public static String parseStringExtra(String key, Intent intent) {
		String value = null;
		Bundle extras = intent.getExtras();
		if (extras != null) {
			value = extras.getString(key);
		}
		return value;
	}

	public static boolean parseBooleanExtra(String key, FragmentActivity activity) {
		boolean value = false;
		Intent intent = activity.getIntent();
		if (intent != null) {
			Bundle extras = intent.getExtras();
			if (extras != null) {
				value = extras.getBoolean(key);
			}
		}
		return value;
	}

	public static boolean parseBooleanExtra(String key, Intent intent) {
		boolean value = false;
		Bundle extras = intent.getExtras();
		if (extras != null) {
			value = extras.getBoolean(key);
		}
		return value;
	}

}
