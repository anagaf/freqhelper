package com.anagaf.freqhelper.converters;

import android.test.AndroidTestCase;

import com.anagaf.freqhelper.Frequency;
import com.anagaf.freqhelper.model.Pmr;
import com.anagaf.freqhelper.model.Range;

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

    public void testGetLowerEntry() {
        testGetLowerEntry(new Frequency(446006250), null);
        testGetLowerEntry(new Frequency(446056250), new Range.Entry(new Frequency(446043750), 4));
        testGetLowerEntry(new Frequency(446093755), new Range.Entry(new Frequency(446093750), 8));
    }

    public void testGetHigherEntry() {
        testGetHigherEntry(new Frequency(446006245), new Range.Entry(new Frequency(446006250), 1));
        testGetHigherEntry(new Frequency(446056250), new Range.Entry(new Frequency(446068750), 6));
        testGetHigherEntry(new Frequency(446093755), null);
    }

    public void testGetLowerEntry(Frequency frequency, Range.Entry expectedEntry) {
        Range.Entry entry = mPmr.getLowerEntry(frequency);
        assertEquals(expectedEntry, entry);
    }

    public void testGetHigherEntry(Frequency frequency, Range.Entry expectedEntry) {
        Range.Entry entry = mPmr.getHigherEntry(frequency);
        assertEquals(expectedEntry, entry);
    }
}
