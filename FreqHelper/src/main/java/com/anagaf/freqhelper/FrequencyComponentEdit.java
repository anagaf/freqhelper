package com.anagaf.freqhelper;

import android.content.Context;
import android.text.InputFilter;
import android.util.AttributeSet;

public class FrequencyComponentEdit extends BaseEdit {

    public FrequencyComponentEdit(Context context) {
        super(context);
        init();
    }

    public FrequencyComponentEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FrequencyComponentEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(3)
        });
    }


    public void setValue(int value) {
        setText(frequencyComponentIntegerToString(value));
    }

    private static String frequencyComponentIntegerToString(Integer value) {
        return String.format("%03d", value);
    }
}
