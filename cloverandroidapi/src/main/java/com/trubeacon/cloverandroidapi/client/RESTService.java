package com.trubeacon.cloverandroidapi.client;


import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import com.trubeacon.cloverandroidapi.client.param.Header;
import com.trubeacon.cloverandroidapi.client.param.PathParam;
import com.trubeacon.cloverandroidapi.client.param.QueryParam;

public abstract class RESTService {

	public interface RESTServiceCallback {
		public void onResponse(RESTHttpResponse response);
	}
	
	protected abstract RESTHttpResponse get(String token, String url, Set<PathParam> pathParams, Set<QueryParam> queryParams, Set<Header> headers) throws InterruptedException, ExecutionException, IOException;
	protected abstract void get(String token, String url, Set<PathParam> pathParams, Set<QueryParam> queryParams, Set<Header> headers, final RESTServiceCallback callback) throws IOException;
	
	protected abstract RESTHttpResponse post(String token, String url, Set<PathParam> pathParams, Set<QueryParam> queryParams, String entity, Set<Header> headers) throws InterruptedException, ExecutionException, IOException;
	protected abstract void post(String token, String url, Set<PathParam> pathParams, Set<QueryParam> queryParams, String entity, Set<Header> headers, final RESTServiceCallback callback) throws IOException;
	
	protected abstract RESTHttpResponse put(String token, String url, Set<PathParam> pathParams, Set<QueryParam> queryParams, String entity, Set<Header> headers) throws InterruptedException, ExecutionException, IOException;
	protected abstract void put(String token, String url, Set<PathParam> pathParams, Set<QueryParam> queryParams, String entity, Set<Header> headers, final RESTServiceCallback callback) throws IOException;
	
	protected abstract RESTHttpResponse delete(String token, String url, Set<PathParam> pathParams, Set<QueryParam> queryParams, Set<Header> headers) throws InterruptedException, ExecutionException, IOException;
	protected abstract void delete(String token, String url, Set<PathParam> pathParams, Set<QueryParam> queryParams, Set<Header> headers, final RESTServiceCallback callback) throws IOException;
	
}
