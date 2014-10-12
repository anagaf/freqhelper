package com.anagaf.freqhelper;

import android.test.AndroidTestCase;

import com.anagaf.freqhelper.model.Range;

public class SettingsTest extends AndroidTestCase {

    public void testModeChannels() {
        final String key1 = "key1";
        final int channel1 = 15;
        final String key2 = "key2";
        final int channel2 = 3;
        final String keyInvalid = "invalid";
        Settings.setModeChannel(getContext(), key1, channel1);
        Settings.setModeChannel(getContext(), key2, channel2);
        assertEquals(channel1, Settings.getModeChannel(getContext(), key1));
        assertEquals(channel2, Settings.getModeChannel(getContext(), key2));
        assertEquals(Range.INVALID_CHANNEL, Settings.getModeChannel(getContext(), keyInvalid));
    }
}
