package com.anagaf.freqhelper;

public class Frequency implements Comparable<Frequency> {
    private static final int HERTZ_PER_MEGAHERTZ = 1000000;
    private static final int HERTZ_PER_KILOHERTZ = 1000;
    private final Integer mHertz;
    private final Integer mDeciHertz;

    private Frequency(int hertz, int deciHertz) {
        mHertz = hertz;
        mDeciHertz = deciHertz;
    }

    public static Frequency newChannelFrequency(int hertz) {
        return new Frequency(hertz, 0);
    }

    public static Frequency newChannelFrequency(int megahertz, int kilohertz, int hertz) {
        return new Frequency(megahertz * HERTZ_PER_MEGAHERTZ + kilohertz * HERTZ_PER_KILOHERTZ + hertz, 0);
    }

    public static Frequency newCtcssFrequency(int hertz, int deciHertz) {
        return new Frequency(hertz, deciHertz);
    }

    @Override
    public String toString() {
        return String.format("%d.%03d.%03d.%d", getMegahertzComponent(), getKilohertzComponent(), getHertzComponent(), getDeciHertzComponent());
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

    public int getDeciHertzComponent() {
        return mDeciHertz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Frequency frequency = (Frequency) o;

        if (mHertz != null ? !mHertz.equals(frequency.mHertz) : frequency.mHertz != null)
            return false;
        if (mDeciHertz != null ? !mDeciHertz.equals(frequency.mDeciHertz) : frequency.mDeciHertz != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mHertz != null ? mHertz.hashCode() : 0;
        result = 31 * result + (mDeciHertz != null ? mDeciHertz.hashCode() : 0);
        return result;
    }

    public Frequency append(int hertz) {
        return new Frequency(mHertz + hertz, mDeciHertz);
    }

    @Override
    public int compareTo(Frequency frequency) {
        return getDeciHertz().compareTo(frequency.getDeciHertz());
    }

    private Long getDeciHertz() {
        return mHertz * 10L + mDeciHertz;
    }
}
