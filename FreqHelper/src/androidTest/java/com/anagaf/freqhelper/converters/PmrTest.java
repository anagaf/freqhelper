package com.anagaf.freqhelper.converters;

import android.test.AndroidTestCase;

import com.anagaf.freqhelper.Frequency;
import com.anagaf.freqhelper.model.Pmr;
import com.anagaf.freqhelper.model.Range;

public class PmrTest extends AndroidTestCase {

    private final Range mPmr = new Pmr();

    public void testGetFrequency() {
        assertTrue(mPmr.getFrequency(1).equals(Frequency.newChannelFrequency(446, 6, 250)));
        assertTrue(mPmr.getFrequency(4).equals(Frequency.newChannelFrequency(446, 43, 750)));
        assertTrue(mPmr.getFrequency(8).equals(Frequency.newChannelFrequency(446, 93, 750)));
    }

    public void testGetChannel() {
        assertTrue(mPmr.find(Frequency.newChannelFrequency(446, 6, 250)) == 1);
        assertTrue(mPmr.find(Frequency.newChannelFrequency(446, 43, 750)) == 4);
        assertTrue(mPmr.find(Frequency.newChannelFrequency(446, 93, 750)) == 8);
    }

    public void testGetPrevChannel() {
        testGetPrevChannel(Frequency.newChannelFrequency(446, 6, 250), Range.INVALID_INDEX);
        testGetPrevChannel(Frequency.newChannelFrequency(446, 56, 250), 4);
        testGetPrevChannel(Frequency.newChannelFrequency(446, 93, 755), 8);
    }

    public void testGetNextChannel() {
        testGetNextChannel(Frequency.newChannelFrequency(446, 6, 245), 1);
        testGetNextChannel(Frequency.newChannelFrequency(446, 56, 250), 6);
        testGetNextChannel(Frequency.newChannelFrequency(446, 93, 755), Range.INVALID_INDEX);
    }

    private void testGetPrevChannel(Frequency frequency, int expectedChannel) {
        assertEquals(expectedChannel, mPmr.findPrev(frequency));
    }

    private void testGetNextChannel(Frequency frequency, int expectedChannel) {
        assertEquals(expectedChannel, mPmr.findNext(frequency));
    }
}
