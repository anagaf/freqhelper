package com.anagaf.freqhelper;

import android.app.Activity;
import android.test.ActivityTestCase;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.anagaf.freqhelper.ui.views.RangeView;
import com.anagaf.freqhelper.ui.views.ValueComponentEdit;

public class ChannelsPageTest extends ActivityTestCase {
    private ValueComponentEdit mMhzEdit;
    private ValueComponentEdit mKhzEdit;
    private ValueComponentEdit mHzEdit;

    private RangeView mLpd69View;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        Activity activity = getActivity();

        mMhzEdit = (ValueComponentEdit) getActivity().findViewById(R.id.frequency_mhz_edit);
        mKhzEdit = (ValueComponentEdit) getActivity().findViewById(R.id.frequency_khz_edit);
        mHzEdit = (ValueComponentEdit) getActivity().findViewById(R.id.frequency_hz_edit);

        ViewGroup rangesLayout = (ViewGroup) getActivity().findViewById(R.id.ranges_layout);
        mLpd69View = (RangeView) rangesLayout.getChildAt(0);
    }

    public void testSetFrequency() throws Exception {
        mMhzEdit.setValueComponent(433);
        mKhzEdit.setValueComponent(75);
        mHzEdit.setValueComponent(0);

        EditText channelEdit = (EditText) mLpd69View.findViewById(R.id.channel);
        assertEquals("1", channelEdit.getText().toString());
    }
}