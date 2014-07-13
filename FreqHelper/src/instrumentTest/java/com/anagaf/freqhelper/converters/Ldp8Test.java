package com.anagaf.freqhelper.converters;

import android.test.AndroidTestCase;

import com.anagaf.freqhelper.Frequency;
import com.anagaf.freqhelper.ranges.Lpd8;
import com.anagaf.freqhelper.ranges.Range;

public class Ldp8Test extends AndroidTestCase {

    private Range mLpd8 = new Lpd8();

    public void testGetFrequency() {
        assertTrue(mLpd8.getFrequency(1).equals(new Frequency(433, 75)));
        assertTrue(mLpd8.getFrequency(4).equals(new Frequency(433, 300)));
        assertTrue(mLpd8.getFrequency(8).equals(new Frequency(433, 800)));
    }

    public void testGetChannel() {
        assertTrue(mLpd8.getChannel(new Frequency(433, 75)) == 1);
        assertTrue(mLpd8.getChannel(new Frequency(433, 300)) == 4);
        assertTrue(mLpd8.getChannel(new Frequency(433, 800)) == 8);
    }
}
