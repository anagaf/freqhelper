package com.anagaf.freqhelper.model;

import com.anagaf.freqhelper.BuildConfig;
import com.anagaf.freqhelper.Frequency;

public abstract class FixedStepRange extends AbstractRange {

    @Override
    public Frequency getFrequency(int channel) {
        if (BuildConfig.DEBUG && (channel < 1 || channel > getChannelCount())) {
            throw new IllegalArgumentException();
        }
        Frequency baseFreq = getBaseFrequency();
        return baseFreq.append((channel - 1) * getStep());
    }

    protected abstract Frequency getBaseFrequency();

    protected abstract int getStep();
}
