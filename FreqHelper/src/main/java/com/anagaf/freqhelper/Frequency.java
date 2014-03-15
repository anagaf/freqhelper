package com.anagaf.freqhelper;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Frequency {
    private static final int KILOHERTZ_PER_MEGAHERTZ = 1000;
    private static final NumberFormat sKilohertzFormat = new DecimalFormat("@@@");
    private final int mKilohertz;

    public Frequency(int megahertz, int kilohertz) {
        this(megahertz * KILOHERTZ_PER_MEGAHERTZ + kilohertz);
    }

    public Frequency(int kilohertz) {
        mKilohertz = kilohertz;
    }

    public int getKilohertz() {
        return mKilohertz;
    }

    @Override
    public String toString() {
        final int mhz = mKilohertz / KILOHERTZ_PER_MEGAHERTZ;
        final int khz = mKilohertz % KILOHERTZ_PER_MEGAHERTZ;
        return String.format("%d.%03d", mhz, khz);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Frequency frequency = (Frequency) o;

        if (mKilohertz != frequency.mKilohertz) return false;

        return true;
    }
}
