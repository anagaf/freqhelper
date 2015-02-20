package com.anagaf.freqhelper.converters;

import android.test.AndroidTestCase;

import com.anagaf.freqhelper.Frequency;
import com.anagaf.freqhelper.model.Ctcss64;
import com.anagaf.freqhelper.model.Range;

public class Ctcss64Test extends AndroidTestCase {

    private final Range mRange = new Ctcss64();

    public void testGetFrequency() {
        assertEquals(mRange.getFrequency(1), Frequency.newCtcssFrequency(33, 0));
        assertEquals(mRange.getFrequency(13), Frequency.newCtcssFrequency(58, 8));
        assertEquals(mRange.getFrequency(28), Frequency.newCtcssFrequency(103, 5));
        assertEquals(mRange.getFrequency(64), Frequency.newCtcssFrequency(254, 1));
    }

    public void testFind() {
        assertEquals(mRange.find(Frequency.newCtcssFrequency(0, 0)), Range.INVALID_INDEX);
        assertEquals(mRange.find(Frequency.newCtcssFrequency(33, 0)), 1);
        assertEquals(mRange.find(Frequency.newCtcssFrequency(88, 5)), 23);
        assertEquals(mRange.find(Frequency.newCtcssFrequency(177, 3)), 47);
        assertEquals(mRange.find(Frequency.newCtcssFrequency(254, 1)), 64);
    }
}
