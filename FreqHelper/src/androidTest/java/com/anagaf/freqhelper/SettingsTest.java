package com.anagaf.freqhelper;

import android.test.AndroidTestCase;

public class SettingsTest extends AndroidTestCase {

    public void testFrequency() {
        Frequency frequency = Frequency.newChannelFrequency(475, 133, 0);
        Settings.setFrequency(getContext(), frequency);
        assertEquals(Settings.getFrequency(getContext()), frequency);
    }
}
