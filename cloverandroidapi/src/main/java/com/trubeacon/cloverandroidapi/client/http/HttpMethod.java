package com.trubeacon.cloverandroidapi.client.http;

/**
 * Simple enum for choosing supported HTTP methods
 * 
 * @author atsharp
 *
 */
public enum HttpMethod {
	
	GET,
	POST,
	PUT,
	DELETE;

	public static HttpMethod fromString(String httpMethodString) {
		HttpMethod method = null;
		for (HttpMethod m : HttpMethod.values()) {
			if (m.toString().equalsIgnoreCase(httpMethodString)) {
				method = m;
				break;
			}
		}
		return method;
	}
}
