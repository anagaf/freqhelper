package com.anagaf.freqhelper.model;

import com.anagaf.freqhelper.BuildConfig;

import java.util.List;

public abstract class StaticRange extends AbstractRange {

    protected abstract List<Key> getKeys();

    @Override
    public int getCount() {
        return getKeys().size();
    }

    @Override
    public Key getKey(int index) {
        if (BuildConfig.DEBUG && (index < 1 || index > getCount())) {
            throw new IllegalArgumentException();
        }
        return getKeys().get(index - 1);
    }

}
