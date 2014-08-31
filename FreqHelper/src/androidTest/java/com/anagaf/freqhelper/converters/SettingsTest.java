package com.anagaf.freqhelper.converters;

import android.test.AndroidTestCase;

import com.anagaf.freqhelper.Frequency;
import com.anagaf.freqhelper.Settings;

public class SettingsTest extends AndroidTestCase {

    public void testSaveLoad() {
        final Frequency originalFrequency = new Frequency(433, 750, 125);
        Settings.setFrequency(getContext(), originalFrequency);
        final Frequency resultingFrequency = Settings.getFrequency(getContext());
        assertEquals(originalFrequency, resultingFrequency);
    }
}