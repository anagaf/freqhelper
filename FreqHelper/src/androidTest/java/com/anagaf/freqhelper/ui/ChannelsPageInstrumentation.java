package com.anagaf.freqhelper.ui;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.anagaf.freqhelper.R;
import com.anagaf.freqhelper.ui.views.RangeView;
import com.anagaf.freqhelper.ui.views.ValueComponentEdit;

import java.util.ArrayList;
import java.util.List;

public class ChannelsPageInstrumentation {
    private static final int LPD69_INDEX = 0;
    private static final int LPD8_INDEX = 1;
    private static final int FRS_INDEX = 3;
    private static final int PMR_INDEX = 2;

    private final ActivityInstrumentationTestCase2 mTest;

    private final ValueComponentEdit mMhzEdit;
    private final ValueComponentEdit mKhzEdit;
    private final ValueComponentEdit mHzEdit;

    private List<RangeView> mRangeViews;

    public ChannelsPageInstrumentation(ActivityInstrumentationTestCase2 test) {
        mTest = test;
        mMhzEdit = (ValueComponentEdit) mTest.getActivity().findViewById(R.id.frequency_mhz_edit);
        mKhzEdit = (ValueComponentEdit) mTest.getActivity().findViewById(R.id.frequency_khz_edit);
        mHzEdit = (ValueComponentEdit) mTest.getActivity().findViewById(R.id.frequency_hz_edit);

        mRangeViews = new ArrayList<>();
        final ViewGroup rangesLayout = (ViewGroup) mTest.getActivity().findViewById(R.id.channels_ranges_layout);
        for (int i = 0; i < rangesLayout.getChildCount(); i++) {
            mRangeViews.add((RangeView) rangesLayout.getChildAt(i));
        }
    }

    public void setFrequency(String mhz, String khz, String hz) {
        TestUtils.typeEditText(mTest.getInstrumentation(), mMhzEdit, mhz);
        TestUtils.typeEditText(mTest.getInstrumentation(), mKhzEdit, khz);
        TestUtils.typeEditText(mTest.getInstrumentation(), mHzEdit, hz);
    }

    public void setChannel(int rangeIndex, String channel) {
        final RangeView rangeView = mRangeViews.get(rangeIndex);
        final EditText channelEdit = TestUtils.getChannelEdit(rangeView);
        TestUtils.typeEditText(mTest.getInstrumentation(), channelEdit, channel);
    }

    public void checkFrequency(String mhz, String khz, String hz) {
        mTest.assertEquals(mhz, mMhzEdit.getText().toString());
        mTest.assertEquals(khz, mKhzEdit.getText().toString());
        mTest.assertEquals(hz, mHzEdit.getText().toString());
    }

    public void setLpd69Channel(String channel) {
        final RangeView rangeView = mRangeViews.get(LPD69_INDEX);
        final EditText channelEdit = TestUtils.getChannelEdit(rangeView);
        TestUtils.typeEditText(mTest.getInstrumentation(), channelEdit, channel);
    }

    public void pressLpd69NextButton() {
        final View button = mRangeViews.get(LPD69_INDEX).findViewById(R.id.next_channel_button);
        TouchUtils.tapView(mTest, button);
    }

    public void setFrequencyKHzValue(String value) {
        TestUtils.typeEditText(mTest.getInstrumentation(), mKhzEdit, value);
    }
}
