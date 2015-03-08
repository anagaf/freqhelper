package com.anagaf.freqhelper.ui;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.anagaf.freqhelper.R;
import com.anagaf.freqhelper.ui.views.RangeView;
import com.anagaf.freqhelper.ui.views.ValueComponentEdit;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

class CtcssPageInstrumentation {
    public static final int TONES38_INDEX = 0;
    public static final int TONES39_INDEX = 1;
    public static final int TONES64_INDEX = 2;

    private final ActivityInstrumentationTestCase2 mTest;

    private final ValueComponentEdit mHzEdit;
    private final ValueComponentEdit mDHzEdit;

    private final List<RangeView> mRangeViews;

    public CtcssPageInstrumentation(ActivityInstrumentationTestCase2 test) {
        mTest = test;

        mHzEdit = (ValueComponentEdit) mTest.getActivity().findViewById(R.id.ctcss_hz_edit);
        mDHzEdit = (ValueComponentEdit) mTest.getActivity().findViewById(R.id.ctcss_dhz_edit);

        mRangeViews = new ArrayList<>();
        final ViewGroup rangesLayout = (ViewGroup) mTest.getActivity().findViewById(R.id.ctcss_ranges_layout);
        for (int i = 0; i < rangesLayout.getChildCount(); i++) {
            mRangeViews.add((RangeView) rangesLayout.getChildAt(i));
        }
    }

    public void setFrequency(String hz, String dHz) {
        TestUtils.typeEditText(mTest.getInstrumentation(), mHzEdit, hz);
        TestUtils.typeEditText(mTest.getInstrumentation(), mDHzEdit, dHz);
    }

    public void setChannel(int rangeIndex, String channel) {
        final RangeView rangeView = mRangeViews.get(rangeIndex);
        final EditText channelEdit = TestUtils.getChannelEdit(rangeView);
        TestUtils.typeEditText(mTest.getInstrumentation(), channelEdit, channel);
    }

    public void checkFrequency(String hz, String dHz) {
        Assert.assertEquals(hz, mHzEdit.getText().toString());
        Assert.assertEquals(dHz, mDHzEdit.getText().toString());
    }

    public void setFrequencyHzValue(String value) {
        TestUtils.typeEditText(mTest.getInstrumentation(), mHzEdit, value);
    }

    public void checkChannels(String[] expectedChannels) {
        TestUtils.checkChannels(mRangeViews, expectedChannels);
    }

    // TODO: code duplication

    public void pressNextChannelButton(int rangeIndex) {
        final View button = mRangeViews.get(rangeIndex).findViewById(R.id.next_channel_button);
        TouchUtils.tapView(mTest, button);
    }

    public void pressPrevChannelButton(int rangeIndex) {
        final View button = mRangeViews.get(rangeIndex).findViewById(R.id.prev_channel_button);
        TouchUtils.tapView(mTest, button);
    }
}
