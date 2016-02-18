package com.trubeacon.cloverandroidapi.client;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.param.PathParam;
import com.trubeacon.cloverandroidapi.client.param.QueryParam;
import com.trubeacon.cloverandroidapi.BundleUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Set;

public class AndroidRESTIntentService extends IntentService {

	private static final String TAG = AndroidRESTIntentService.class.getName();

	public static final String TOKEN = TAG + ".TOKEN";
	public static final String URL = TAG + ".URL";
	public static final String HTTP_METHOD = TAG + ".HTTP_METHOD";
	public static final String QUERY_PARAMS = TAG + ".QUERY_PARAMS";
	public static final String PATH_PARAMS = TAG + ".PATH_PARAMS";
	public static final String ENTITY = TAG + ".ENTITY";
	public static final String REST_RESULT = TAG + ".REST_DATA";
	public static final String REST_RESULT_TYPE = TAG + ".REST_RESULT_TYPE";
	public static final String RESULT_RECEIVER = TAG + ".RESULT_RECEIVER";

	public AndroidRESTIntentService() {
		super(TAG);
	}

	@Override
	protected void onHandleIntent(Intent intent) {

		Bundle args = intent.getExtras();

		// this is critical, if we do not have a receiver there's no point in even performing the call
		if (args == null || args.getParcelable(RESULT_RECEIVER) == null) {
			Log.e(TAG, "Args and result receiver cannot be empty");
			return;
		}

		// all resources require authentication
		String token = args.getString(TOKEN);

		// web service params
		String url = args.getString(URL);
		HttpMethod method = HttpMethod.fromString(args.getString(HTTP_METHOD));
		Set<QueryParam> queryParams = AndroidRESTService.mapToQueryParams(BundleUtils.bundleToMap(args.getBundle(QUERY_PARAMS)));
		Set<PathParam> pathParams = AndroidRESTService.mapToPathParams(BundleUtils.bundleToMap(args.getBundle(PATH_PARAMS)));
		String entityString = args.getString(ENTITY);

		ResultReceiver receiver = args.getParcelable(RESULT_RECEIVER);
		try {
			AndroidRESTHttpResponse response = AndroidRESTService.doRequest(token, url, method, pathParams, queryParams, entityString);
			Bundle resultBundle = new Bundle();
			resultBundle.putParcelable(AndroidRESTResultReceiver.REST_HTTP_RESPONSE, response);
			receiver.send(AndroidRESTResultReceiver.OK, resultBundle);
		} catch (JsonGenerationException e) { // probably an issue with Jackson
			Log.e(TAG, "Problem mapping JSON entity");
			receiver.send(AndroidRESTResultReceiver.INVALID_JSON_ENCODING, null);
		} catch (JsonMappingException e) { // probably an issue with Jackson
			Log.e(TAG, "Problem mapping JSON entity");
			receiver.send(AndroidRESTResultReceiver.INVALID_JSON_ENCODING, null);
		} catch (UnsupportedEncodingException e) { // probably a problem encoding the entity
			Log.e(TAG, "Problem encoding URI");
			receiver.send(AndroidRESTResultReceiver.INVALID_REQUEST, null);
		} catch (IOException e) { // a generic IO exception, most likely a failed Http connection
			Log.e(TAG, "Problem sending HTTP request");
			receiver.send(AndroidRESTResultReceiver.IO_ERROR, null);
		}

	}

}
