package com.android.pharmeasy.pharmeasydemoproject.data;

import com.android.pharmeasy.pharmeasydemoproject.data.model.Data;
import com.android.pharmeasy.pharmeasydemoproject.data.model.PharmEasyDataResponse;
import com.android.pharmeasy.pharmeasydemoproject.util.threading.MainUiThread;
import com.android.pharmeasy.pharmeasydemoproject.util.threading.ThreadExecutor;

/**
 * Created by Admin on 12/10/2017.
 */

public abstract class DataSource {
    protected MainUiThread mainUiThread;
    protected ThreadExecutor threadExecutor;

    public DataSource(MainUiThread mainUiThread, ThreadExecutor threadExecutor) {
        this.mainUiThread = mainUiThread;
        this.threadExecutor = threadExecutor;
    }

    public interface GetPharmEasyListCallback {
        void onSuccess(PharmEasyDataResponse listPharmEasyData);

        void onFailure(Throwable throwable);

        void onNetworkFailure();

    }

    public interface GetPharmEasyDetailCallback {
        void onSuccess(Data data);

        void onFailure(Throwable throwable);

        void onNetworkFailure();

    }

    public abstract void getPharmEasyList(int page, GetPharmEasyListCallback callback);

    //public abstract void getPharmEasyDetail(GetPharmEasyDetailCallback callback);

}
