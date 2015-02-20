package com.anagaf.freqhelper.model;

import com.anagaf.freqhelper.BuildConfig;
import com.anagaf.freqhelper.Frequency;

import java.util.List;

public abstract class AbstractStaticRange extends AbstractRange {

    protected abstract List<Frequency> getFrequencies();

    @Override
    public int getCount() {
        return getFrequencies().size();
    }

    @Override
    public Frequency getFrequency(int index) {
        if (BuildConfig.DEBUG && (index < 1 || index > getCount())) {
            throw new IllegalArgumentException();
        }
        return getFrequencies().get(index - 1);
    }

}
