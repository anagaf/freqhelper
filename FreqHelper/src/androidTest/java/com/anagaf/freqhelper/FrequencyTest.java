package com.anagaf.freqhelper;

import android.test.AndroidTestCase;

public class FrequencyTest extends AndroidTestCase {

    public void testComponents() {
        testComponents(0, 0, 0);
        testComponents(1, 1, 1);
        testComponents(22, 22, 22);
        testComponents(333, 333, 333);
    }

    private void testComponents(Integer mhz, Integer khz, Integer hz) {
        Frequency frequency = Frequency.newChannelFrequency(mhz, khz, hz);
        assertEquals(mhz, frequency.getMegahertzComponent());
        assertEquals(khz, frequency.getKilohertzComponent());
        assertEquals(hz, frequency.getHertzComponent());
    }

    public void testToString() {
        assertEquals(Frequency.newChannelFrequency(446, 6, 250).toString(), "446.006.250.0");
        assertEquals(Frequency.newCtcssFrequency(31, 5).toString(), "0.000.031.5");
    }

    public void testAppend() {
        Frequency channelFrequency = Frequency.newChannelFrequency(433, 175, 250);
        assertEquals(channelFrequency.append(2500), Frequency.newChannelFrequency(433, 177, 750));

        Frequency ctcssFrequency = Frequency.newCtcssFrequency(31, 5);
        assertEquals(ctcssFrequency.append(3), Frequency.newCtcssFrequency(34, 5));
    }

    public void testComparison() {
        assertTrue(Frequency.newChannelFrequency(433, 175, 250).compareTo(Frequency.newChannelFrequency(433, 175, 250)) == 0);
        assertTrue(Frequency.newChannelFrequency(433, 175, 150).compareTo(Frequency.newChannelFrequency(433, 175, 250)) < 0);
        assertTrue(Frequency.newChannelFrequency(433, 175, 350).compareTo(Frequency.newChannelFrequency(433, 175, 250)) > 0);

        assertTrue(Frequency.newCtcssFrequency(31, 5).compareTo(Frequency.newCtcssFrequency(31, 5)) == 0);
        assertTrue(Frequency.newCtcssFrequency(31, 4).compareTo(Frequency.newCtcssFrequency(31, 5)) < 0);
        assertTrue(Frequency.newCtcssFrequency(31, 6).compareTo(Frequency.newCtcssFrequency(31, 5)) > 0);
        assertTrue(Frequency.newCtcssFrequency(21, 5).compareTo(Frequency.newCtcssFrequency(31, 5)) < 0);
        assertTrue(Frequency.newCtcssFrequency(41, 5).compareTo(Frequency.newCtcssFrequency(31, 5)) > 0);
    }
}
