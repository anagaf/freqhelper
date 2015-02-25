package com.anagaf.freqhelper.ui.views;

import android.content.Context;
import android.text.InputFilter;
import android.util.AttributeSet;

public class ValueEdit extends BaseEdit {

    public ValueEdit(Context context) {
        super(context);
        init();
    }

    public ValueEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ValueEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(3)
        });
    }

    @Override
    public void setValue(int value) {
        setText(String.format("%03d", value));
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
