package com.softxperttask.app.ui.home;

import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.softxperttask.app.data.model.CarResponse;
import com.softxperttask.app.repo.CarsRepo;
import com.softxperttask.app.utils.Resource;

public class HomeViewModel extends ViewModel {

    Integer pageNum = 1;

    private CarsRepo repository;

    public HomeViewModel() {
        repository = CarsRepo.getInstance();
    }

    private MutableLiveData<Integer> pageTrigger = new MutableLiveData<>();
    private LiveData<Resource<CarResponse>> carsLiveData =
            Transformations.switchMap(pageTrigger, new Function<Integer,
                    LiveData<Resource<CarResponse>>>() {
                @Override
                public LiveData<Resource<CarResponse>> apply(Integer input) {
                    return repository.getCarsLiveData(input);
                }
            });

    public void getCars() {
        pageTrigger.setValue(pageNum);
    }

    public LiveData<Resource<CarResponse>> getCarsLiveData() {
        return carsLiveData;
    }
}