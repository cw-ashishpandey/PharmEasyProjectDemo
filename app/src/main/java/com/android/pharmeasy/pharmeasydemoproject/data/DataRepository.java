package com.android.pharmeasy.pharmeasydemoproject.data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.pharmeasy.pharmeasydemoproject.data.model.PharmEasyDataResponse;
import com.android.pharmeasy.pharmeasydemoproject.util.NetworkHelper;

/**
 * Created by Admin on 12/10/2017.
 */

public class DataRepository {
    private DataSource remoteDataSource;

    private static DataRepository dataRepository;

    private DataRepository(DataSource remoteDataSource, DataSource localDataSource
    ) {
        this.remoteDataSource = remoteDataSource;
    }

    public static synchronized DataRepository getInstance(DataSource remoteDataSource,
                                                          DataSource localDataSource
    ) {
        if (dataRepository == null) {
            dataRepository = new DataRepository(remoteDataSource, localDataSource);
        }
        return dataRepository;
    }

    public void getPharmEasyListData(Context context, int page, final DataSource.GetPharmEasyListCallback callback) {
        if (isNetworkAvailable(context)) {
            remoteDataSource.getPharmEasyList(page, new DataSource.GetPharmEasyListCallback() {
                @Override
                public void onSuccess(PharmEasyDataResponse listPharmEasyData) {
                    callback.onSuccess(listPharmEasyData);
                    //((LocalDataSource) localDataSource).(listPharmEasyData);
                }

                @Override
                public void onFailure(Throwable throwable) {
                    callback.onFailure(throwable);
                }

                @Override
                public void onNetworkFailure() {
                    callback.onNetworkFailure();
                }
            });
        }
    }

    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
    }

    /*public void getPharmEasyDetailData(Context context, final DataSource.GetPharmEasyDetailCallback callback) {
        remoteDataSource.getPharmEasyDetail(new DataSource.GetPharmEasyDetailCallback() {
            @Override
            public void onSuccess(Data data) {
                callback.onSuccess(data);
                //((LocalDataSource) localDataSource).(listPharmEasyData);
            }

            @Override
            public void onFailure(Throwable throwable) {
                callback.onFailure(throwable);
            }

            @Override
            public void onNetworkFailure() {
                callback.onNetworkFailure();
            }
        });
    }*/


}
