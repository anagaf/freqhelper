package com.anagaf.freqhelper;

import android.test.AndroidTestCase;

public class SettingsTest extends AndroidTestCase {

    public void testFrequency() {
        final String frequency = "XXX";
        final Long value = 10L;
        Settings.write(getContext(), frequency, value);
        assertEquals(value, Settings.read(getContext(), frequency));
    }
}
