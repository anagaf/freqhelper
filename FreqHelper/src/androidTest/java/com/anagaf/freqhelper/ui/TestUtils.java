package com.anagaf.freqhelper.ui;

import android.app.Instrumentation;
import android.test.InstrumentationTestCase;
import android.test.TouchUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.anagaf.freqhelper.R;
import com.anagaf.freqhelper.ui.views.RangeView;

class TestUtils {

    public static EditText getChannelEdit(RangeView rangeView) {
        return (EditText) rangeView.findViewById(R.id.channel);
    }

    public static void typeEditText(InstrumentationTestCase test, EditText editText, String text) {
        TouchUtils.tapView(test, editText);
        test.sendKeys(text);
        pressEnter(test.getInstrumentation(), editText);
    }

    private static void pressEnter(Instrumentation instrumentation, final EditText editText)
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
