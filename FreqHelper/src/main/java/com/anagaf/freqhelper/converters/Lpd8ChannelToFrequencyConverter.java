package com.anagaf.freqhelper.converters;

import com.anagaf.freqhelper.Frequency;

public class Lpd8ChannelToFrequencyConverter implements ChannelToFrequencyConverter {
    @Override
    public int getChannelCount() {
        return 8;
    }

    @Override
    public Frequency getFrequency(int channel) {
        return null;
    }
}
