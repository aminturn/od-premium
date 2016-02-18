package com.trubeacon.cloverandroidapi.client;

import android.content.Intent;
import android.os.Looper;

import com.trubeacon.cloverandroidapi.BundleUtils;
import com.trubeacon.cloverandroidapi.ContextResolver;
import com.trubeacon.cloverandroidapi.client.http.HttpMethod;
import com.trubeacon.cloverandroidapi.client.http.HttpUtil;
import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.client.param.PathParam;
import com.trubeacon.cloverandroidapi.client.param.QueryParam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class AndroidRESTService extends RESTService {

    private static final String JSON_CONTENT_TYPE = "application/json";
    private static CloverRequestThrottler throttler = new CloverRequestThrottler(1000, TimeUnit.MILLISECONDS, 15);

    private ContextResolver contextResolver;
    public AndroidRESTService(ContextResolver contextResolver) {
        this.contextResolver = contextResolver;
    }

    @Override
    protected RESTHttpResponse get(String token, String url, Set<PathParam> pathParams, Set<QueryParam> queryParams, Set<com.trubeacon.cloverandroidapi.client.param.Header> headers) throws InterruptedException, ExecutionException, IOException {
        assertNotUiThread();
        return null;
    }

    @Override
    protected void get(String token, String url, Set<PathParam> pathParams, Set<QueryParam> queryParams, Set<com.trubeacon.cloverandroidapi.client.param.Header> headers, RESTServiceCallback restServiceCallback) throws IOException {
        Intent intent = new Intent(contextResolver.getContext(), AndroidRESTIntentService.class);
        intent.putExtra(AndroidRESTIntentService.RESULT_RECEIVER, new AndroidRESTResultReceiver(restServiceCallback)); // most important arg, without this the service can't communicate back
        intent.putExtra(AndroidRESTIntentService.TOKEN, token);
        intent.putExtra(AndroidRESTIntentService.URL, url);
        intent.putExtra(AndroidRESTIntentService.HTTP_METHOD, HttpMethod.GET.toString());
        intent.putExtra(AndroidRESTIntentService.PATH_PARAMS, BundleUtils.mapToBundle(pathParamsToMap(pathParams)));
        intent.putExtra(AndroidRESTIntentService.QUERY_PARAMS, BundleUtils.mapToBundle(queryParamsToMap(queryParams)));
        contextResolver.getContext().startService(intent);
    }

    @Override
    protected RESTHttpResponse post(String token, String url, Set<PathParam> pathParams, Set<QueryParam> queryParams, String entity, Set<com.trubeacon.cloverandroidapi.client.param.Header> headers) throws InterruptedException, ExecutionException, IOException {
        return null;
    }

    @Override
    protected void post(String token, String url, Set<PathParam> pathParams, Set<QueryParam> queryParams, String entity, Set<com.trubeacon.cloverandroidapi.client.param.Header> headers, RESTServiceCallback restServiceCallback) throws IOException {
        Intent intent = new Intent(contextResolver.getContext(), AndroidRESTIntentService.class);
        intent.putExtra(AndroidRESTIntentService.RESULT_RECEIVER, new AndroidRESTResultReceiver(restServiceCallback)); // most important arg, without this the service can't communicate back
        intent.putExtra(AndroidRESTIntentService.TOKEN, token);
        intent.putExtra(AndroidRESTIntentService.URL, url);
        intent.putExtra(AndroidRESTIntentService.HTTP_METHOD, HttpMethod.POST.toString());
        intent.putExtra(AndroidRESTIntentService.ENTITY, entity);
        intent.putExtra(AndroidRESTIntentService.PATH_PARAMS, BundleUtils.mapToBundle(pathParamsToMap(pathParams)));
        intent.putExtra(AndroidRESTIntentService.QUERY_PARAMS, BundleUtils.mapToBundle(queryParamsToMap(queryParams)));
        contextResolver.getContext().startService(intent);
    }

    @Override
    protected RESTHttpResponse put(String token, String url, Set<PathParam> pathParams, Set<QueryParam> queryParams, String entity, Set<com.trubeacon.cloverandroidapi.client.param.Header> headers) throws InterruptedException, ExecutionException, IOException {
        return null;
    }

    @Override
    protected void put(String token, String url, Set<PathParam> pathParams, Set<QueryParam> queryParams, String entity, Set<com.trubeacon.cloverandroidapi.client.param.Header> headers, RESTServiceCallback restServiceCallback) throws IOException {
        Intent intent = new Intent(contextResolver.getContext(), AndroidRESTIntentService.class);
        intent.putExtra(AndroidRESTIntentService.RESULT_RECEIVER, new AndroidRESTResultReceiver(restServiceCallback)); // most important arg, without this the service can't communicate back
        intent.putExtra(AndroidRESTIntentService.TOKEN, token);
        intent.putExtra(AndroidRESTIntentService.URL, url);
        intent.putExtra(AndroidRESTIntentService.HTTP_METHOD, HttpMethod.PUT.toString());
        intent.putExtra(AndroidRESTIntentService.ENTITY, entity);
        intent.putExtra(AndroidRESTIntentService.PATH_PARAMS, BundleUtils.mapToBundle(pathParamsToMap(pathParams)));
        intent.putExtra(AndroidRESTIntentService.QUERY_PARAMS, BundleUtils.mapToBundle(queryParamsToMap(queryParams)));
        contextResolver.getContext().startService(intent);
    }

    @Override
    protected RESTHttpResponse delete(String token, String url, Set<PathParam> pathParams, Set<QueryParam> queryParams, Set<com.trubeacon.cloverandroidapi.client.param.Header> headers) throws InterruptedException, ExecutionException, IOException {
        return null;
    }

    @Override
    protected void delete(String token, String url, Set<PathParam> pathParams, Set<QueryParam> queryParams, Set<com.trubeacon.cloverandroidapi.client.param.Header> headers, RESTServiceCallback restServiceCallback) throws IOException {
        Intent intent = new Intent(contextResolver.getContext(), AndroidRESTIntentService.class);
        intent.putExtra(AndroidRESTIntentService.RESULT_RECEIVER, new AndroidRESTResultReceiver(restServiceCallback)); // most important arg, without this the service can't communicate back
        intent.putExtra(AndroidRESTIntentService.TOKEN, token);
        intent.putExtra(AndroidRESTIntentService.URL, url);
        intent.putExtra(AndroidRESTIntentService.HTTP_METHOD, HttpMethod.PUT.toString());
        intent.putExtra(AndroidRESTIntentService.PATH_PARAMS, BundleUtils.mapToBundle(pathParamsToMap(pathParams)));
        intent.putExtra(AndroidRESTIntentService.QUERY_PARAMS, BundleUtils.mapToBundle(queryParamsToMap(queryParams)));
        contextResolver.getContext().startService(intent);
    }

    private void assertNotUiThread() {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            throw new IllegalStateException("Blocking REST calls cannot be performed on UI thread!");
        }
    }

    public static AndroidRESTHttpResponse doRequest(String token, String uri, HttpMethod method, Set<PathParam> pathParams, Set<QueryParam> queryParams, String entityString) throws IOException {
        AndroidRESTHttpResponse response = _doRequest(token, uri, method, pathParams, queryParams, entityString);
        int retries = 0;
        while (retries++ < 3 && response.getStatusCode() == 429) {
            // throttle the connection for this token
            throttler.throttle(token);
            try {
                Thread.sleep(1000);
            } catch (Exception ex) {}
            response = _doRequest(token, uri, method, pathParams, queryParams, entityString);
        }
        return response;
    }

    private static AndroidRESTHttpResponse _doRequest(String token, String uri, HttpMethod method, Set<PathParam> pathParams, Set<QueryParam> queryParams, String entityString) throws IOException {

        URL url = new URL(HttpUtil.buildUrl(uri, pathParams, queryParams));

        String responseBody = null;
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {

            urlConnection.setRequestProperty("Accept", JSON_CONTENT_TYPE);
            urlConnection.setRequestProperty("Content-Type", JSON_CONTENT_TYPE);
            urlConnection.setRequestProperty("Authorization", "Bearer " + token);
            urlConnection.setRequestMethod(method.toString());
            urlConnection.setDoInput(true);

            // get the appropriate request type
            switch (method) {
                case POST:
                case PUT:
                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(true);
                    byte[] content = entityString.getBytes("UTF-8");
                    urlConnection.setFixedLengthStreamingMode(content.length);
                    writeStream(content, urlConnection.getOutputStream());
                    break;
                default:
                    break;
            }

            urlConnection.connect();
            try {
                responseBody = readStream(urlConnection.getInputStream());
            } catch (Exception ex) {
                responseBody = readStream(urlConnection.getErrorStream());
            }

        } finally {
            urlConnection.disconnect();
        }
        return new AndroidRESTHttpResponse(urlConnection.getResponseCode(), responseBody, urlConnection.getHeaderFields());
    }

    private static String readStream(InputStream is) throws IOException {

        if (is == null) throw new FileNotFoundException("Cannot read empty inputstream");

        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }

    private static void writeStream(byte[] body, OutputStream os) throws IOException {
        if (os == null) throw new FileNotFoundException("Cannot write to empty outputstream");
        os.write(body);
    }

    public static Map<String, String> queryParamsToMap(Collection<QueryParam> queryParams) {
        Map<String, String> map = new HashMap<String, String>();
        if (queryParams != null) {
            for (QueryParam param : queryParams) {
                String key = param.getKey();
                String value = (param.getValue() != null) ? param.getValue().toString() : null;
                if (map.get(key) != null) {
                    map.put(key, map.get(key) + "|" + value);
                }
                else {
                    map.put(key, value);
                }
            }
        }
        return map;
    }

    public static Set<QueryParam> mapToQueryParams(Map<String, String> map) {
        Set<QueryParam> params = new HashSet<QueryParam>();
        if (map != null) {
            for (String key : map.keySet()) {
                String value = map.get(key);
                if (value != null) {
                    String[] delimited = value.split("\\|");
                    for (String v : delimited) {
                        params.add(Param.query(key, v));
                    }
                }
            }
        }
        return params;
    }

    public static Map<String, String> pathParamsToMap(Collection<PathParam> pathParams) {
        Map<String, String> map = new HashMap<String, String>();
        if (pathParams != null) {
            for (PathParam param : pathParams) {
                map.put(param.getKey(), param.getValue());
            }
        }
        return map;
    }

    public static Set<PathParam> mapToPathParams(Map<String, String> map) {
        Set<PathParam> params = new HashSet<PathParam>();
        if (map != null) {
            for (String key : map.keySet()) {
                params.add(Param.path(key, map.get(key)));
            }
        }
        return params;
    }

}
