package com.anagaf.freqhelper.converters;

import com.anagaf.freqhelper.Frequency;

public interface ChannelToFrequencyConverter {
    public int getChannelCount();
    public Frequency getFrequency(int channel);
}
