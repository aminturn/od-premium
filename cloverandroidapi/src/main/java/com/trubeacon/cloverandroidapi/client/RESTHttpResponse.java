package com.trubeacon.cloverandroidapi.client;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RESTHttpResponse {

	private int statusCode;
	private String responseBody;
	private Map<String, List<String>> headers = new HashMap<String, List<String>>();

	public RESTHttpResponse() {}
	public RESTHttpResponse(int statusCode, String responseBody, Map<String, List<String>> headers) {
		this.statusCode = statusCode;
		this.responseBody = responseBody;
		this.headers = headers;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	public String getResponseBody() {
		return responseBody;
	}
	public Map<String, List<String>> getHeaders() {
		return headers;
	}
	
	public String getHeader(String header) {
		String value = null;
		for (String h : headers.keySet()) {
			if (h.equalsIgnoreCase(header)) {
				List<String> values = headers.get(h);
				if (values != null && values.size() > 0) {
					value = values.get(0);
				}
			}
		}
		return value;
	}
	
}
