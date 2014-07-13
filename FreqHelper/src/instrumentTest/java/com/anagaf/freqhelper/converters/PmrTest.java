package com.anagaf.freqhelper.converters;

import android.test.AndroidTestCase;

import com.anagaf.freqhelper.Frequency;
import com.anagaf.freqhelper.ranges.Pmr;
import com.anagaf.freqhelper.ranges.Range;

public class PmrTest extends AndroidTestCase {

    private Range mPmr = new Pmr();

    public void testGetFrequency() {
        assertTrue(mPmr.getFrequency(1).equals(new Frequency(446006250)));
        assertTrue(mPmr.getFrequency(4).equals(new Frequency(446043750)));
        assertTrue(mPmr.getFrequency(8).equals(new Frequency(446093750)));
    }

    public void testGetChannel() {
        assertTrue(mPmr.getChannel(new Frequency(446006250)) == 1);
        assertTrue(mPmr.getChannel(new Frequency(446043750)) == 4);
        assertTrue(mPmr.getChannel(new Frequency(446093750)) == 8);
    }

}
