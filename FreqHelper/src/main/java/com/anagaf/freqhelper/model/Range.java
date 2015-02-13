package com.anagaf.freqhelper.model;

import com.anagaf.freqhelper.Frequency;

public interface Range {

    public static final int INVALID_CHANNEL = 0;

    Integer getNameStringId();

    int getChannelCount();

    Frequency getFrequency(int channel);

    int getChannel(Frequency frequency);

    int getPrevChannel(Frequency frequency);

    int getNextChannel(Frequency frequency);
}
