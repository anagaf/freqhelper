package com.anagaf.freqhelper;

public class Frequency implements Comparable<Frequency> {
    private static final int HERTZ_PER_MEGAHERTZ = 1000000;
    private static final int HERTZ_PER_KILOHERTZ = 1000;
    private final Integer mHertz;
    private final Integer mHertzFraction;

//    public Frequency(int megahertz, int kilohertz) {
//        this(megahertz, kilohertz, 0);
//    }
//
//    public Frequency(int megahertz, int kilohertz, int hertz) {
//        this(megahertz * HERTZ_PER_MEGAHERTZ + kilohertz * HERTZ_PER_KILOHERTZ + hertz, 0);
//    }

    private Frequency(int hertz, int hertzFraction) {
        mHertz = hertz;
        mHertzFraction = hertzFraction;
    }

    public static Frequency newChannelFrequency(int hertz) {
        return new Frequency(hertz, 0);
    }

    public static Frequency newChannelFrequency(int megahertz, int kilohertz, int hertz) {
        return new Frequency(megahertz * HERTZ_PER_MEGAHERTZ + kilohertz * HERTZ_PER_KILOHERTZ + hertz, 0);
    }

    public static Frequency newCtcssFrequency(int hertz, int hertzFraction) {
        return new Frequency(hertz, hertzFraction);
    }

    @Override
    public String toString() {
        return String.format("%d.%03d.%03d.%d", getMegahertzComponent(), getKilohertzComponent(), getHertzComponent(), mHertzFraction);
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

    public int getHertzFractionComponent() {
        return mHertzFraction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Frequency frequency = (Frequency) o;

        if (mHertz != null ? !mHertz.equals(frequency.mHertz) : frequency.mHertz != null)
            return false;
        if (mHertzFraction != null ? !mHertzFraction.equals(frequency.mHertzFraction) : frequency.mHertzFraction != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mHertz != null ? mHertz.hashCode() : 0;
        result = 31 * result + (mHertzFraction != null ? mHertzFraction.hashCode() : 0);
        return result;
    }

    public Frequency append(int hertz) {
        return new Frequency(mHertz + hertz, mHertzFraction);
    }

    @Override
    public int compareTo(Frequency frequency) {
        return mHertz.compareTo(frequency.mHertz);
    }
}
