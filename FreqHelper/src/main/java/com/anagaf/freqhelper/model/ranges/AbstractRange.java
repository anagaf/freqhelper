package com.anagaf.freqhelper.model.ranges;

import com.anagaf.freqhelper.model.keys.Frequency;

import java.util.NavigableMap;
import java.util.TreeMap;

public abstract class AbstractRange implements Range {

    private final NavigableMap<Frequency, Integer> mItems;

    AbstractRange() {
        mItems = new TreeMap<>();
        for (int i = 1; i <= getCount(); i++) {
            mItems.put(getFrequency(i), i);
        }
    }

    @Override
    public int find(Frequency frequency) {
        if (mItems.containsKey(frequency)) {
            return mItems.get(frequency);
        } else {
            return INVALID_INDEX;
        }
    }

    @Override
    public int findPrev(Frequency frequency) {
        final TreeMap.Entry<Frequency, Integer> entry = mItems.lowerEntry(frequency);
        return (entry != null ? entry.getValue() : INVALID_INDEX);
    }

    @Override
    public int findNext(Frequency frequency) {
        final TreeMap.Entry<Frequency, Integer> entry = mItems.higherEntry(frequency);
        return (entry != null ? entry.getValue() : INVALID_INDEX);
    }

}
