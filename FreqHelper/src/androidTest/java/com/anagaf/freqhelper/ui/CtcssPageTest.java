package com.anagaf.freqhelper.ui;

import android.test.ActivityInstrumentationTestCase2;

public class CtcssPageTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private static final String INVALID_CHANNEL = "--";

    private CtcssPageInstrumentation mPage;

    public CtcssPageTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        getInstrumentation().setInTouchMode(false);

        mPage = new CtcssPageInstrumentation(this);

        TestUtils.setPage(this, getActivity(), 2);
    }

    public void testSetFrequency() {
        mPage.setFrequency("71", "9");
        mPage.checkFrequency("071", "9");
        mPage.checkChannels(new String[]{"2", "3", "17"});

        mPage.setFrequency("077", "0");
        mPage.checkFrequency("077", "0");
        mPage.checkChannels(new String[]{"4", "5", "19"});

        mPage.setFrequency("13", "8");
        mPage.checkFrequency("013", "8");
        mPage.checkChannels(new String[]{INVALID_CHANNEL, INVALID_CHANNEL, INVALID_CHANNEL});
    }


    public void testSetChannel() throws InterruptedException {
        mPage.setFrequency("71", "9");
        mPage.checkFrequency("071", "9");
        mPage.checkChannels(new String[]{"2", "3", "17"});

        mPage.setChannel(CtcssPageInstrumentation.TONES38_INDEX, "5");
        mPage.checkFrequency("079", "7");
        mPage.checkChannels(new String[]{"5", "6", "20"});

        mPage.setChannel(CtcssPageInstrumentation.TONES39_INDEX, "39");
        mPage.checkFrequency("250", "3");
        mPage.checkChannels(new String[]{"38", "39", "63"});

        mPage.setChannel(CtcssPageInstrumentation.TONES64_INDEX, "1");
        mPage.checkFrequency("033", "0");
        mPage.checkChannels(new String[]{INVALID_CHANNEL, INVALID_CHANNEL, "1"});
    }


    public void testNextChannel() {
        mPage.setFrequency("67", "0");
        mPage.checkFrequency("067", "0");
        mPage.checkChannels(new String[]{"1", "1", "15"});

        mPage.pressNextChannelButton(CtcssPageInstrumentation.TONES38_INDEX);
        mPage.checkFrequency("071", "9");
        mPage.checkChannels(new String[]{"2", "3", "17"});

        mPage.pressNextChannelButton(CtcssPageInstrumentation.TONES39_INDEX);
        mPage.checkFrequency("074", "4");
        mPage.checkChannels(new String[]{"3", "4", "18"});

        mPage.pressNextChannelButton(CtcssPageInstrumentation.TONES64_INDEX);
        mPage.checkFrequency("077", "0");
        mPage.checkChannels(new String[]{"4", "5", "19"});
    }

    public void testPrevChannel() throws InterruptedException {
        mPage.setFrequency("254", "1");
        Thread.sleep(1000);
        mPage.checkFrequency("254", "1");
        mPage.checkChannels(new String[]{INVALID_CHANNEL, INVALID_CHANNEL, "64"});

        mPage.pressPrevChannelButton(CtcssPageInstrumentation.TONES64_INDEX);
        mPage.checkFrequency("250", "3");
        mPage.checkChannels(new String[]{"38", "39", "63"});

        mPage.pressPrevChannelButton(CtcssPageInstrumentation.TONES39_INDEX);
        mPage.checkFrequency("241", "8");
        mPage.checkChannels(new String[]{"37", "38", "62"});

        mPage.pressPrevChannelButton(CtcssPageInstrumentation.TONES38_INDEX);
        mPage.checkFrequency("233", "6");
        mPage.checkChannels(new String[]{"36", "37", "61"});
    }
}
