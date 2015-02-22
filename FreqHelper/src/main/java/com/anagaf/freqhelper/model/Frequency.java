package com.anagaf.freqhelper.model;

public class Frequency extends Key {
    private static final long DECI_HERTZ_PER_MEGAHERTZ = 10000000L;
    private static final long DECI_HERTZ_PER_KILOHERTZ = 10000L;
    private static final long DECI_HERTZ_PER_HERTZ = 10L;

    private Frequency(long deciHertz) {
        super(deciHertz);
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
        return getValue();
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
}
