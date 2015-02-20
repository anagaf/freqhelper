package com.anagaf.freqhelper.converters;

import android.test.AndroidTestCase;

import com.anagaf.freqhelper.Frequency;
import com.anagaf.freqhelper.model.Lpd8;
import com.anagaf.freqhelper.model.Range;

public class Ldp8Test extends AndroidTestCase {

    private final Range mLpd8 = new Lpd8();

    public void testGetFrequency() {
        assertTrue(mLpd8.getFrequency(1).equals(Frequency.newChannelFrequency(433, 75, 0)));
        assertTrue(mLpd8.getFrequency(4).equals(Frequency.newChannelFrequency(433, 300, 0)));
        assertTrue(mLpd8.getFrequency(8).equals(Frequency.newChannelFrequency(433, 800, 0)));
    }

    public void testGetChannel() {
        assertTrue(mLpd8.find(Frequency.newChannelFrequency(433, 75, 0)) == 1);
        assertTrue(mLpd8.find(Frequency.newChannelFrequency(433, 300, 0)) == 4);
        assertTrue(mLpd8.find(Frequency.newChannelFrequency(433, 800, 0)) == 8);
    }

    public void testGetPrevChannel() {
        testGetPrevChannel(Frequency.newChannelFrequency(433, 75, 0), Range.INVALID_INDEX);
        testGetPrevChannel(Frequency.newChannelFrequency(433, 300, 0), 3);
        testGetPrevChannel(Frequency.newChannelFrequency(433, 900, 0), 8);
    }

    public void testGetNextChannel() {
        testGetNextChannel(Frequency.newChannelFrequency(433, 0, 0), 1);
        testGetNextChannel(Frequency.newChannelFrequency(433, 300, 0), 5);
        testGetNextChannel(Frequency.newChannelFrequency(433, 800, 0), Range.INVALID_INDEX);
    }

    private void testGetPrevChannel(Frequency frequency, int expectedChannel) {
        assertEquals(expectedChannel, mLpd8.findPrev(frequency));
    }

    private  void testGetNextChannel(Frequency frequency, int expectedChannel) {
        assertEquals(expectedChannel, mLpd8.findNext(frequency));
    }
}
