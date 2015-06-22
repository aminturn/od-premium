package com.trubeacon.cloverandroidapi;

import android.content.Intent;
import android.os.Looper;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.tru.clover.api.client.RESTHttpResponse;
import com.tru.clover.api.client.RESTService;
import com.tru.clover.api.client.http.HttpMethod;
import com.tru.clover.api.client.http.HttpUtil;
import com.tru.clover.api.client.param.Param;
import com.tru.clover.api.client.param.PathParam;
import com.tru.clover.api.client.param.QueryParam;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class AndroidRESTService extends RESTService {

    private static final int HTTPS_PORT = 443;
    private static final NameValuePair CONTENT_TYPE = new BasicNameValuePair("Content-Type", "application/json");
    private static final String JSON_CONTENT_TYPE = "application/json";

    private ContextResolver contextResolver;
    public AndroidRESTService(ContextResolver contextResolver) {
        this.contextResolver = contextResolver;
    }

    @Override
    protected RESTHttpResponse get(String token, String url, Set<PathParam> pathParams, Set<QueryParam> queryParams, Set<com.tru.clover.api.client.param.Header> headers) throws InterruptedException, ExecutionException, IOException {
        assertNotUiThread();
        return null;
    }

    @Override
    protected void get(String token, String url, Set<PathParam> pathParams, Set<QueryParam> queryParams, Set<com.tru.clover.api.client.param.Header> headers, RESTServiceCallback restServiceCallback) throws IOException {
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
    protected RESTHttpResponse post(String token, String url, Set<PathParam> pathParams, Set<QueryParam> queryParams, String entity, Set<com.tru.clover.api.client.param.Header> headers) throws InterruptedException, ExecutionException, IOException {
        return null;
    }

    @Override
    protected void post(String token, String url, Set<PathParam> pathParams, Set<QueryParam> queryParams, String entity, Set<com.tru.clover.api.client.param.Header> headers, RESTServiceCallback restServiceCallback) throws IOException {
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
    protected RESTHttpResponse put(String token, String url, Set<PathParam> pathParams, Set<QueryParam> queryParams, String entity, Set<com.tru.clover.api.client.param.Header> headers) throws InterruptedException, ExecutionException, IOException {
        return null;
    }

    @Override
    protected void put(String token, String url, Set<PathParam> pathParams, Set<QueryParam> queryParams, String entity, Set<com.tru.clover.api.client.param.Header> headers, RESTServiceCallback restServiceCallback) throws IOException {
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
    protected RESTHttpResponse delete(String token, String url, Set<PathParam> pathParams, Set<QueryParam> queryParams, Set<com.tru.clover.api.client.param.Header> headers) throws InterruptedException, ExecutionException, IOException {
        return null;
    }

    @Override
    protected void delete(String token, String url, Set<PathParam> pathParams, Set<QueryParam> queryParams, Set<com.tru.clover.api.client.param.Header> headers, RESTServiceCallback restServiceCallback) throws IOException {
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

    public static AndroidRESTHttpResponse doRequest(String token, String url, HttpMethod method, Set<PathParam> pathParams, Set<QueryParam> queryParams, String entityString) throws JsonGenerationException, JsonMappingException, IOException {

        // get the appropriate request type
        HttpRequestBase request = null;
        switch(method) {
            case GET:
                request = get(url, pathParams, queryParams);
                request.addHeader("Accept", JSON_CONTENT_TYPE);
                request.addHeader("Content-Type", JSON_CONTENT_TYPE);
                break;
            case POST:
                request = post(url, entityString, pathParams, queryParams);
                request.addHeader("Accept", JSON_CONTENT_TYPE);
                request.addHeader("Content-Type", JSON_CONTENT_TYPE);
                break;
            case PUT:
                request = put(url, entityString, pathParams, queryParams);
                request.addHeader("Accept", JSON_CONTENT_TYPE);
                request.addHeader("Content-Type", JSON_CONTENT_TYPE);
                break;
            case DELETE:
                request = delete(url, pathParams, queryParams);
                request.addHeader("Accept", JSON_CONTENT_TYPE);
                request.addHeader("Content-Type", JSON_CONTENT_TYPE);
                break;
            default:
                break;
        }

        // get the thread safe client
        HttpClient client = threadSafeClient();

        // set the user credentials
        Header credHeader = authorizationHeader(token);
        request.setHeader(credHeader);

        // execute the request
        HttpResponse response = client.execute(request);

        return new AndroidRESTHttpResponse(response);
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

    private static Header authorizationHeader(String token) {
        return new BasicHeader("Authorization", "Bearer " + token);
    }

    /**
     * Get a thread safe Http client for requests
     * @return thread safe http client
     */
    private static DefaultHttpClient threadSafeClient() {

        // Create and initialize scheme registry, only allowing HTTPS
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), HTTPS_PORT));

        DefaultHttpClient client = new DefaultHttpClient();
        HttpParams params = client.getParams();

        client = new DefaultHttpClient(new ThreadSafeClientConnManager(params, schemeRegistry), params);
        return client;
    }

    /**
     *
     * Form an HttpGet using query params and path params
     *
     * @param url
     * @param queryParams
     * @param pathParams
     * @return
     */
    private static HttpGet get(String url, Set<PathParam> pathParams, Set<QueryParam> queryParams) {
        String uri = HttpUtil.buildUrl(url, pathParams, queryParams);
        return new HttpGet(uri);
    }

    /**
     *
     * Form an HttpPost using the provided entity (as a string), path params
     *
     * @param url
     * @param entityString
     * @param pathParams
     * @return
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    private static HttpPost post(String url, String entityString, Set<PathParam> pathParams, Set<QueryParam> queryParams) throws UnsupportedEncodingException {

        String uri = HttpUtil.buildUrl(url, pathParams, queryParams);

        HttpPost post = new HttpPost(uri);
        post.setHeader(new BasicHeader(CONTENT_TYPE.getName(), CONTENT_TYPE.getValue()));

        StringEntity se = new StringEntity(entityString);
        se.setContentType(new BasicHeader(CONTENT_TYPE.getName(), CONTENT_TYPE.getValue()));
        post.setEntity(se);

        return post;
    }

    /**
     *
     * Form an HttpPut using the provided entity (as a string), path params
     *
     * @param url
     * @param entityString
     * @param pathParams
     * @return
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    private static HttpPut put(String url, String entityString, Set<PathParam> pathParams, Set<QueryParam> queryParams) throws JsonGenerationException, JsonMappingException, UnsupportedEncodingException, IOException {

        String uri = HttpUtil.buildUrl(url, pathParams, queryParams);

        HttpPut put = new HttpPut(uri);
        put.setHeader(new BasicHeader(CONTENT_TYPE.getName(), CONTENT_TYPE.getValue()));

        StringEntity se = new StringEntity(entityString);
        put.setEntity(se);

        return put;

    }

    /**
     *
     * Form an HttpDelete using the provided pathparams
     *
     * @param url
     * @param pathParams
     * @return
     */
    private static HttpDelete delete(String url, Set<PathParam> pathParams, Set<QueryParam> queryParams) {
        String uri = HttpUtil.buildUrl(url, pathParams, queryParams);
        return new HttpDelete(uri);
    }

}
