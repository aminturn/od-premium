package com.trubeacon.cloverandroidapi.client;


public class RESTServiceResolverHolder {
	
	private static RESTServiceResolver restServiceResolver;
	
	public static void withRESTServiceResolver(RESTServiceResolver restServiceResolver) {
		RESTServiceResolverHolder.restServiceResolver = restServiceResolver;
	}

    public static RESTService getRESTService() {
	    return restServiceResolver.getRESTService();
    }
	
}
