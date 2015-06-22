package com.trubeacon.ordermonitorgui;

import android.content.Context;

import com.tru.clover.api.client.RESTService;
import com.tru.clover.api.client.RESTServiceResolver;
import com.trubeacon.cloverandroidapi.AndroidRESTService;
import com.trubeacon.cloverandroidapi.ContextResolver;


/**
 * Author: Aaron S.
 * Email: aaron@trubeacon.com
 */
public class CloverService {

    private static com.tru.clover.api.CloverService cloverService;

    public static com.tru.clover.api.CloverService getService() {
        if (cloverService == null) {
            cloverService = new com.tru.clover.api.CloverService.Builder()
                    .withProduction(false)
                    .withRESTServiceResolver(new RESTServiceResolver() {
                        @Override
                        public RESTService getRESTService() {
                            return new AndroidRESTService(new ContextResolver() {
                                @Override
                                public Context getContext() {
                                    return OrderMonitorGUI.context();
                                }
                            });
                        }
                    })
                    .build();
        }
        return CloverService.cloverService;
    }

}
