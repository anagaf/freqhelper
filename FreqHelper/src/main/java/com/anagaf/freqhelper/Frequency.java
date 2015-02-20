package com.anagaf.freqhelper;

public class Frequency implements Comparable<Frequency> {
    private static final long DECI_HERTZ_PER_MEGAHERTZ = 10000000L;
    private static final long DECI_HERTZ_PER_KILOHERTZ = 10000L;
    private static final long DECI_HERTZ_PER_HERTZ = 10L;
    private final Long mDeciHertz;

    private Frequency(long deciHertz) {
        mDeciHertz = deciHertz;
    }

    public static Frequency newFrequency(long deciHertz) {
        return new Frequency(deciHertz);
    }

    public static Frequency newChannelFrequency(int megahertz, int kilohertz, int hertz) {
        return newFrequency(megahertz * DECI_HERTZ_PER_MEGAHERTZ + kilohertz * DECI_HERTZ_PER_KILOHERTZ + DECI_HERTZ_PER_HERTZ * hertz);
    }

    public static Frequency newCtcssFrequency(int hertz, int deciHertz) {
        return newFrequency(DECI_HERTZ_PER_HERTZ * hertz + deciHertz);
    }

    @Override
    public String toString() {
        return String.format("%d.%03d.%03d.%d", getMegahertzComponent(), getKilohertzComponent(), getHertzComponent(), getDeciHertzComponent());
    }

    public long getDeciHertz() {
        return mDeciHertz;
    }

    public int getMegahertzComponent() {
        return (int) (getDeciHertz() / DECI_HERTZ_PER_MEGAHERTZ);
    }

    public int getKilohertzComponent() {
        return (int) (getDeciHertz() % DECI_HERTZ_PER_MEGAHERTZ / DECI_HERTZ_PER_KILOHERTZ);
    }

    public int getHertzComponent() {
        return (int) (getDeciHertz() % DECI_HERTZ_PER_KILOHERTZ / DECI_HERTZ_PER_HERTZ);
    }

    public int getDeciHertzComponent() {
        return (int) (getDeciHertz() % DECI_HERTZ_PER_HERTZ);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Frequency frequency = (Frequency) o;

        if (mDeciHertz != null ? !mDeciHertz.equals(frequency.mDeciHertz) : frequency.mDeciHertz != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return mDeciHertz != null ? mDeciHertz.hashCode() : 0;
    }

    public Frequency append(int hertz) {
        return new Frequency(mDeciHertz + DECI_HERTZ_PER_HERTZ * hertz);
    }

    @Override
    public int compareTo(Frequency frequency) {
        return mDeciHertz.compareTo(frequency.mDeciHertz);
    }
}
