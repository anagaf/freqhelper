package com.anagaf.freqhelper.ranges;

import com.anagaf.freqhelper.Frequency;

public abstract class FixedStepRange extends AbstractRange {

    @Override
    public Frequency getFrequency(int channel) {
        assert (channel > 0 && channel <= getChannelCount());
        return getBaseFrequency().append(channel * getStep());
    }

    protected abstract Frequency getBaseFrequency();

    protected abstract int getStep();
}
