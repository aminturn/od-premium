package com.trubeacon.cloverandroidapi.client;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;

import com.trubeacon.cloverandroidapi.client.RESTHttpResponse;
import com.trubeacon.cloverandroidapi.client.RESTService;

public class AndroidRESTResultReceiver extends ResultReceiver {

	public static final String REST_HTTP_RESPONSE = "REST_HTTP_RESPONSE";
	public static final int OK = 200;
	public static final int INVALID_JSON_ENCODING = 405;
	public static final int INVALID_REQUEST = 400;
	public static final int IO_ERROR = 404;

	private RESTService.RESTServiceCallback callback;

	public AndroidRESTResultReceiver(RESTService.RESTServiceCallback callback) {
		super(new Handler(Looper.getMainLooper()));
		this.callback = callback;
	}

	@Override
	public void onReceiveResult(int resultCode, Bundle resultData) {

		if (resultData != null && resultData.getParcelable(REST_HTTP_RESPONSE) != null) {
			RESTHttpResponse response = (RESTHttpResponse) resultData.getParcelable(REST_HTTP_RESPONSE);
			if (response != null && callback != null) {
				callback.onResponse(response);
			}
		}
		else {
			callback.onResponse(new RESTHttpResponse(resultCode, null, null));
		}

	}

}
