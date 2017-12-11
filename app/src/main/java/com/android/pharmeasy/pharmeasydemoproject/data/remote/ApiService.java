package com.android.pharmeasy.pharmeasydemoproject.data.remote;


import com.android.pharmeasy.pharmeasydemoproject.data.model.PharmEasyDataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Admin on 12/11/2017.
 */

public interface ApiService {
    @GET("users/")
    Call<PharmEasyDataResponse> getPharmEasyList(@Query("page") int id);

}
