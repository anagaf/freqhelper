package com.anagaf.freqhelper.ui;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.v4.view.ViewPager;
import android.test.InstrumentationTestCase;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.anagaf.freqhelper.R;
import com.anagaf.freqhelper.ui.views.RangeView;

import junit.framework.TestCase;

import java.util.List;

class TestUtils {

    public static void setPage(InstrumentationTestCase test, Activity activity, final int pageIndex) {
        final ViewPager pager = (ViewPager) activity.findViewById(R.id.pager);
        try {
            test.runTestOnUiThread(new Runnable() {
                @Override
                public void run() {
                    pager.setCurrentItem(pageIndex);
                }
            });
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable.getMessage());
        }
        test.getInstrumentation().waitForIdleSync();
    }

    public static EditText getChannelEdit(RangeView rangeView) {
        return (EditText) rangeView.findViewById(R.id.channel);
    }

    public static void typeEditText(Instrumentation instrumentation, final EditText editText, String text) {
        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                editText.requestFocus();
            }
        });
        instrumentation.waitForIdleSync();
        instrumentation.sendStringSync(text);
        instrumentation.waitForIdleSync();

        pressEnter(instrumentation, editText);
    }

    public static void checkChannels(List<RangeView> rangeViews, String[] expectedChannels) {
        for (int i = 0; i < expectedChannels.length; i++) {
            final EditText edit = TestUtils.getChannelEdit(rangeViews.get(i));
            TestCase.assertEquals(expectedChannels[i], edit.getText().toString());
        }
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
