package com.trubeacon.cloverandroidapi.client;

import com.trubeacon.cloverandroidapi.Region;

public class CloverUrlResolver extends UrlResolver {

	private static final String US = "https://api.clover.com/";
	private static final String US_OAUTH = "https://www.clover.com/oauth";
	private static final String EU = "https://api.eu.clover.com/";
	private static final String EU_OAUTH = "https://www.eu.clover.com/oauth";
	
	public CloverUrlResolver(Region region) {
		super(region);
	}
	
	/**
	 * Be careful when using this method, it should only be used during configuration.
	 * If you are trying to switch between regions, you should use multiple CloverService instances
	 * @param region
	 */
	public synchronized void setRegion(Region region) {
		this.region = region;
	}
	
	@Override
    protected String getRegionBaseUrl() {
		String url = US;
		if (super.region != null) {
			switch(super.region) {
			case EU:
				url = EU;
				break;
			case US:
			default:	
				url = US;
				break;
			}
		}
	    return url;
    }
		
	@Override
	public String getRegionOAuthBaseUrl() {
		String url = US_OAUTH;
		if (super.region != null) {
			switch(super.region) {
			case EU:
				url = EU_OAUTH;
				break;
			case US:
			default:
				url = US_OAUTH;
				break;
			}
		}
		return url;
	}
	
}
