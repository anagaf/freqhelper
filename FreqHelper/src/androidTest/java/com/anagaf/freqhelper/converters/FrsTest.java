package com.anagaf.freqhelper.converters;

import android.test.AndroidTestCase;

import com.anagaf.freqhelper.Frequency;
import com.anagaf.freqhelper.model.Frs;
import com.anagaf.freqhelper.model.Range;

public class FrsTest extends AndroidTestCase {

    private final Range mFrs = new Frs();

    public void testGetFrequency() {
        assertEquals(mFrs.getFrequency(1), new Frequency(462, 562, 500));
        assertEquals(mFrs.getFrequency(2), new Frequency(462, 587, 500));
        assertEquals(mFrs.getFrequency(4), new Frequency(462, 637, 500));
        assertEquals(mFrs.getFrequency(7), new Frequency(462, 712, 500));
        assertEquals(mFrs.getFrequency(8), new Frequency(467, 562, 500));
        assertEquals(mFrs.getFrequency(10), new Frequency(467, 612, 500));
        assertEquals(mFrs.getFrequency(12), new Frequency(467, 662, 500));
        assertEquals(mFrs.getFrequency(14), new Frequency(467, 712, 500));
    }

    public void testGetChannel() {
        assertEquals(mFrs.getChannel(new Frequency(462, 562, 400)), Range.INVALID_CHANNEL);
        assertEquals(mFrs.getChannel(new Frequency(462, 562, 500)), 1);
        assertEquals(mFrs.getChannel(new Frequency(462, 587, 500)), 2);
        assertEquals(mFrs.getChannel(new Frequency(462, 637, 500)), 4);
        assertEquals(mFrs.getChannel(new Frequency(462, 712, 500)), 7);
        assertEquals(mFrs.getChannel(new Frequency(467, 562, 500)), 8);
        assertEquals(mFrs.getChannel(new Frequency(467, 612, 500)), 10);
        assertEquals(mFrs.getChannel(new Frequency(467, 662, 500)), 12);
        assertEquals(mFrs.getChannel(new Frequency(467, 712, 500)), 14);
    }
}
