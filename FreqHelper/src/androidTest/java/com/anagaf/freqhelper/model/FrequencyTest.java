package com.anagaf.freqhelper.model;

import android.test.AndroidTestCase;

public class FrequencyTest extends AndroidTestCase {

    public void testGetComponents() {
        Frequency frequency = createChannelFrequency(433, 250, 150);
        assertEquals(433, frequency.getMegahertzComponent());
        assertEquals(250, frequency.getKilohertzComponent());
        assertEquals(150, frequency.getHertzComponent());
        assertEquals(0, frequency.getDeciHertzComponent());

        frequency = createCtcssFrequency(45, 5);
        assertEquals(0, frequency.getMegahertzComponent());
        assertEquals(0, frequency.getKilohertzComponent());
        assertEquals(45, frequency.getHertzComponent());
        assertEquals(5, frequency.getDeciHertzComponent());
    }

    public void testToString() {
        assertEquals("446.006.250.0", createChannelFrequency(446, 6, 250).toString());
        assertEquals("0.000.031.5", createCtcssFrequency(31, 5).toString());
    }

    public void testAppend() {
        Frequency originalFrequency = createChannelFrequency(433, 175, 250);
        Frequency resultFrequency = originalFrequency.append(2500);
        Frequency expectedFrequency = createChannelFrequency(433, 177, 750);
        assertEquals(expectedFrequency, resultFrequency);

        Frequency ctcssFrequency = createCtcssFrequency(31, 5);
        assertEquals(ctcssFrequency.append(3), createCtcssFrequency(34, 5));
    }

    public void testComparison() {
        assertTrue(createChannelFrequency(433, 175, 250).compareTo(createChannelFrequency(433, 175, 250)) == 0);
        assertTrue(createChannelFrequency(433, 175, 150).compareTo(createChannelFrequency(433, 175, 250)) < 0);
        assertTrue(createChannelFrequency(433, 175, 350).compareTo(createChannelFrequency(433, 175, 250)) > 0);

        assertTrue(createCtcssFrequency(31, 5).compareTo(createCtcssFrequency(31, 5)) == 0);
        assertTrue(createCtcssFrequency(31, 4).compareTo(createCtcssFrequency(31, 5)) < 0);
        assertTrue(createCtcssFrequency(31, 6).compareTo(createCtcssFrequency(31, 5)) > 0);
        assertTrue(createCtcssFrequency(21, 5).compareTo(createCtcssFrequency(31, 5)) < 0);
        assertTrue(createCtcssFrequency(41, 5).compareTo(createCtcssFrequency(31, 5)) > 0);
    }

    private Frequency createChannelFrequency(int megaHz, int kiloHz, int hz) {
        return Frequency.newFrequency(Frequency.getChannelFrequencyDecihertz(megaHz, kiloHz, hz));
    }

    private Frequency createCtcssFrequency(int hz, int deciHz) {
        return Frequency.newFrequency(Frequency.getCtcssFrequencyDecihertz(hz, deciHz));
    }
}
