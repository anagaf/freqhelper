package com.anagaf.freqhelper.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.util.AttributeSet;

public class FrequencyComponentEdit extends BaseEdit {
    private static final int DEFAULT_MAX_LENGTH = 1;
    private int mMaxLength = DEFAULT_MAX_LENGTH;

    public FrequencyComponentEdit(Context context) {
        super(context);
        init(null);
    }

    public FrequencyComponentEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public FrequencyComponentEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedAttrs = getContext().getTheme().obtainStyledAttributes(attrs, new int[]{android.R.attr.maxLength}, 0, 0);
            try {
                mMaxLength = typedAttrs.getInt(0, DEFAULT_MAX_LENGTH);
            } finally {
                typedAttrs.recycle();
            }
        }
        setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(mMaxLength)
        });
    }

    @Override
    public void setValue(int value) {
        setText(String.format("%0" + mMaxLength + "d", value));
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
