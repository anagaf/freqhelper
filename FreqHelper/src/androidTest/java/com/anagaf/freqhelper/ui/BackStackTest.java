package com.anagaf.freqhelper.ui;

import android.support.v4.view.ViewPager;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;

import com.anagaf.freqhelper.R;

public class BackStackTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private ChannelsPageInstrumentation channelsPageInstrumentation;

    private ViewPager mViewPager;

    public BackStackTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        getInstrumentation().setInTouchMode(false);

        mViewPager = (ViewPager) getActivity().findViewById(R.id.pager);
    }

    public void test() {
        assertEquals(1, mViewPager.getCurrentItem());

        final ChannelsPageInstrumentation channelsPage = new ChannelsPageInstrumentation(this);

        channelsPage.setFrequency("433", "75", "0");
        channelsPage.checkFrequency("433", "075", "000");

        channelsPage.setFrequencyKHzValue("100");
        channelsPage.checkFrequency("433", "100", "000");

        channelsPage.setLpd69Channel("3");
        channelsPage.checkFrequency("433", "125", "000");

        channelsPage.pressLpd69NextButton();
        channelsPage.checkFrequency("433", "150", "000");

        // TODO: edit channel

        TestUtils.setPage(this, getActivity(), 2);
        assertEquals(2, mViewPager.getCurrentItem());

        final CtcssPageInstrumentation ctcssPage = new CtcssPageInstrumentation(this);

        ctcssPage.setFrequency("67", "0");
        ctcssPage.checkFrequency("067", "0");

        ctcssPage.setFrequencyHzValue("77");
        ctcssPage.checkFrequency("077", "0");

        ctcssPage.press38TonesNextButton();
        ctcssPage.checkFrequency("079", "7");

        // TODO: edit channel

        pressBack();
        ctcssPage.checkFrequency("077", "0");

        pressBack();
        ctcssPage.checkFrequency("067", "0");

        pressBack();
        ctcssPage.checkFrequency("077", "0");

        pressBack();

        assertEquals(1, mViewPager.getCurrentItem());
        channelsPage.checkFrequency("433", "150", "000");

        pressBack();
        channelsPage.checkFrequency("433", "125", "000");

        pressBack();
        channelsPage.checkFrequency("433", "100", "000");

        pressBack();
        channelsPage.checkFrequency("433", "075", "000");
    }

    private void pressBack() {
        getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
        getInstrumentation().waitForIdleSync();
    }
}
