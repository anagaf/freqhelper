package com.anagaf.freqhelper.app;

import android.test.AndroidTestCase;

public class SettingsTest extends AndroidTestCase {

    public void testFrequency() {
        final String key = "XXX";
        final Long value = 10L;
        Settings.write(getContext(), key, value);
        assertTrue(value == Settings.read(getContext(), key));
    }
}
