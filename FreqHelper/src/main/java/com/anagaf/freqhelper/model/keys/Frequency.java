package com.anagaf.freqhelper.model.keys;

public class Frequency implements Comparable<Frequency> {
    private static final long DECI_HERTZ_PER_MEGAHERTZ = 10000000L;
    private static final long DECI_HERTZ_PER_KILOHERTZ = 10000L;
    private static final long DECI_HERTZ_PER_HERTZ = 10L;

    private final Long mDecihertz;

    private Frequency(long deciHertz) {
        mDecihertz = deciHertz;
    }

    public static Frequency newFrequency(long deciHertz) {
        return new Frequency(deciHertz);
    }

    public static long getChannelFrequencyDecihertz(int megahertz, int kilohertz, int hertz) {
        return megahertz * DECI_HERTZ_PER_MEGAHERTZ + kilohertz * DECI_HERTZ_PER_KILOHERTZ + DECI_HERTZ_PER_HERTZ * hertz;
    }

    public static Long getCtcssFrequencyDecihertz(int hertz, int deciHertz) {
        return DECI_HERTZ_PER_HERTZ * hertz + deciHertz;
    }

    @Override
    public String toString() {
        return String.format("%d.%03d.%03d.%d", getMegahertzComponent(), getKilohertzComponent(), getHertzComponent(), getDeciHertzComponent());
    }

    public long getDeciHertz() {
        return mDecihertz;
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

    public Frequency append(int hertz) {
        return new Frequency(getDeciHertz() + DECI_HERTZ_PER_HERTZ * hertz);
    }

    public int compareTo(Frequency frequency) {
        return mDecihertz.compareTo(frequency.mDecihertz);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Frequency frequency = (Frequency) o;

        return mDecihertz.equals(frequency.mDecihertz);

    }

    @Override
    public int hashCode() {
        return mDecihertz.hashCode();
    }
}
