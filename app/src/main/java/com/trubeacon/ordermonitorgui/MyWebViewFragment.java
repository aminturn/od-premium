package com.trubeacon.ordermonitorgui;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Config;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tru.clover.api.auth.AccessToken;
import com.tru.clover.api.auth.service.GetAccessToken;
import com.tru.clover.api.client.error.*;

/**
 * Created by Andrew on 5/20/15.
 */

public class MyWebViewFragment extends Fragment {

//    MMGZVXP1ARPEC
//
//    merchant id: V258CNZXRRXVE
//    token: afd0b9e2-3d4a-f469-3f1a-233632e7a9ce

    private static String appId = "MMGZVXP1ARPEC";
    private static String redirectUri = "https://order-display.trubeacon.com";
    private static String APP_SECRET = "92922db0-c52d-fcca-6035-c8797d6e2626";
    private OrderMonitorData orderMonitorData = OrderMonitorData.getOrderMonitorData();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        WebView webView = (WebView) inflater.inflate(R.layout.web_view,container,false);

            // The URL that will fetch the Access Token, Merchant ID, and Employee ID
            String url = "https://clover.com/oauth/authorize" +
                    "?client_id=" + appId +
                    "&redirect_uri=" + redirectUri;


            webView.getSettings().setJavaScriptEnabled(true);

            webView.setWebViewClient(new WebViewClient() {
                public void onPageStarted(WebView view, String url, Bitmap favicon) {

                    // Parses the fetched URL
                    String authCodeFragment = "&code=";
                    String clientIdFragment = "&client_id=";
                    String merchantIdFragment = "?merchant_id=";
                    String employeeIdFragment = "&employee_id=";

                    int authCodeStart = url.indexOf(authCodeFragment);
                    int clientIdStart = url.indexOf(clientIdFragment);
                    int merchantIdStart = url.indexOf(merchantIdFragment);
                    int employeeIdStart = url.indexOf(employeeIdFragment);

                    Log.v("url",url);

                        //CloverService.getService().get
                    //CloverService.getService().getAccessToken()


                    //this will evaluate true if "&code=" appears in the url
                    if (authCodeStart > -1) {

                        String authCode = url.substring(authCodeStart + authCodeFragment.length(), url.length());

                        Log.v("access token",authCode);

                        String clientId = url.substring(clientIdStart+clientIdFragment.length(),authCodeStart);

                        Log.v("client Id",clientId);

                        String merchantId = url.substring(merchantIdStart + merchantIdFragment.length(), employeeIdStart);

                        orderMonitorData.setmId(merchantId);



                        CloverService.getService().getAccessToken(clientId, APP_SECRET, authCode, new GetAccessToken.GetAccessTokenCallback() {
                            @Override
                            public void onGetAccessToken(AccessToken accessToken) {

                                Log.v("the token is", accessToken.getToken());

                                orderMonitorData.setToken(accessToken.getToken());

                                getActivity().onBackPressed();

                            }

                            @Override
                            public void onFailGetAccessToken(com.tru.clover.api.client.error.Error error) {

                                Log.v("failed to fetch token", error.getMessage());

                            }
                        });

                    }

                }
            });

            // Loads the WebView
            webView.loadUrl(url);

        return webView;
    }
}
