package com.anagaf.freqhelper.converters;

import com.anagaf.freqhelper.Frequency;

public class Lpd69ChannelToFrequencyConverter implements ChannelToFrequencyConverter {

    private static final Frequency BASE_FREQ = new Frequency(433, 50);
    private static final int STEP_KILOHERTZ = 25;

    @Override
    public int getChannelCount() {
        return 69;
    }

    @Override
    public Frequency getFrequency(int channel) {
        assert (channel > 0 && channel <= getChannelCount());
        return new Frequency(BASE_FREQ.getKilohertz() + channel * STEP_KILOHERTZ);
    }
}
