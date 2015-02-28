package com.anagaf.freqhelper.ui;

import android.widget.EditText;

import com.anagaf.freqhelper.R;
import com.anagaf.freqhelper.ui.views.RangeView;

class TestUtils {

    public static EditText getChannelEdit(RangeView rangeView) {
        return (EditText) rangeView.findViewById(R.id.channel);
    }
}
