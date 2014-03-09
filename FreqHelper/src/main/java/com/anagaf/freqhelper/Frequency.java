package com.anagaf.freqhelper;

public class Frequency {
    private static final int KILOHERTZ_PER_MEGAHERTZ = 1000;

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
        return String.valueOf(mKilohertz / KILOHERTZ_PER_MEGAHERTZ) + "." + String.valueOf(mKilohertz % KILOHERTZ_PER_MEGAHERTZ);
    }
}
