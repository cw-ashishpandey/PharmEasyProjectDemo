package com.android.pharmeasy.pharmeasydemoproject.data;

import com.android.pharmeasy.pharmeasydemoproject.data.remote.RemoteDataSource;
import com.android.pharmeasy.pharmeasydemoproject.util.NetworkHelper;
import com.android.pharmeasy.pharmeasydemoproject.util.threading.MainUiThread;
import com.android.pharmeasy.pharmeasydemoproject.util.threading.ThreadExecutor;

/**
 * Created by Admin on 12/10/2017.
 */

public class Injector {
    public static DataRepository provideDataRepository(MainUiThread mainUiThread,
                                                       ThreadExecutor threadExecutor) {
        return DataRepository.getInstance(
                RemoteDataSource.getInstance(mainUiThread, threadExecutor), null);
    }
}
