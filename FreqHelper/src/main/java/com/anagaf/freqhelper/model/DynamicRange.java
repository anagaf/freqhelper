package com.anagaf.freqhelper.model;

import com.anagaf.freqhelper.BuildConfig;

public abstract class DynamicRange extends AbstractRange {

    @Override
    public Frequency getFrequency(int index) {
        if (BuildConfig.DEBUG && (index < 1 || index > getCount())) {
            throw new IllegalArgumentException();
        }
        Frequency baseFreq = getBaseFrequency();
        return baseFreq.append((index - 1) * getStep());
    }

    protected abstract Frequency getBaseFrequency();

    protected abstract int getStep();
}
