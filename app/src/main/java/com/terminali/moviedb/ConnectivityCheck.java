package com.terminali.moviedb;

import android.app.Application;

/**
 * Created by TERMINALi on 7/30/2016.
 */

public class ConnectivityCheck extends Application {
    private static ConnectivityCheck mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized ConnectivityCheck getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}