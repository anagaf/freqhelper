package com.anagaf.freqhelper.model.ranges;

import com.anagaf.freqhelper.BuildConfig;

public abstract class DynamicRange extends AbstractRange {

    @Override
    public long getValue(int index) {
        if (BuildConfig.DEBUG && (index < 1 || index > getCount())) {
            throw new IllegalArgumentException();
        }
        long baseValue = getBaseValue();
        return baseValue + (index - 1) * getStep();
    }

    protected abstract long getBaseValue();

    protected abstract long getStep();
}
