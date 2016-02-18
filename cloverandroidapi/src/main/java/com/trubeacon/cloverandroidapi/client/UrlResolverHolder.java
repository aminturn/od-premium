package com.trubeacon.cloverandroidapi.client;


import com.trubeacon.cloverandroidapi.Version;

public class UrlResolverHolder {

	private static UrlResolver urlResolver;
	
	public static void withUrlResolver(UrlResolver urlResolver) {
		UrlResolverHolder.urlResolver = urlResolver;
	}
	
	public static String getBaseUrl() {
		return urlResolver.getBaseUrl();
	}
	
	public static String getBaseUrl(Version version) {
		return urlResolver.getBaseUrl(version);
	}
	
	public static String getOAuthBaseUrl() {
		return urlResolver.getOAuthBaseUrl();
	}
	
}
