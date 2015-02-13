package com.anagaf.freqhelper;

public class Frequency implements Comparable<Frequency> {
    private static final int HERTZ_PER_MEGAHERTZ = 1000000;
    private static final int HERTZ_PER_KILOHERTZ = 1000;
    private final Integer mHertz;

    public Frequency(int megahertz, int kilohertz) {
        this(megahertz, kilohertz, 0);
    }

    public Frequency(int megahertz, int kilohertz, int hertz) {
        this(megahertz * HERTZ_PER_MEGAHERTZ + kilohertz * HERTZ_PER_KILOHERTZ + hertz);
    }

    public Frequency(int hertz) {
        mHertz = hertz;
    }

    @Override
    public String toString() {
        final int mhz = mHertz / HERTZ_PER_MEGAHERTZ;
        final int khz = mHertz % HERTZ_PER_MEGAHERTZ;
        return String.format("%d.%06d", mhz, khz);
    }

    public Integer getHertz() {
        return mHertz;
    }

    public Integer getMegahertzComponent() {
        return getHertz() / HERTZ_PER_MEGAHERTZ;
    }

    public Integer getKilohertzComponent() {
        return getHertz() % HERTZ_PER_MEGAHERTZ / HERTZ_PER_KILOHERTZ;
    }

    public Integer getHertzComponent() {
        return getHertz() % HERTZ_PER_KILOHERTZ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Frequency frequency = (Frequency) o;

        return (mHertz.equals(frequency.mHertz));

    }

    public Frequency append(int hertz) {
        return new Frequency(mHertz + hertz);
    }

    @Override
    public int compareTo(Frequency frequency) {
        return mHertz.compareTo(frequency.mHertz);
    }

}
