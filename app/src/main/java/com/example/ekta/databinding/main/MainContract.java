package com.example.ekta.databinding.main;

import com.example.ekta.databinding.viewmodels.MainViewModel;

/**
 * Created by ekta on 10/1/17.
 */

public class MainContract {

    public interface Presenter {
        void onButtonClick(MainViewModel mainViewModel);
    }

    public interface View {
        void showProgress(MainViewModel mainViewModel);

        void hideProgress(MainViewModel mainViewModel);

    }
}
