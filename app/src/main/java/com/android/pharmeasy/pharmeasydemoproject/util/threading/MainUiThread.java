package com.android.pharmeasy.pharmeasydemoproject.util.threading;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by user on 12/10/2017.
 */

public class MainUiThread {
    private static MainUiThread mainUiThread;

    private Handler handler;

    private MainUiThread() {
        handler = new Handler(Looper.getMainLooper());
    }

    public static synchronized MainUiThread getInstance() {
        if (mainUiThread == null) {
            mainUiThread = new MainUiThread();
        }
        return mainUiThread;
    }

    public void post(Runnable runnable) {
        handler.post(runnable);
    }
}
