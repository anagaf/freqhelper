package com.anagaf.freqhelper.converters;

import android.test.AndroidTestCase;

import com.anagaf.freqhelper.Frequency;
import com.anagaf.freqhelper.model.Lpd69;
import com.anagaf.freqhelper.model.Range;

public class Lpd69Test extends AndroidTestCase {

    private final Range mLpd69 = new Lpd69();

    public void testGetFrequency() {
        assertTrue(mLpd69.getFrequency(1).equals(new Frequency(433, 75)));
        assertTrue(mLpd69.getFrequency(69).equals(new Frequency(434, 775)));
    }

    public void testGetChannel() {
        assertTrue(mLpd69.getChannel(new Frequency(433, 75)) == 1);
        assertTrue(mLpd69.getChannel(new Frequency(434, 775)) == 69);
    }

    public void testGetPrevChannel() {
        testGetPrevChannel(new Frequency(433, 75), Range.INVALID_CHANNEL);
        testGetPrevChannel(new Frequency(433, 300), 9);
        testGetPrevChannel(new Frequency(434, 800), 69);
    }

    public void testGetNextChannel() {
        testGetNextChannel(new Frequency(433, 0), 1);
        testGetNextChannel(new Frequency(433, 300), 11);
        testGetNextChannel(new Frequency(434, 800), Range.INVALID_CHANNEL);
    }

    private void testGetPrevChannel(Frequency frequency, int expectedChannel) {
        assertEquals(expectedChannel, mLpd69.getPrevChannel(frequency));
    }

    private void testGetNextChannel(Frequency frequency, int expectedChannel) {
        assertEquals(expectedChannel, mLpd69.getNextChannel(frequency));
    }
}
