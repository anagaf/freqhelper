package com.anagaf.freqhelper.ui;

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

        TestUtils.setPage(this, getActivity(), 0);
    }

    public void testSetCode() throws InterruptedException {
        getInstrumentation().waitForIdleSync();

        TestUtils.typeEditText(getInstrumentation(), mDirectCodeEdit, "1");
        Thread.sleep(1000);
        checkCodes("001", INVALID_CODE);
        checkChannel(INVALID_CHANNEL);

        TestUtils.typeEditText(getInstrumentation(), mDirectCodeEdit, "2 3");
        Thread.sleep(1000);
        checkCodes("023", "047");
        checkChannel("1");

        // TODO: check 0 input

        TestUtils.typeEditText(getInstrumentation(), mInverseCodeEdit, "5 6");
        Thread.sleep(1000);
        checkCodes(INVALID_CODE, "056");
        checkChannel(INVALID_CHANNEL);

        TestUtils.typeEditText(getInstrumentation(), mInverseCodeEdit, "4 4 5");
        Thread.sleep(1000);
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

