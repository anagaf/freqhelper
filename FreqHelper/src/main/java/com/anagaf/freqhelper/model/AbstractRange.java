package com.anagaf.freqhelper.model;

import com.anagaf.freqhelper.Frequency;

import java.util.NavigableMap;
import java.util.TreeMap;

public abstract class AbstractRange implements Range {

    private final NavigableMap<Frequency, Integer> mFrequencyChannelMap;

    protected AbstractRange() {
        mFrequencyChannelMap = new TreeMap<Frequency, Integer>();
        for (int i = 1; i <= getChannelCount(); i++) {
            mFrequencyChannelMap.put(getFrequency(i), i);
        }
    }

    @Override
    public int getChannel(Frequency frequency) {
        if (mFrequencyChannelMap.containsKey(frequency)) {
            return mFrequencyChannelMap.get(frequency);
        } else {
            return INVALID_CHANNEL;
        }
    }

    @Override
    public Range.Entry getLowerEntry(Frequency frequency) {
        Range.Entry entry = null;
        TreeMap.Entry<Frequency, Integer> mapEntry = mFrequencyChannelMap.lowerEntry(frequency);
        if (mapEntry != null) {
            entry = new Range.Entry(mapEntry.getKey(), mapEntry.getValue());
        }
        return entry;
    }

    @Override
    public Range.Entry getCeilingEntry(Frequency frequency) {
        Range.Entry entry = null;
        TreeMap.Entry<Frequency, Integer> mapEntry = mFrequencyChannelMap.ceilingEntry(frequency);
        if (mapEntry != null) {
            entry = new Range.Entry(mapEntry.getKey(), mapEntry.getValue());
        }
        return entry;
    }
}
