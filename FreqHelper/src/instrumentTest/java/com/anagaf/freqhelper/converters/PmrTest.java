package com.anagaf.com.converters;

import android.test.AndroidTestCase;

import com.anagaf.com.Frequency;
import com.anagaf.com.ranges.Pmr;
import com.anagaf.com.ranges.Range;

public class PmrTest extends AndroidTestCase {

    private Range mPmr = new Pmr();

    public void testGetFrequency() {
//        assertTrue(mPmr.getFrequency(1).equals(new Frequency(446006250)));
        Frequency freq1 = mPmr.getFrequency(4);
        Frequency freq2 = new Frequency(446043750);
        assertTrue(freq1.equals(freq2));
        assertTrue(mPmr.getFrequency(8).equals(new Frequency(446093750)));
    }

//    public void testGetChannel() {
//        assertTrue(mPmr.getChannel(new Frequency(446006250)) == 1);
//        assertTrue(mPmr.getChannel(new Frequency(446043750)) == 4);
//        assertTrue(mPmr.getChannel(new Frequency(446093750)) == 8);
//    }

}
