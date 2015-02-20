package com.anagaf.freqhelper.converters;

import android.test.AndroidTestCase;

import com.anagaf.freqhelper.Frequency;
import com.anagaf.freqhelper.model.Pmr;
import com.anagaf.freqhelper.model.Range;

public class PmrTest extends AndroidTestCase {

    private final Range mPmr = new Pmr();

    public void testGetFrequency() {
        assertTrue(mPmr.getFrequency(1).equals(new Frequency(446006250)));
        assertTrue(mPmr.getFrequency(4).equals(new Frequency(446043750)));
        assertTrue(mPmr.getFrequency(8).equals(new Frequency(446093750)));
    }

    public void testGetChannel() {
        assertTrue(mPmr.find(new Frequency(446006250)) == 1);
        assertTrue(mPmr.find(new Frequency(446043750)) == 4);
        assertTrue(mPmr.find(new Frequency(446093750)) == 8);
    }

    public void testGetPrevChannel() {
        testGetPrevChannel(new Frequency(446006250), Range.INVALID_INDEX);
        testGetPrevChannel(new Frequency(446056250), 4);
        testGetPrevChannel(new Frequency(446093755), 8);
    }

    public void testGetNextChannel() {
        testGetNextChannel(new Frequency(446006245), 1);
        testGetNextChannel(new Frequency(446056250), 6);
        testGetNextChannel(new Frequency(446093755), Range.INVALID_INDEX);
    }

    private void testGetPrevChannel(Frequency frequency, int expectedChannel) {
        assertEquals(expectedChannel, mPmr.findPrev(frequency));
    }

    private void testGetNextChannel(Frequency frequency, int expectedChannel) {
        assertEquals(expectedChannel, mPmr.findNext(frequency));
    }
}
