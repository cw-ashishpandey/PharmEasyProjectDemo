package com.android.pharmeasy.pharmeasydemoproject.data.remote;

import com.android.pharmeasy.pharmeasydemoproject.data.DataSource;
import com.android.pharmeasy.pharmeasydemoproject.data.model.PharmEasyDataResponse;
import com.android.pharmeasy.pharmeasydemoproject.util.threading.MainUiThread;
import com.android.pharmeasy.pharmeasydemoproject.util.threading.ThreadExecutor;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin on 12/11/2017.
 */

public class RemoteDataSource extends DataSource {

    public static final String BASE_URL = "https://reqres.in/api/";
    public static final String API_KEY = "";
    private static Retrofit retrofit = null;


    private static RemoteDataSource remoteDataSource;


    private RemoteDataSource(MainUiThread mainUiThread,
                             ThreadExecutor threadExecutor) {
        super(mainUiThread, threadExecutor);

    }

    public static synchronized RemoteDataSource getInstance(MainUiThread mainUiThread,
                                                            ThreadExecutor threadExecutor
    ) {
        if (remoteDataSource == null) {

            remoteDataSource = new RemoteDataSource(mainUiThread, threadExecutor);
        }
        return remoteDataSource;
    }

    @Override
    public void getPharmEasyList(int page, final GetPharmEasyListCallback callback) {

        ApiService apiService =
                RemoteDataSource.getClient().create(ApiService.class);
        retrofit2.Call<PharmEasyDataResponse> call =
                apiService.getPharmEasyList(page);

        call.enqueue(
                new retrofit2.Callback<PharmEasyDataResponse>() {
                    @Override
                    public void onResponse(
                            retrofit2.Call<PharmEasyDataResponse> call,
                            retrofit2.Response<PharmEasyDataResponse> response) {
                        if (response.isSuccessful()) {
                            PharmEasyDataResponse pharmEasyListDataResponse = response.body();
                            callback.onSuccess(pharmEasyListDataResponse);
                        } else {
                            callback.onFailure(new Throwable());
                        }
                    }

                    @Override
                    public void onFailure(
                            retrofit2.Call<PharmEasyDataResponse> call,
                            Throwable t) {
                        callback.onFailure(t);
                    }
                });
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}