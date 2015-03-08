package com.anagaf.freqhelper.ui;

import android.test.ActivityInstrumentationTestCase2;

public class ChannelsPageTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private static final String INVALID_CHANNEL_2DIGITS = "--";
    private static final String INVALID_CHANNEL_1DIGIT = "-";

    private ChannelsPageInstrumentation mPage;

    public ChannelsPageTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        getInstrumentation().setInTouchMode(false);

        mPage = new ChannelsPageInstrumentation(this);
    }

    public void testSetFrequency() throws Throwable {
        mPage.setFrequency("433", "75", "0");
        mPage.checkFrequency("433", "075", "000");
        mPage.checkChannels(new String[]{"1", "1", INVALID_CHANNEL_1DIGIT, INVALID_CHANNEL_2DIGITS});

        TestUtils.typeEditText(getInstrumentation(), mPage.getMHzEdit(), "434");
        mPage.checkFrequency("434", "075", "000");
        mPage.checkChannels(new String[]{"41", INVALID_CHANNEL_1DIGIT, INVALID_CHANNEL_1DIGIT, INVALID_CHANNEL_2DIGITS});

        TestUtils.typeEditText(getInstrumentation(), mPage.getKHzEdit(), "100");
        mPage.checkFrequency("434", "100", "000");
        mPage.checkChannels(new String[]{"42", INVALID_CHANNEL_1DIGIT, INVALID_CHANNEL_1DIGIT, INVALID_CHANNEL_2DIGITS});

        TestUtils.typeEditText(getInstrumentation(), mPage.getHzEdit(), "50");
        mPage.checkFrequency("434", "100", "050");
        mPage.checkChannels(new String[]{INVALID_CHANNEL_2DIGITS, INVALID_CHANNEL_1DIGIT, INVALID_CHANNEL_1DIGIT, INVALID_CHANNEL_2DIGITS});

        TestUtils.typeEditText(getInstrumentation(), mPage.getHzEdit(), "50");
        mPage.checkFrequency("434", "100", "050");
        mPage.checkChannels(new String[]{INVALID_CHANNEL_2DIGITS, INVALID_CHANNEL_1DIGIT, INVALID_CHANNEL_1DIGIT, INVALID_CHANNEL_2DIGITS});

        // TODO: test restoring value in case of empty input
    }

    public void testSetChannel() throws Throwable {
        mPage.setFrequency("433", "75", "0");
        mPage.checkFrequency("433", "075", "000");
        mPage.checkChannels(new String[]{"1", "1", INVALID_CHANNEL_1DIGIT, INVALID_CHANNEL_2DIGITS});

        mPage.setChannel(ChannelsPageInstrumentation.LPD69_INDEX, "32");
        mPage.checkFrequency("433", "850", "000");

        mPage.setChannel(ChannelsPageInstrumentation.LPD8_INDEX, "8");
        mPage.checkFrequency("433", "800", "000");

        mPage.setChannel(ChannelsPageInstrumentation.PMR_INDEX, "5");
        mPage.checkFrequency("446", "056", "250");

        mPage.setChannel(ChannelsPageInstrumentation.FRS_INDEX, "12");
        mPage.checkFrequency("467", "662", "500");
    }


    public void testNextChannel() throws Throwable {
        mPage.setFrequency("4 3 3", "7 5", "0");
        mPage.checkFrequency("433", "075", "000");
        mPage.checkChannels(new String[]{"1", "1", INVALID_CHANNEL_1DIGIT, INVALID_CHANNEL_2DIGITS});

        mPage.pressNextChannelButton(ChannelsPageInstrumentation.LPD69_INDEX);
        mPage.checkFrequency("433", "100", "000");
        mPage.checkChannels(new String[]{"2", "2", INVALID_CHANNEL_1DIGIT, INVALID_CHANNEL_2DIGITS});

        mPage.pressNextChannelButton(ChannelsPageInstrumentation.LPD8_INDEX);
        mPage.checkFrequency("433", "200", "000");
        mPage.checkChannels(new String[]{"6", "3", INVALID_CHANNEL_1DIGIT, INVALID_CHANNEL_2DIGITS});

        mPage.pressNextChannelButton(ChannelsPageInstrumentation.PMR_INDEX);
        mPage.checkFrequency("446", "006", "250");
        mPage.checkChannels(new String[]{INVALID_CHANNEL_2DIGITS, INVALID_CHANNEL_1DIGIT, "1", INVALID_CHANNEL_2DIGITS});

        mPage.pressNextChannelButton(ChannelsPageInstrumentation.FRS_INDEX);
        mPage.checkFrequency("462", "562", "500");
        mPage.checkChannels(new String[]{INVALID_CHANNEL_2DIGITS, INVALID_CHANNEL_1DIGIT, INVALID_CHANNEL_1DIGIT, "1"});
    }

    public void testPrevChannel() throws Throwable {
        mPage.setFrequency("4 6 7", "7 1 2", "5 0 0");
        mPage.checkFrequency("467", "712", "500");
        mPage.checkChannels(new String[]{INVALID_CHANNEL_2DIGITS, INVALID_CHANNEL_1DIGIT, INVALID_CHANNEL_1DIGIT, "14"});

        mPage.pressPrevChannelButton(ChannelsPageInstrumentation.FRS_INDEX);
        mPage.checkFrequency("467", "687", "500");
        mPage.checkChannels(new String[]{INVALID_CHANNEL_2DIGITS, INVALID_CHANNEL_1DIGIT, INVALID_CHANNEL_1DIGIT, "13"});

        mPage.pressPrevChannelButton(ChannelsPageInstrumentation.PMR_INDEX);
        mPage.checkFrequency("446", "093", "750");
        mPage.checkChannels(new String[]{INVALID_CHANNEL_2DIGITS, INVALID_CHANNEL_1DIGIT, "8", INVALID_CHANNEL_2DIGITS});

        mPage.pressPrevChannelButton(ChannelsPageInstrumentation.LPD8_INDEX);
        mPage.checkFrequency("433", "800", "000");
        mPage.checkChannels(new String[]{"30", "8", INVALID_CHANNEL_1DIGIT, INVALID_CHANNEL_2DIGITS});

        mPage.pressPrevChannelButton(ChannelsPageInstrumentation.LPD69_INDEX);
        mPage.checkFrequency("433", "775", "000");
        mPage.checkChannels(new String[]{"29", INVALID_CHANNEL_1DIGIT, INVALID_CHANNEL_1DIGIT, INVALID_CHANNEL_2DIGITS});
    }
}
