package com.example.ekta.databinding.view_models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingConversion;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by ekta on 9/1/17.
 */

public class MainViewModel extends BaseObservable {
    private String input;
    private boolean isLoading;

    @Bindable
    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
        notifyPropertyChanged(BR.loading);
    }

    @Bindable
    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
        notifyPropertyChanged(BR.input);
    }

    @BindingConversion
    public static int convertBooleanToVisibility(boolean visible) {
        return visible ? View.VISIBLE : View.GONE;
    }
}
