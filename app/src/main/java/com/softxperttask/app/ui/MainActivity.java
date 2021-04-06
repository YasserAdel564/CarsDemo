package com.softxperttask.app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.softxperttask.app.R;
import com.softxperttask.app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}