package com.example.ekta.databinding.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.ekta.databinding.AmountToWordsConverter;
import com.example.ekta.databinding.R;
import com.example.ekta.databinding.databinding.ActivityMainBinding;
import com.example.ekta.databinding.viewmodels.MainViewModel;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout
                .activity_main);
        activityMainBinding.setMainViewModel(new MainViewModel());
        activityMainBinding.setPresenter(new MainPresenter(this));
        init();
    }

    private void init() {
        AppCompatEditText inNumber = (AppCompatEditText) findViewById(R.id.in_number);
        final AppCompatTextView inWords = (AppCompatTextView) findViewById(R.id.in_words);
        inNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                AmountToWordsConverter convertor = new AmountToWordsConverter() {
                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        inWords.setText(s);
                    }
                };
                long i = 0;
                try {
                    i = Long.parseLong(s.toString());

                } catch (NumberFormatException e) {

                }
                convertor.execute(i);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
