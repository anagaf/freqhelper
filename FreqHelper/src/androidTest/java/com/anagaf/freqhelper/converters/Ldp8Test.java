package com.anagaf.freqhelper.converters;

import android.test.AndroidTestCase;

import com.anagaf.freqhelper.Frequency;
import com.anagaf.freqhelper.model.Lpd8;
import com.anagaf.freqhelper.model.Range;

public class Ldp8Test extends AndroidTestCase {

    private final Range mLpd8 = new Lpd8();

    public void testGetFrequency() {
        assertTrue(mLpd8.getFrequency(1).equals(new Frequency(433, 75)));
        assertTrue(mLpd8.getFrequency(4).equals(new Frequency(433, 300)));
        assertTrue(mLpd8.getFrequency(8).equals(new Frequency(433, 800)));
    }

    public void testGetChannel() {
        assertTrue(mLpd8.find(new Frequency(433, 75)) == 1);
        assertTrue(mLpd8.find(new Frequency(433, 300)) == 4);
        assertTrue(mLpd8.find(new Frequency(433, 800)) == 8);
    }

    public void testGetPrevChannel() {
        testGetPrevChannel(new Frequency(433, 75), Range.INVALID_INDEX);
        testGetPrevChannel(new Frequency(433, 300), 3);
        testGetPrevChannel(new Frequency(433, 900), 8);
    }

    public void testGetNextChannel() {
        testGetNextChannel(new Frequency(433, 0), 1);
        testGetNextChannel(new Frequency(433, 300), 5);
        testGetNextChannel(new Frequency(433, 800), Range.INVALID_INDEX);
    }

    private void testGetPrevChannel(Frequency frequency, int expectedChannel) {
        assertEquals(expectedChannel, mLpd8.findPrev(frequency));
    }

    private  void testGetNextChannel(Frequency frequency, int expectedChannel) {
        assertEquals(expectedChannel, mLpd8.findNext(frequency));
    }
}
