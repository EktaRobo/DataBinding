package com.example.ekta.databinding.customviews;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by ekta on 8/8/17.
 */

public class AmountToWords extends FrameLayout {
    public AmountToWords(@NonNull Context context) {
        super(context);
    }

    public AmountToWords(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AmountToWords(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
