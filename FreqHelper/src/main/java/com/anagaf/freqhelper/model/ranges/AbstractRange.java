package com.anagaf.freqhelper.model.ranges;

import java.util.NavigableMap;
import java.util.TreeMap;

public abstract class AbstractRange implements Range {

    private final NavigableMap<Long, Integer> mItems;

    protected AbstractRange() {
        mItems = new TreeMap<>();
        for (int i = 1; i <= getCount(); i++) {
            mItems.put(getValue(i), i);
        }
    }

    @Override
    public int find(long value) {
        if (mItems.containsKey(value)) {
            return mItems.get(value);
        } else {
            return INVALID_INDEX;
        }
    }

    @Override
    public int findPrev(long value) {
        final TreeMap.Entry<Long, Integer> entry = mItems.lowerEntry(value);
        return (entry != null ? entry.getValue() : INVALID_INDEX);
    }

    @Override
    public int findNext(long value) {
        final TreeMap.Entry<Long, Integer> entry = mItems.higherEntry(value);
        return (entry != null ? entry.getValue() : INVALID_INDEX);
    }

}
