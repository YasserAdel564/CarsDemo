package com.softxperttask.app.data.api;

import com.softxperttask.app.data.model.CarResponse;
import com.softxperttask.app.utils.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RetrofitApiService {

    @GET(Constants.GetCars)
    Call<CarResponse> getCarsData(@Query("page") Integer pageNum);

}
