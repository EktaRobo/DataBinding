package com.example.ekta.databinding.main;

import com.example.ekta.databinding.viewmodels.MainViewModel;

/**
 * Created by ekta on 10/1/17.
 */

public class MainPresenter implements MainContract.Presenter{

    private MainContract.View mView;

    public MainPresenter (MainContract.View view) {

        mView = view;
    }
    @Override
    public void onButtonClick(MainViewModel mainViewModel) {
        if (mainViewModel.isLoading()) {
            mView.hideProgress(mainViewModel);
        } else {
            mView.showProgress(mainViewModel);
        }

    }

}
