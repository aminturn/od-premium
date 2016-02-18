package com.trubeacon.cloverandroidapi.client;


import com.trubeacon.cloverandroidapi.Region;
import com.trubeacon.cloverandroidapi.Version;

public abstract class UrlResolver {

	protected Region region;
	protected UrlResolver(Region region) {
		if (region == null) throw new IllegalArgumentException("Region must be specified!");
		this.region = region;
	}
	
	public String getBaseUrl() {
		return getRegionBaseUrl() + Version.CURRENT.toString();
	}
	
	public String getBaseUrl(Version version) {
		return getRegionBaseUrl() + ((version != null) ? version : Version.CURRENT).toString();
	}
	
	public String getOAuthBaseUrl() {
		return getRegionOAuthBaseUrl();
	}
	
	public abstract void setRegion(Region region);
	protected abstract String getRegionBaseUrl();
	protected abstract String getRegionOAuthBaseUrl();
	
}
