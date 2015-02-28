package com.anagaf.freqhelper.ui.views;

import android.content.Context;
import android.util.AttributeSet;

import com.anagaf.freqhelper.model.ranges.Range;

public class ValueEdit extends AbstractEdit {

    public ValueEdit(Context context) {
        super(context);
    }

    public ValueEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ValueEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setValue(long value) {
        if (value != Range.INVALID_VALUE) {
            setText(String.format("%0" + mMaxLength + "d", Math.abs(value)));
        } else {
            setInvalidValue();
        }
    }

    public long getValue() {
        if (isInvalidValue()) {
            return Range.INVALID_VALUE;
        } else {
            return Long.parseLong(getText().toString());
        }
    }

}
