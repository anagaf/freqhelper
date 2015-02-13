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
        assertTrue(mLpd8.getChannel(new Frequency(433, 75)) == 1);
        assertTrue(mLpd8.getChannel(new Frequency(433, 300)) == 4);
        assertTrue(mLpd8.getChannel(new Frequency(433, 800)) == 8);
    }

    public void testGetLowerEntry() {
        testGetLowerEntry(new Frequency(433, 75), null);
        testGetLowerEntry(new Frequency(433, 300), new Range.Entry(new Frequency(433, 200), 3));
        testGetLowerEntry(new Frequency(433, 900), new Range.Entry(new Frequency(433, 800), 8));
    }

    public void testGetHigherEntry() {
        testGetHigherEntry(new Frequency(433, 0), new Range.Entry(new Frequency(433, 75), 1));
        testGetHigherEntry(new Frequency(433, 300), new Range.Entry(new Frequency(433, 350), 5));
        testGetHigherEntry(new Frequency(433, 800), null);
    }

    private void testGetLowerEntry(Frequency frequency, Range.Entry expectedEntry) {
        Range.Entry entry = mLpd8.getLowerEntry(frequency);
        assertEquals(expectedEntry, entry);
    }

    private  void testGetHigherEntry(Frequency frequency, Range.Entry expectedEntry) {
        Range.Entry entry = mLpd8.getHigherEntry(frequency);
        assertEquals(expectedEntry, entry);
    }
}
