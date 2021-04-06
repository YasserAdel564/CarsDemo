package com.softxperttask.app.ui.home;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;

import com.softxperttask.app.R;
import com.softxperttask.app.data.model.CarModel;
import com.softxperttask.app.data.model.CarResponse;
import com.softxperttask.app.databinding.HomeFragmentBinding;
import com.softxperttask.app.databinding.SplashFragmentBinding;
import com.softxperttask.app.utils.Resource;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.softxperttask.app.utils.Resource.Status.ERROR;
import static com.softxperttask.app.utils.Resource.Status.LOADING;
import static com.softxperttask.app.utils.Resource.Status.SUCCESS;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private HomeViewModel mViewModel;
    private HomeFragmentBinding binding;
    private CarsAdapter adapter;
    private ArrayList<CarModel> carsList= new ArrayList<>();
    LinearLayoutManager layoutManager;
    boolean isScrolling = true;
    int currentItems, totalItems, scrollItems;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = HomeFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        mViewModel.getCarsLiveData().observe(getViewLifecycleOwner(), this::onResponse);
        binding.carsSwipe.setOnRefreshListener(this);
        getCarsData();
        setRecycleViewScrolling();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpRv();
    }

    private void setUpRv() {
        layoutManager = new LinearLayoutManager(requireActivity());
        binding.carsRv.setHasFixedSize(true);
        binding.carsRv.setLayoutManager(layoutManager);
    }

    private void setRecycleViewScrolling() {

        binding.carsRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (isScrolling && newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && (currentItems + scrollItems == totalItems)) {
                    mViewModel.pageNum++;
                    mViewModel.getCars();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void getCarsData() {
        mViewModel.getCars();
    }


    private void onResponse(Resource<CarResponse> resource) {
        if (resource != null) {
            switch (resource.status) {
                case SUCCESS:
                    if (resource.data != null) {
                        onSuccess(resource.data);
                    }
                    break;
                case ERROR:
                    onError(resource.message);
                    break;
                case LOADING:
                    onLoading();
                    break;
            }
        }
    }

    private void onLoading() {
        binding.progress.setVisibility(View.VISIBLE);
    }

    private void onSuccess(CarResponse data) {
        binding.progress.setVisibility(View.GONE);
        binding.carsRv.setVisibility(View.VISIBLE);
        binding.carsRv.setAdapter(adapter);
        carsList.addAll(data.getData());
        adapter = new CarsAdapter(requireActivity(), carsList);
        binding.carsRv.setAdapter(adapter);
    }

    private void onError(String message) {
        isScrolling = false;
        binding.progress.setVisibility(View.GONE);
        Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRefresh() {
        carsList.clear();
        isScrolling = true;
        mViewModel.pageNum = 1;
        getCarsData();
        binding.carsSwipe.setRefreshing(false);
    }
}