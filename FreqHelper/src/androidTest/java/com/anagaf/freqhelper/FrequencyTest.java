package com.anagaf.freqhelper;

import android.test.AndroidTestCase;

import com.anagaf.freqhelper.Frequency;

public class FrequencyTest extends AndroidTestCase {

    public void testComponents() {
        testComponents(0, 0, 0);
        testComponents(1, 1, 1);
        testComponents(22, 22, 22);
        testComponents(333, 333, 333);
    }

    private void testComponents(Integer mhz, Integer khz, Integer hz) {
        Frequency frequency = new Frequency(mhz, khz, hz);
        assertEquals(mhz, frequency.getMegahertzComponent());
        assertEquals(khz, frequency.getKilohertzComponent());
        assertEquals(hz, frequency.getHertzComponent());
    }

    public void testToString() {
        assertEquals(new Frequency(446, 006, 250).toString(), "446.006250");
    }
}
