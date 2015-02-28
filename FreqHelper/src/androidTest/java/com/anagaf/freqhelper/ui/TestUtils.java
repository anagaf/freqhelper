package com.anagaf.freqhelper.ui;

import android.app.Instrumentation;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.anagaf.freqhelper.R;
import com.anagaf.freqhelper.ui.views.RangeView;

class TestUtils {

    public static EditText getChannelEdit(RangeView rangeView) {
        return (EditText) rangeView.findViewById(R.id.channel);
    }

    public static void pressEnter(Instrumentation instrumentation, final EditText editText)
    {
        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                editText.onEditorAction(EditorInfo.IME_ACTION_DONE);
            }
        });
        instrumentation.waitForIdleSync();
    }
}
