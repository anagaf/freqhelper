package com.anagaf.freqhelper.model;

import com.anagaf.freqhelper.Frequency;

public abstract class FixedStepRange extends AbstractRange {

    @Override
    public Frequency getFrequency(int channel) {
        assert (channel > 0 && channel <= getChannelCount());
        Frequency baseFreq = getBaseFrequency();
        return baseFreq.append((channel  - 1) * getStep());
    }

    protected abstract Frequency getBaseFrequency();

    protected abstract int getStep();
}
