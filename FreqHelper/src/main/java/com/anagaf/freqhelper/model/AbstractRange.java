package com.anagaf.freqhelper.model;

import com.anagaf.freqhelper.Frequency;

import java.util.NavigableMap;
import java.util.TreeMap;

public abstract class AbstractRange implements Range {

    private final NavigableMap<Frequency, Integer> mFrequencyChannelMap;

    AbstractRange() {
        mFrequencyChannelMap = new TreeMap<>();
        for (int i = 1; i <= getCount(); i++) {
            mFrequencyChannelMap.put(getFrequency(i), i);
        }
    }

    @Override
    public int find(Frequency frequency) {
        if (mFrequencyChannelMap.containsKey(frequency)) {
            return mFrequencyChannelMap.get(frequency);
        } else {
            return INVALID_INDEX;
        }
    }

    @Override
    public int findPrev(Frequency frequency) {
        final TreeMap.Entry<Frequency, Integer> entry = mFrequencyChannelMap.lowerEntry(frequency);
        return (entry != null ? entry.getValue() : INVALID_INDEX);
    }

    @Override
    public int findNext(Frequency frequency) {
        final TreeMap.Entry<Frequency, Integer> entry = mFrequencyChannelMap.higherEntry(frequency);
        return (entry != null ? entry.getValue() : INVALID_INDEX);
    }

}
