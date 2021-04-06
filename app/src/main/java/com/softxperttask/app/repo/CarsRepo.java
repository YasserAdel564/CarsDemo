package com.softxperttask.app.repo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softxperttask.app.data.api.RetrofitApiService;
import com.softxperttask.app.data.model.CarResponse;
import com.softxperttask.app.utils.Resource;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarsRepo {

    private RetrofitApiService retrofitService;
    private static CarsRepo INSTANCE = null;

    private CarsRepo(RetrofitApiService service) {
        this.retrofitService = service;
    }
    public static void init(RetrofitApiService service) {
        INSTANCE = new CarsRepo(service);
    }

    public static CarsRepo getInstance() {
        if (INSTANCE == null) {
            throw new RuntimeException("Must call init first.");
        }
        return (INSTANCE);
    }

    public LiveData<Resource<CarResponse>> getCarsLiveData(Integer pageNum) {

        MutableLiveData<Resource<CarResponse>> carsLiveData = new MutableLiveData<>();

        carsLiveData.setValue(Resource.loading(null));

        Call<CarResponse> loginCall = retrofitService.getCarsData(pageNum);

        loginCall.enqueue(new Callback<CarResponse>() {
            @Override
            public void onResponse(@NonNull Call<CarResponse> call,
                                   @NonNull Response<CarResponse> response) {
                CarResponse carResponse;
                carResponse = response.body();
                if (carResponse != null) {
                    if (carResponse.getStatus() == 1 && carResponse.getData().size() > 0) {
                        carsLiveData.setValue(Resource.success(carResponse));
                    } else {
                        carsLiveData.setValue(Resource.error("No More Data To Load", null));
                    }
                } else {
                    carsLiveData.setValue(Resource.error("Connection Error", null));
                }
            }

            @Override
            public void onFailure(@NonNull Call<CarResponse> call,
                                  @NonNull Throwable t) {
                carsLiveData.setValue(Resource.error("Connection Error", null));
            }
        });

        return carsLiveData;
    }
}
