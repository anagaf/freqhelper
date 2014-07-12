package com.anagaf.freqhelper.converters;

import android.test.AndroidTestCase;

import com.anagaf.freqhelper.Frequency;

public class FrequencyTest extends AndroidTestCase {

    public void testToString() {
        assertEquals(new Frequency(446, 006, 250).toString(), "446.006250");
    }

}
