package com.trubeacon.cloverandroidapi.client;


import com.trubeacon.cloverandroidapi.client.util.TimedSemaphore;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Inspired from @see com.ning.http.client.extra.ThrottleRequestFilter
 * but implemented per-authorization token. This way, requests are throttled
 * per-user/auth rather than per client/ip
 * @author atsharp
 *
 */
public class CloverRequestThrottler {

    private final int maxConnections;
    private final long timePeriod;
    private final TimeUnit timeUnit;
    private final Map<String, SoftReference<TimedSemaphore>> available;

    public CloverRequestThrottler(long timePeriod, TimeUnit timeUnit, int maxConnections) {
    	this.timePeriod = timePeriod;
    	this.timeUnit = timeUnit;
        this.maxConnections = maxConnections;
        available = new ConcurrentHashMap<String, SoftReference<TimedSemaphore>>();
    }

    public void throttle(String token) {
        try {
            safeGet(token).acquire();
        } catch (InterruptedException ex) {}
    }
	
    private TimedSemaphore safeGet(String key) {
    	TimedSemaphore semaphore = (available.get(key) != null && available.get(key).get() != null) ? available.get(key).get() : null;
    	if (semaphore == null) {
    		semaphore = constructSemaphore(key);
    	}
		return semaphore;
    }
    
    private synchronized TimedSemaphore constructSemaphore(String key) {
    	TimedSemaphore semaphore = (available.get(key) != null && available.get(key).get() != null) ? available.get(key).get() : null;
    	if (semaphore == null) {
			semaphore = new TimedSemaphore(timePeriod, timeUnit, maxConnections);
			available.put(key, new SoftReference<TimedSemaphore>(semaphore));
    	}
    	return semaphore;
    }
    
}
