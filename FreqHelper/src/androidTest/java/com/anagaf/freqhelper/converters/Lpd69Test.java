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
}