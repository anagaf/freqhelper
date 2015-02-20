package com.anagaf.freqhelper.converters;

import android.test.AndroidTestCase;

import com.anagaf.freqhelper.Frequency;
import com.anagaf.freqhelper.model.Lpd69;
import com.anagaf.freqhelper.model.Range;

public class Lpd69Test extends AndroidTestCase {

    private final Range mLpd69 = new Lpd69();

    public void testGetFrequency() {
        assertTrue(mLpd69.getFrequency(1).equals(Frequency.newChannelFrequency(433, 75, 0)));
        assertTrue(mLpd69.getFrequency(69).equals(Frequency.newChannelFrequency(434, 775, 0)));
    }

    public void testGetChannel() {
        assertTrue(mLpd69.find(Frequency.newChannelFrequency(433, 75, 0)) == 1);
        assertTrue(mLpd69.find(Frequency.newChannelFrequency(434, 775, 0)) == 69);
    }

    public void testGetPrevChannel() {
        testGetPrevChannel(Frequency.newChannelFrequency(433, 75, 0), Range.INVALID_INDEX);
        testGetPrevChannel(Frequency.newChannelFrequency(433, 300, 0), 9);
        testGetPrevChannel(Frequency.newChannelFrequency(434, 800, 0), 69);
    }

    public void testGetNextChannel() {
        testGetNextChannel(Frequency.newChannelFrequency(433, 0, 0), 1);
        testGetNextChannel(Frequency.newChannelFrequency(433, 300, 0), 11);
        testGetNextChannel(Frequency.newChannelFrequency(434, 800, 0), Range.INVALID_INDEX);
    }

    private void testGetPrevChannel(Frequency frequency, int expectedChannel) {
        assertEquals(expectedChannel, mLpd69.findPrev(frequency));
    }

    private void testGetNextChannel(Frequency frequency, int expectedChannel) {
        assertEquals(expectedChannel, mLpd69.findNext(frequency));
    }
}
