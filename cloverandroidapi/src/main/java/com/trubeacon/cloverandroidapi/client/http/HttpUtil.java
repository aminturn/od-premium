package com.trubeacon.cloverandroidapi.client.http;

import com.trubeacon.cloverandroidapi.client.param.PathParam;
import com.trubeacon.cloverandroidapi.client.param.QueryParam;

import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * Some helper methods for initializing an HTTP connection
 * 
 * @author atsharp
 *
 */
public class HttpUtil {

	public static String buildUrl(String baseUri, Set<PathParam> pathParams) {
		
		if (pathParams != null) {
			for (PathParam param : pathParams) {
				baseUri = baseUri.replaceAll("\\{" + param.getKey() + "\\}", param.getValue());
			}
		}
		
		return baseUri;
	}
	
	public static String buildUrl(String url, Set<PathParam> pathParams, Set<QueryParam> queryParams) {
		
		String qualifiedUrl = url;
		if (pathParams != null) {
			for (PathParam param : pathParams) {
				qualifiedUrl = qualifiedUrl.replaceAll("\\{" + param.getKey() + "\\}", param.getValue());
			}
		}

		// http://stackoverflow.com/questions/1861620/is-there-a-java-package-to-handle-building-urls
		String query = "";
		if (queryParams != null) {

			Iterator<QueryParam> iter = queryParams.iterator();
			while (iter.hasNext()) {
				QueryParam next = iter.next();
				try {
					query += URLEncoder.encode(next.getKey(), "UTF-8") + "=" + URLEncoder.encode(next.getValue().toString(), "UTF-8");
					if (iter.hasNext()) query += "&";
				} catch (Exception ex) {}				
			}
			
		}
		
		if (query != null && !query.isEmpty()) {
			qualifiedUrl += "?" + query;
		}
		
		return qualifiedUrl;
	}
			
}
