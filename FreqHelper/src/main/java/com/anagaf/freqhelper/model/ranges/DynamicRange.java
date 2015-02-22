package com.anagaf.freqhelper.model.ranges;

import com.anagaf.freqhelper.BuildConfig;
import com.anagaf.freqhelper.model.keys.Frequency;
import com.anagaf.freqhelper.model.keys.Key;

public abstract class DynamicRange extends AbstractRange {

    @Override
    public Key getKey(int index) {
        if (BuildConfig.DEBUG && (index < 1 || index > getCount())) {
            throw new IllegalArgumentException();
        }
        Frequency baseFreq = getBaseFrequency();
        return baseFreq.append((index - 1) * getStep());
    }

    protected abstract Frequency getBaseFrequency();

    protected abstract int getStep();
}
