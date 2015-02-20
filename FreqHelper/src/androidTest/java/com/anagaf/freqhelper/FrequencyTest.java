package com.anagaf.freqhelper;

import android.test.AndroidTestCase;

public class FrequencyTest extends AndroidTestCase {

    public void testGetComponents() {
        Frequency frequency = Frequency.newChannelFrequency(433, 250, 150);
        assertEquals(433, frequency.getMegahertzComponent());
        assertEquals(250, frequency.getKilohertzComponent());
        assertEquals(150, frequency.getHertzComponent());
        assertEquals(0, frequency.getDeciHertzComponent());

        frequency = Frequency.newCtcssFrequency(45, 5);
        assertEquals(0, frequency.getMegahertzComponent());
        assertEquals(0, frequency.getKilohertzComponent());
        assertEquals(45, frequency.getHertzComponent());
        assertEquals(5, frequency.getDeciHertzComponent());
    }

    public void testToString() {
        assertEquals(Frequency.newChannelFrequency(446, 6, 250).toString(), "446.006.250.0");
        assertEquals(Frequency.newCtcssFrequency(31, 5).toString(), "0.000.031.5");
    }

    public void testAppend() {
        Frequency originalFrequency = Frequency.newChannelFrequency(433, 175, 250);
        Frequency resultFrequency = originalFrequency.append(2500);
        Frequency expectedFrequency = Frequency.newChannelFrequency(433, 177, 750);
        assertEquals(expectedFrequency, resultFrequency);

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
