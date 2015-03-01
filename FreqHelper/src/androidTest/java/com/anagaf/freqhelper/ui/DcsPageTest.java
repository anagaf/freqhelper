package com.anagaf.freqhelper.ui;

import android.support.v4.view.ViewPager;
import android.test.ActivityInstrumentationTestCase2;
import android.view.ViewGroup;

import com.anagaf.freqhelper.R;
import com.anagaf.freqhelper.ui.views.RangeView;
import com.anagaf.freqhelper.ui.views.ValueEdit;

public class DcsPageTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private static final String INVALID_CODE = "---";
    private static final String INVALID_CHANNEL = "--"; // TODO: ---

    private ValueEdit mDirectCodeEdit;
    private ValueEdit mInverseCodeEdit;
    private RangeView mRangeView;

    public DcsPageTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        mDirectCodeEdit = (ValueEdit) getActivity().findViewById(R.id.dcs_direct_code_edit);
        mInverseCodeEdit = (ValueEdit) getActivity().findViewById(R.id.dcs_inverse_code_edit);

        final ViewGroup rangesLayout = (ViewGroup) getActivity().findViewById(R.id.dcs_ranges_layout);
        mRangeView = (RangeView) rangesLayout.getChildAt(0);

        final ViewPager pager = (ViewPager) getActivity().findViewById(R.id.pager);
        try {
            runTestOnUiThread(new Runnable() {
                @Override
                public void run() {
                    pager.setCurrentItem(0);
                }
            });
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable.getMessage());
        }
    }

    public void testSetDirectCode() throws InterruptedException {
        getInstrumentation().waitForIdleSync();

        TestUtils.typeEditText(this, mDirectCodeEdit, "1");
        checkCodes("001", INVALID_CODE);
        checkChannel(INVALID_CHANNEL);

        TestUtils.typeEditText(this, mDirectCodeEdit, "2 3");
        checkCodes("023", "047");
        checkChannel("1");

        // TODO: check 0 input
    }

    public void testSetInverseCode() throws InterruptedException {
        getInstrumentation().waitForIdleSync();

        TestUtils.typeEditText(this, mInverseCodeEdit, "5 6");
        checkCodes(INVALID_CODE, "056");
        checkChannel(INVALID_CHANNEL);

        TestUtils.typeEditText(this, mInverseCodeEdit, "4 4 5");
        checkCodes("043", "445");
        checkChannel("7");
    }

    private void checkCodes(String expectedDirectCode, String expectedInverseCode) {
        assertEquals(expectedDirectCode, mDirectCodeEdit.getText().toString());
        assertEquals(expectedInverseCode, mInverseCodeEdit.getText().toString());
    }

    private void checkChannel(String expectedChannel) {
        final String actualChannel = TestUtils.getChannelEdit(mRangeView).getText().toString();
        assertEquals(expectedChannel, actualChannel);
    }
}

