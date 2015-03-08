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

public class ChannelsPageInstrumentation {
    public static final int LPD69_INDEX = 0;
    public static final int LPD8_INDEX = 1;
    public static final int FRS_INDEX = 3;
    public static final int PMR_INDEX = 2;

    private final ActivityInstrumentationTestCase2 mTest;

    private final ValueComponentEdit mMHzEdit;
    private final ValueComponentEdit mKHzEdit;
    private final ValueComponentEdit mHzEdit;

    private List<RangeView> mRangeViews;

    public ChannelsPageInstrumentation(ActivityInstrumentationTestCase2 test) {
        mTest = test;
        mMHzEdit = (ValueComponentEdit) mTest.getActivity().findViewById(R.id.frequency_mhz_edit);
        mKHzEdit = (ValueComponentEdit) mTest.getActivity().findViewById(R.id.frequency_khz_edit);
        mHzEdit = (ValueComponentEdit) mTest.getActivity().findViewById(R.id.frequency_hz_edit);

        mRangeViews = new ArrayList<>();
        final ViewGroup rangesLayout = (ViewGroup) mTest.getActivity().findViewById(R.id.channels_ranges_layout);
        for (int i = 0; i < rangesLayout.getChildCount(); i++) {
            mRangeViews.add((RangeView) rangesLayout.getChildAt(i));
        }
    }

    public void setFrequency(String mhz, String khz, String hz) {
        TestUtils.typeEditText(mTest.getInstrumentation(), mMHzEdit, mhz);
        TestUtils.typeEditText(mTest.getInstrumentation(), mKHzEdit, khz);
        TestUtils.typeEditText(mTest.getInstrumentation(), mHzEdit, hz);
    }

    public void setChannel(int rangeIndex, String channel) {
        final RangeView rangeView = mRangeViews.get(rangeIndex);
        final EditText channelEdit = TestUtils.getChannelEdit(rangeView);
        TestUtils.typeEditText(mTest.getInstrumentation(), channelEdit, channel);
    }

    public void checkFrequency(String mhz, String khz, String hz) {
        Assert.assertEquals(mhz, mMHzEdit.getText().toString());
        Assert.assertEquals(khz, mKHzEdit.getText().toString());
        Assert.assertEquals(hz, mHzEdit.getText().toString());
    }

    public void checkChannels(String[] expectedChannels) {
        for (int i = 0; i < expectedChannels.length; i++) {
            final EditText edit = TestUtils.getChannelEdit(mRangeViews.get(i));
            Assert.assertEquals(expectedChannels[i], edit.getText().toString());
        }
    }
    public void setLpd69Channel(String channel) {
        final RangeView rangeView = mRangeViews.get(LPD69_INDEX);
        final EditText channelEdit = TestUtils.getChannelEdit(rangeView);
        TestUtils.typeEditText(mTest.getInstrumentation(), channelEdit, channel);
    }

    public void pressNextChannelButton(int rangeIndex) {
        final View button = mRangeViews.get(rangeIndex).findViewById(R.id.next_channel_button);
        TouchUtils.tapView(mTest, button);
    }

    public void pressPrevChannelButton(int rangeIndex) {
        final View button = mRangeViews.get(rangeIndex).findViewById(R.id.prev_channel_button);
        TouchUtils.tapView(mTest, button);
    }

    public void setFrequencyKHzValue(String value) {
        TestUtils.typeEditText(mTest.getInstrumentation(), mKHzEdit, value);
    }

    public EditText getMHzEdit() {
        return mMHzEdit;
    }

    public EditText getKHzEdit() {
        return mKHzEdit;
    }

    public EditText getHzEdit() {
        return mHzEdit;
    }
}
