package com.trubeacon.ordermonitorgui;

import android.content.Context;


import com.trubeacon.cloverandroidapi.ContextResolver;
import com.trubeacon.cloverandroidapi.client.AndroidRESTService;
import com.trubeacon.cloverandroidapi.client.RESTService;
import com.trubeacon.cloverandroidapi.client.RESTServiceResolver;


/**
 * Author: Aaron S.
 * Email: aaron@trubeacon.com
 */
public class CloverService {

    private static com.trubeacon.cloverandroidapi.CloverService cloverService;

    public static com.trubeacon.cloverandroidapi.CloverService getService() {
        if (cloverService == null) {
            cloverService = new com.trubeacon.cloverandroidapi.CloverService.Builder()
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
