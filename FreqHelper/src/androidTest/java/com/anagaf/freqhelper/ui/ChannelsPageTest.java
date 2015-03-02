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

public class ChannelsPageTest extends ActivityInstrumentationTestCase2<MainActivity> {
    public static final String INVALID_CHANNEL = "--";

    private static final int LPD69_INDEX = 0;
    private static final int LPD8_INDEX = 1;
    private static final int FRS_INDEX = 3;
    private static final int PMR_INDEX = 2;

    private ValueComponentEdit mMhzEdit;
    private ValueComponentEdit mKhzEdit;
    private ValueComponentEdit mHzEdit;

    private List<RangeView> mRangeViews;

    public ChannelsPageTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        getInstrumentation().setInTouchMode(false);

        mMhzEdit = (ValueComponentEdit) getActivity().findViewById(R.id.frequency_mhz_edit);
        mKhzEdit = (ValueComponentEdit) getActivity().findViewById(R.id.frequency_khz_edit);
        mHzEdit = (ValueComponentEdit) getActivity().findViewById(R.id.frequency_hz_edit);

        mRangeViews = new ArrayList<>();
        final ViewGroup rangesLayout = (ViewGroup) getActivity().findViewById(R.id.channels_ranges_layout);
        for (int i = 0; i < rangesLayout.getChildCount(); i++) {
            mRangeViews.add((RangeView) rangesLayout.getChildAt(i));
        }
    }

    public void testSetFrequency() throws Throwable {
        setFrequency("4 3 3", "7 5", "0"); // TODO: test empty input
        checkFrequency("433", "075", "000");
        checkChannels(new String[]{"1", "1", INVALID_CHANNEL, INVALID_CHANNEL});

        TestUtils.typeEditText(getInstrumentation(), mMhzEdit, "4 3 4");
        checkFrequency("434", "075", "000");
        checkChannels(new String[] {"41", INVALID_CHANNEL, INVALID_CHANNEL, INVALID_CHANNEL});

        TestUtils.typeEditText(getInstrumentation(), mKhzEdit, "1 0 0");
        checkFrequency("434", "100", "000");
        checkChannels(new String[]{"42", INVALID_CHANNEL, INVALID_CHANNEL, INVALID_CHANNEL});

        TestUtils.typeEditText(getInstrumentation(), mHzEdit, "5 0");
        checkFrequency("434", "100", "050");
        checkChannels(new String[]{INVALID_CHANNEL, INVALID_CHANNEL, INVALID_CHANNEL, INVALID_CHANNEL});
    }


    public void testSetChannel() throws Throwable {
        setFrequency("433", "75", "0");
        checkFrequency("433", "075", "000");
        checkChannels(new String[]{"1", "1", INVALID_CHANNEL, INVALID_CHANNEL});

        setChannel(LPD69_INDEX, "32");
        checkFrequency("433", "850", "000");

        setChannel(LPD8_INDEX, "8");
        checkFrequency("433", "800", "000");

        setChannel(PMR_INDEX, "5");
        checkFrequency("446", "056", "250");

        setChannel(FRS_INDEX, "12");
        checkFrequency("467", "662", "500");
    }


    public void testNextChannel() throws Throwable {
        setFrequency("4 3 3", "7 5", "0");
        checkFrequency("433", "075", "000");
        checkChannels(new String[] {"1", "1", INVALID_CHANNEL, INVALID_CHANNEL});

        TouchUtils.tapView(this, getNextChannelButton(LPD69_INDEX));
        checkFrequency("433", "100", "000");
        checkChannels(new String[] {"2", "2", INVALID_CHANNEL, INVALID_CHANNEL});

        TouchUtils.tapView(this, getNextChannelButton(LPD8_INDEX));
        checkFrequency("433", "200", "000");
        checkChannels(new String[]{"6", "3", INVALID_CHANNEL, INVALID_CHANNEL});

        TouchUtils.tapView(this, getNextChannelButton(PMR_INDEX));
        checkFrequency("446", "006", "250");
        checkChannels(new String[] {INVALID_CHANNEL, INVALID_CHANNEL, "1", INVALID_CHANNEL});

        TouchUtils.tapView(this, getNextChannelButton(FRS_INDEX));
        checkFrequency("462", "562", "500");
        checkChannels(new String[] {INVALID_CHANNEL, INVALID_CHANNEL, INVALID_CHANNEL, "1"});
    }

    public void testPrevChannel() throws Throwable {
        setFrequency("4 6 7", "7 1 2", "5 0 0");
        checkFrequency("467", "712", "500");
        checkChannels(new String[] {INVALID_CHANNEL, INVALID_CHANNEL, INVALID_CHANNEL, "14"});

        TouchUtils.tapView(this, getPrevChannelButton(FRS_INDEX));
        checkFrequency("467", "687", "500");
        checkChannels(new String[]{INVALID_CHANNEL, INVALID_CHANNEL, INVALID_CHANNEL, "13"});

        TouchUtils.tapView(this, getPrevChannelButton(PMR_INDEX));
        checkFrequency("446", "093", "750");
        checkChannels(new String[]{INVALID_CHANNEL, INVALID_CHANNEL, "8", INVALID_CHANNEL});

        TouchUtils.tapView(this, getPrevChannelButton(LPD8_INDEX));
        checkFrequency("433", "800", "000");
        checkChannels(new String[]{"30", "8", INVALID_CHANNEL, INVALID_CHANNEL});

        TouchUtils.tapView(this, getPrevChannelButton(LPD69_INDEX));
        checkFrequency("433", "775", "000");
        checkChannels(new String[]{"29", INVALID_CHANNEL, INVALID_CHANNEL, INVALID_CHANNEL});
    }

    private void setFrequency(String mhz, String khz, String hz) {
        TestUtils.typeEditText(getInstrumentation(), mMhzEdit, mhz);
        TestUtils.typeEditText(getInstrumentation(), mKhzEdit, khz);
        TestUtils.typeEditText(getInstrumentation(), mHzEdit, hz);
    }

    private void setChannel(int rangeIndex, String channel) {
        final RangeView rangeView = mRangeViews.get(rangeIndex);
        final EditText channelEdit = TestUtils.getChannelEdit(rangeView);
        TestUtils.typeEditText(getInstrumentation(), channelEdit, channel);
    }

    private void checkFrequency(String mhz, String khz, String hz) {
        assertEquals(mhz, mMhzEdit.getText().toString());
        assertEquals(khz, mKhzEdit.getText().toString());
        assertEquals(hz, mHzEdit.getText().toString());
    }

    private void checkChannels(String[] expectedChannels) {
        for (int i = 0; i < expectedChannels.length; i++) {
            final EditText edit = TestUtils.getChannelEdit(mRangeViews.get(i));
            assertEquals(expectedChannels[i], edit.getText().toString());
        }
    }

    private View getNextChannelButton(int rangeIndex) {
        return mRangeViews.get(rangeIndex).findViewById(R.id.next_channel_button);
    }

    private View getPrevChannelButton(int rangeIndex) {
        return mRangeViews.get(rangeIndex).findViewById(R.id.prev_channel_button);
    }
}
