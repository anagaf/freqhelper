package com.anagaf.freqhelper.ui.views;

import android.content.Context;
import android.text.InputFilter;
import android.util.AttributeSet;

import com.anagaf.freqhelper.model.ranges.Range;

public class DcsCodeEdit extends BaseEdit {

    public DcsCodeEdit(Context context) {
        super(context);
        init();
    }

    public DcsCodeEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DcsCodeEdit(Context context, AttributeSet attrs, int defStyleAttr) {
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
        if (value == Range.INVALID_VALUE) {
            setText(INVALID_VALUE);
        } else {
            setText(String.format("%03d", value));
        }
    }

    @Override
    protected int getValue() {
        return Integer.parseInt(getText().toString());
    }
}
