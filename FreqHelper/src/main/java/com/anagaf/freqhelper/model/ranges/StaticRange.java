package com.anagaf.freqhelper.model.ranges;

import com.anagaf.freqhelper.BuildConfig;
import com.anagaf.freqhelper.model.keys.Frequency;

import java.util.List;

public abstract class StaticRange extends AbstractRange {

    protected abstract List<Frequency> getKeys();

    @Override
    public int getCount() {
        return getKeys().size();
    }

    @Override
    public Frequency getFrequency(int index) {
        if (BuildConfig.DEBUG && (index < 1 || index > getCount())) {
            throw new IllegalArgumentException();
        }
        return getKeys().get(index - 1);
    }

}
