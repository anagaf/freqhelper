package com.anagaf.freqhelper.converters;

import android.test.AndroidTestCase;

import com.anagaf.freqhelper.Frequency;
import com.anagaf.freqhelper.model.Lpd69;
import com.anagaf.freqhelper.model.Range;

public class Lpd69Test extends AndroidTestCase {

    private Range mLpd69 = new Lpd69();

    public void testGetFrequency() {
        assertTrue(mLpd69.getFrequency(1).equals(new Frequency(433, 75)));
        assertTrue(mLpd69.getFrequency(69).equals(new Frequency(434, 775)));
    }

    public void testGetChannel() {
        assertTrue(mLpd69.getChannel(new Frequency(433, 75)) == 1);
        assertTrue(mLpd69.getChannel(new Frequency(434, 775)) == 69);
    }

    public void testGetLowerEntry() {
        testGetLowerEntry(new Frequency(433, 75), null);
        testGetLowerEntry(new Frequency(433, 300), new Range.Entry(new Frequency(433, 275), 9));
        testGetLowerEntry(new Frequency(434, 800), new Range.Entry(new Frequency(434, 775), 69));
    }

    public void testGetHigherEntry() {
        testGetHigherEntry(new Frequency(433, 0), new Range.Entry(new Frequency(433, 75), 1));
        testGetHigherEntry(new Frequency(433, 300), new Range.Entry(new Frequency(433, 325), 11));
        testGetHigherEntry(new Frequency(434, 800), null);
    }

    public void testGetLowerEntry(Frequency frequency, Range.Entry expectedEntry) {
        Range.Entry entry = mLpd69.getLowerEntry(frequency);
        assertEquals(expectedEntry, entry);
    }

    public void testGetHigherEntry(Frequency frequency, Range.Entry expectedEntry) {
        Range.Entry entry = mLpd69.getHigherEntry(frequency);
        assertEquals(expectedEntry, entry);
    }
}
