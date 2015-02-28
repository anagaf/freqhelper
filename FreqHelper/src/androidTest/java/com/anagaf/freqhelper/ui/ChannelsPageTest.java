package com.anagaf.freqhelper.ui;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.ViewGroup;
import android.widget.EditText;

import com.anagaf.freqhelper.R;
import com.anagaf.freqhelper.ui.views.RangeView;
import com.anagaf.freqhelper.ui.views.ValueComponentEdit;

import java.util.ArrayList;
import java.util.List;

public class ChannelsPageTest extends ActivityInstrumentationTestCase2<MainActivity> {
    public static final String INVALID_CHANNEL = "--";
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

        mMhzEdit = (ValueComponentEdit) getActivity().findViewById(R.id.frequency_mhz_edit);
        mKhzEdit = (ValueComponentEdit) getActivity().findViewById(R.id.frequency_khz_edit);
        mHzEdit = (ValueComponentEdit) getActivity().findViewById(R.id.frequency_hz_edit);

        mRangeViews = new ArrayList<>();
        final ViewGroup rangesLayout = (ViewGroup) getActivity().findViewById(R.id.ranges_layout);
        for (int i = 0; i < rangesLayout.getChildCount(); i++) {
            mRangeViews.add((RangeView) rangesLayout.getChildAt(i));
        }
    }

    public void testSetFrequency() throws Throwable {
        TouchUtils.tapView(this, mMhzEdit);
        sendKeys("4 3 3 ENTER");

        TouchUtils.tapView(this, mKhzEdit);
        sendKeys("7 5 ENTER");

        TouchUtils.tapView(this, mHzEdit);
        sendKeys("0 ENTER");

        Thread.sleep(1000);

        checkChannels(new String[] {"1", "1", INVALID_CHANNEL, INVALID_CHANNEL});

        TouchUtils.tapView(this, mMhzEdit);
        sendKeys("4 3 4 ENTER");

        Thread.sleep(1000);

        checkChannels(new String[] {"41", INVALID_CHANNEL, INVALID_CHANNEL, INVALID_CHANNEL});

        TouchUtils.tapView(this, mKhzEdit);
        sendKeys("1 0 0 ENTER");
        TouchUtils.tapView(this, mHzEdit);
        sendKeys("0 ENTER");

        Thread.sleep(1000);

        checkChannels(new String[]{"42", INVALID_CHANNEL, INVALID_CHANNEL, INVALID_CHANNEL});

        TouchUtils.tapView(this, mHzEdit);
        sendKeys("5 0 ENTER");

        Thread.sleep(1000);

        checkChannels(new String[]{INVALID_CHANNEL, INVALID_CHANNEL, INVALID_CHANNEL, INVALID_CHANNEL});
    }


    public void testSetChannel() throws Throwable {
        TouchUtils.tapView(this, mMhzEdit);
        sendKeys("4 3 3 ENTER");

        TouchUtils.tapView(this, mKhzEdit);
        sendKeys("7 5 ENTER");

        TouchUtils.tapView(this, mHzEdit);
        sendKeys("0 ENTER");

        final RangeView lpd69 = mRangeViews.get(0);
        final EditText lpd69ChannelEdit = getChannelEdit(lpd69);

        TouchUtils.tapView(this, lpd69ChannelEdit);
        sendKeys("3 2 ENTER");

        checkFrequency("433", "850", "000");
    }

    private void checkFrequency(String mhz, String khz, String hz) {
        assertEquals(mhz, mMhzEdit.getText().toString());
        assertEquals(khz, mKhzEdit.getText().toString());
        assertEquals(hz, mHzEdit.getText().toString());
    }

    private void checkChannels(String[] expectedChannels) {
        for (int i = 0; i < expectedChannels.length; i++) {
            assertEquals(expectedChannels[i], getChannelEdit(mRangeViews.get(i)).getText().toString());
        }
    }

    private EditText getChannelEdit(RangeView rangeView) {
        return (EditText) rangeView.findViewById(R.id.channel);
    }
}
