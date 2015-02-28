package com.anagaf.freqhelper.ui.views;

import android.content.Context;
import android.util.AttributeSet;

public class ValueComponentEdit extends AbstractEdit {
    public ValueComponentEdit(Context context) {
        super(context);
    }

    public ValueComponentEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ValueComponentEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setValueComponent(int valueComponent) {
        setText(formatValue(Math.abs(valueComponent)));
    }

    public int getValueComponent() {
        if (getText().toString().isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(getText().toString());
        }
    }
}
