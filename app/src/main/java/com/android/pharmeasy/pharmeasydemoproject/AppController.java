package com.android.pharmeasy.pharmeasydemoproject;

import android.app.Application;

import com.android.pharmeasy.pharmeasydemoproject.util.NetworkHelper;

/**
 * Created by Admin on 12/10/2017.
 */

public class AppController extends Application {

    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(NetworkHelper.ConnectivityReceiverListener listener) {
        NetworkHelper.connectivityReceiverListener = listener;
    }
}
