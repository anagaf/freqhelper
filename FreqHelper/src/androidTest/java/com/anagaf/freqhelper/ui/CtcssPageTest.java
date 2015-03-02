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

public class CtcssPageTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private static final String INVALID_CHANNEL = "--";

    private static final int TONES38_INDEX = 0;
    private static final int TONES39_INDEX = 1;
    private static final int TONES64_INDEX = 2;

    private ValueComponentEdit mHzEdit;
    private ValueComponentEdit mDHzEdit;

    private List<RangeView> mRangeViews;

    public CtcssPageTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        getInstrumentation().setInTouchMode(false);

        mHzEdit = (ValueComponentEdit) getActivity().findViewById(R.id.ctcss_hz_edit);
        mDHzEdit = (ValueComponentEdit) getActivity().findViewById(R.id.ctcss_dhz_edit);

        mRangeViews = new ArrayList<>();
        final ViewGroup rangesLayout = (ViewGroup) getActivity().findViewById(R.id.ctcss_ranges_layout);
        for (int i = 0; i < rangesLayout.getChildCount(); i++) {
            mRangeViews.add((RangeView) rangesLayout.getChildAt(i));
        }

        TestUtils.setPage(this, getActivity(), 2);
    }

    public void testSetFrequency() {
        setFrequency("71", "9"); // TODO: test empty input
        checkFrequency("071", "9");
        TestUtils.checkChannels(mRangeViews, new String[]{"2", "3", "17"});

        setFrequency("077", "0");
        checkFrequency("077", "0");
        TestUtils.checkChannels(mRangeViews, new String[]{"4", "5", "19"});

        setFrequency("13", "8");
        checkFrequency("013", "8");
        TestUtils.checkChannels(mRangeViews, new String[]{INVALID_CHANNEL, INVALID_CHANNEL, INVALID_CHANNEL});
    }


    public void testSetChannel() throws InterruptedException {
        setFrequency("71", "9");
        checkFrequency("071", "9");
        TestUtils.checkChannels(mRangeViews, new String[]{"2", "3", "17"});

        setChannel(TONES38_INDEX, "5");
        checkFrequency("079", "7");
        TestUtils.checkChannels(mRangeViews, new String[]{"5", "6", "20"});

        setChannel(TONES39_INDEX, "39");
        checkFrequency("250", "3");
        TestUtils.checkChannels(mRangeViews, new String[]{"38", "39", "63"});

        setChannel(TONES64_INDEX, "1");
        checkFrequency("033", "0");
        TestUtils.checkChannels(mRangeViews, new String[]{INVALID_CHANNEL, INVALID_CHANNEL, "1"});
    }


    public void testNextChannel() {
        setFrequency("67", "0");
        checkFrequency("067", "0");
        TestUtils.checkChannels(mRangeViews, new String[]{"1", "1", "15"});

        TouchUtils.tapView(this, getNextChannelButton(TONES38_INDEX));
        checkFrequency("071", "9");
        TestUtils.checkChannels(mRangeViews, new String[]{"2", "3", "17"});

        TouchUtils.tapView(this, getNextChannelButton(TONES39_INDEX));
        checkFrequency("074", "4");
        TestUtils.checkChannels(mRangeViews, new String[]{"3", "4", "18"});

        TouchUtils.tapView(this, getNextChannelButton(TONES64_INDEX));
        checkFrequency("077", "0");
        TestUtils.checkChannels(mRangeViews, new String[]{"4", "5", "19"});
    }

    public void testPrevChannel() throws InterruptedException {
        setFrequency("254", "1");
        Thread.sleep(1000);
        checkFrequency("254", "1");
        TestUtils.checkChannels(mRangeViews, new String[]{INVALID_CHANNEL, INVALID_CHANNEL, "64"});

        TouchUtils.tapView(this, getPrevChannelButton(TONES64_INDEX));
        checkFrequency("250", "3");
        TestUtils.checkChannels(mRangeViews, new String[]{"38", "39", "63"});

        TouchUtils.tapView(this, getPrevChannelButton(TONES39_INDEX));
        checkFrequency("241", "8");
        TestUtils.checkChannels(mRangeViews, new String[]{"37", "38", "62"});

        TouchUtils.tapView(this, getPrevChannelButton(TONES38_INDEX));
        checkFrequency("233", "6");
        TestUtils.checkChannels(mRangeViews, new String[]{"36", "37", "61"});
    }

    private void setFrequency(String hz, String dHz) {
        TestUtils.typeEditText(getInstrumentation(), mHzEdit, hz);
        TestUtils.typeEditText(getInstrumentation(), mDHzEdit, dHz);
    }

    private void setChannel(int rangeIndex, String channel) {
        final RangeView rangeView = mRangeViews.get(rangeIndex);
        final EditText channelEdit = TestUtils.getChannelEdit(rangeView);
        TestUtils.typeEditText(getInstrumentation(), channelEdit, channel);
    }

    private void checkFrequency(String hz, String dHz) {
        assertEquals(hz, mHzEdit.getText().toString());
        assertEquals(dHz, mDHzEdit.getText().toString());
    }

    private View getNextChannelButton(int rangeIndex) {
        return mRangeViews.get(rangeIndex).findViewById(R.id.next_channel_button);
    }

    private View getPrevChannelButton(int rangeIndex) {
        return mRangeViews.get(rangeIndex).findViewById(R.id.prev_channel_button);
    }
}
