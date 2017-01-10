package com.example.ekta.databinding.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ekta.databinding.R;
import com.example.ekta.databinding.databinding.ActivityMainBinding;
import com.example.ekta.databinding.viewmodels.MainViewModel;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout
                .activity_main);
        activityMainBinding.setMainViewModel(new MainViewModel());
        activityMainBinding.setPresenter(new MainPresenter(this));
    }

    @Override
    public void showProgress(MainViewModel mainViewModel) {
        mainViewModel.setLoading(true);
    }

    @Override
    public void hideProgress(MainViewModel mainViewModel) {
        mainViewModel.setLoading(false);

    }

}
