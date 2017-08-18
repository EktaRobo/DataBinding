/*
 *
 *   Copyright © 2014, ICICI Prudential Asset Management Company Ltd.
 *
 *   Written under contract by Robosoft Technologies Pvt. Ltd.
 *
 */

package com.example.ekta.databinding.customviews;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.text.Editable;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.ekta.databinding.AmountToWordsConverter;
import com.example.ekta.databinding.R;
import com.example.ekta.databinding.databinding.AmountToWordsForDatabindingLayoutBinding;

/**
 * Created by ekta on 7/8/17.
 */

@InverseBindingMethods(value = {
        @InverseBindingMethod(type = AmountToWordsForDataBinding.class, attribute = "text",
                method = "getText", event = "textAttrChanged")
})
public class AmountToWordsForDataBinding extends FrameLayout {

    private TextView mInWordsTextView;
    private TextView mAmountRupeeSymbol;
    private EditText mInNumberEditText;
    private boolean mIsNonAmountField; //To handle changes from top up screen
    private AmountToWordsForDatabindingLayoutBinding mBinding;


    public AmountToWordsForDataBinding(Context context) {
        super(context);
        initView(context);
    }

    public AmountToWordsForDataBinding(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public AmountToWordsForDataBinding(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * To Convert Amount to string in a thread using TextWatcher.
     *
     * @param amountToWordsForDataBinding
     * @param textAttrChanged
     */
    @BindingAdapter(value = "textAttrChanged", requireAll = false)
    public static void convertAmountToString(final AmountToWordsForDataBinding
                                                     amountToWordsForDataBinding,
                                             final InverseBindingListener textAttrChanged) {

        TextWatcher newValue = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (textAttrChanged != null) {
                    textAttrChanged.onChange();
                }
            }
        };
        amountToWordsForDataBinding.addTextChangedListener(newValue);
    }

    private void initView(Context context) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout
                .amount_to_words_for_databinding_layout, this, true);
        mInNumberEditText = mBinding.inNumber;
        mInWordsTextView = mBinding.inWords;
        mAmountRupeeSymbol = mBinding.amountRupeeSymbol;
    }

    private void convertAmountToWords(CharSequence s) {
        if (s != null && s.toString().matches("^0")) {
            mBinding.inNumber.setText("");
        }
        if (!TextUtils.isEmpty(mBinding.inNumber.getText()) && !mIsNonAmountField) {
            mBinding.inWords.setVisibility(View.VISIBLE);
        } else {
            mBinding.inWords.setVisibility(View.GONE);
            return;
        }
        AmountToWordsConverter convertor = new AmountToWordsConverter() {
            @Override
            protected void onPostExecute(String amountInWords) {
                super.onPostExecute(amountInWords);
                if (TextUtils.isEmpty(amountInWords)) {
                    amountInWords = getResources().getString(R.string.zero);
                }
                amountInWords = getResources().getString(R.string
                        .inr) + " " +
                        amountInWords;
                mBinding.inWords.setText(amountInWords);
            }
        };
        long i = 0;
        try {
            if (s != null) {
                i = Long.parseLong(s.toString());
            }

        } catch (NumberFormatException e) {

        }
        convertor.execute(i);
    }


    /**
     * Set hint for edit Text for amount field in numbers
     *
     * @param hint: Hint text
     */
    public void setHint(Spanned hint) {
        mInNumberEditText.setHint(hint);
    }

    /**
     * Set hint for edit Text for amount field in numbers
     *
     * @param resId: Resource ID of the text
     */
    public void setHint(int resId) {
        mInNumberEditText.setHint(resId);
    }

    /**
     * Set hint for edit Text for amount field in numbers
     *
     * @param string: Hint Text
     */
    public void setHint(String string) {
        mInNumberEditText.setHint(string);
    }

    /**
     * Get Text for edit Text for amount field in numbers
     *
     * @return : String - regular getText().toString()
     */
    public String getText() {
        return mBinding.inNumber.getText().toString();
    }

    /**
     * Set text for edit Text for amount field in numbers
     *
     * @param amount : String to be displayed (Amount here)
     */
    public void setText(String amount) {
//        mBinding.inNumber.setText(amount);
        convertAmountToWords(amount);
    }

    /**
     * Set text for edit Text for amount field in numbers
     *
     * @param amount : String to be displayed (Amount here)
     */
    public void setText(Editable amount) {
        mBinding.inNumber.setText(amount);
    }

    /**
     * Set selection for edit Text for amount field in numbers
     *
     * @param index : index
     */
    public void setSelection(int index) {
        mInNumberEditText.setSelection(index);
    }

    public void setSelection(int start, int stop) {
        mInNumberEditText.setSelection(start, stop);
    }

    /**
     * Set onEditorActionListener for edit Text for amount field in numbers
     *
     * @param onEditorActionListener : listener
     */
    public void setOnEditorActionListener(TextView.OnEditorActionListener onEditorActionListener) {
        mInNumberEditText.setOnEditorActionListener(onEditorActionListener);
    }

    /**
     * Set gravity for edit Text for amount field in numbers
     *
     * @param gravity
     */
    public void setGravity(int gravity) {
        mInNumberEditText.setGravity(gravity);
    }

    /**
     * method used only by top up screen to handle the special cases by the inherited class
     *
     * @param textWatcher
     */
    protected void addTextChangedListener(TextWatcher textWatcher) {
        mInNumberEditText.addTextChangedListener(textWatcher);
    }


    /**
     * method used only by top up screen to handle the special cases by the inherited class
     *
     * @param isNonAmountField
     */
    protected void isNonAmountField(boolean isNonAmountField) {
        if (isNonAmountField) {
            if (mAmountRupeeSymbol != null) {
                mAmountRupeeSymbol.setVisibility(GONE);
            }
            mIsNonAmountField = true;
            mInNumberEditText.setEnabled(false);
        } else {
            if (mAmountRupeeSymbol != null) {
                mAmountRupeeSymbol.setVisibility(VISIBLE);
            }
            mIsNonAmountField = false;
            mInNumberEditText.setEnabled(true);
        }
    }
}
