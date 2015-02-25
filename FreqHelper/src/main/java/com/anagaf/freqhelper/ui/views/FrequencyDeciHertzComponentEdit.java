package com.anagaf.freqhelper.ui.views;

import android.content.Context;
import android.text.InputFilter;
import android.util.AttributeSet;

public class FrequencyDeciHertzComponentEdit extends BaseEdit {

    public FrequencyDeciHertzComponentEdit(Context context) {
        super(context);
        init();
    }

    public FrequencyDeciHertzComponentEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FrequencyDeciHertzComponentEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(1)
        });
    }

    @Override
    public void setValue(int value) {
        setText(String.valueOf(value));
    }

    @Override
    public int getValue() {
        if (getText().toString().isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(getText().toString());
        }
    }
}
