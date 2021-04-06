package com.softxperttask.app.ui.splash;

import androidx.lifecycle.ViewModelProvider;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softxperttask.app.R;
import com.softxperttask.app.databinding.SplashFragmentBinding;

public class SplashFragment extends Fragment {

    private SplashFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = SplashFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startSplash();
    }

    private void startSplash() {
        binding.splahIcon.animate()
                .translationY(binding.splahIcon.getHeight())
                .alpha(1.2f)
                .setDuration(2000)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        NavHostFragment.findNavController(SplashFragment.this).navigate(R.id.action_splash_fragment_to_home_fragment);
                    }
                });

    }

}